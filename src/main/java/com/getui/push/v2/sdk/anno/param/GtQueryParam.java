package com.getui.push.v2.sdk.anno.param;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 修饰HTTP请求中的query参数
 * create by getui on 2020/6/4
 *
 * @author getui
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface GtQueryParam {
    /**
     * 参数名
     *
     * @return
     */
    String name();
}
