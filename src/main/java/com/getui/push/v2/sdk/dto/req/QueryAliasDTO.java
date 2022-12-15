package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;

/**
 * create by getui on 2020/6/3
 *
 * @author getui
 */
public class QueryAliasDTO implements BaseReqDTO {

    /**
     * 路径参数cid
     */
    private String cid;

    @Override
    public void check() throws ApiException {

    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "QueryAliasDTO{" +
                "cid='" + cid + '\'' +
                '}';
    }
}
