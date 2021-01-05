package com.getui.push.v2.sdk.common.http;

import com.getui.push.v2.sdk.GtHttpProxyConfig;

import java.util.Map;

/**
 * @author lg
 * @version V1.0
 * @Title: HttpManager.java
 * @Package com.gexin.rp.sdk.http
 * @date 2012-9-5 下午4:46:50
 */
public class HttpManager {

    private GtHttpClient client;

    /**
     * @param connectionTimeOut 连接超时时间
     * @param readTimeout       读超时时间
     * @param maxHttpTryTime    失败最大尝试次数
     */
    public HttpManager(int connectionTimeOut, int readTimeout, int maxHttpTryTime, GtHttpProxyConfig proxyConfig) {
        this.client = new GtHttpClient(connectionTimeOut, readTimeout, maxHttpTryTime, proxyConfig);
    }

    public String syncHttps(String url, String method, Map<String, Object> headers, String body, String contentType) {
        return client.execute(url, method, headers, body, contentType);
    }
}
