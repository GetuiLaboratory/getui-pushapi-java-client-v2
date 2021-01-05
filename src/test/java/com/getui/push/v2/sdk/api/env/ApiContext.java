package com.getui.push.v2.sdk.api.env;

import com.getui.push.v2.sdk.GtApiConfiguration;

public class ApiContext {

    public GtApiConfiguration configuration;
    public String cid;

    public static ApiContext build() {
        ApiContext context = new ApiContext();
        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
        context.configuration = apiConfiguration;

        apiConfiguration.setAppId("appId");
        apiConfiguration.setAppKey("appKey");
        apiConfiguration.setMasterSecret("masterSecret");
        // 接口调用前缀，请查看文档: 接口调用规范 -> 接口前缀, 可不填写appId
        apiConfiguration.setDomain("https://restapi.getui.com/v2/");
        context.cid = "CID";

        return context;
    }
}
