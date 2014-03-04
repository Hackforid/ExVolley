package com.smilehacker.sample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kleist on 14-1-9.
 */
public class Diary {
    @Expose
    @SerializedName("update_time")
    public String updateTime;

    @Expose
    @SerializedName("publish_time")
    public String publishTime;

    @Expose
    public Author author;

    @Expose
    public List<Image> images;

    @Expose
    @SerializedName("recs_count")
    public int recsCount;

    @Expose
    public String alt;

    @Expose
    public String id;

    @Expose
    @SerializedName("can_reply")
    public Boolean canReply;

    @Expose
    public String title;

    @Expose
    public String privacy;

    @Expose
    public String summary;

    @Expose
    public String content;

    @Expose
    @SerializedName("comments_count")
    public int commentsCount;

    @Expose
    @SerializedName("liked_count")
    public int likedCount;
}
