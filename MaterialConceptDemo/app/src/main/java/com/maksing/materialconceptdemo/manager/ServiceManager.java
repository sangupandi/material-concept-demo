package com.maksing.materialconceptdemo.manager;

import android.content.Context;

import com.maksing.materialconceptdemo.R;
import com.maksing.moviedbdata.data.ConfigurationData;
import com.maksing.moviedbdata.datastore.MovieDbAuthenticateDataStoreFactory;
import com.maksing.moviedbdata.datastore.MovieDbConfigDataStoreFactory;
import com.maksing.moviedbdata.datastore.MovieDbMovieDataStoreFactory;
import com.maksing.moviedbdata.service.ConfigurationDataService;
import com.maksing.moviedbdata.service.MovieDataService;
import com.maksing.moviedbdata.service.SessionDataService;
import com.maksing.moviedbdomain.service.ConfigurationService;
import com.maksing.moviedbdomain.service.MovieService;
import com.maksing.moviedbdomain.service.ServiceHolder;
import com.maksing.moviedbdomain.service.SessionService;

/**
 * Created by maksing on 26/12/14.
 */
public class ServiceManager {
    private final ServiceHolder mServiceHolder;
    private final Context mContext = ApplicationManager.getInstance().getApplicationContext();
    private final String END_POINT = mContext.getString(R.string.api_endpoint);
    private final String API_KEY = mContext.getString(R.string.moviedb_apikey);

    private static class LazyLoader {
        private static final ServiceManager INSTANCE = new ServiceManager();
    }

    synchronized public static ServiceManager getInstance() {
        return LazyLoader.INSTANCE;
    }

    private ServiceManager() {
        mServiceHolder = new ServiceHolder() {
            @Override
            public ConfigurationService getConfigurationService() {
                return ConfigurationDataService.getInstance(mContext, new MovieDbConfigDataStoreFactory(END_POINT), API_KEY);
            }

            @Override
            public MovieService getMovieService() {
                return MovieDataService.getInstance(new MovieDbMovieDataStoreFactory(END_POINT), API_KEY);
            }

            @Override
            public SessionService getSessionService() {
                return SessionDataService.getInstance(new MovieDbAuthenticateDataStoreFactory(END_POINT), API_KEY);
            }
        };
    }

    public ServiceHolder getServiceHolder() {
        return mServiceHolder;
    }
}