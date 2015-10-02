package com.jmhummel.redditmaterialdesign;

import com.jmhummel.redditmaterialdesign.Model.Listing;

/**
 * Created by jhummel on 10/2/15.
 */
public class ListingEvent {
    public Listing listing;
    public ListingEvent(Listing listing) {
        this.listing = listing;
    }
}
