package com.getui.push.v2.sdk.dto.req;

/**
 * 推送条件
 */
public class Settings {
    /**
     * 消息离线时间设置，单位毫秒，-1表示不设离线, -1 ～ 3 * 24 * 3600 * 1000之间
     */
    private Integer ttl;
    /**
     * 厂商通道策略
     */
    private Strategy strategy;
    /**
     * 推送速度
     */
    private Integer speed;
    /**
     * 定时推送时间，格式：毫秒时间戳
     */
    private Long scheduleTime;
    /**
     * 自定义回执字段，SVIP功能
     */
    private String customCallback;

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Long getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getCustomCallback() {
        return customCallback;
    }

    public void setCustomCallback(String customCallback) {
        this.customCallback = customCallback;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "ttl=" + ttl +
                ", strategy=" + strategy +
                ", speed=" + speed +
                ", scheduleTime=" + scheduleTime +
                ", customCallback=" + customCallback +
                '}';
    }
}
