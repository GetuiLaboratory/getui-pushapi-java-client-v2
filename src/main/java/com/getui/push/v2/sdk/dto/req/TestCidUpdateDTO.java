package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;

/**
 * 修改测试cid备注的请求参数。
 */
public class TestCidUpdateDTO implements BaseReqDTO {

    private String cid;
    private String remark;

    public TestCidUpdateDTO() {
    }

    public TestCidUpdateDTO(String cid, String remark) {
        this.cid = cid;
        this.remark = remark;
    }

    @Override
    public void check() throws ApiException {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "TestCidUpdateDTO{" +
                "cid='" + cid + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
