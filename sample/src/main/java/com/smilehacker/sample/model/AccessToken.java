package com.smilehacker.sample.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kleist on 14-1-8.
 */
public class AccessToken {
    @Expose
    @SerializedName("access_token")
    public String accessToken;

    @Expose
    @SerializedName("expires_in")
    public int expiresIn;

    @Expose
    @SerializedName("refresh_token")
    public String refreshToken;

    @Expose
    @SerializedName("douban_user_id")
    public String UserId;
}
