package com.iosxc.android.http.request;

/**
 * Created by crazz on 5/2/15.
 */
public class HttpDownload extends HttpRequest {

    public HttpDownload(String url) {
        super(url, HTTP_METHOD_GET);
    }
}
