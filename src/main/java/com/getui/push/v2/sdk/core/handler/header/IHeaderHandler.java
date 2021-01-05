package com.getui.push.v2.sdk.core.handler.header;

import com.getui.push.v2.sdk.core.handler.IHandler;

import java.util.Map;

/**
 * 处理header参数的handler
 * create by getui on 2020/6/15
 *
 * @author getui
 */
public interface IHeaderHandler extends IHandler<Map<String, Object>> {
    /**
     * 处理header参数
     *
     * @param header header，可能为null
     * @return 将作为新的header参数
     */
    @Override
    Map<String, Object> handle(Map<String, Object> header);
}
