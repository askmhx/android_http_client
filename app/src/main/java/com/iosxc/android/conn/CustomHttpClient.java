package com.iosxc.android.conn;

import com.iosxc.android.http.HttpClient;
import com.iosxc.android.http.HttpException;
import com.iosxc.android.http.request.HttpGet;
import com.iosxc.android.http.response.HttpHandler;
import com.iosxc.android.http.request.HttpPost;

/**
 * Created by crazz on 5/2/15.
 */
public class CustomHttpClient{

    private HttpClient httpClient;

    public static CustomHttpClient defaultClient(){
        CustomHttpClient customHttpClient =  new CustomHttpClient();
        customHttpClient.httpClient = HttpClient.defaultClient();
        customHttpClient.httpClient.getRequestInterceptors().add(new HttpAuthIntercepter());
        customHttpClient.httpClient.getRequestInterceptors().add(new HttpHudIntercepter());
        customHttpClient.httpClient.getRequestInterceptors().add(new HttpJsonIntercepter());
        return customHttpClient;
    }

    public void post(String url,HttpHandler httpHandler){
        try {
            defaultClient().httpClient.execute(new HttpPost(url),httpHandler);
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    public void get(String url,HttpHandler httpHandler){
        try {
            defaultClient().httpClient.execute(new HttpGet(url),httpHandler);
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }
}
