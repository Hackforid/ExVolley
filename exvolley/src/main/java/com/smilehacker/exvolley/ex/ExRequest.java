package com.smilehacker.exvolley.ex;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by kleist on 14-1-6.
 */
public class ExRequest<T> extends Request<T> {

    private Gson mGson;
    private Class mResponseType;
    private String mContentType;
    private Map<String, String> mHeader;
    private Map<String, String> mParams;
    private String mRequestBody;

    private Response.Listener<T> mListener;

    public ExRequest(int method, String url, Response.Listener<T> listener, Response.ErrorListener errorListener, Class responseType) {
        super(method, url, errorListener);
        mResponseType = responseType;
        mListener = listener;
    }

    public ExRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        if (String.class.equals(mResponseType)) {
            return (Response<T>) parseToString(response);
        } else if (JSONObject.class.equals(mResponseType)) {
            return (Response<T>) parseToJsonObject(response);
        } else if (JSONArray.class.equals(mResponseType)) {
            return (Response<T>) parseToJsonArray(response);
        }

        return null;
    }

    private Response<T> parseToGson(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return (Response<T>) Response.success(mGson.fromJson(json, mResponseType), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    private Response<String> parseToString(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    private Response<JSONObject> parseToJsonObject(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    private Response<JSONArray> parseToJsonArray(NetworkResponse response) {
        try {
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONArray(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    public void setContentType(String contentType) {
        mContentType = contentType;
    }

    @Override
    public String getBodyContentType() {
        if (mContentType == null) {
            return super.getBodyContentType();
        }
        return mContentType;
    }

    public void setHeader(Map<String, String> header) {
        mHeader = header;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (mHeader != null) {
            return mHeader;
        }
        return super.getHeaders();
    }

    public void setParams(Map<String, String> params) {
        mParams = params;
    }

    public void setRequestBody(String requestBody) {
        mRequestBody = requestBody;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        // 优先request body
        if (mRequestBody != null) {
            try {
                return mRequestBody.getBytes(ExRequestBuilder.PROTOCOL_CHARSET);
            } catch (UnsupportedEncodingException e) {
                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                        mRequestBody, ExRequestBuilder.PROTOCOL_CHARSET);
            }
        }

        return super.getBody();
    }
}
