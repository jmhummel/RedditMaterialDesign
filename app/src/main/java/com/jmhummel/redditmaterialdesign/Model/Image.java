
package com.jmhummel.redditmaterialdesign.Model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Image {

    @SerializedName("source")
    @Expose
    public Source source;
    @SerializedName("resolutions")
    @Expose
    public List<Resolution> resolutions = new ArrayList<Resolution>();
    @SerializedName("variants")
    @Expose
    public Variants variants;
    @SerializedName("id")
    @Expose
    public String id;

}
