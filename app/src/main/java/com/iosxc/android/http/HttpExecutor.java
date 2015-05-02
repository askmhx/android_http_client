package com.iosxc.android.http;

import android.os.AsyncTask;

import com.iosxc.android.http.request.HttpPost;
import com.iosxc.android.http.request.HttpRequest;
import com.iosxc.android.http.response.HttpHandler;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Objects;

/**
 * Created by crazz on 5/2/15.
 */
public class HttpExecutor extends AsyncTask {

    private HttpClient httpClient;
    private HttpHandler httpHandler;
    private HttpRequest httpRequest;

    @Override
    protected Object doInBackground(Object[] params) {
        HttpRsp httpRsp = new HttpRsp();
        HttpURLConnection connection = null;
        try {
            connection = httpRequest.getConnection();
            List<HttpInterceptor> requestInterceptors = httpClient.getInterceptors();
            for(HttpInterceptor interceptor:requestInterceptors){
                interceptor.preRequest(httpClient,httpRequest,httpHandler);
            }
            if(Objects.equals(httpRequest.getMethod(), HttpRequest.HTTP_METHOD_POST)){
                HttpPost httpPost = (HttpPost) httpRequest;
                if(httpPost.getRequestBody()!=null){
                    connection.setDoOutput(true);
                    BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
                    out.write(httpPost.getRequestBody());
                }
            }
            for(HttpInterceptor interceptor:requestInterceptors){
                interceptor.postRequest(httpClient,httpRequest,httpHandler);
            }
            for(HttpInterceptor interceptor:requestInterceptors){
                interceptor.preResponse(httpClient,httpRequest,httpHandler);
            }
            if (connection.getResponseCode() != 200) {
                httpRsp.setStatus(connection.getResponseCode());
                httpRsp.setIsSuccess(false);
                httpRsp.setMessage(connection.getResponseMessage());
            }else{
                InputStream stream = connection.getInputStream();
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                int data = -1;
                while ((data = stream.read()) != -1) {
                    buf.write(data);
                }
                String ret = buf.toString("utf-8");
                stream.close();
                buf.close();
                httpRsp.setStatus(connection.getResponseCode());
                httpRsp.setIsSuccess(true);
                httpRsp.setMessage(connection.getResponseMessage());
                httpRsp.setRspBody(ret);
            }
            for(HttpInterceptor interceptor:requestInterceptors){
                interceptor.postResponse(httpClient,httpRequest,httpHandler);
            }
            return httpRsp;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection!=null){
                connection.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (httpHandler != null) {
            HttpRsp httpRsp = (HttpRsp) o;
            if (httpRsp.isSuccess) {
                httpHandler.success(httpRsp.headers, httpRsp.rspBody);
            } else {
                httpHandler.fail(httpRsp.status, httpRsp.message,httpRsp.headers);
            }

        }
    }

    public HttpHandler getHttpHandler() {
        return httpHandler;
    }

    public void setHttpHandler(HttpHandler httpHandler) {
        this.httpHandler = httpHandler;
    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }

    public void setHttpRequest(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    private class HttpRsp {
        private boolean isSuccess;
        private int status;
        private String message;
        private String rspBody;
        private List<HttpHeader> headers;

        public boolean isSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getRspBody() {
            return rspBody;
        }

        public void setRspBody(String rspBody) {
            this.rspBody = rspBody;
        }

        public List<HttpHeader> getHeaders() {
            return headers;
        }

        public void setHeaders(List<HttpHeader> headers) {
            this.headers = headers;
        }
    }
}
