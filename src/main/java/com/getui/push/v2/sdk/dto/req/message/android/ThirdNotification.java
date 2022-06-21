package com.getui.push.v2.sdk.dto.req.message.android;

import com.getui.push.v2.sdk.dto.CommonEnum;
import com.google.gson.annotations.SerializedName;

public class ThirdNotification {
    /**
     * 第三方厂商通知标题，长度 ≤ 50
     */
    private String title;
    /**
     * 第三方厂商通知内容，长度 ≤ 256
     */
    private String body;
    /**
     * @see CommonEnum.ClickTypeEnum
     * 点击通知后续动作,
     * 目前支持5种后续动作，
     * intent：打开应用内特定页面，
     * url：打开网页地址，
     * payload：启动应用加自定义消息内容，
     * startapp：打开应用首页，
     * none：纯通知，无后续动作
     */
    @SerializedName("click_type")
    private String clickType;

    /**
     * 点击通知打开应用特定页面，长度 ≤ 4096，不同厂商限制不同，具体限制参考各厂商文档;
     * hw：没有限制
     * vv：1024字符
     * mz：1000字节
     * op: ≤2000字节
     * 示例：intent:#Intent;component=你的包名/你要打开的 activity 全路径;S.parm1=value1;S.parm2=value2;end
     */
    private String intent;
    /**
     * 点击通知打开链接，长度 ≤ 1024
     */
    private String url;
    /**
     * 点击通知加自定义消息，长度 ≤ 3072
     */
    private String payload;
    /**
     * 消息覆盖使用，两条消息的notify_id相同，新的消息会覆盖老的消息
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

    public String getClickType() {
        return clickType;
    }

    public void setClickType(String clickType) {
        this.clickType = clickType;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "ThirdNotification{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", clickType='" + clickType + '\'' +
                ", intent='" + intent + '\'' +
                ", url='" + url + '\'' +
                ", payload='" + payload + '\'' +
                ", notifyId='" + notifyId + '\'' +
                '}';
    }
}
