package com.getui.push.v2.sdk.anno.method;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by getui on 2020/6/4
 *
 * @author getui
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GtDelete {

    /**
     * 接口相对路径, 不包含路径参数
     *
     * @return
     */
    String uri();

    /**
     * 是否需要token，默认需要
     *
     * @return
     */
    boolean needToken() default true;
}
