package com.getui.push.v2.sdk.dto.res;

/**
 * create by getui on 2020/6/2
 *
 * @author getui
 */
public class TokenDTO {
    /**
     * token有效期
     */
    public final static int EXPIRE_INTERVAL = 60 * 60 * 1000;

    private String token;
    private Long expireTime;

    /**
     * 校验token是否过期
     *
     * @return true表示过期
     */
    public boolean expired() {
        if (expireTime == null) {
            return true;
        }
        if (expireTime - System.currentTimeMillis() < EXPIRE_INTERVAL) {
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    public boolean isNew() {
        if (expireTime == null) {
            return false;
        }
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "TokenDTO{" +
                "token='" + token + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }
}
