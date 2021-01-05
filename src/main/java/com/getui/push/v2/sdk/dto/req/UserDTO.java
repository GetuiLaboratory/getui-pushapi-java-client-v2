package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.common.util.Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * create by getui on 2020/6/4
 *
 * @author getui
 */
public class UserDTO implements BaseReqDTO {

    private Set<String> cid;

    public static UserDTO build() {
        return new UserDTO();
    }

    /**
     * 添加cid
     *
     * @param cid
     * @return
     */
    public UserDTO addCid(String cid) {
        if (Utils.isEmpty(cid)) {
            return this;
        }
        if (this.cid == null) {
            this.cid = new HashSet<String>();
        }
        this.cid.add(cid);
        return this;
    }

    @Override
    public void check() throws ApiException {
    }

    public Set<String> getCid() {
        return cid;
    }

    public void setCid(Set<String> cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "cid=" + cid +
                '}';
    }
}
