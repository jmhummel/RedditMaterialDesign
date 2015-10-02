package com.jmhummel.redditmaterialdesign;

import android.util.Log;

import com.jmhummel.redditmaterialdesign.Model.Listing;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jhummel on 10/1/15.
 */
public class RedditClient {
    public static final String BASE_URL = "https://www.reddit.com";

    public static RestAdapter restAdapter;
    public static Reddit reddit;

    public RedditClient() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        reddit = restAdapter.create(Reddit.class);
    }

    public void getAll() {

        Callback<Listing> callback = new Callback<Listing>() {
            @Override
            public void success(Listing listing, Response response) {
                BusProvider.getInstance().post(produceListingEvent(listing));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Retrofit error", error.toString());
            }
        };
        reddit.getAll(callback);
    }

    private ListingEvent produceListingEvent(Listing listing) {
        return new ListingEvent(listing);
    }

}
