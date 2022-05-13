package com.getui.push.v2.sdk.api.util;

import java.util.HashSet;
import java.util.Set;

/**
 * create by 远见 on 2022/5/12
 *
 * @author liuyj@getui.com
 */
public class Utils {
    public static <T> Set<T> newHashSet(T... arr) {
        Set<T> set = new HashSet<T>(arr.length);
        for (T t : arr) {
            set.add(t);
        }
        return set;
    }
}
