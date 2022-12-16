package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.ApiHelper;
import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.api.env.ApiContext;
import com.getui.push.v2.sdk.api.util.Utils;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.res.PushCountDTO;
import com.getui.push.v2.sdk.dto.res.statistic.StatisticDTO;
import com.getui.push.v2.sdk.dto.res.statistic.UserStatisticDTO;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * create by getui on 2020/10/28
 *
 * @author getui
 */
public class StatisticApiTest {

    StatisticApi pushApi;

    @Before
    public void before() {
        final ApiContext apiContext = ApiContext.build();
        GtApiConfiguration apiConfiguration = apiContext.configuration;
        // apiConfiguration.setOpenAnalyseStableDomainSwitch(false);

        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
        pushApi = apiHelper.creatApi(StatisticApi.class);
    }

    @Test
    public void queryPushResultByTaskIds() {
        final ApiResult<Map<String, Map<String, StatisticDTO>>> result = pushApi.queryPushResultByTaskIds(
                Utils.newHashSet("taskId"));
        System.out.println(result);
    }

    @Test
    public void queryPushResultByTaskIdsAndActionIds() {
        final ApiResult<Map<String, Map<String, StatisticDTO>>> result = pushApi.queryPushResultByTaskIdsAndActionIds(Utils.newHashSet("taskId1",
                "taskId2", "taskId3"), Utils.newHashSet("actionId1", "actionId2"));
        System.out.println(result);
    }

    @Test
    public void queryPushResultByGroupName() {
        final ApiResult<Map<String, Map<String, StatisticDTO>>> result = pushApi.queryPushResultByGroupName("group1");
        System.out.println(result);
    }

    @Test
    public void queryPushResultByDate() {
        final ApiResult<Map<String, Map<String, StatisticDTO>>> result = pushApi.queryPushResultByDate("2021-01-01");
        System.out.println(result);
    }

    @Test
    public void queryUserDataByDate() {
        final ApiResult<Map<String, Map<String, UserStatisticDTO>>> result = pushApi.queryUserDataByDate("2021-01-01");
        System.out.println(result);
    }

    @Test
    public void queryOnlineUserData() {
        final ApiResult<Map<String, Map<String, Integer>>> result = pushApi.queryOnlineUserData();
        System.out.println(result);
    }

    @Test
    public void queryPushCountData() {
        final ApiResult<Map<String, Map<String, PushCountDTO>>> result = pushApi.queryPushCountData();
        System.out.println(result);
    }

    @Test
    public void queryPushTaskDetailData() {
        final ApiResult<Map<String, Map<String, StatisticDTO>>> result = pushApi.queryPushTaskDetailData(
                Sets.newHashSet("taskId1", "taskId2")
        );
        System.out.println(result);
    }

}