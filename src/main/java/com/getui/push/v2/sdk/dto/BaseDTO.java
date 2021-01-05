package com.getui.push.v2.sdk.dto;

import com.getui.push.v2.sdk.common.ApiException;

/**
 * create by getui on 2020/6/2
 *
 * @author getui
 */
public interface BaseDTO {

    /**
     * 参数校验, 当参数有问题时，直接抛出异常{@link ApiException}
     *
     * @throws ApiException
     */
    void check() throws ApiException;

}
