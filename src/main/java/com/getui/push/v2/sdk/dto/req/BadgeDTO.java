package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;

/**
 * create by getui on 2020/6/4
 *
 * @author getui
 */
public class BadgeDTO implements BaseReqDTO {

    private String badge;

    @Override
    public void check() throws ApiException {
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    @Override
    public String toString() {
        return "BadgeDTO{" +
                "badge='" + badge + '\'' +
                '}';
    }
}
