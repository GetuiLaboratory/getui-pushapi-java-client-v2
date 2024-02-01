package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.ApiHelper;
import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.api.env.ApiContext;
import com.getui.push.v2.sdk.api.util.Utils;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.CommonEnum;
import com.getui.push.v2.sdk.dto.req.*;
import com.getui.push.v2.sdk.dto.res.AliasResDTO;
import com.getui.push.v2.sdk.dto.res.QueryCidResDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * create by getui on 2020/6/4
 *
 * @author getui
 */
public class UserApiTest {
    String cid = "";
    UserApi userApi;
    ApiContext apiContext = ApiContext.build();

    @Before
    public void setUp() {
        GtApiConfiguration apiConfiguration = apiContext.configuration;
        apiConfiguration.setMaxHttpTryTime(0);
        apiConfiguration.setOpenCheckHealthDataSwitch(true);
        apiConfiguration.setOpenAnalyseStableDomainSwitch(true);
        apiConfiguration.setSoTimeout(5000);
        apiConfiguration.setConnectTimeout(5000);
        // apiConfiguration.setProxyConfig(new GtHttpProxyConfig("proxy_ip", 80, "proxy_username", "proxy_password"));

        cid = apiContext.cid;

        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
        userApi = apiHelper.creatApi(UserApi.class);
    }

    @Test
    public void addBlackUser() {
        System.out.println(userApi.addBlackUser(Utils.newHashSet(cid)));
    }

    @Test
    public void removeBlackUser() {
        System.out.println(userApi.removeBlackUser(Utils.newHashSet(cid)));
    }

    @Test
    public void queryUserStatus() {
        System.out.println(userApi.queryUserStatus(Utils.newHashSet(cid)));
    }

    @Test
    public void setBadge() {
        BadgeDTO badgeDTO = new BadgeDTO();
        badgeDTO.setBadge("+1");
        System.out.println(userApi.setBadge(Utils.newHashSet(cid), badgeDTO));
    }

    @Test
    public void queryUser() {
        final Condition condition = new Condition();
        condition.setKey("region");
        condition.addValue("");
        condition.setOptType(CommonEnum.OptTypeEnum.TYPE_AND.type);
        System.out.println(userApi.queryUser(ConditionListDTO.build().addCondition(condition)));
        ;
    }

    @Test
    public void bindAlias() throws InterruptedException {
        while (true) {
            System.out.println("===============================================");
            CidAliasListDTO cidAliasListDTO = new CidAliasListDTO();
            cidAliasListDTO.add(new CidAliasListDTO.CidAlias(cid, "2221"));
            ApiResult<Void> apiResult = userApi.bindAlias(cidAliasListDTO);
            System.out.println(apiResult);
            Thread.sleep(1000);
            return;
        }
    }

    @Test
    public void queryAliasByCid() {
        QueryAliasDTO queryAliasDTO = new QueryAliasDTO();
        queryAliasDTO.setCid(cid);
        ApiResult<AliasResDTO> apiResult = userApi.queryAliasByCid(queryAliasDTO.getCid());
        System.out.println(apiResult);
    }

    @Test
    public void queryAliasByCidAndType() {
        QueryAliasDTO queryAliasDTO = new QueryAliasDTO();
        queryAliasDTO.setCid(cid);
        ApiResult<AliasResDTO> apiResult = userApi.queryAliasByCid(queryAliasDTO.getCid(),"t-2");
        System.out.println(apiResult);
    }

    @Test
    public void queryCidByAlias() {
        ApiResult<QueryCidResDTO> apiResult = userApi.queryCidByAlias("alias");
        System.out.println(apiResult);
    }

    @Test
    public void queryCidByAliasAndType() {
        ApiResult<QueryCidResDTO> apiResult = userApi.queryCidByAlias("2221","t-1");
        System.out.println(apiResult);
    }

    @Test
    public void batchUnboundAlias() {
        CidAliasListDTO cidAliasListDTO = new CidAliasListDTO();
        cidAliasListDTO.add(new CidAliasListDTO.CidAlias(cid, "alias"));
        ApiResult<Void> apiResult = userApi.batchUnbindAlias(cidAliasListDTO);
        System.out.println(apiResult);
    }

    @Test
    public void unboundAllAlias() {
        ApiResult<Void> apiResult = userApi.unbindAllAlias("alias");
        System.out.println(apiResult);
    }

    @Test
    public void unboundAllAliasWithType() {
        ApiResult<Void> apiResult = userApi.unbindAllAlias("2221","t-1");
        System.out.println(apiResult);
    }

    @Test
    public void userBindTags() {
        ApiResult<Void> apiResult = userApi.userBindTags(cid, TagDTO.build().addTag("tag1").addTag("tag2").addTag("tag3"));
        System.out.println(apiResult);
    }

    @Test
    public void usersBindTag() {
        ApiResult<Map<String, String>> apiResult = userApi.usersBindTag("tag", UserDTO.build().addCid(cid).addCid(cid));
        System.out.println(apiResult);
    }

    @Test
    public void deleteUsersTag() {
        ApiResult<Map<String, String>> apiResult = userApi.deleteUsersTag("tag", UserDTO.build().addCid(cid));
        System.out.println(apiResult);
    }

    @Test
    public void queryUserTags() {
        System.out.println(userApi.toString());
        ApiResult<Map<String, List<String>>> apiResult = userApi.queryUserTags(cid);
        System.out.println(apiResult);
    }
}