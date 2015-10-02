package com.jmhummel.redditmaterialdesign;

import com.jmhummel.redditmaterialdesign.Model.Listing;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jhummel on 9/25/15.
 */
public interface Reddit {
    @Headers("Content-Type: application/json")

    @GET("/r/all.json")
    void getAll(
            Callback<Listing> cb
    );

}
