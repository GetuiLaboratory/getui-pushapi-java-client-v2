package com.getui.push.v2.sdk.core.handler;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.core.factory.GtApiProxyFactory;

import java.util.Map;

/**
 * create by getui on 2020/9/28
 *
 * @author getui
 */
public interface GtInterceptor {
    /**
     * http请求前调用
     *
     * @param apiParam 请求参数
     * @param header   请求header
     * @param body     请求body
     */
    void pre(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body);

    /**
     * http请求成功后调用此方法
     *
     * @param apiParam 请求参数
     * @param header   请求header
     * @param body     请求body
     * @param result   返回值
     */
    void post(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, String result);

    /**
     * 报错时调用此方法
     *
     * @param apiParam 请求参数
     * @param header   请求header
     * @param body     请求body
     * @param e        异常信息
     */
    void handleException(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, ApiException e);

    /**
     * http请求后调用，不管成功或者失败都会调用
     *
     * @param apiParam 请求参数
     * @param header   请求header
     * @param body     请求body
     * @param result   返回值
     */
    void afterCompletion(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, String result);
}
