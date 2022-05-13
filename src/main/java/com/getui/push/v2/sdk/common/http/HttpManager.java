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
     * @param connectionTimeOut        连接超时时间
     * @param readTimeout              读超时时间
     * @param connectionRequestTimeout 从连接池中获取连接的超时时间
     * @param maxHttpTryTime           失败最大尝试次数
     * @param trustSSL
     */
    public HttpManager(int connectionTimeOut, int readTimeout, int connectionRequestTimeout, int maxHttpTryTime, long keepAliveSeconds, GtHttpProxyConfig proxyConfig, boolean trustSSL) {
        this.client = new GtHttpClient(connectionTimeOut, readTimeout, connectionRequestTimeout, maxHttpTryTime, keepAliveSeconds, proxyConfig, trustSSL);
    }

    public String syncHttps(String url, String method, Map<String, Object> headers, String body, String contentType) {
        return client.execute(url, method, headers, body, contentType);
    }
}
