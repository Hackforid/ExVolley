package com.smilehacker.exvolley.toolbox.request;

import android.content.Context;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.net.URL;
import java.security.GeneralSecurityException;

import javax.net.ssl.SSLContext;

/**
 * Created by kleist on 14-1-6.
 */
public class OkVolley {

    /** Default on-disk cache directory. */
    private static final String DEFAULT_CACHE_DIR = "volley";

    /**
     * Creates a default instance of the worker pool and calls {@link RequestQueue#start()} on it.
     *
     * @param context A {@link android.content.Context} to use for creating the cache dir.
     * @param stack An {@link HttpStack} to use for the network, or null for default.
     * @return A started {@link RequestQueue} instance.
     */
    public static RequestQueue newRequestQueue(Context context, HttpStack stack) {
        File cacheDir = new File(context.getCacheDir(), DEFAULT_CACHE_DIR);

        if (stack == null) {
            stack = new OkHttpStack();
            OkHttpClient client = ((OkHttpStack)stack).getClient();

            SSLContext sslContext;
            try {
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, null, null);
            } catch (GeneralSecurityException e) {
                throw new AssertionError(); // The system has no TLS. Just give up.
            }
            client.setSslSocketFactory(sslContext.getSocketFactory());
            URL.setURLStreamHandlerFactory(client);
        }

        Network network = new BasicNetwork(stack);

        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir), network);
        queue.start();

        return queue;
    }

    /**
     * Creates a default instance of the worker pool and calls {@link RequestQueue#start()} on it.
     *
     * @param context A {@link android.content.Context} to use for creating the cache dir.
     * @return A started {@link RequestQueue} instance.
     */
    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }
}
