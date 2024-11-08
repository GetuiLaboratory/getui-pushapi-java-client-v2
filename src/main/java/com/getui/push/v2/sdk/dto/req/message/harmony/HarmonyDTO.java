package com.getui.push.v2.sdk.dto.req.message.harmony;

import com.getui.push.v2.sdk.dto.req.message.CustomBean;
import com.getui.push.v2.sdk.dto.req.message.android.ThirdRevokeBean;

import java.util.HashMap;
import java.util.Map;

public class HarmonyDTO {

    /**
     * 通知消息内容，与transmission、custom、revoke四选一，都填写时报错。
     * 若希望客户端离线时，直接在系统通知栏中展示通知栏消息，推荐使用此参数。
     */
    private HarmonyNotification notification;
    /**
     * 透传消息内容，与notification、custom、revoke四选一，都填写时报错，长度 ≤ 3072字
     */
    private String transmission;
    /**
     * 自定义消息内容，与notification、custom、revoke四选一，都填写时报错。
     */
    private Map<String, CustomBean> custom;
    /**
     * 撤回消息时使用
     */
    private ThirdRevokeBean revoke;
    /**
     * 第三方厂商扩展内容
     */
    private Map<String, Map<String, Object>> options;

    public HarmonyNotification getNotification() {
        return notification;
    }

    public void setNotification(HarmonyNotification notification) {
        this.notification = notification;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Map<String, CustomBean> getCustom() {
        return custom;
    }

    public void setCustom(Map<String, CustomBean> custom) {
        this.custom = custom;
    }

    public ThirdRevokeBean getRevoke() {
        return revoke;
    }

    public void setRevoke(ThirdRevokeBean revoke) {
        this.revoke = revoke;
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

    @Override
    public String toString() {
        return "HarmonyDTO{" +
                "notification=" + notification +
                ", transmission='" + transmission + '\'' +
                ", custom=" + custom +
                ", revoke=" + revoke +
                ", options=" + options +
                '}';
    }
}
