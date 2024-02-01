package com.getui.push.v2.sdk.dto.req.message.harmony;

import com.getui.push.v2.sdk.dto.CommonEnum;
import com.google.gson.annotations.SerializedName;

public class HarmonyNotification {
    /**
     * 第三方厂商通知标题，长度 ≤ 50
     */
    private String title;
    /**
     * 第三方厂商通知内容，长度 ≤ 256
     */
    private String body;
    /**
     * 鸿蒙华为通知消息类别，长度 ≤ 50
     */
    private String category;
    /**
     * @see CommonEnum.HarmonyClickTypeEnum
     * 点击通知后续动作,
     * 目前支持2种后续动作，
     * want：打开应用内特定页面，
     * startapp：打开应用首页
     */
    @SerializedName("click_type")
    private String clickType;

    /**
     * click_type为want时，action和uri必须填一个，点击通知打开应用特定页面
     */
    private String action;
    /**
     * click_type为want时，action和uri必须填一个，点击通知打开应用特定页面
     */
    private String uri;
    /**
     * 点击通知加附加消息，长度 ≤ 3072
     */
    private String payload;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClickType() {
        return clickType;
    }

    public void setClickType(String clickType) {
        this.clickType = clickType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "HarmonyNotification{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", category='" + category + '\'' +
                ", clickType='" + clickType + '\'' +
                ", action='" + action + '\'' +
                ", uri='" + uri + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
