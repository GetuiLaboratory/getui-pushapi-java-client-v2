package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.dto.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加测试cid的请求参数。
 */
public class TestCidAddDTO implements BaseReqDTO {

    private List<TestCidItem> cidList;

    public TestCidAddDTO add(String cid, String remark) {
        return add(new TestCidItem(cid, remark));
    }

    public TestCidAddDTO add(TestCidItem item) {
        if (cidList == null) {
            cidList = new ArrayList<TestCidItem>();
        }
        cidList.add(item);
        return this;
    }

    @Override
    public void check() throws ApiException {
    }

    public List<TestCidItem> getCidList() {
        return cidList;
    }

    public void setCidList(List<TestCidItem> cidList) {
        this.cidList = cidList;
    }

    @Override
    public String toString() {
        return "TestCidAddDTO{" +
                "cidList=" + cidList +
                '}';
    }

    public static class TestCidItem implements BaseDTO {
        private String cid;
        private String remark;

        public TestCidItem() {
        }

        public TestCidItem(String cid, String remark) {
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
            return "TestCidItem{" +
                    "cid='" + cid + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }
}
