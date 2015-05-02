package com.iosxc.android.http.response;

import com.iosxc.android.http.HttpHeader;

import java.util.List;

/**
 * Created by crazz on 5/2/15.
 */
public interface HttpHandler {

    public void success(List<HttpHeader> headers, Object response);

    public void fail(int httpStatus, String httpMsg, List<HttpHeader> headers);
}
