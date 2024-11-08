package com.getui.push.v2.sdk.dto.req.message;

public class CustomBean {
    /**
     * 消息类型
     * subscribe：授权订阅消息（仅支持toSingle推送API）
     * form_update：卡片刷新消息（仅支持toSingle推送API）
     * live_view：实况窗消息
     * voip_call：VoIP呼叫消息
     */
    private String type;
    /**
     * 相应消息类型指定请求体的JSON字符串
     */
    private String payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "CustomBean{" +
                "type='" + type + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
