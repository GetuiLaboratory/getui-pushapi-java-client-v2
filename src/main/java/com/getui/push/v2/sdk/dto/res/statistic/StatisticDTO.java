package com.getui.push.v2.sdk.dto.res.statistic;

import com.google.gson.annotations.SerializedName;

/**
 * @author getui
 */
public class StatisticDTO {

    /**
     * 可下发数
     */
    @SerializedName("msg_num")
    private Integer msgNum;
    /**
     * 下发数
     */
    @SerializedName("target_num")
    private Integer targetNum;
    /**
     * 到达数
     */
    @SerializedName("receive_num")
    private Integer receiveNum;
    /**
     * 展示数
     */
    @SerializedName("display_num")
    private Integer displayNum;
    /**
     * 点击数
     */
    @SerializedName("click_num")
    private Integer clickNum;

    public Integer getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(Integer msgNum) {
        this.msgNum = msgNum;
    }

    public Integer getTargetNum() {
        return targetNum;
    }

    public void setTargetNum(Integer targetNum) {
        this.targetNum = targetNum;
    }

    public Integer getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(Integer receiveNum) {
        this.receiveNum = receiveNum;
    }

    public Integer getDisplayNum() {
        return displayNum;
    }

    public void setDisplayNum(Integer displayNum) {
        this.displayNum = displayNum;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    @Override
    public String toString() {
        return "StatisticDTO{" +
                "msgNum=" + msgNum +
                ", targetNum=" + targetNum +
                ", receiveNum=" + receiveNum +
                ", displayNum=" + displayNum +
                ", clickNum=" + clickNum +
                '}';
    }
}
