package com.getui.push.v2.sdk.dto.req.message.android;

import java.util.HashMap;
import java.util.Map;

/**
 * Android厂商消息
 */
public class Ups {
    /**
     * 通知消息内容，与transmission 二选一，两个都填写时报错
     */
    private ThirdNotification notification;
    /**
     * 透传消息内容，与notification 二选一，两个都填写时报错，长度 ≤ 3072
     */
    private String transmission;

    /**
     * 第三方厂商扩展内容
     */
    private Map<String, Map<String, Object>> options;

    /**
     * 撤回消息时使用
     */
    private ThirdRevokeBean revoke;

    public ThirdNotification getNotification() {
        return notification;
    }

    public void setNotification(ThirdNotification notification) {
        this.notification = notification;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Map<String, Map<String, Object>> getOptions() {
        return options;
    }

    public void setOptions(Map<String, Map<String, Object>> options) {
        this.options = options;
    }

    public void addOptionAll(String key, Object value) {
        addOption("ALL", key, value);
    }

    public void addOption(String constraint, String key, Object value) {
        if (constraint == null || "".equals(constraint)) {
            constraint = "ALL";
        }
        if (options == null) {
            options = new HashMap<String, Map<String, Object>>();
        }
        if (!options.containsKey(constraint)) {
            options.put(constraint, new HashMap<String, Object>());
        }
        Map<String, Object> map = options.get(constraint);
        map.put(key, value);
    }

    public ThirdRevokeBean getRevoke() {
        return revoke;
    }

    public void setRevoke(ThirdRevokeBean revoke) {
        this.revoke = revoke;
    }

    @Override
    public String toString() {
        return "Ups{" +
                "notification=" + notification +
                ", transmission='" + transmission + '\'' +
                ", options=" + options +
                ", revoke=" + revoke +
                '}';
    }
}
