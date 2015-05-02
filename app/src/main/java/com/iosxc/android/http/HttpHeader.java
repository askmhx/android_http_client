package com.iosxc.android.http;

/**
 * Created by crazz on 5/2/15.
 */
public class HttpHeader {
    private String headerName;
    private String headerValue;

    public HttpHeader() {
    }

    public HttpHeader(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }
}
