package com.getui.push.v2.sdk.common.http;

import com.getui.push.v2.sdk.GtHttpProxyConfig;
import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.common.Config;
import com.getui.push.v2.sdk.common.util.Utils;
import com.getui.push.v2.sdk.dto.CommonEnum;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Map;

public class GtHttpClient {
    private int maxHttpTryTime;
    CloseableHttpClient httpclient;

    public GtHttpClient(int connectTimeout, int soTimeout, int maxHttpTryTime, GtHttpProxyConfig proxyConfig) {
        if (connectTimeout <= 0) {
            throw new IllegalArgumentException("connectTimeout must be > 0.");
        }
        if (soTimeout <= 0) {
            throw new IllegalArgumentException("soTimeout must be > 0.");
        }
        this.maxHttpTryTime = maxHttpTryTime;
        final HttpClientBuilder builder = HttpClients.custom();
        if (proxyConfig != null && Utils.isNotEmpty(proxyConfig.getHost())) {
            if (Utils.isNotEmpty(proxyConfig.getUsername())) {
                CredentialsProvider credsProvider = new BasicCredentialsProvider();
                credsProvider.setCredentials(
                        new AuthScope(proxyConfig.getHost(), proxyConfig.getPort()),
                        new UsernamePasswordCredentials(proxyConfig.getUsername(), proxyConfig.getPassword()));
                builder.setDefaultCredentialsProvider(credsProvider);
            }
            builder.setProxy(new HttpHost(proxyConfig.getHost(), proxyConfig.getPort()));
        }
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(soTimeout)
                .setMaxRedirects(0)
                .build();
        builder.setDefaultRequestConfig(config);
        this.httpclient = builder.build();
    }

    public String execute(String url, String method, Map<String, Object> headers, String body, String contentType) {
        final HttpUriRequest request = genHttpUriRequest(url, method);
        if (Utils.isNotEmpty(headers)) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        if (Utils.isNotEmpty(body) && request instanceof HttpEntityEnclosingRequestBase) {
            HttpEntity entity;
            if (Utils.isEmpty(contentType)) {
                try {
                    entity = new StringEntity(body);
                } catch (UnsupportedEncodingException e) {
                    throw new ApiException("设置body失败", 5000, e);
                }
            } else {
                entity = new StringEntity(body, ContentType.create(contentType, Config.UTF_8));
            }
            ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
        }
        return doRequest(request, 0);
    }

    private String doRequest(HttpUriRequest request, int tryTimes) {
        CloseableHttpResponse response = null;
        int code = 5000;
        try {
            response = httpclient.execute(request);
            final StatusLine statusLine = response.getStatusLine();
            code = statusLine.getStatusCode();
            if (code == HttpURLConnection.HTTP_OK ||
                    code == HttpURLConnection.HTTP_BAD_REQUEST ||
                    code == HttpURLConnection.HTTP_UNAUTHORIZED ||
                    code == HttpURLConnection.HTTP_FORBIDDEN) {
                return EntityUtils.toString(response.getEntity());
            } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                throw new ApiException("not found.", code);
            } else if (code == HttpURLConnection.HTTP_GATEWAY_TIMEOUT) {
                throw new ApiException("timeout.", code);
            } else {
                throw new ApiException("Http Response Error.", code);
            }
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            if (tryTimes > maxHttpTryTime) {
                throw new ApiException("http error", code, e);
            } else {
                return doRequest(request, ++tryTimes);
            }
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private HttpUriRequest genHttpUriRequest(String url, String method) {
        HttpUriRequest request = null;
        if (CommonEnum.MethodEnum.METHOD_GET.is(method)) {
            request = new HttpGet(url);
        } else if (CommonEnum.MethodEnum.METHOD_POST.is(method)) {
            request = new HttpPost(url);
        } else if (CommonEnum.MethodEnum.METHOD_DELETE.is(method)) {
            request = new GtHttpDelete(url);
        } else if (CommonEnum.MethodEnum.METHOD_PUT.is(method)) {
            request = new HttpPut(url);
        } else if (CommonEnum.MethodEnum.METHOD_HEAD.is(method)) {
            request = new HttpHead(url);
        } else if (CommonEnum.MethodEnum.METHOD_PATCH.is(method)) {
            request = new HttpPatch(url);
        } else if (CommonEnum.MethodEnum.METHOD_OPTIONS.is(method)) {
            request = new HttpOptions(url);
        } else if (CommonEnum.MethodEnum.METHOD_TRACE.is(method)) {
            request = new HttpTrace(url);
        } else {
            throw new ApiException("暂时不支持" + method + "方法");
        }
        return request;
    }

}
