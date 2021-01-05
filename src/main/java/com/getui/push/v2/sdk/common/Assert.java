package com.getui.push.v2.sdk.common;

import com.getui.push.v2.sdk.common.util.Utils;

import java.util.Collection;

/**
 * create by getui on 2020/6/3
 *
 * @author getui
 */
public class Assert {

    public static void notEmpty(String param, String paramName, boolean writableStackTrace) {
        if (Utils.isEmpty(param)) {
            throw new ApiException(paramName + " 不能为空", 400, writableStackTrace);
        }
    }

    public static void notBlank(String param, String paramName) {
        if (Utils.isEmpty(param)) {
            throw new ApiException(paramName + " 不能为空", 400);
        }
    }

    public static void notBlank(String param) {
        if (Utils.isEmpty(param)) {
            throw new ApiException("参数不能为空", 400);
        }
    }

    public static void notBlank(String param, boolean writableStackTrace) {
        if (Utils.isEmpty(param)) {
            throw new ApiException("参数不能为空", 400, writableStackTrace);
        }
    }

    public static <T> void notEmpty(Collection<T> collection, String paramName) {
        if (Utils.isEmpty(collection)) {
            throw new ApiException(paramName + " 不能为空", 400);
        }
    }

    public static <T> void notEmpty(Collection<T> collection, String paramName, boolean writableStackTrace) {
        if (Utils.isEmpty(collection)) {
            throw new ApiException(paramName + " 不能为空", 400, writableStackTrace);
        }
    }

    public static void notNull(Object obj, String paramName) {
        if (obj == null) {
            throw new ApiException(paramName + " 不能为null", 400);
        }
    }

}
