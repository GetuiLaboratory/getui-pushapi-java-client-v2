package com.getui.push.v2.sdk.dto.res;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 批量绑定或解绑cid和deviceToken的失败明细。
 */
public class BindDeviceTokenResDTO {

    @SerializedName("errorList")
    private List<Error> errorList;

    public List<Error> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Error> errorList) {
        this.errorList = errorList;
    }

    @Override
    public String toString() {
        return "BindDeviceTokenResDTO{" +
                "errorList=" + errorList +
                '}';
    }

    public static class Error {
        private String cid;
        private String deviceToken;
        private Integer errorCode;

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

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "cid='" + cid + '\'' +
                    ", deviceToken='" + deviceToken + '\'' +
                    ", errorCode=" + errorCode +
                    '}';
        }
    }
}
