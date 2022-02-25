package com.getui.push.v2.sdk.core.registry;

import com.getui.push.v2.sdk.core.factory.GtApiProxyFactory;

import java.lang.reflect.Method;

/**
 * create by getui on 2020/6/8
 *
 * @author getui
 */
public interface GtApiRegistry {

    /**
     * 注册, 解析出HTTP请求方法、路径、返回值类型等并缓存
     *
     * @param method
     */
    void register(Method method);

    /**
     * 获取缓存的方法信息，如果没有缓存则解析并缓存
     *
     * @param method 方法
     * @return
     */
    GtApiProxyFactory.BaseParam get(Method method);

}
