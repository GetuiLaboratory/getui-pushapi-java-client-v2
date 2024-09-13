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
     * 目前支持3种后续动作，
     * want：打开应用内特定页面，
     * startapp：打开应用首页
     * payload：通知扩展消息
     */
    @SerializedName("click_type")
    private String clickType;
    /**
     * 鸿蒙平台点击动作 <br><br/>
     * 示例：{"deviceId":"","bundleName":"com.getui.push","abilityName":"TestAbility","uri":"https://www.test.com:8080/push/test","action":"com.test.action","parameters":{"name":"Getui","age":12}}
     */
    private String want;
    /**
     * 鸿蒙平台通知扩展消息（消息分类category参数必填，且设置“EXPRESS”，发送通知扩展消息前请先申请开通对应的消息自分类权益）
     * <a href="https://developer.huawei.com/consumer/cn/doc/harmonyos-guides/push-noti-classification-0000001727885246#section0965171625420">自分类权益申请</a>
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

    public String getWant() {
        return want;
    }

    public void setWant(String want) {
        this.want = want;
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
                "want='" + want + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", category='" + category + '\'' +
                ", clickType='" + clickType + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
