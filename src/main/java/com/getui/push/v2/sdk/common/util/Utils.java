package com.getui.push.v2.sdk.common.util;

import com.getui.push.v2.sdk.common.Strings;

import java.util.Collection;
import java.util.Map;

public final class Utils {

    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }

    public static boolean isNotEmpty(Map map) {
        return map != null && map.size() > 0;
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection collection) {
        return collection != null && collection.size() > 0;
    }

    public static boolean isEmpty(CharSequence sequence) {
        return Strings.isBlank(sequence);
    }

    public static boolean isNotEmpty(CharSequence sequence) {
        return Strings.isNotBlank(sequence);
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(byte[] array) {
        return array != null && array.length > 0;
    }


    public static boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static final String v2UrlPrefix = "/v2";

    /**
     * 对于v2的接口，一定存在/v2前缀
     *
     * @param url
     * @return
     */
    public static String v2UrlToHost(String url) {
        int v2Index = url.indexOf(v2UrlPrefix);
        if (v2Index > 0) {
            return url.substring(0, v2Index);
        } else {
            return url;
        }
    }

}
