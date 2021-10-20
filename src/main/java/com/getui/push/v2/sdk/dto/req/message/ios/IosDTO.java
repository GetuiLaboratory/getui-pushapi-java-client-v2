package com.getui.push.v2.sdk.dto.req.message.ios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * create by getui on 2020/7/20
 *
 * @author getui
 */
public class IosDTO extends HashMap<String, Object> {
    /**
     * voip：voip语音推送，notify：apns通知消息
     */
    private final String type = "type";

    /**
     * 推送通知消息内容
     */
    private final String aps = "aps";
    /**
     * 用于计算icon上显示的数字，还可以实现显示数字的自动增减，如“+1”、 “-1”、 “1” 等，计算结果将覆盖badge
     */
    private final String autoBadge = "auto_badge";
    /**
     * 增加自定义的数据
     */
    private final String payload = "payload";
    /**
     * 多媒体设置
     */
    private final String multimedia = "multimedia";

    /**
     * 使用相同的apns-collapse-id可以覆盖之前的消息
     */
    private final String apnsCollapseId = "apns-collapse-id";

    public IosDTO addMultimedia(Multimedia multimedia) {
        List<Multimedia> multimediaList = getMultimedia();
        if (multimedia == null) {
            return this;
        }
        if (multimediaList == null) {
            multimediaList = new ArrayList<Multimedia>();
            setMultimedia(multimediaList);
        }
        multimediaList.add(multimedia);
        return this;
    }

    public String getType() {
        return (String) super.get(this.type);
    }

    public void setType(String type) {
        super.put(this.type, type);
    }

    public Aps getAps() {
        return (Aps) super.get(this.aps);
    }

    public void setAps(Aps aps) {
        super.put(this.aps, aps);
    }

    public String getAutoBadge() {
        return (String) super.get(this.autoBadge);
    }

    public void setAutoBadge(String autoBadge) {
        super.put(this.autoBadge, autoBadge);
    }

    public String getPayload() {
        return (String) super.get(this.payload);
    }

    public void setPayload(String payload) {
        super.put(this.payload, payload);
    }

    public List<Multimedia> getMultimedia() {
        return (List<Multimedia>) super.get(this.multimedia);
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        super.put(this.multimedia, multimedia);
    }

    public String getApnsCollapseId() {
        return (String) super.get(this.apnsCollapseId);
    }

    public void setApnsCollapseId(String apnsCollapseId) {
        super.put(this.apnsCollapseId, apnsCollapseId);
    }

    @Override
    public Object put(String key, Object value) {
        return super.put(key, value);
    }

}
