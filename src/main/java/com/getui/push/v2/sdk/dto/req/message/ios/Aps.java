package com.getui.push.v2.sdk.dto.req.message.ios;

import com.google.gson.annotations.SerializedName;

/**
 * create by getui on 2020/7/20
 *
 * @author getui
 */
public class Aps {
    /**
     * 通知消息
     */
    private Alert alert;
    /**
     * 推送直接带有透传数据，content-available=1表示静默推送，静默推送时不需要填写其他参数，详细参数填写见示例，苹果建议1小时最多推送3条静默消息
     */
    @SerializedName("content-available")
    private Integer contentAvailable;
    /**
     * 通知铃声文件名，无声设置为“com.gexin.ios.silence”
     */
    private String sound;
    /**
     * 在客户端通知栏触发特定的action和button显示
     */
    private String category;

    /**
     * ios的远程通知通过该属性对通知进行分组，仅支持iOS 12.0以上版本
     */
    @SerializedName("thread-id")
    private String threadId;

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public Integer getContentAvailable() {
        return contentAvailable;
    }

    public void setContentAvailable(Integer contentAvailable) {
        this.contentAvailable = contentAvailable;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    @Override
    public String toString() {
        return "Aps{" +
                "alert=" + alert +
                ", contentAvailable=" + contentAvailable +
                ", sound='" + sound + '\'' +
                ", category='" + category + '\'' +
                ", threadId='" + threadId + '\'' +
                '}';
    }
}
