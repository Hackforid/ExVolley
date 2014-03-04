package com.smilehacker.exvolley.toolbox.request;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kleist on 14-1-6.
 */
public class OkHttpStack extends HurlStack {

    private final OkHttpClient client;

    public OkHttpStack() {
        this(new OkHttpClient());
    }

    public OkHttpStack(OkHttpClient client) {
        if (client == null) {
            throw new NullPointerException("Client must not be null.");
        }
        this.client = client;

        // No SPDY :( https://github.com/square/okhttp/issues/184
        // tried URL.setURLStreamHandlerFactory(new OkHttpClient()); but not working atm
//        ArrayList< String > list = new ArrayList< String >();
//        list.add("http/1.1");
//        client.setTransports(list);
    }

    public OkHttpClient getClient() {
        return client;
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return client.open(url);
    }

}
