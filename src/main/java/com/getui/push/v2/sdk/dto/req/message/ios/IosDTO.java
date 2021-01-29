package com.getui.push.v2.sdk.dto.req.message.ios;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * create by getui on 2020/7/20
 *
 * @author getui
 */
public class IosDTO {
    /**
     * voip：voip语音推送，notify：apns通知消息
     */
    private String type;

    /**
     * 推送通知消息内容
     */
    private Aps aps;
    /**
     * 用于计算icon上显示的数字，还可以实现显示数字的自动增减，如“+1”、 “-1”、 “1” 等，计算结果将覆盖badge
     */
    @SerializedName("auto_badge")
    private String autoBadge;
    /**
     * 增加自定义的数据
     */
    private String payload;
    /**
     * 多媒体设置
     */
    private List<Multimedia> multimedia;

    /**
     * 使用相同的apns-collapse-id可以覆盖之前的消息
     */
    @SerializedName("apns-collapse-id")
    private String apnsCollapseId;

    public IosDTO addMultimedia(Multimedia multimedia) {
        if (multimedia == null) {
            return this;
        }
        if (this.multimedia == null) {
            this.multimedia = new ArrayList<Multimedia>();
        }
        this.multimedia.add(multimedia);
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Aps getAps() {
        return aps;
    }

    public void setAps(Aps aps) {
        this.aps = aps;
    }

    public String getAutoBadge() {
        return autoBadge;
    }

    public void setAutoBadge(String autoBadge) {
        this.autoBadge = autoBadge;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public String getApnsCollapseId() {
        return apnsCollapseId;
    }

    public void setApnsCollapseId(String apnsCollapseId) {
        this.apnsCollapseId = apnsCollapseId;
    }

    @Override
    public String toString() {
        return "IosDTO{" +
                "type='" + type + '\'' +
                ", aps=" + aps +
                ", autoBadge='" + autoBadge + '\'' +
                ", payload='" + payload + '\'' +
                ", multimedia=" + multimedia +
                ", apnsCollapseId='" + apnsCollapseId + '\'' +
                '}';
    }
}
