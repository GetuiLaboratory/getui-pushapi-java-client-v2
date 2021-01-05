package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.anno.method.GtGet;
import com.getui.push.v2.sdk.anno.param.GtPathParam;
import com.getui.push.v2.sdk.anno.param.GtQueryParam;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.res.statistic.StatisticDTO;
import com.getui.push.v2.sdk.dto.res.statistic.UserStatisticDTO;

import java.util.Map;
import java.util.Set;

/**
 * 统计API
 * create by getui on 2020/6/7
 *
 * @author getui
 */
public interface StatisticApi {

    /**
     * 获取推送结果
     *
     * @param taskIds
     * @return
     */
    @GtGet(uri = "/report/push/task/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByTaskIds(@GtPathParam Set<String> taskIds);

    /**
     * 获取推送结果
     *
     * @param taskIds
     * @param actionIds
     * @return
     */
    @GtGet(uri = "/report/push/task/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByTaskIdsAndActionIds(@GtPathParam Set<String> taskIds, @GtQueryParam(name = "actionIdList") Set<String> actionIds);

    /**
     * 根据组名查询报表
     *
     * @param groupName
     * @return
     */
    @GtGet(uri = "/report/push/task_group/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByGroupName(@GtPathParam String groupName);

    /**
     * 获取单日推送数据
     *
     * @param date formatted as yyyy-MM-dd
     * @return
     */
    @GtGet(uri = "/report/push/date/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByDate(@GtPathParam String date);

    /**
     * 获取单日用户数据
     *
     * @param date formatted as yyyy-MM-dd
     * @return
     */
    @GtGet(uri = "/report/user/date/")
    ApiResult<Map<String, Map<String, UserStatisticDTO>>> queryUserDataByDate(@GtPathParam String date);

    /**
     * 获取24小时在线用户数
     *
     * @return
     */
    @GtGet(uri = "/report/online_user")
    ApiResult<Map<String, Map<String, Integer>>> queryOnlineUserData();

}
