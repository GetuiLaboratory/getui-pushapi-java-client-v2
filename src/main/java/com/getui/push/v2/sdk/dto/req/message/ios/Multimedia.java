package com.getui.push.v2.sdk.dto.req.message.ios;

import com.google.gson.annotations.SerializedName;

/**
 * create by getui on 2020/7/20
 *
 * @author getui
 */
public class Multimedia {
    /**
     * 多媒体资源地址
     */
    private String url;
    /**
     * 资源类型（1.图片，2.音频，3.视频）
     */
    private Integer type;
    /**
     * 是否只在wifi环境下加载，如果设置成true,但未使用wifi时，会展示成普通通知
     */
    @SerializedName("only_wifi")
    private boolean onlyWifi;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isOnlyWifi() {
        return onlyWifi;
    }

    public void setOnlyWifi(boolean onlyWifi) {
        this.onlyWifi = onlyWifi;
    }

    @Override
    public String toString() {
        return "Multimedia{" +
                "url='" + url + '\'' +
                ", type=" + type +
                ", onlyWifi=" + onlyWifi +
                '}';
    }
}
