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
     * 鸿蒙平台点击动作 <br><br/>
     * 示例：{"deviceId":"","bundleName":"com.getui.push","abilityName":"TestAbility","uri":"https://www.test.com:8080/push/test","action":"com.test.action","parameters":{"name":"Getui","age":12}}
     */
    private String want;

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

    @Override
    public String toString() {
        return "HarmonyNotification{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", category='" + category + '\'' +
                ", clickType='" + clickType + '\'' +
                ", want='" + want + '\'' +
                '}';
    }
}
