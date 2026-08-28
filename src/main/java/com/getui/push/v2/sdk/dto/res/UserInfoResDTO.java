package com.getui.push.v2.sdk.dto.res;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.Set;

/**
 * 用户信息查询结果。
 */
public class UserInfoResDTO {

    @SerializedName(value = "validCids", alternate = {"valid_cids"})
    private Map<String, UserInfo> validCids;

    @SerializedName(value = "invalidCids", alternate = {"invalid_cids"})
    private Set<String> invalidCids;

    public Map<String, UserInfo> getValidCids() {
        return validCids;
    }

    public void setValidCids(Map<String, UserInfo> validCids) {
        this.validCids = validCids;
    }

    public Set<String> getInvalidCids() {
        return invalidCids;
    }

    public void setInvalidCids(Set<String> invalidCids) {
        this.invalidCids = invalidCids;
    }

    @Override
    public String toString() {
        return "UserInfoResDTO{" +
                "validCids=" + validCids +
                ", invalidCids=" + invalidCids +
                '}';
    }

    public static class UserInfo {
        private String clientAppId;
        private String packageName;
        private String deviceToken;
        private Integer phoneType;
        private String phoneModel;
        private Boolean notificationSwitch;
        private String createTime;
        private Integer loginFreq;
        private String brand;

        public String getClientAppId() {
            return clientAppId;
        }

        public void setClientAppId(String clientAppId) {
            this.clientAppId = clientAppId;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

        public Integer getPhoneType() {
            return phoneType;
        }

        public void setPhoneType(Integer phoneType) {
            this.phoneType = phoneType;
        }

        public String getPhoneModel() {
            return phoneModel;
        }

        public void setPhoneModel(String phoneModel) {
            this.phoneModel = phoneModel;
        }

        public Boolean getNotificationSwitch() {
            return notificationSwitch;
        }

        public void setNotificationSwitch(Boolean notificationSwitch) {
            this.notificationSwitch = notificationSwitch;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Integer getLoginFreq() {
            return loginFreq;
        }

        public void setLoginFreq(Integer loginFreq) {
            this.loginFreq = loginFreq;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "clientAppId='" + clientAppId + '\'' +
                    ", packageName='" + packageName + '\'' +
                    ", deviceToken='" + deviceToken + '\'' +
                    ", phoneType=" + phoneType +
                    ", phoneModel='" + phoneModel + '\'' +
                    ", notificationSwitch=" + notificationSwitch +
                    ", createTime='" + createTime + '\'' +
                    ", loginFreq=" + loginFreq +
                    ", brand='" + brand + '\'' +
                    '}';
        }
    }
}
