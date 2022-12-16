package com.getui.push.v2.sdk.dto.res;

import com.google.gson.annotations.SerializedName;

/**
 * @author getui
 */
public class PushCountDTO {

    /**
     * 单日可推送总量
     */
    @SerializedName("total_num")
    private Integer totalNum;

    /**
     * 单日可推送剩余量
     */
    @SerializedName("remain_num")
    private Integer remainNum;

    /**
     * 单日可推送请求量
     * 仅vv返回该字段
     */
    @SerializedName("push_num")
    private Integer pushNum;

    /**
     * 是否被限量
     * 当日可推送总量使用完时，该字段更新true
     */
    @SerializedName("limit")
    private Boolean limit;


    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public Integer getPushNum() {
        return pushNum;
    }

    public void setPushNum(Integer pushNum) {
        this.pushNum = pushNum;
    }

    public Boolean getLimit() {
        return limit;
    }

    public void setLimit(Boolean limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PushCountDTO{" +
                "totalNum=" + totalNum +
                ", remainNum=" + remainNum +
                ", pushNum=" + pushNum +
                ", limit=" + limit +
                '}';
    }
}