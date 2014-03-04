package com.smilehacker.exvolley.utils;


import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
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

    /**
     * read VolleyError in a simple way
     * @param error
     * @return
     */
    public static SimpleResponseError parseError(VolleyError error) {
        NetworkResponse response = error.networkResponse;
        SimpleResponseError simpleResponseError = new SimpleResponseError();

        simpleResponseError.code = response.statusCode;
        simpleResponseError.message = parseResponse(response);

        return simpleResponseError;
    }


    public static class SimpleResponseError {
        /**
         * status code
         */
        public int code;

        /**
         * response data
         */
        public String message;
    }
}
