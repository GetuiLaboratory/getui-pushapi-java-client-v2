package com.getui.push.v2.sdk;

import com.getui.push.v2.sdk.api.AuthApi;
import com.getui.push.v2.sdk.api.PushApi;
import com.getui.push.v2.sdk.api.UserApi;
import com.getui.push.v2.sdk.common.Assert;
import com.getui.push.v2.sdk.core.DefaultJson;
import com.getui.push.v2.sdk.core.client.DefaultApiClient;
import com.getui.push.v2.sdk.core.factory.GtApiProxyFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by getui on 2020/6/7
 *
 * @author getui
 */
public class ApiHelper {

    private final GtApiProxyFactory gtApiProxyFactory;

    private static final Object BUILD_LOCK = new Object();

    private static final Map<String, ApiHelper> apiHelperCache = new ConcurrentHashMap<String, ApiHelper>(4);

    /**
     * @param configuration 配置信息类
     * @return
     */
    public static ApiHelper build(GtApiConfiguration configuration) {
        return build(configuration, new DefaultJson());
    }

    /**
     * @param configuration 配置信息类
     * @return
     */
    public static ApiHelper build(GtApiConfiguration configuration, IJson json) {
        Assert.notNull(configuration, "configuration");
        configuration.check();
        String key = configuration.keyOfCache();
        ApiHelper apiHelper = apiHelperCache.get(key);
        if (apiHelper != null) {
            return apiHelper;
        }
        if (json == null) {
            json = new DefaultJson();
        }
        synchronized (BUILD_LOCK) {
            apiHelper = apiHelperCache.get(key);
            if (apiHelper != null) {
                return apiHelper;
            }
            final DefaultApiClient defaultApiClient = DefaultApiClient.build(configuration, json);
            GtApiProxyFactory factory = GtApiProxyFactory.build(defaultApiClient);
            final AuthApi authApi = factory.createProxy(AuthApi.class);
            defaultApiClient.setAuthApiAndAuth(authApi);
            apiHelper = new ApiHelper(factory);
            apiHelperCache.put(key, apiHelper);
            return apiHelper;
        }
    }

    /**
     * 删除缓存，并关闭守护线程，不影响已创建的Api的使用
     *
     * @param configuration
     */
    public static void close(GtApiConfiguration configuration) {
        Assert.notNull(configuration, "configuration");
        configuration.check();
        String key = configuration.keyOfCache();
        ApiHelper apiHelper = apiHelperCache.remove(key);
        if (apiHelper != null) {
            apiHelper.gtApiProxyFactory.close();
        }
    }

    private ApiHelper(GtApiProxyFactory gtApiProxyFactory) {
        this.gtApiProxyFactory = gtApiProxyFactory;
    }

    /**
     * 创建接口实例
     *
     * @param apiClass {@link UserApi} {@link PushApi}
     * @param <T>
     * @return
     */
    public <T> T creatApi(Class<T> apiClass) {
        return this.gtApiProxyFactory.createProxy(apiClass);
    }

}
