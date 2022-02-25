package com.getui.push.v2.sdk.core.client;

import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.IJson;
import com.getui.push.v2.sdk.api.AuthApi;
import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.common.Assert;
import com.getui.push.v2.sdk.common.http.HttpManager;
import com.getui.push.v2.sdk.common.util.Utils;
import com.getui.push.v2.sdk.core.Configs;
import com.getui.push.v2.sdk.core.domain.RasDomainBO;
import com.getui.push.v2.sdk.core.factory.GtApiProxyFactory;
import com.getui.push.v2.sdk.core.handler.GtInterceptor;
import com.getui.push.v2.sdk.core.handler.impl.DefaultGtInterceptor;
import com.getui.push.v2.sdk.core.manager.HostManager;
import com.getui.push.v2.sdk.core.status.ServiceState;
import com.getui.push.v2.sdk.core.status.StateWrapper;
import com.getui.push.v2.sdk.dto.req.AuthDTO;
import com.getui.push.v2.sdk.dto.res.TokenDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.*;

/**
 * 1. 管理token
 * 2. 解析请求参数
 * 3. 发送HTTP请求
 * 4. 解析请求结果
 * create by getui on 2020/6/2
 *
 * @author getui
 */
public class DefaultApiClient {

    private static Logger log = LoggerFactory.getLogger(DefaultApiClient.class);

    /**
     * key: {@link GtApiConfiguration}, value: {@link DefaultApiClient}
     * 保证一个{@link GtApiConfiguration}对象对应一个{@link DefaultApiClient}对象
     */
    private static ConcurrentMap<String, DefaultApiClient> cache = new ConcurrentHashMap<String, DefaultApiClient>(2);
    private final static Object CACHE_LOCK = new Object();

    /**
     * 配置信息
     */
    private GtApiConfiguration apiConfiguration;

    /**
     * 鉴权token数据
     */
    private TokenDTO token;

    final String CONTENT_TYPE = "application/json";

    /**
     * 拦截器
     */
    private final List<GtInterceptor> interceptorList = new ArrayList<GtInterceptor>();

    /**
     * 域名管理，用于切换域名
     */
    private HostManager hostManager;

    /**
     * json转换
     */
    private IJson json;

    /**
     * 是否关闭
     */
    private boolean closed = false;

    private DefaultGtInterceptor defaultGtInterceptor;

    private HttpManager httpManager;

    /**
     * 分析的队列，异步守护线程{@link #initAnalyseDomainThread()}会循环pool
     */
    private final BlockingQueue<Object> analyseDomainQueue = new ArrayBlockingQueue<Object>(1);

    /**
     * 健康度数据队列
     */
    private final BlockingQueue<StateWrapper> reportDataQueue = new ArrayBlockingQueue<StateWrapper>(10);

    private AuthApi authApi;

    public static DefaultApiClient build(GtApiConfiguration apiConfiguration, IJson json) {
        apiConfiguration.check();
        if (json == null) {
            throw new InvalidParameterException("IJson 对象不能为null");
        }
        String key = apiConfiguration.keyOfCache();
        DefaultApiClient defaultApiClient = cache.get(key);
        if (defaultApiClient == null) {
            String prefixOfKey = apiConfiguration.prefixOfKey();
            synchronized (CACHE_LOCK) {
                Iterator<Map.Entry<String, DefaultApiClient>> iterator = cache.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, DefaultApiClient> entry = iterator.next();
                    if (entry.getKey().startsWith(prefixOfKey)) {
                        defaultApiClient = entry.getValue();
                        defaultApiClient.apiConfiguration = apiConfiguration;
                        iterator.remove();
                    }
                }
                if (defaultApiClient == null) {
                    defaultApiClient = new DefaultApiClient(apiConfiguration, json);
                }
                cache.put(key, defaultApiClient);
            }
        }
        return defaultApiClient;
    }

    private DefaultApiClient(GtApiConfiguration apiConfiguration, IJson json) {
        if (apiConfiguration == null) {
            throw new ApiException("apiConfiguration cannot be null.", true);
        }
        this.json = json;
        apiConfiguration.check();
        this.apiConfiguration = apiConfiguration;

        this.httpManager = new HttpManager(apiConfiguration.getConnectTimeout(),
                apiConfiguration.getSoTimeout(), apiConfiguration.getMaxHttpTryTime(),
                apiConfiguration.getKeepAliveSeconds(), apiConfiguration.getProxyConfig(),
                apiConfiguration.isTrustSSL());

        this.hostManager = new HostManager(apiConfiguration, this.httpManager);
        // 分析最稳定域名
        initAnalyseDomainThread();
        // 数据上报
        initReportDataThread();
        if (apiConfiguration.isOpenCheckHealthDataSwitch()
                || apiConfiguration.isOpenAnalyseStableDomainSwitch()) {
            defaultGtInterceptor = new DefaultGtInterceptor(hostManager, reportDataQueue, apiConfiguration);
            this.interceptorList.add(defaultGtInterceptor);
        }
    }

    private void initAnalyseDomainThread() {
        if (!apiConfiguration.isOpenAnalyseStableDomainSwitch()) {
            log.debug("The switch of analysing domain is closed.");
            return;
        }
        Thread analyseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!closed) {
                    try {
                        analyseDomainQueue.poll(apiConfiguration.getAnalyseStableDomainInterval(), TimeUnit.MILLISECONDS);
                        log.debug("start to analyse domain.");
                        hostManager.analyseStableDomain();
                    } catch (Exception e) {
                        log.debug("analyse domain failed.", e);
                    }
                }
            }
        }, "analyse high domain thread");

        analyseThread.setDaemon(true);
        analyseThread.setPriority(Thread.NORM_PRIORITY);
        analyseThread.start();
    }

    private void initReportDataThread() {
        if (!apiConfiguration.isOpenCheckHealthDataSwitch()) {
            return;
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!closed) {
                    try {
                        StateWrapper stateWrapper = reportDataQueue.poll(apiConfiguration.getCheckHealthInterval(), TimeUnit.MILLISECONDS);
                        if (!apiConfiguration.isOpenCheckHealthDataSwitch()) {
                            continue;
                        }
                        log.debug("start to report data.");
                        if (stateWrapper == null) {
                            // 获取监控数据
                            stateWrapper = defaultGtInterceptor.getAndRemove(hostManager.getUsing());
                        }
                        // 两次数据上报之间未发生任何请求
                        if (stateWrapper == null) {
                            return;
                        }
                        uploadHealthyData(stateWrapper);
                    } catch (Exception e) {
                    }
                }
            }
        }, "reportDataThread");

        thread.setDaemon(true);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.start();
    }

    private void uploadHealthyData(StateWrapper stateWrapper) {
        Map<String, Object> header = new HashMap<String, Object>(4);
        header.put("token", this.token.getToken());
        // 数据上报
        try {
            final String fullUrl = genFullUrl("/log/upload", null, null);
            final String result = httpManager.syncHttps(fullUrl, "POST", header, buildSdkHealthData(stateWrapper), this.CONTENT_TYPE);
            log.debug("upload data. result: {}", result);
        } catch (Exception e) {
            log.debug("upload data failed.", e);
        }
    }

    private String buildSdkHealthData(StateWrapper stateWrapper) {
        ConcurrentMap<String, ServiceState> stateData = stateWrapper.getUriToServiceState();
        if (stateData == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("time", System.currentTimeMillis());
        map.put("pushData", stateData);
        map.put("host", stateWrapper.getHost());
        map.put("highSwitch", apiConfiguration.isOpenAnalyseStableDomainSwitch());
        map.put("sdkVersion", Configs.SDK_VERSION);
        // 切换次数-增量
        map.put("switchIncr", hostManager.getSwitchIncrNum());
        // 切换次数-总量
        map.put("switchTotal", hostManager.getSwitchTotalNum());
        return json.toJson(map);
    }

    /**
     * 执行http调用之前调用此方法
     *
     * @param apiParam 请求参数
     * @param header   请求header
     * @param body     请求body
     */
    protected void beforeExecute(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body) {
        if (Utils.isEmpty(interceptorList)) {
            return;
        }
        for (GtInterceptor gtInterceptor : interceptorList) {
            gtInterceptor.pre(apiParam, header, body);
        }
    }

    /**
     * 1. 调用接口
     * 2. 如果返回token失效，刷新token后再次调用
     *
     * @param apiParam
     * @return
     */
    public ApiResult<?> execute(GtApiProxyFactory.ApiParam apiParam) {
        Assert.notNull(authApi, "authApi");
        final TokenDTO token = this.token;
        ApiResult<?> apiResult = doExecute(apiParam, token);
        // token失效，刷新token后重试
        if (apiResult.getCode() == 10001) {
            refreshTokenAndGet(token);
            return doExecute(apiParam, this.token);
        }
        // 天上域名变了
        else if (apiResult.getCode() == 301) {
            log.debug("domain changed. result: {}", apiResult);
            hostManager.handleDomain(json.fromJson(json.toJson(apiResult.getData()), RasDomainBO.class));
            // 触发域名分析
            analyseDomainQueue.offer("");
            return doExecute(apiParam, token);
        } else {
            return apiResult;
        }
    }

    /**
     * 执行HTTP调用并解析返回值
     *
     * @param apiParam
     * @return
     */
    private ApiResult<?> doExecute(GtApiProxyFactory.ApiParam apiParam, TokenDTO token) {
        Map<String, Object> header = new HashMap<String, Object>(4);
        if (apiParam.getNeedToken()) {
            if (token == null) {
                header.put("token", refreshTokenAndGet(token));
            } else {
                header.put("token", token.getToken());
            }
        }
        String body = null;
        if (apiParam.getBody() != null) {
            body = json.toJson(apiParam.getBody());
        }
        String result = null;
        String fullUrl = genFullUrl(apiParam.getUri(), apiParam.getPathParams(), apiParam.getQueryParams());
        try {
            // 处理header
            handleHeader(header);
            beforeExecute(apiParam, header, body);
            result = httpManager.syncHttps(fullUrl, apiParam.getMethod(), header, body, CONTENT_TYPE);
            postExecute(apiParam, header, body, result);
        } catch (ApiException e) {
            handleException(apiParam, header, body, e);
            return ApiResult.fail(e.getMessage(), e.getCode());
        } finally {
            afterDoExecute(apiParam, header, body, result);
        }
        if (result == null) {
            throw new ApiException(String.format("请求失败，返回值为空。url:%s, body: %s.", fullUrl, body));
        }
        try {
            ApiResult<?> apiResult = json.fromJson(result, apiParam.getReturnType());
            if (apiResult.getCode() == 301) {
                // 兼容域名改变
                apiResult = json.fromJson(result, ApiResult.class);
            }
            if (apiResult == null) {
                throw new ApiException(String.format("请求失败，解析返回值失败。url:%s, body: %s, result: %s.", fullUrl, body, result));
            }
            return apiResult;
        } catch (Exception e) {
            // 兼容域名改变
            ApiResult<?> apiResult = json.fromJson(result, ApiResult.class);
            if (apiResult == null) {
                throw new ApiException(String.format("请求失败，解析返回值失败。url:%s, body: %s, result: %s.", fullUrl, body, result));
            }
            return apiResult;
        }
    }


    /**
     * 处理header参数
     *
     * @param header header参数
     * @return
     */
    private void handleHeader(Map<String, Object> header) {
        header.put("sdkVersion", Configs.SDK_VERSION);
        if (!apiConfiguration.isOpenAnalyseStableDomainSwitch()) {
            return;
        }
        header.put(Configs.HEADER_OPEN_STABLE_DOMAIN, Boolean.TRUE);
        if (Utils.isNotEmpty(hostManager.domainHash())) {
            header.put(Configs.HEADER_DOMAIN_HASH_KEY, hostManager.domainHash());
        }
    }

    /**
     * 调用远程接口后调用此方法，如果报错则不会调用
     *
     * @param apiParam http请求的参数
     * @param header   http请求的header
     * @param body     http请求的body
     */
    protected void postExecute(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, String result) {
        if (Utils.isEmpty(interceptorList)) {
            return;
        }
        for (GtInterceptor gtInterceptor : interceptorList) {
            gtInterceptor.post(apiParam, header, body, result);
        }
    }

    /**
     * 调用远程接口报错后调用此方法
     *
     * @param apiParam http请求的参数
     * @param header   http请求的header
     * @param body     http请求的body
     * @param e        http请求抛出的异常
     */
    protected void handleException(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, ApiException e) {
        if (Utils.isEmpty(interceptorList)) {
            return;
        }
        for (GtInterceptor gtInterceptor : interceptorList) {
            gtInterceptor.handleException(apiParam, header, body, e);
        }
    }

    /**
     * 调用接口后调用此方法
     *
     * @param apiParam http请求的参数
     * @param header   http请求的header
     * @param body     http请求的body
     * @param result   http请求的返回值
     */
    protected void afterDoExecute(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, String result) {
        if (Utils.isEmpty(interceptorList)) {
            return;
        }
        for (GtInterceptor gtInterceptor : interceptorList) {
            gtInterceptor.afterCompletion(apiParam, header, body, result);
        }
    }

    /**
     * 刷新token
     *
     * @param usedToken 上次请求使用的token, usedToken==null时一定会刷新
     * @return
     */
    private synchronized String refreshTokenAndGet(TokenDTO usedToken) {
        TokenDTO token = this.token;
        if (usedToken != token && token != null && usedToken != null && !token.expired()) {
            return token.getToken();
        }
        AuthDTO authDTO = AuthDTO.build(apiConfiguration.getAppKey(), apiConfiguration.getMasterSecret());
        final ApiResult<TokenDTO> apiResult = authApi.auth(authDTO);
        if (apiResult.isSuccess()) {
            this.token = apiResult.getData();
        } else {
            log.error("get auth token failed. code: {}, msg: {}, param: {}.", apiResult.getCode(), apiResult.getMsg(), authDTO);
            throw new ApiException("获取token失败: " + apiResult.getMsg(), true);
        }
        if (this.token == null) {
            throw new ApiException("获取token失败: " + apiResult.getMsg(), true);
        }
        return this.token.getToken();
    }

    /**
     * 生成完整的请求链接
     *
     * @param uri
     * @param pathParams
     * @param queryParams
     * @return
     */
    private String genFullUrl(String uri, String pathParams, List<String> queryParams) {
        String host = hostManager.getUsing();
        if (!host.endsWith("v2") && !host.endsWith("v2/")) {
            if (host.endsWith("/")) {
                host = host + "v2/";
            } else {
                host = host + "/v2/";
            }
        }

        if (!host.contains(apiConfiguration.getAppId())) {
            if (host.endsWith("/")) {
                host = host + apiConfiguration.getAppId();
            } else {
                host = host + "/" + apiConfiguration.getAppId();
            }
        }

        if (Utils.isEmpty(pathParams)) {
            host = host + uri;
        } else {
            host += uri;
            if (host.endsWith("/")) {
                host += pathParams;
            } else {
                host = host + "/" + pathParams;
            }
        }
        if (Utils.isEmpty(queryParams)) {
            return host;
        }
        StringBuilder queryParamSb = new StringBuilder();
        for (String param : queryParams) {
            queryParamSb.append(param).append("&");
        }
        final String queryParam = queryParamSb.substring(0, queryParamSb.length() - 1);
        return host + "?" + queryParam;
    }

    public void setJson(IJson json) {
        this.json = json;
    }

    /**
     * 释放 {@link #cache}中的对象，但是此对象仍然可以使用，直到没有内存引用被回收
     */
    public void close() {
        this.closed = true;
    }

    public void setAuthApiAndAuth(AuthApi authApi) {
        if (authApi == null) {
            throw new ApiException("authApi 不能为null");
        }
        this.authApi = authApi;
        // 刷新token
        try {
            refreshTokenAndGet(null);
        } catch (ApiException e) {
            log.error("refresh token failed.", e);
            throw e;
        }
    }
}
