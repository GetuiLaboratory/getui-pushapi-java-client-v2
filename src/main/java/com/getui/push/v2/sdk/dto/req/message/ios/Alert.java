package com.getui.push.v2.sdk.dto.req.message.ios;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * create by getui on 2020/7/20
 *
 * @author getui
 */
public class Alert {
    /**
     * 通知消息标题
     */
    private String title;
    /**
     * 通知消息内容
     */
    private String body;
    /**
     * (用于多语言支持)指定执行按钮所使用的Localizable.strings
     */
    @SerializedName("action-loc-key")
    private String actionLocKey;
    /**
     * (用于多语言支持)指定Localizable.strings文件中相应的key
     */
    @SerializedName("loc-key")
    private String locKey;
    /**
     * 如果loc-key中使用了占位符，则在loc-args中指定各参数
     */
    @SerializedName("loc-args")
    private List<String> locArgs;
    /**
     * 指定启动界面图片名
     */
    @SerializedName("launch-image")
    private String launchImage;
    /**
     * (用于多语言支持）对于标题指定执行按钮所使用的Localizable.strings,仅支持iOS8.2以上版本
     */
    @SerializedName("title-loc-key")
    private String titleLocKey;
    /**
     * 对于标题,如果loc-key中使用的占位符，则在loc-args中指定各参数,仅支持iOS8.2以上版本
     */
    @SerializedName("title-loc-args")
    private List<String> titleLocArgs;
    /**
     * 通知子标题,仅支持iOS8.2以上版本
     */
    private String subtitle;
    /**
     * 当前本地化文件中的子标题字符串的关键字,仅支持iOS8.2以上版本
     */
    @SerializedName("subtitle-loc-key")
    private String subtitleLocKey;
    /**
     * 当前本地化子标题内容中需要置换的变量参数 ,仅支持iOS8.2以上版本
     */
    @SerializedName("subtitle-loc-args")
    private List<String> subtitleLocArgs;

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

    public String getActionLocKey() {
        return actionLocKey;
    }

    public void setActionLocKey(String actionLocKey) {
        this.actionLocKey = actionLocKey;
    }

    public String getLocKey() {
        return locKey;
    }

    public void setLocKey(String locKey) {
        this.locKey = locKey;
    }

    public List<String> getLocArgs() {
        return locArgs;
    }

    public void setLocArgs(List<String> locArgs) {
        this.locArgs = locArgs;
    }

    public String getLaunchImage() {
        return launchImage;
    }

    public void setLaunchImage(String launchImage) {
        this.launchImage = launchImage;
    }

    public String getTitleLocKey() {
        return titleLocKey;
    }

    public void setTitleLocKey(String titleLocKey) {
        this.titleLocKey = titleLocKey;
    }

    public List<String> getTitleLocArgs() {
        return titleLocArgs;
    }

    public void setTitleLocArgs(List<String> titleLocArgs) {
        this.titleLocArgs = titleLocArgs;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubtitleLocKey() {
        return subtitleLocKey;
    }

    public void setSubtitleLocKey(String subtitleLocKey) {
        this.subtitleLocKey = subtitleLocKey;
    }

    public List<String> getSubtitleLocArgs() {
        return subtitleLocArgs;
    }

    public void setSubtitleLocArgs(List<String> subtitleLocArgs) {
        this.subtitleLocArgs = subtitleLocArgs;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", actionLocKey='" + actionLocKey + '\'' +
                ", locKey='" + locKey + '\'' +
                ", locArgs=" + locArgs +
                ", launchImage='" + launchImage + '\'' +
                ", titleLocKey='" + titleLocKey + '\'' +
                ", titleLocArgs=" + titleLocArgs +
                ", subtitle='" + subtitle + '\'' +
                ", subtitleLocKey='" + subtitleLocKey + '\'' +
                ", subtitleLocArgs=" + subtitleLocArgs +
                '}';
    }
}
