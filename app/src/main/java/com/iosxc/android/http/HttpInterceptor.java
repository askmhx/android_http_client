package com.iosxc.android.http;

import com.iosxc.android.http.request.HttpRequest;
import com.iosxc.android.http.response.HttpHandler;

/**
 * Created by crazz on 5/2/15.
 */
public interface HttpInterceptor {
    public void preRequest(HttpClient client,HttpRequest request,HttpHandler handler);
    public void postRequest(HttpClient client,HttpRequest request,HttpHandler handler);
    public void preResponse(HttpClient client,HttpRequest request,HttpHandler handler);
    public void postResponse(HttpClient client,HttpRequest request,HttpHandler handler);
}
