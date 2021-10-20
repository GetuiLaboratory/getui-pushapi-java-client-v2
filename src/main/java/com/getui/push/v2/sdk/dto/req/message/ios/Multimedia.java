package com.getui.push.v2.sdk.dto.req.message.ios;

import java.util.HashMap;

/**
 * create by getui on 2020/7/20
 *
 * @author getui
 */
public class Multimedia extends HashMap<String, Object> {
    /**
     * 多媒体资源地址
     */
    private final String url = "url";
    /**
     * 资源类型（1.图片，2.音频，3.视频）
     */
    private final String type = "type";
    /**
     * 是否只在wifi环境下加载，如果设置成true,但未使用wifi时，会展示成普通通知
     */
    private final String onlyWifi = "only_wifi";

    public String getUrl() {
        return (String) super.get(this.url);
    }

    public void setUrl(String url) {
        super.put(this.url, url);
    }

    public Integer getType() {
        return (Integer) super.get(this.type);
    }

    public void setType(Integer type) {
        super.put(this.type, type);
    }

    public boolean isOnlyWifi() {
        final Boolean isOnlyWifi = (Boolean) super.get(this.onlyWifi);
        return isOnlyWifi != null && isOnlyWifi;
    }

    public void setOnlyWifi(boolean onlyWifi) {
        super.put(this.onlyWifi, onlyWifi);
    }
}
