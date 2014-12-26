package com.maksing.moviedbdata.datastore;

import retrofit.RestAdapter;

/**
 * Created by maksing on 23/12/14.
 */
public class MovieDbConfigDataStoreFactory {
    private final String mEndpoint;
    private final RestAdapter mRestAdapter;
    private final MovieDbConfigDataStore mResApiDataStore;

    public MovieDbConfigDataStoreFactory(String endpoint) {
        mEndpoint = endpoint;
        mRestAdapter = new RestAdapter.Builder()
                .setEndpoint(mEndpoint)
                .build();
        mResApiDataStore = mRestAdapter.create(MovieDbConfigDataStore.class);
    }

    public MovieDbConfigDataStore create() {
        return mResApiDataStore;
    }
}
