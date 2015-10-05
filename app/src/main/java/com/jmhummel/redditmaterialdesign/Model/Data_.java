
package com.jmhummel.redditmaterialdesign.Model;

import android.text.format.Time;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data_ {

    @SerializedName("domain")
    @Expose
    public String domain;
    @SerializedName("banned_by")
    @Expose
    public Object bannedBy;
    @SerializedName("media_embed")
    @Expose
    public MediaEmbed mediaEmbed;
    @SerializedName("subreddit")
    @Expose
    public String subreddit;
    @SerializedName("selftext_html")
    @Expose
    public Object selftextHtml;
    @SerializedName("selftext")
    @Expose
    public String selftext;
    @SerializedName("likes")
    @Expose
    public Object likes;
    @SerializedName("suggested_sort")
    @Expose
    public Object suggestedSort;
    @SerializedName("user_reports")
    @Expose
    public List<Object> userReports = new ArrayList<Object>();
    @SerializedName("secure_media")
    @Expose
    public Object secureMedia;
    @SerializedName("link_flair_text")
    @Expose
    public Object linkFlairText;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("from_kind")
    @Expose
    public Object fromKind;
    @SerializedName("gilded")
    @Expose
    public Integer gilded;
    @SerializedName("archived")
    @Expose
    public Boolean archived;
    @SerializedName("clicked")
    @Expose
    public Boolean clicked;
    @SerializedName("report_reasons")
    @Expose
    public Object reportReasons;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("media")
    @Expose
    public Object media;
    @SerializedName("score")
    @Expose
    public Integer score;
    @SerializedName("approved_by")
    @Expose
    public Object approvedBy;
    @SerializedName("over_18")
    @Expose
    public Boolean over18;
    @SerializedName("hidden")
    @Expose
    public Boolean hidden;
    @SerializedName("preview")
    @Expose
    public Preview preview;
    @SerializedName("num_comments")
    @Expose
    public Integer numComments;
    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;
    @SerializedName("subreddit_id")
    @Expose
    public String subredditId;
    @SerializedName("hide_score")
    @Expose
    public Boolean hideScore;
    @SerializedName("edited")
    @Expose
    public String edited;
    @SerializedName("link_flair_css_class")
    @Expose
    public Object linkFlairCssClass;
    @SerializedName("author_flair_css_class")
    @Expose
    public Object authorFlairCssClass;
    @SerializedName("downs")
    @Expose
    public Integer downs;
    @SerializedName("secure_media_embed")
    @Expose
    public SecureMediaEmbed secureMediaEmbed;
    @SerializedName("saved")
    @Expose
    public Boolean saved;
    @SerializedName("removal_reason")
    @Expose
    public Object removalReason;
    @SerializedName("post_hint")
    @Expose
    public String postHint;
    @SerializedName("stickied")
    @Expose
    public Boolean stickied;
    @SerializedName("from")
    @Expose
    public Object from;
    @SerializedName("is_self")
    @Expose
    public Boolean isSelf;
    @SerializedName("from_id")
    @Expose
    public Object fromId;
    @SerializedName("permalink")
    @Expose
    public String permalink;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("created")
    @Expose
    public Integer created;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("author_flair_text")
    @Expose
    public Object authorFlairText;
    @SerializedName("quarantine")
    @Expose
    public Boolean quarantine;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("created_utc")
    @Expose
    public Integer createdUtc;
    @SerializedName("distinguished")
    @Expose
    public Object distinguished;
    @SerializedName("mod_reports")
    @Expose
    public List<Object> modReports = new ArrayList<Object>();
    @SerializedName("visited")
    @Expose
    public Boolean visited;
    @SerializedName("num_reports")
    @Expose
    public Object numReports;
    @SerializedName("ups")
    @Expose
    public Integer ups;

    public Date getDateCreated() {
        Date time = new Date((long)createdUtc*1000);
        return time;
    }

    public String getTimeAgo() {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        Date now = new Date();
        Date created = getDateCreated();
        long diffInMillis = now.getTime() - created.getTime();
        long days = timeUnit.toDays(diffInMillis);
        long hours = timeUnit.toHours(diffInMillis);
        long minutes = timeUnit.toMinutes(diffInMillis);
        long seconds = timeUnit.toSeconds(diffInMillis);

        if (days > 0)
            return days + " days ago";
        if (hours > 0)
            return hours + " hours ago";
        if (minutes > 0)
            return minutes + " minutes ago";
        if (seconds > 0)
            return seconds + " seconds ago";
        return diffInMillis + " milliseconds ago";
    }
}
