package com.getui.push.v2.sdk;

import java.lang.reflect.Type;

/**
 * 开发者实现此接口可用于自定义json转换方式
 * 注意：要保证线程安全
 * create by getui on 2020/9/25
 *
 * @author getui
 */
public interface IJson {

    /**
     * 对象转json
     *
     * @param obj
     * @return
     */
    String toJson(Object obj);

    /**
     * json转对象
     *
     * @param jsonString
     * @param type
     * @param <T>
     * @return
     */
    <T> T fromJson(String jsonString, Type type);

    <T> T fromJson(String jsonString, Class<T> tClass);

}
