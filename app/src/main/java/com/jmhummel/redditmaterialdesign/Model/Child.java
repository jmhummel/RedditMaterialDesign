
package com.jmhummel.redditmaterialdesign.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Child {

    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("data")
    @Expose
    public Data_ data;

}
