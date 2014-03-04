package com.smilehacker.sample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.smilehacker.exvolley.ex.ExVolley;
import com.smilehacker.sample.model.Diary;

public class MainActivity extends ActionBarActivity {

    private TextView mTvResult;
    private Button mBtnGET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvResult = (TextView) findViewById(R.id.tv_result);
        mBtnGET = (Button) findViewById(R.id.btn_get);
        init();
    }


    private void init() {
        mBtnGET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExVolley.with(MainActivity.this)
                        .load("https://api.douban.com/v2/note/333576388")
                        .method(Request.Method.GET)
                        .response(new Response.Listener<Diary>() {
                            @Override
                            public void onResponse(Diary response) {

                            }
                        }, Diary.class)
                        .error(new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        })
                        .excute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
