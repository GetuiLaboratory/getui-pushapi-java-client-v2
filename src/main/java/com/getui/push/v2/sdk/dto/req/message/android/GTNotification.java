package com.getui.push.v2.sdk.dto.req.message.android;

import com.getui.push.v2.sdk.dto.CommonEnum;
import com.google.gson.annotations.SerializedName;

/**
 * @author getui
 */
public class GTNotification {
    /**
     * 第三方厂商通知标题，长度 ≤ 50
     */
    private String title;
    /**
     * 第三方厂商通知内容，长度 ≤ 256
     */
    private String body;
    @SerializedName("big_text")
    private String bigText;
    @SerializedName("big_image")
    private String bigImage;
    private String logo;
    @SerializedName("logo_url")
    private String logoUrl;
    @SerializedName("channel_id")
    private String channelId;

    @SerializedName("channel_name")
    private String channelName;

    @SerializedName("channel_level")
    private String channelLevel;

    /**
     * 鸿蒙通知渠道类型
     */
    @SerializedName("slot_type")
    private String slotType;
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
     * 点击通知打开应用特定页面，长度 ≤ 4096;
     * 示例：intent:#Intent;component=你的包名/你要打开的 activity 全路径;S.parm1=value1;S.parm2=value2;end
     */
    private String intent;

    /**
     * 鸿蒙平台点击动作 <br><br/>
     * 示例：{"deviceId":"","bundleName":"com.getui.push","abilityName":"TestAbility","uri":"https://www.test.com:8080/push/test","action":"com.test.action","parameters":{"name":"Getui","age":12}}
     */
    private String want;

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

    /**
     * 自定义铃声，请填写文件名，不包含后缀名(需要在客户端开发时嵌入)，个推通道下发有效
     * 客户端SDK最低要求 2.14.0.0
     */
    @SerializedName("ring_name")
    private String ringName;
    /**
     * 角标, 必须大于0, 个推通道下发有效
     * 此属性目前仅针对华为 EMUI 4.1 及以上设备有效
     * 角标数字数据会和之前角标数字进行叠加；
     * 举例：角标数字配置1，应用之前角标数为2，发送此角标消息后，应用角标数显示为3。
     * 客户端SDK最低要求 2.14.0.0
     */
    @SerializedName("badge_add_num")
    private String badgeAddNum;

    /**
     * 消息折叠分组，设置成相同thread_id的消息会被折叠（仅支持个推渠道下发的安卓消息）。
     * 目前与iOS的thread_id设置无关，安卓和iOS需要分别设置。
     */
    @SerializedName("thread_id")
    private String threadId;

    /**
     * 消息重弹次数，0代表不重弹，若要设置消息重弹则必须大于1，上限3次。
     */
    @SerializedName("redisplay_freq")
    private Integer redisplayFreq;

    /**
     * 消息重弹间隔（单位小时），仅当重弹次数大于0时才生效，代表每次消息重弹之间的最小间隔。
     * 由于重弹依赖SDK登录动作，因此实际的重弹间隔可能会大于设置的最小间隔。
     * 0代表登录即弹，大于0即忽略在最小间隔内的登录重弹动作，上限为24小时
     */
    @SerializedName("redisplay_duration")
    private Integer redisplayDuration;

    /**
     * 消息分类
     */
    private String category;

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

    public String getBigText() {
        return bigText;
    }

    public void setBigText(String bigText) {
        this.bigText = bigText;
    }

    public String getBigImage() {
        return bigImage;
    }

    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelLevel() {
        return channelLevel;
    }

    public void setChannelLevel(String channelLevel) {
        this.channelLevel = channelLevel;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
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

    public String getWant() {
        return want;
    }

    public void setWant(String want) {
        this.want = want;
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

    public String getRingName() {
        return ringName;
    }

    public void setRingName(String ringName) {
        this.ringName = ringName;
    }

    public String getBadgeAddNum() {
        return badgeAddNum;
    }

    public void setBadgeAddNum(String badgeAddNum) {
        this.badgeAddNum = badgeAddNum;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public Integer getRedisplayFreq() {
        return redisplayFreq;
    }

    public void setRedisplayFreq(Integer redisplayFreq) {
        this.redisplayFreq = redisplayFreq;
    }

    public Integer getRedisplayDuration() {
        return redisplayDuration;
    }

    public void setRedisplayDuration(Integer redisplayDuration) {
        this.redisplayDuration = redisplayDuration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "GTNotification{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", bigText='" + bigText + '\'' +
                ", bigImage='" + bigImage + '\'' +
                ", logo='" + logo + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channelLevel='" + channelLevel + '\'' +
                ", slotType='" + slotType + '\'' +
                ", clickType='" + clickType + '\'' +
                ", intent='" + intent + '\'' +
                ", want='" + want + '\'' +
                ", url='" + url + '\'' +
                ", payload='" + payload + '\'' +
                ", notifyId='" + notifyId + '\'' +
                ", ringName='" + ringName + '\'' +
                ", badgeAddNum='" + badgeAddNum + '\'' +
                ", threadId='" + threadId + '\'' +
                ", redisplayFreq=" + redisplayFreq +
                ", redisplayDuration=" + redisplayDuration +
                ", category='" + category + '\'' +
                '}';
    }
}
