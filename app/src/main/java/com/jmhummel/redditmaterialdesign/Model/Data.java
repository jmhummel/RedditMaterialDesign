
package com.jmhummel.redditmaterialdesign.Model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("modhash")
    @Expose
    public String modhash;
    @SerializedName("children")
    @Expose
    public List<Child> children = new ArrayList<Child>();
    @SerializedName("after")
    @Expose
    public String after;
    @SerializedName("before")
    @Expose
    public Object before;

}
