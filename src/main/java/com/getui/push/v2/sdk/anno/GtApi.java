package com.getui.push.v2.sdk.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用此注解修饰的类代表个推API接口，会在应用启动时，提前初始化，初始化的过程中，会检测接口规范
 * create by getui on 2020/6/7
 *
 * @author getui
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GtApi {
}
