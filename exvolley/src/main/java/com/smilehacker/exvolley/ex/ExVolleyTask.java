package com.smilehacker.exvolley.ex;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import static com.android.volley.Response.ErrorListener;


/**
 * Created by kleist on 14-1-7.
 */
public abstract class ExVolleyTask<T> {

    private ExRequest mRequest;
    private ExRequestBuilder mBuilder;
    private ExVolleyTaskCallBack<T> mCallBack;
    private Context mContext;

    public ExVolleyTask(Context context, ExVolleyTaskCallBack<T> callBack) {
        mContext = context;
        mCallBack = callBack;
    }

    protected Context getContext() {
        return mContext;
    }

    /**
     * 请实现此函数来创建ExRequestBuilder 不要添加listener
     *
     * @return
     */
    protected abstract ExRequestBuilder buildRequest();

    public void cancel() {
        if (mRequest != null) {
            mRequest.cancel();
        }
    }

    public void execute() {
        mBuilder = buildRequest();
        mCallBack.onStart();

        mBuilder.setResponseListener(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                T result = null;
                try {
                    result = ExVolleyTask.this.onResponse(response);
                    mCallBack.onFinish();
                } catch (Throwable throwable) {
                    mCallBack.onFail(throwable);
                }

                mCallBack.onSuccess(result);
            }
        }, String.class);

        mBuilder.setErrorListener(new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Throwable e= ExVolleyTask.this.onErrorResponse(error);
                mCallBack.onFail(e);
                mCallBack.onFinish();
            }
        });

        mRequest = mBuilder.excute();
    }

    public interface ExVolleyTaskCallBack<T> {
        public void onStart();
        public void onSuccess(T result);
        public void onFail(Throwable e);
        public void onFinish();
    }

    protected abstract T onResponse(String response) throws Throwable;
    protected  abstract Throwable onErrorResponse(VolleyError volleyError);

}
