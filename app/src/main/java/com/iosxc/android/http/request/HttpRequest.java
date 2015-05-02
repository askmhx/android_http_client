package com.iosxc.android.http.request;

import com.iosxc.android.http.HttpException;
import com.iosxc.android.http.HttpHeader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by crazz on 5/2/15.
 */
public class HttpRequest{

    public static final String HTTP_METHOD_GET = "GET";
    public static final String HTTP_METHOD_POST = "POST";

    private String url;
    private String method;
    private List<HttpHeader> headers;
    private HashMap<String,String> params;
    private HttpURLConnection connection;

    public HttpRequest(String url, String method) {
        this.url = url;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<HttpHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HttpHeader> headers) {
        this.headers = headers;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public HttpURLConnection getConnection() throws HttpException {
        try {
            return (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            throw new HttpException(e.getMessage());
        }
    }
}
