package com.getui.push.v2.sdk.core.handler;

/**
 * create by getui on 2020/6/15
 *
 * @author getui
 */
public interface IHandler<T> {

    /**
     * 处理器接口
     *
     * @param t
     * @return
     */
    T handle(T t);

}
