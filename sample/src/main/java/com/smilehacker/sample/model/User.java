package com.smilehacker.sample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kleist on 14-2-13.
 */
public class User {
    @Expose
    public String id;

    @Expose
    public String uid;

    @Expose
    public String name;

    @Expose
    public String avatar;

    @Expose
    public String alt;

    @Expose
    public String relation;

    @Expose
    public String created;

    @Expose
    @SerializedName("loc_id")
    public String locId;

    @Expose
    @SerializedName("loc_name")
    public String locName;

    @Expose
    public String desc;
}
