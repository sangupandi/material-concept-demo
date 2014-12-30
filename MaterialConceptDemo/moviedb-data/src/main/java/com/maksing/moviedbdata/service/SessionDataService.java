package com.maksing.moviedbdata.service;

import com.maksing.moviedbdata.data.AccountData;
import com.maksing.moviedbdata.data.GuestSessionData;
import com.maksing.moviedbdata.data.RequestTokenData;
import com.maksing.moviedbdata.data.SessionData;
import com.maksing.moviedbdata.datastore.MovieDbAuthenticateDataStore;
import com.maksing.moviedbdata.datastore.MovieDbAuthenticateDataStoreFactory;
import com.maksing.moviedbdomain.entity.Session;
import com.maksing.moviedbdomain.entity.User;
import com.maksing.moviedbdomain.service.SessionService;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by maksing on 24/12/14.
 */
public class SessionDataService implements SessionService {
    private final MovieDbAuthenticateDataStoreFactory mFactory;
    private final String mApiKey;

    private static volatile SessionDataService sInstance;

    public static SessionDataService getInstance(MovieDbAuthenticateDataStoreFactory factory, String apiKey) {
        if (sInstance == null) {
            synchronized (SessionDataService.class) {
                if (sInstance == null) {
                    sInstance = new SessionDataService(factory, apiKey);
                }
            }
        }
        return sInstance;
    }

    //Make it private to disallow to call constructor directly
    private SessionDataService(MovieDbAuthenticateDataStoreFactory factory, String apiKey) {
        if (factory == null || apiKey == null) {
            throw new IllegalArgumentException("Arguments must not be null in constructing ConfigurationDataRepository");
        }

        mFactory = factory;
        mApiKey = apiKey;
    }

    @Override
    public Observable<String> getRequestToken() {
        return mFactory.create().createRequestToken(mApiKey).map(new Func1<RequestTokenData, String>() {
            @Override
            public String call(RequestTokenData requestToken) {
                return requestToken.getRequestToken();
            }
        });
    }

    @Override
    public Observable<Session> getAuthenticatedSession(String requestToken) {
        final MovieDbAuthenticateDataStore store = mFactory.create();
        return store.createSession(mApiKey, requestToken).flatMap(new Func1<SessionData, Observable<Session>>() {
            @Override
            public Observable<Session> call(final SessionData sessionData) {
                return store.getAccount(mApiKey, sessionData.getSessionId()).map(new Func1<AccountData, Session>() {
                    @Override
                    public Session call(AccountData accountData) {
                        User user = new User(Integer.toString(accountData.getId()), accountData.getUsername());
                        user.setIncludeAdult(accountData.isIncludeAdult());
                        return new Session(user, sessionData.getSessionId());
                    }
                });
            }
        });
    }

    @Override
    public Observable<String> authenticateRequestToken(String requestToken, String userName, String password) {
        return mFactory.create().login(mApiKey, requestToken, userName, password).map(new Func1<RequestTokenData, String>() {
            @Override
            public String call(RequestTokenData requestTokenData) {
                return requestTokenData.getRequestToken();
            }
        });
    }

    @Override
    public Observable<Session> getGuestSession() {
        return mFactory.create().createGuestSession(mApiKey).map(new Func1<GuestSessionData, Session>() {
            @Override
            public Session call(GuestSessionData guestSessionData) {
                return new Session(User.GUEST, guestSessionData.getGuestSessionId());
            }
        });
    }
}