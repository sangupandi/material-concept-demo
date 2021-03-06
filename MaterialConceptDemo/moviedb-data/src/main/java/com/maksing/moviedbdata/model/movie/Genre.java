package com.maksing.moviedbdata.model.movie;

import com.google.gson.annotations.SerializedName;


public class Genre{

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";


    @SerializedName(FIELD_ID)
    private int mId;
    @SerializedName(FIELD_NAME)
    private String mName;


    public Genre(){

    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }


}