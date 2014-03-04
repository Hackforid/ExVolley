package com.smilehacker.exvolley.utils;


import com.android.volley.NetworkResponse;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created by kleist on 14-1-8.
 */
public class VolleyUtils {

    public static String parseResponse(NetworkResponse response) {
        try {
            return new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
