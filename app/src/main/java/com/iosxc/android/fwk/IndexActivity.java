package com.iosxc.android.fwk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.iosxc.android.http.HttpClient;
import com.iosxc.android.http.request.HttpGet;
import com.iosxc.android.http.response.HttpHandler;
import com.iosxc.android.http.HttpHeader;
import com.iosxc.android.http.HttpInterceptor;
import com.iosxc.android.http.request.HttpRequest;

import java.util.List;


public class IndexActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setHttpCookieBtnAction(View view) throws Exception {
        HttpClient  client = HttpClient.defaultClient();
        client.getRequestInterceptors().add(new HttpInterceptor() {
            @Override
            public void preRequest(HttpClient client,HttpRequest request,HttpHandler handler) {
                Log.d("http","preRequest:"+request.getUrl());
            }

            @Override
            public void postRequest(HttpClient client,HttpRequest request,HttpHandler handler) {
                Log.d("http","postRequest:" +request.getUrl());
            }

            @Override
            public void preResponse(HttpClient client,HttpRequest request,HttpHandler handler) {
                Log.d("http","preResponse:"+request.getUrl());
            }

            @Override
            public void postResponse(HttpClient client,HttpRequest request,HttpHandler handler) {
                Log.d("http","postResponse:" + request.getUrl());
            }
        });
        HttpHandler handler = new HttpHandler() {
            @Override
            public void success(List<HttpHeader> headers, String responseBody) {
                Log.d("http","success headers:"+headers);
                Log.d("http","success responseBody:"+responseBody);
            }

            @Override
            public void fail(int httpStatus, String httpMsg, List<HttpHeader> headers) {
                Log.d("http","fail httpStatus:"+httpStatus);
                Log.d("http","fail httpMsg:"+httpMsg);
            }
        };
        client.execute(new HttpGet("http://www.baidu.com/aaa"), handler);
        client.execute(new HttpGet("http://www.baidu.com"), handler);
        client.execute(new HttpGet("http://www.baidu.com"), handler);
        client.execute(new HttpGet("http://www.baidu.com/aaa"), handler);
        client.execute(new HttpGet("http://www.baidu.com"), handler);
        client.execute(new HttpGet("http://www.baidu.com/aaa"), handler);
        client.execute(new HttpGet("http://www.baidu.com"), handler);
        client.execute(new HttpGet("http://www.baidu.com/aaa"), handler);
        client.execute(new HttpGet("http://www.baidu.com"), handler);
    }

    public void getHttpCookieBtnAction(View view) {

    }
}
