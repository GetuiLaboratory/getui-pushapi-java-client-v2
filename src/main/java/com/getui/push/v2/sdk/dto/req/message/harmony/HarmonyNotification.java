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
     * 鸿蒙平台通知扩展消息的额外数据，传递给应用的数据，应用根据数据自行处理相关逻辑
     */
    private String payload;

    /**
     * 消息覆盖使用，两条消息的notify_id相同，新的消息会覆盖老的消息
     * 范围：[0, 2147483647]（如果要使用鸿蒙华为的消息撤回功能，此参数必填）
     */
    @SerializedName("notify_id")
    private String notifyId;

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

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    @Override
    public String toString() {
        return "HarmonyNotification{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", category='" + category + '\'' +
                ", clickType='" + clickType + '\'' +
                ", want='" + want + '\'' +
                ", payload='" + payload + '\'' +
                ", notifyId='" + notifyId + '\'' +
                '}';
    }
}
