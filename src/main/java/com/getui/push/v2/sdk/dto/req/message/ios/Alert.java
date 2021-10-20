package com.getui.push.v2.sdk.dto.req.message.ios;

import java.util.HashMap;
import java.util.List;

/**
 * create by getui on 2020/7/20
 *
 * @author getui
 */
public class Alert extends HashMap<String, Object> {
    /**
     * 通知消息标题
     */
    private final String title = "title";
    /**
     * 通知消息内容
     */
    private final String body = "body";
    /**
     * (用于多语言支持)指定执行按钮所使用的Localizable.strings
     */
    private final String actionLocKey = "action-loc-key";
    /**
     * (用于多语言支持)指定Localizable.strings文件中相应的key
     */
    private final String locKey = "loc-key";
    /**
     * 如果loc-key中使用了占位符，则在loc-args中指定各参数
     */
    private final String locArgs = "loc-args";
    /**
     * 指定启动界面图片名
     */
    private final String launchImage = "launch-image";
    /**
     * (用于多语言支持）对于标题指定执行按钮所使用的Localizable.strings,仅支持iOS8.2以上版本
     */
    private final String titleLocKey = "title-loc-key";
    /**
     * 对于标题,如果loc-key中使用的占位符，则在loc-args中指定各参数,仅支持iOS8.2以上版本
     */
    private final String titleLocArgs = "title-loc-args";
    /**
     * 通知子标题,仅支持iOS8.2以上版本
     */
    private final String subtitle = "subtitle";
    /**
     * 当前本地化文件中的子标题字符串的关键字,仅支持iOS8.2以上版本
     */
    private final String subtitleLocKey = "subtitle-loc-key";
    /**
     * 当前本地化子标题内容中需要置换的变量参数 ,仅支持iOS8.2以上版本
     */
    private final String subtitleLocArgs = "subtitle-loc-args";

    public String getTitle() {
        return (String) super.get(this.title);
    }

    public void setTitle(String title) {
        super.put(this.title, title);
    }

    public String getBody() {
        return (String) super.get(this.body);
    }

    public void setBody(String body) {
        super.put(this.body, body);
    }

    public String getActionLocKey() {
        return (String) super.get(this.actionLocKey);
    }

    public void setActionLocKey(String actionLocKey) {
        super.put(this.actionLocKey, actionLocKey);
    }

    public String getLocKey() {
        return (String) super.get(this.locKey);
    }

    public void setLocKey(String locKey) {
        super.put(this.locKey, locKey);
    }

    public List<String> getLocArgs() {
        return (List<String>) super.get(this.locArgs);
    }

    public void setLocArgs(List<String> locArgs) {
        super.put(this.locArgs, locArgs);
    }

    public String getLaunchImage() {
        return (String) super.get(this.launchImage);
    }

    public void setLaunchImage(String launchImage) {
        super.put(this.launchImage, launchImage);
    }

    public String getTitleLocKey() {
        return (String) super.get(this.titleLocKey);
    }

    public void setTitleLocKey(String titleLocKey) {
        super.put(this.titleLocKey, titleLocKey);
    }

    public List<String> getTitleLocArgs() {
        return (List<String>) super.get(this.titleLocArgs);
    }

    public void setTitleLocArgs(List<String> titleLocArgs) {
        super.put(this.titleLocArgs, titleLocArgs);
    }

    public String getSubtitle() {
        return (String) super.get(this.subtitle);
    }

    public void setSubtitle(String subtitle) {
        super.put(this.subtitle, subtitle);
    }

    public String getSubtitleLocKey() {
        return (String) super.get(this.subtitleLocKey);
    }

    public void setSubtitleLocKey(String subtitleLocKey) {
        super.put(this.subtitleLocKey, subtitleLocKey);
    }

    public List<String> getSubtitleLocArgs() {
        return (List<String>) super.get(this.subtitleLocArgs);
    }

    public void setSubtitleLocArgs(List<String> subtitleLocArgs) {
        super.put(this.subtitleLocArgs, subtitleLocArgs);
    }
}
