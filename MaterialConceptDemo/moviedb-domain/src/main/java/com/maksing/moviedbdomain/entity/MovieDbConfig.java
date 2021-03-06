package com.maksing.moviedbdomain.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maksing on 24/12/14.
 */
public class MovieDbConfig {
    private final String mImageBaseUrl;
    private final List<String> mPosterSizesList;
    private final List<String> mBackdropSizesList;

    public MovieDbConfig(String imageBaseUrl, List<String> posterSizesList, List<String> backdropSizesList) {
        if (imageBaseUrl == null) {
            imageBaseUrl = "";
        }

        if (posterSizesList == null) {
            posterSizesList = new ArrayList<>();
        }

        if (posterSizesList.size() == 0) {
            posterSizesList.add("original");
        }

        if (backdropSizesList == null) {
            backdropSizesList = new ArrayList<>();
        }

        if (backdropSizesList.size() == 0) {
            backdropSizesList.add("original");
        }

        mImageBaseUrl = imageBaseUrl;
        mPosterSizesList = posterSizesList;
        mBackdropSizesList = backdropSizesList;
    }

    public String getPosterPath() {
        if (mPosterSizesList.size() > 1) {
            return mImageBaseUrl + mPosterSizesList.get(mPosterSizesList.size() - 2);
        } else {
            return mImageBaseUrl + "original";
        }
    }

    public String getBackdropPath() {
        if (mBackdropSizesList.size() > 1) {
            return mImageBaseUrl + mBackdropSizesList.get(mBackdropSizesList.size() - 2);
        } else {
            return mImageBaseUrl + "original";
        }
    }
}
