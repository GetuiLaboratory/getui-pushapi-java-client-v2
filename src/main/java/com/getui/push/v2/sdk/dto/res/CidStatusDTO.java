package com.getui.push.v2.sdk.dto.res;

/**
 * create by getui on 2020/7/29
 *
 * @author getui
 */
public class CidStatusDTO {

    private String lastLoginTime;
    private String status;

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CidStatusDTO{" +
                "lastLoginTime='" + lastLoginTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
