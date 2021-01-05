package com.getui.push.v2.sdk.dto.req.message.android;

public class AndroidDTO {
    /**
     * android厂商通道推送消息内容
     */
    private Ups ups;

    public Ups getUps() {
        return ups;
    }

    public AndroidDTO setUps(Ups ups) {
        this.ups = ups;
        return this;
    }

    @Override
    public String toString() {
        return "AndroidDTO{" +
                "ups=" + ups +
                '}';
    }
}
