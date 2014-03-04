package com.smilehacker.sample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kleist on 14-1-9.
 */
public class Author {

    @Expose
    public String name;

    @Expose
    @SerializedName("is_banned")
    public Boolean isBanned;

    @Expose
    @SerializedName("is_suicide")
    public Boolean isSuicide;

    @Expose
    public String avatar;

    @Expose
    @SerializedName("large_avatar")
    public String LargeAvatar;

    @Expose
    public String uid;

    @Expose
    public String id;

    @Expose
    public String alt;

    @Expose
    public String type;

}
