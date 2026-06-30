package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.dto.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * cid与deviceToken关系列表。
 */
public class CidDeviceTokenListDTO implements BaseReqDTO {

    private List<CidDeviceToken> dtList;

    public CidDeviceTokenListDTO add(CidDeviceToken cidDeviceToken) {
        if (dtList == null) {
            dtList = new ArrayList<CidDeviceToken>();
        }
        dtList.add(cidDeviceToken);
        return this;
    }

    public CidDeviceTokenListDTO add(String cid, String deviceToken) {
        return add(new CidDeviceToken(cid, deviceToken));
    }

    @Override
    public void check() throws ApiException {
    }

    public List<CidDeviceToken> getDtList() {
        return dtList;
    }

    public void setDtList(List<CidDeviceToken> dtList) {
        this.dtList = dtList;
    }

    @Override
    public String toString() {
        return "CidDeviceTokenListDTO{" +
                "dtList=" + dtList +
                '}';
    }

    public static class CidDeviceToken implements BaseDTO {
        private String cid;
        private String deviceToken;

        public CidDeviceToken() {
        }

        public CidDeviceToken(String cid, String deviceToken) {
            this.cid = cid;
            this.deviceToken = deviceToken;
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

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        @Override
        public String toString() {
            return "CidDeviceToken{" +
                    "cid='" + cid + '\'' +
                    ", deviceToken='" + deviceToken + '\'' +
                    '}';
        }
    }
}
