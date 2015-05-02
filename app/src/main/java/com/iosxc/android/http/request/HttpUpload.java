package com.iosxc.android.http.request;

import java.io.File;

/**
 * Created by crazz on 5/2/15.
 */
public class HttpUpload extends HttpRequest {
    public HttpUpload(String url,File file) {
        super(url, HTTP_METHOD_POST);
    }
}
