package com.smilehacker.exvolley.ex;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.smilehacker.exvolley.toolbox.request.OkVolley;

/**
 * Created by kleist on 14-1-6.
 */
public class ExVolley {

    private volatile static ExVolley mInstance;
    private static RequestQueue mRequestQueue;

    private ExVolley(Context context) {
        mRequestQueue = OkVolley.newRequestQueue(context.getApplicationContext());
    }

    public static ExRequestBuilder with(Context context) {

        if (mInstance == null) {
            synchronized (ExVolley.class) {
                if (mInstance == null) {
                    mInstance = new ExVolley(context);
                }
            }
        }

        return new ExRequestBuilder(mRequestQueue);
    }

    public RequestQueue createDefaultRequestQueue(Context context) {
        try {
            Class.forName("com.squareup.okhttp.OkHttpClient");
            return OkVolley.newRequestQueue(context.getApplicationContext());
        } catch (ClassNotFoundException e) {
            return Volley.newRequestQueue(context.getApplicationContext());
        }
    }

}
