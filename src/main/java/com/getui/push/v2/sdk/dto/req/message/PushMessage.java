package com.getui.push.v2.sdk.dto.req.message;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.dto.BaseDTO;
import com.getui.push.v2.sdk.dto.CommonEnum;
import com.getui.push.v2.sdk.dto.req.message.android.GTNotification;

/**
 * 个推推送消息参数
 * create by getui on 2020/6/3
 *
 * @author getui
 */
public class PushMessage implements BaseDTO {

    /**
     * 推送消息使用网络类型，0：不限制，1：仅wifi
     *
     * @see CommonEnum.NetworkTypeEnum
     */
    private Integer networkType;

    /**
     * 通知展示时间段，格式为毫秒时间戳段，两个时间的时间差必须大于10分钟，例如："1590547347000-1590633747000"
     */
    private String duration;

    /**
     * 个推通知消息内容，与{@link #transmission}、{@link #revoke} 三选一
     */
    private GTNotification notification;

    /**
     * 透传消息内容，与{@link #notification}、{@link #revoke} 三选一
     */
    private String transmission;

    /**
     * 撤回消息，撤回消息不能与{@link #notification}和{@link #transmission}并存
     */
    private RevokeBean revoke;

    @Override
    public void check() throws ApiException {
    }

    public Integer getNetworkType() {
        return networkType;
    }

    public void setNetworkType(Integer networkType) {
        this.networkType = networkType;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public GTNotification getNotification() {
        return notification;
    }

    public void setNotification(GTNotification notification) {
        this.notification = notification;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public RevokeBean getRevoke() {
        return revoke;
    }

    public void setRevoke(RevokeBean revoke) {
        this.revoke = revoke;
    }

    @Override
    public String toString() {
        return "PushMessage{" +
                "networkType=" + networkType +
                ", duration='" + duration + '\'' +
                ", notification=" + notification +
                ", transmission='" + transmission + '\'' +
                ", revoke=" + revoke +
                '}';
    }
}
