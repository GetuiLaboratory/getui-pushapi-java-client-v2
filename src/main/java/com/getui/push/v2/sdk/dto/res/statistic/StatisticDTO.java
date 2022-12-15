package com.getui.push.v2.sdk.dto.res.statistic;

import java.util.HashMap;

/**
 * @author getui
 */
public class StatisticDTO extends HashMap<String, Object> {

    /**
     * 可下发数
     */
    public Integer getMsgNum() {
        return (Integer) this.get("msg_num");
    }

    public void setMsgNum(Integer msgNum) {
        this.put("msg_num", msgNum);
    }

    /**
     * 下发数
     */
    public Integer getTargetNum() {
        return (Integer) this.get("target_num");
    }

    public void setTargetNum(Integer targetNum) {
        this.put("target_num", targetNum);
    }

    /**
     * 到达数
     */
    public Integer getReceiveNum() {
        return (Integer) this.get("receive_num");
    }

    public void setReceiveNum(Integer receiveNum) {
        this.put("receive_num", receiveNum);
    }

    /**
     * 展示数
     */
    public Integer getDisplayNum() {
        return (Integer) this.get("display_num");
    }

    public void setDisplayNum(Integer displayNum) {
        this.put("display_num", displayNum);
    }

    /**
     * 点击数
     */
    public Integer getClickNum() {
        return (Integer) this.get("click_num");
    }

    public void setClickNum(Integer clickNum) {
        this.put("click_num", clickNum);
    }

    @Override
    public String toString() {
        return "StatisticDTO" + super.toString();
    }
}