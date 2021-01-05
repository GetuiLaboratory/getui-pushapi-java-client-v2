package com.getui.push.v2.sdk.dto.res.statistic;

import com.google.gson.annotations.SerializedName;

/**
 * @author getui
 */
public class UserStatisticDTO {
    @SerializedName("accumulative_num")
    private Integer accumulativeNum;
    @SerializedName("register_num")
    private Integer registerNum;
    @SerializedName("active_num")
    private Integer activeNum;
    @SerializedName("online_num")
    private Integer onlineNum;

    public Integer getAccumulativeNum() {
        return accumulativeNum;
    }

    public void setAccumulativeNum(Integer accumulativeNum) {
        this.accumulativeNum = accumulativeNum;
    }

    public Integer getRegisterNum() {
        return registerNum;
    }

    public void setRegisterNum(Integer registerNum) {
        this.registerNum = registerNum;
    }

    public Integer getActiveNum() {
        return activeNum;
    }

    public void setActiveNum(Integer activeNum) {
        this.activeNum = activeNum;
    }

    public Integer getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(Integer onlineNum) {
        this.onlineNum = onlineNum;
    }

    @Override
    public String toString() {
        return "UserStatisticDTO{" +
                "accumulativeNum=" + accumulativeNum +
                ", registerNum=" + registerNum +
                ", activeNum=" + activeNum +
                ", onlineNum=" + onlineNum +
                '}';
    }
}
