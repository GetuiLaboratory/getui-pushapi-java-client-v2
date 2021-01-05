package com.getui.push.v2.sdk.core.domain;

/**
 * create by getui on 2020/7/21
 *
 * @author getui
 */
public interface IDomainCheck {

    /**
     * 域名检测
     *
     * @param url
     * @return true表示成功，false表示失败，成功数越多，表示域名可用性越高
     */
    boolean check(String url);

}
