package com.iosxc.android.http.request;

/**
 * Created by crazz on 5/2/15.
 */
public class HttpPost extends HttpRequest {


    public HttpPost(String url) {
        super(url, HTTP_METHOD_POST);
    }

    private byte[] requestBody;

    public byte[] getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(byte[] requestBody) {
        this.requestBody = requestBody;
    }
}
