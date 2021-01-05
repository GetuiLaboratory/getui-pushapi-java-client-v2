package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * create by getui on 2020/6/2
 *
 * @author getui
 */
public class AuthDTO implements BaseReqDTO {

    private String sign;
    private Long timestamp;
    private String appkey;

    /**
     * 生成签名
     *
     * @param appKey
     * @param masterSecret
     */
    public static AuthDTO build(String appKey, String masterSecret) {
        AuthDTO authDTO = new AuthDTO();
        authDTO.appkey = appKey;
        authDTO.timestamp = System.currentTimeMillis();
        authDTO.sign = sha256(appKey + authDTO.timestamp + masterSecret);
        return authDTO;
    }

    private static String sha256(String s) {
        try {
            return hex(MessageDigest.getInstance("SHA-256").digest(s.getBytes(Charset.forName("UTF-8"))));
        } catch (NoSuchAlgorithmException var3) {
            throw new AssertionError(var3);
        }
    }

    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String hex(byte[] data) {
        char[] result = new char[data.length * 2];
        int c = 0;
        byte[] var3 = data;
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            result[c++] = HEX_DIGITS[b >> 4 & 15];
            result[c++] = HEX_DIGITS[b & 15];
        }

        return new String(result);
    }


    @Override
    public void check() throws ApiException {
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    @Override
    public String toString() {
        return "AuthDTO{" +
                "sign='" + sign + '\'' +
                ", timestamp=" + timestamp +
                ", appkey='" + appkey + '\'' +
                '}';
    }
}
