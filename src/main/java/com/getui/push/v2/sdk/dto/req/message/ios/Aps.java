package com.getui.push.v2.sdk.dto.req.message.ios;

import java.util.HashMap;
import java.util.Map;

/**
 * create by getui on 2020/7/20
 *
 * @author getui
 */
public class Aps extends HashMap<String, Object> {
    /**
     * 通知消息
     */
    private final String alert = "alert";
    /**
     * 推送直接带有透传数据，content-available=1表示静默推送，静默推送时不需要填写其他参数，详细参数填写见示例，苹果建议1小时最多推送3条静默消息
     */
    private final String contentAvailable = "content-available";
    /**
     * 通知铃声文件名，无声设置为“com.gexin.ios.silence”
     */
    private final String sound = "sound";
    /**
     * 在客户端通知栏触发特定的action和button显示
     */
    private final String category = "category";

    /**
     * ios的远程通知通过该属性对通知进行分组，仅支持iOS 12.0以上版本
     */
    private final String threadId = "thread-id";

    /**
     * type为liveactivity时必填，当前时间，秒级10位时间戳
     */
    private final String timestamp = "timestamp";

    /**
     * 灵动岛推送事件，update：更新灵动岛，end：关闭灵动岛
     */
    private final String event = "event";

    /**
     * 实时活动消失时间，秒级10位时间戳，event为end，且需要按时关闭实时活动时填写
     */
    private final String dismissalDate = "dismissal-date";

    /**
     * 灵动岛推送透传参数，Json内的kv由业务方自定义，客户APP拿到值后自行解析
     */
    private final String contentState = "content-state";

    public Alert getAlert() {
        return (Alert) super.get(this.alert);
    }

    public void setAlert(Alert alert) {
        super.put(this.alert, alert);
    }

    public Integer getContentAvailable() {
        return (Integer) super.get(this.contentAvailable);
    }

    public void setContentAvailable(Integer contentAvailable) {
        super.put(this.contentAvailable, contentAvailable);
    }

    public String getSound() {
        return (String) super.get(this.sound);
    }

    public void setSound(String sound) {
        super.put(this.sound, sound);
    }

    public String getCategory() {
        return (String) super.get(this.category);
    }

    public void setCategory(String category) {
        super.put(this.category, category);
    }

    public String getThreadId() {
        return (String) super.get(this.threadId);
    }

    public void setThreadId(String threadId) {
        super.put(this.threadId, threadId);
    }

    public Integer getTimestamp() {
        return (Integer) super.get(this.timestamp);
    }

    public void setTimestamp(Integer timestamp) {
        super.put(this.timestamp, timestamp);
    }

    public String getEvent() {
        return (String) super.get(this.event);
    }

    public void setEvent(String event) {
        super.put(this.event, event);
    }

    public Integer getDismissalDate() {
        return (Integer) super.get(this.dismissalDate);
    }

    public void setDismissalDate(Integer dismissalDate) {
        super.put(this.dismissalDate, dismissalDate);
    }

    public Map<String, Object> getContentState() {
        return (Map<String, Object>) super.get(this.contentState);
    }

    public void setContentState(Map<String, Object> contentState) {
        super.put(this.contentState, contentState);
    }
}
