package com.maksing.moviedbdomain.entity;

import java.io.Serializable;

/**
 * Created by maksing on 5/1/15.
 */
public class ListItem implements Serializable{
    private final String mQuery;
    private final String mTitle;

    public ListItem(String title, String query) {
        if (query == null) {
            query = "";
        }

        if (title == null) {
            title = "";
        }

        mQuery = query;
        mTitle = title;
    }

    public String getQuery() {
        return mQuery;
    }

    public String getTitle() {
        return mTitle;
    }
}
