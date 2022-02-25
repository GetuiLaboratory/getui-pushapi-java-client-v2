package com.getui.push.v2.sdk.core;

import java.util.ArrayList;
import java.util.List;

/**
 * create by getui on 2020/9/28
 *
 * @author getui
 */
public interface Configs {
    int MAX_FAIL_CONTINUOUSLY = 3;

    String HEADER_DOMAIN_HASH_KEY = "domainHash";
    String HEADER_OPEN_STABLE_DOMAIN = "openStableDomain";
    String SDK_VERSION = "1.0.0.6";
    /**
     * 预置域名列表
     */
    List<String> URLS = new ArrayList<String>() {
        {
            this.add("https://restapi.getui.com/v2/");
            this.add("https://cncrestapi.getui.com/v2/");
            this.add("https://nzrestapi.getui.com/v2/");
        }
    };
}
