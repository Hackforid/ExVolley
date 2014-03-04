package com.smilehacker.exvolley.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kleist on 14-1-7.
 */
public class JsonUtils {
    public static JSONObject parseToJson(String str) throws JSONException {
        return new JSONObject(str);
    }

    public static JSONArray parseToJsonArray(String str) throws JSONException {
        return new JSONArray(str);
    }
}
