package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.anno.method.GtGet;
import com.getui.push.v2.sdk.anno.param.GtPathParam;
import com.getui.push.v2.sdk.anno.param.GtQueryParam;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.res.PushCountDTO;
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
     * @param taskIds 任务id集合
     * @return 推送结果（不含自定义事件）
     */
    @GtGet(uri = "/report/push/task/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByTaskIds(@GtPathParam Set<String> taskIds);

    /**
     * 获取推送结果
     *
     * @param taskIds   任务id集合
     * @param actionIds 自定义事件id集合
     * @return 推送结果（含自定义事件）
     */
    @GtGet(uri = "/report/push/task/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByTaskIdsAndActionIds(@GtPathParam Set<String> taskIds,
                                                                                           @GtQueryParam(name = "actionIdList") Set<String> actionIds);

    /**
     * 获取推送结果
     *
     * @param taskIds          任务id集合
     * @param actionIds        自定义事件id列表
     * @param needGetuiByBrand 是否需要个推品牌报表
     * @return 推送结果（含自定义事件）
     */
    @GtGet(uri = "/report/push/task/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByTaskIdsAndActionIds(@GtPathParam Set<String> taskIds,
                                                                                           @GtQueryParam(name = "actionIdList") Set<String> actionIds,
                                                                                           @GtQueryParam(name = "needGetuiByBrand") Boolean needGetuiByBrand);

    /**
     * 根据组名查询报表
     *
     * @param groupName 任务组名
     * @return 任务组名对应的推送结果
     */
    @GtGet(uri = "/report/push/task_group/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByGroupName(@GtPathParam String groupName);


    /**
     * 根据组名查询报表
     *
     * @param groupName        任务组名
     * @param needGetuiByBrand 是否需要个推品牌报表
     * @param startDate        查询报表开始日期,格式: yyyy-MM-dd
     * @param endDate          查询报表结束日期,格式: yyyy-MM-dd
     * @return 任务组名对应的推送结果
     */
    @GtGet(uri = "/report/push/task_group/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByGroupName(@GtPathParam String groupName,
                                                                                 @GtQueryParam(name = "needGetuiByBrand") Boolean needGetuiByBrand,
                                                                                 @GtQueryParam(name = "startDate") String startDate,
                                                                                 @GtQueryParam(name = "endDate") String endDate);

    /**
     * 获取单日推送数据
     *
     * @param date 日期，格式: yyyy-MM-dd
     * @return 某个应用单日的推送数据(推送数据包括 ： 下发数 ， 接收数 、 展示数 、 点击数)
     */
    @GtGet(uri = "/report/push/date/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByDate(@GtPathParam String date);


    /**
     * 获取单日推送数据
     *
     * @param date             日期，格式: yyyy-MM-dd
     * @param needGetuiByBrand 是否需要个推品牌报表
     * @return 某个应用单日的推送数据(推送数据包括 ： 下发数 ， 接收数 、 展示数 、 点击数)
     */
    @GtGet(uri = "/report/push/date/")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushResultByDate(@GtPathParam String date,
                                                                            @GtQueryParam(name = "needGetuiByBrand") Boolean needGetuiByBrand);

    /**
     * 获取单日用户数据
     *
     * @param date 日期，格式: yyyy-MM-dd
     * @return 某个应用单日的用户数据(用户数据包括 : 新增用户数 ， 累计注册用户总数 ， 在线峰值 ， 日联网用户数)
     */
    @GtGet(uri = "/report/user/date/")
    ApiResult<Map<String, Map<String, UserStatisticDTO>>> queryUserDataByDate(@GtPathParam String date);

    /**
     * 获取24小时在线用户数
     *
     * @return 当前时间一天内的在线用户数
     */
    @GtGet(uri = "/report/online_user")
    ApiResult<Map<String, Map<String, Integer>>> queryOnlineUserData();


    /**
     * 查询推送量
     *
     * @return 应用当日可推送量和推送余量
     */
    @GtGet(uri = "/report/push/count")
    ApiResult<Map<String, Map<String, PushCountDTO>>> queryPushCountData();

    /**
     * 获取推送实时结果
     *
     * @param taskIds 任务id集合
     * @return 推送实时结果，下发数，接收数、展示数、点击数和消息折损详情等结果。
     */
    @GtGet(uri = "/report/push/task/${taskid}/detail")
    ApiResult<Map<String, Map<String, StatisticDTO>>> queryPushTaskDetailData(@GtPathParam Set<String> taskIds);

}
