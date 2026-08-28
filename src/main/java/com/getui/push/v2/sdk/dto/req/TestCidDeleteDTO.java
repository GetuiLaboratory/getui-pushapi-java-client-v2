package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除测试cid的请求参数。
 */
public class TestCidDeleteDTO implements BaseReqDTO {

    private List<String> cidList;

    public TestCidDeleteDTO addCid(String cid) {
        if (cidList == null) {
            cidList = new ArrayList<String>();
        }
        cidList.add(cid);
        return this;
    }

    @Override
    public void check() throws ApiException {
    }

    public List<String> getCidList() {
        return cidList;
    }

    public void setCidList(List<String> cidList) {
        this.cidList = cidList;
    }

    @Override
    public String toString() {
        return "TestCidDeleteDTO{" +
                "cidList=" + cidList +
                '}';
    }
}
