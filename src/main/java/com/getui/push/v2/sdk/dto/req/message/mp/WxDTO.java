package com.getui.push.v2.sdk.dto.req.message.mp;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * 微信小程序订阅消息
 * <a href="https://developers.weixin.qq.com/miniprogram/dev/server/API/mp-message-management/subscribe-message/api_sendmessage.html#%E8%AF%B7%E6%B1%82%E4%BD%93-Request-Payload">微信官方文档</a>
 */
public class WxDTO {

    /**
     * 微信小程序订阅消息模板ID
     */
    @SerializedName("template_id")
    private String templateId;

    /**
     * 点击模板卡片后的跳转页面，仅限本小程序内的页面
     */
    private String page;

    /**
     * 跳转小程序类型：
     * developer：开发版
     * trial：体验版
     * formal：正式版
     */
    @SerializedName("miniprogram_state")
    private String miniprogramState;

    /**
     * 进入小程序查看的语言类型：
     * zh_CN：简体中文
     * en_US：英文
     * zh_HK：繁体中文
     * zh_TW：繁体中文
     */
    private String lang;

    /**
     * 模板内容，格式参考微信小程序订阅消息文档
     */
    private Map<String, Map<String, String>> data;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getMiniprogramState() {
        return miniprogramState;
    }

    public void setMiniprogramState(String miniprogramState) {
        this.miniprogramState = miniprogramState;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WxDTO{" +
                "templateId='" + templateId + '\'' +
                ", page='" + page + '\'' +
                ", miniprogramState='" + miniprogramState + '\'' +
                ", lang='" + lang + '\'' +
                ", data=" + data +
                '}';
    }
}
