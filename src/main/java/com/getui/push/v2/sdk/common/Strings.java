package com.getui.push.v2.sdk.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xiaoxu.yxx on 2014/6/23.
 */
public final class Strings {
    /**
     * 连接字符串数组,
     *
     * @param array
     * @return
     */
    public static String join(CharSequence... array) {
        return join(array, "");
    }

    /**
     * 连接字符串数组,
     *
     * @return
     */
    public static String join(String separator, CharSequence... array) {
        return join(array, separator);
    }

    /**
     * 连接字符串数组,
     *
     * @return
     */
    public static String join(Object[] array) {
        return join(array, "");
    }

    /**
     * 连接字符串数组,
     *
     * @return
     */
    public static String join(Object[] array, String separator) {
        return join(array, separator, 0, array.length);
    }

    /**
     * 连接字符串数组,
     *
     * @return
     */
    public static String join(Object[] array, char separator) {
        return join(array, separator, 0, array.length);
    }

    /**
     * 连接字符串数组,
     *
     * @return
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) return null;
        if (separator == null) separator = "";
        int L = endIndex - startIndex;
        if (L <= 0) return "";
        StringBuilder sb = new StringBuilder(L * (array[startIndex] == null ? 16 : array[startIndex].toString().length())
                + separator.length());
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] != null) {
                sb.append(array[i]).append(separator);
            }
        }
        int index = sb.lastIndexOf(separator);
        if (index != -1) sb.delete(index, sb.length());
        return sb.toString();
    }

    /**
     * 连接字符串数组,
     *
     * @return
     */
    public static String join(Object[] array, char separator, int startIndex, int endIndex) {
        if (array == null) return null;
        int L = endIndex - startIndex;
        if (L <= 0) return "";
        StringBuilder sb = new StringBuilder(L * (array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] != null) {
                sb.append(array[i]).append(separator);
            }
        }
        int index = sb.lastIndexOf(separator + "");
        if (index != -1) sb.deleteCharAt(index);
        return sb.toString();
    }

    /**
     * 连接字符串数组,
     *
     * @return
     */
    public static <T> String join(Iterable<T> iterable, String separator) {
        if (iterable == null) return null;
        StringBuilder sb = new StringBuilder();
        for (T t : iterable) {
            if (t != null) sb.append(t).append(separator);
        }
        int index = sb.lastIndexOf(separator);
        if (index != -1) sb.delete(index, separator.length() + index);
        return sb.toString();
    }

    public static List<String> split(String regex, CharSequence input) {
        int index = 0;
        List<String> matchList = new ArrayList<String>();
        Matcher m = Pattern.compile(regex).matcher(input);
        while (m.find()) {
            matchList.add(input.subSequence(index, m.start()).toString());
            index = m.end();
        }
        if (index < input.length())
            matchList.add(input.subSequence(index, input.length()).toString());
        return matchList;
    }


    /**
     * 将字符串每个单词首字母大写
     *
     * @param s 字符串
     * @return 首字母大写后的新字符串
     */
    public static String capitalize(CharSequence s) {
        return capitalize(s, " \t\r\n");
    }

    public static String capitalize(CharSequence s, String separator) {
        StringTokenizer st = new StringTokenizer(s.toString(), separator, true);
        StringBuilder sb = new StringBuilder(s.length());
        while (st.hasMoreTokens()) {
            String tok = st.nextToken();
            sb.append(tok.substring(0, 1).toUpperCase())
                    .append(tok.substring(1).toLowerCase());
        }
        return sb.toString();
    }

    /**
     * 将字符串首字母小写
     *
     * @param s 字符串
     * @return 首字母小写后的新字符串
     */
    public static String lowerFirst(CharSequence s) {
        int len = s.length();
        if (len == 0) return "";
        char c = s.charAt(0);
        if (Character.isLowerCase(c)) return s.toString();
        return new StringBuilder(len).append(Character.toLowerCase(c))
                .append(s.subSequence(1, len)).toString();
    }

    /**
     * 将字符串首字母大写
     *
     * @param s
     * @return
     */
    public static String upperFirst(CharSequence s) {
        int len = s.length();
        if (len == 0) return "";
        char c = s.charAt(0);
        if (Character.isUpperCase(c)) return s.toString();
        return new StringBuilder(len).append(Character.toUpperCase(c))
                .append(s.subSequence(1, len)).toString();
    }

    /**
     * 检查两个字符串的忽略大小写后是否相等.
     *
     * @param s1 字符串A
     * @param s2 字符串B
     * @return true 如果两个字符串忽略大小写后相等,且两个字符串均不为null
     */
    public static boolean equalsIgnoreCase(String s1, String s2) {
        return s1 == null ? s2 == null : s1.equalsIgnoreCase(s2);
    }

    public static boolean equalsIgnoreCase(Object obj, String str) {
        return obj == null ? str == null : valueOf(obj).equalsIgnoreCase(str);
    }

    /**
     * 检查两个字符串是否相等.
     *
     * @param s1 字符串A
     * @param s2 字符串B
     * @return true 如果两个字符串相等,且两个字符串均不为null
     */
    public static boolean equals(CharSequence s1, CharSequence s2) {
        return s1 == null ? s2 == null : s1.equals(s2);
    }

    /**
     * 判断字符串是否以特殊字符开头
     *
     * @param s 字符串
     * @param c 特殊字符
     * @return 是否以特殊字符开头
     */
    public static boolean startsWithChar(CharSequence s, char c) {
        return null != s && (s.length() != 0 && s.charAt(0) == c);
    }

    /**
     * 判断字符串是否以特殊字符结尾
     *
     * @param s 字符串
     * @param c 特殊字符
     * @return 是否以特殊字符结尾
     */
    public static boolean endsWithChar(CharSequence s, char c) {
        return null != s && (s.length() != 0 && s.charAt(s.length() - 1) == c);
    }

    public static boolean isEmpty(CharSequence... css) {
        for (CharSequence cs : css) {
            if (isEmpty(cs)) return true;
        }
        return false;
    }

    /**
     * @param cs 字符串
     * @return 是不是为空字符串
     */
    public static boolean isEmpty(CharSequence cs) {
        return null == cs || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return null != cs && cs.length() > 0;
    }

    public static boolean isNotEmpty(CharSequence... css) {
        for (CharSequence cs : css) {
            if (isEmpty(cs)) return false;
        }
        return true;
    }

    /**
     * @param cs 字符串
     * @return 是不是为空白字符串
     */
    public static boolean isBlank(CharSequence cs) {
        int L;
        if (cs == null || (L = cs.length()) == 0)
            return true;
        for (int i = 0; i < L; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    /**
     * 去掉字符串前后空白
     *
     * @param cs 字符串
     * @return 新字符串
     */
    public static String trim(CharSequence cs) {
        if (null == cs)
            return null;
        if (cs instanceof String)
            return ((String) cs).trim();
        int length = cs.length();
        if (length == 0)
            return cs.toString();
        int l = 0;
        int last = length - 1;
        int r = last;
        for (; l < length; l++) {
            if (!Character.isWhitespace(cs.charAt(l)))
                break;
        }
        for (; r > l; r--) {
            if (!Character.isWhitespace(cs.charAt(r)))
                break;
        }
        if (l > r)
            return "";
        else if (l == 0 && r == last)
            return cs.toString();
        return cs.subSequence(l, r + 1).toString();
    }

    public static String valueOf(Object o, String defaultVal) {
        return o == null ? defaultVal : o.toString();
    }

    public static String valueOf(Object o) {
        return Strings.valueOf(o, "");
    }

}
