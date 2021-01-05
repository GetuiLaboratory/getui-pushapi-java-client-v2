package com.getui.push.v2.sdk.dto.res;

import java.util.List;

/**
 * create by getui on 2020/6/4
 *
 * @author getui
 */
public class QueryCidResDTO {
    private List<String> cid;

    public List<String> getCid() {
        return cid;
    }

    public void setCid(List<String> cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "QueryCidResDTO{" +
                "cid=" + cid +
                '}';
    }
}
