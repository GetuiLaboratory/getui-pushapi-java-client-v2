package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.common.Assert;

import java.util.HashSet;
import java.util.Set;

/**
 * create by getui on 2020/6/3
 *
 * @author getui
 */
public class QueryAliasDTO implements BaseReqDTO {

    /**
     * 路径参数cid
     */
    private Set<String> cidSet;

    public QueryAliasDTO add(String cid) {
        Assert.notBlank(cid, "cid");
        if (cidSet == null) {
            cidSet = new HashSet<String>();
        }
        cidSet.add(cid);
        return this;
    }

    @Override
    public void check() throws ApiException {

    }

    public Set<String> getCidSet() {
        return cidSet;
    }

    public void setCidSet(Set<String> cidSet) {
        this.cidSet = cidSet;
    }

    @Override
    public String toString() {
        return "QueryAliasDTO{" +
                "cidSet=" + cidSet +
                '}';
    }
}
