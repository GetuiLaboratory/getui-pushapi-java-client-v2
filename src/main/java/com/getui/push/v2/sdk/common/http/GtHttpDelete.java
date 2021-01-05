package com.getui.push.v2.sdk.common.http;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * create by getui on 2020/10/28
 *
 * @author getui
 */
public class GtHttpDelete extends HttpEntityEnclosingRequestBase {

    public GtHttpDelete(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    @Override
    public String getMethod() {
        return HttpDelete.METHOD_NAME;
    }
}
