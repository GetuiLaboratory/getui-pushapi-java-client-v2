package com.getui.push.v2.sdk.common.http;

import com.getui.push.v2.sdk.GtHttpProxyConfig;
import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.common.Config;
import com.getui.push.v2.sdk.common.Monitor;
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
import org.apache.http.impl.client.*;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GtHttpClient {
    private static Logger log = LoggerFactory.getLogger(GtHttpClient.class);
    private int maxHttpTryTime;
    CloseableHttpClient httpclient;
    volatile RequestConfig config;

    public GtHttpClient(int connectTimeout,
                        int soTimeout,
                        int connectionRequestTimeout,
                        int maxHttpTryTime,
                        long keepAliveSeconds,
                        GtHttpProxyConfig proxyConfig,
                        boolean trustSSL) {
        if (connectTimeout <= 0) {
            throw new IllegalArgumentException("connectTimeout must be > 0.");
        }
        if (soTimeout <= 0) {
            throw new IllegalArgumentException("soTimeout must be > 0.");
        }
        this.maxHttpTryTime = maxHttpTryTime;
        final HttpClientBuilder builder = HttpClients.custom();
        // 关闭CloseableHttpClient内默认的重试机制
        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
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
        // jvm1.6 如果不设置信任证书，会报错
        if (System.getProperty("java.version").startsWith("1.6") || trustSSL) {
            builder.setSSLContext(createSSLContext());
        }
        config = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(soTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setMaxRedirects(0)
                .build();
        builder.setDefaultRequestConfig(config)
                .setConnectionTimeToLive(keepAliveSeconds, TimeUnit.SECONDS)
                .useSystemProperties();

        final String maxConnTotal = System.getProperty("http.client.maxConnTotal");
        if (!StringUtils.isEmpty(maxConnTotal)) {
            builder.setMaxConnTotal(Integer.parseInt(maxConnTotal));
        }

        final String maxConnPerRoute = System.getProperty("http.client.maxConnPerRoute");
        if (!StringUtils.isEmpty(maxConnPerRoute)) {
            builder.setMaxConnPerRoute(Integer.parseInt(maxConnPerRoute));
        }

        this.httpclient = builder.build();
    }

    /**
     * @param url
     * @param rasDomain     天上的域名列表
     * @param socketTimeout
     * @param method
     * @param headers
     * @param body
     * @param contentType
     * @return
     */
    public String execute(String url, List<String> rasDomain, int socketTimeout, String method, Map<String, Object> headers, String body, String contentType) {
        int retryTimes = 0;
        String currUrl = url;
        while (true) {
            if (retryTimes > 0) {
                // 重试时，修改host
                currUrl = modifyUrl(rasDomain, currUrl, retryTimes);
            }
            long start = System.currentTimeMillis();
            String logResult = null;
            try {
                return logResult = doExecute(currUrl, socketTimeout, method, headers, body, contentType);
            } catch (ApiException e) {
                Monitor.incrementFailedNum(currUrl);
                if (e.getCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                    logResult = e.toString();
                    throw e;
                }
                if (retryTimes >= maxHttpTryTime) {
                    logResult = e.toString();
                    throw e;
                }
                logResult = "will retry, ex: " + e.getMessage();
            } finally {
                // 记录访问日志: retryTimes|url|header|body|result
                log.debug("http log|retry:{}|time:{}|{}|{}|{}|{}", retryTimes, System.currentTimeMillis() - start, currUrl, headers, body, logResult);
                // 先打印日志，后执行 ++ 操作，保证日志中的次数是当前重试的次数
                retryTimes++;
            }
        }
    }

    static String modifyUrl(List<String> rasDomain, String url, int retryTimes) {
        if (Utils.isEmpty(rasDomain)) {
            return url;
        }
        if (rasDomain.size() == 1) {
            String host = rasDomain.get(0);
            return replaceHost(url, host);
        } else {
            if (retryTimes < 1) {
                retryTimes = 1;
            }
            int sameHostNum = 0;
            int size = rasDomain.size();
            for (int i = retryTimes - 1; ; i++) {
                if (i >= size) {
                    i = retryTimes % size;
                }
                String host = rasDomain.get(i);
                // sameHostNum++ < 2 防止list中有多个相同的host，导致死循环
                if (url.startsWith(host) && sameHostNum++ < 2) {
                    continue;
                } else {
                    return replaceHost(url, host);
                }
            }
        }
    }

    static String replaceHost(String url, String host) {
        if (url.startsWith(host)) {
            return url;
        }
        int v2Index = url.indexOf("/v2/");
        if (host.endsWith("/v2/")) {
            return host + url.substring(v2Index + 4);
        } else if (host.endsWith("/v2")) {
            return host + url.substring(v2Index + 3);
        } else if (host.endsWith("/")) {
            return host + url.substring(v2Index + 1);
        } else {
            return host + url.substring(v2Index);
        }
    }

    public String doExecute(String url, int socketTimeout, String method, Map<String, Object> headers, String body, String contentType) {
        final HttpRequestBase request = genHttpUriRequest(url, method);
        if (socketTimeout > 0) {
            RequestConfig config = RequestConfig.copy(this.config)
                    .setSocketTimeout(socketTimeout)
                    .build();
            request.setConfig(config);
        }
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
                    throw new ApiException("设置body失败:" + e.getMessage(), 5000, e);
                }
            } else {
                entity = new StringEntity(body, ContentType.create(contentType, Config.UTF_8));
            }
            ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
        }
        return doRequest(request);
    }

    public static SSLContext createSSLContext() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            X509TrustManager trustManager = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            return sslContext;
        } catch (Exception e) {
            throw new ApiException("create httpClient error.", e);
        }
    }

    private String doRequest(HttpUriRequest request) {
        CloseableHttpResponse response = null;
        int code = 5000;
        try {
            response = httpclient.execute(request);
            final StatusLine statusLine = response.getStatusLine();
            code = statusLine.getStatusCode();
            String responseBody = "";
            // toString内部回收connection
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseBody = EntityUtils.toString(entity);
            }
            if (code == HttpURLConnection.HTTP_OK ||
                    code == HttpURLConnection.HTTP_BAD_REQUEST ||
                    code == HttpURLConnection.HTTP_UNAUTHORIZED ||
                    code == HttpURLConnection.HTTP_FORBIDDEN) {
                return responseBody;
            } else if (code == HttpURLConnection.HTTP_NOT_FOUND) {
                throw new ApiException("not found.", code);
            }
            // >=500 异常重试
            else if (code >= HttpURLConnection.HTTP_INTERNAL_ERROR) {
                throw new ApiException("http error", code);
            } else {
                throw new ApiException("Http Response Error.", code);
            }
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("http error:" + e.getMessage(), code, e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private HttpRequestBase genHttpUriRequest(String url, String method) {
        HttpRequestBase request;
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
