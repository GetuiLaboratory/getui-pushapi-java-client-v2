package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.dto.CommonEnum;

import java.util.ArrayList;
import java.util.List;

public class CidByTagDTO {
    private String cid;
    /**
     * 标签列表
     */
    private List<String> tag;
    /**
     * @see CommonEnum.CidByTagOpTypeEnum
     * 标签条件过滤类型
     */
    private Integer opType = CommonEnum.CidByTagOpTypeEnum.OR.type;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public void addTag(String tag) {
        if (this.tag == null) {
            this.tag = new ArrayList<>();
        }
        this.tag.add(tag);
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(CommonEnum.CidByTagOpTypeEnum opType) {
        this.opType = opType.type;
    }

    @Override
    public String toString() {
        return "CidByTagDTO{" +
                "cid='" + cid + '\'' +
                ", tag=" + tag +
                ", opType=" + opType +
                '}';
    }
}