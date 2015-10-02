
package com.jmhummel.redditmaterialdesign.Model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Preview {

    @SerializedName("images")
    @Expose
    public List<Image> images = new ArrayList<Image>();

}
