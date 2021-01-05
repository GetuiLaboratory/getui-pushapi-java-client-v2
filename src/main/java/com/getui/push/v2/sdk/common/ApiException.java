package com.getui.push.v2.sdk.common;

/**
 * 此异常类不会打印堆栈
 * create by getui on 2020/4/25
 *
 * @author getui
 */
public class ApiException extends RuntimeException {

    private int code = 500;

    private String message;

    public ApiException(String message) {
        super(message, null);
        this.message = message;
    }

    public ApiException(String message, boolean writableStackTrace) {
        super(message, null);
        this.message = message;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public ApiException(String message, int code) {
        super(message + "::" + code, null);
        this.code = code;
        this.message = message;
    }

    public ApiException(String message, int code, boolean writableStackTrace) {
        super(message + "::" + code, null);
        this.code = code;
        this.message = message;
    }

    public ApiException(String message, int code, Throwable throwable) {
        super(message + "::" + code, throwable);
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
}
