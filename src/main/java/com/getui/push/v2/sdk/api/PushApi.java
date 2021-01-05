package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.anno.method.GtDelete;
import com.getui.push.v2.sdk.anno.method.GtGet;
import com.getui.push.v2.sdk.anno.method.GtPost;
import com.getui.push.v2.sdk.anno.param.GtBodyParam;
import com.getui.push.v2.sdk.anno.param.GtPathParam;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.req.Audience;
import com.getui.push.v2.sdk.dto.req.AudienceDTO;
import com.getui.push.v2.sdk.dto.req.message.PushBatchDTO;
import com.getui.push.v2.sdk.dto.req.message.PushDTO;
import com.getui.push.v2.sdk.dto.res.ScheduleTaskDTO;
import com.getui.push.v2.sdk.dto.res.TaskIdDTO;

import java.util.Map;

/**
 * create by getui on 2020/6/7
 *
 * @author getui
 */
public interface PushApi {

    /**
     * 根据cid推送
     *
     * @param pushDTO
     * @return k: cid, v: status
     */
    @GtPost(uri = "/push/single/cid")
    ApiResult<Map<String, Map<String, String>>> pushToSingleByCid(@GtBodyParam PushDTO<Audience> pushDTO);

    /**
     * 根据别名推送
     *
     * @param pushDTO
     * @return
     */
    @GtPost(uri = "/push/single/alias")
    ApiResult<Map<String, Map<String, String>>> pushToSingleByAlias(@GtBodyParam PushDTO<Audience> pushDTO);

    /**
     * 根据cid批量推送
     *
     * @param pushBatchDTO
     * @return
     */
    @GtPost(uri = "/push/single/batch/cid")
    ApiResult<Map<String, Map<String, String>>> pushBatchByCid(@GtBodyParam PushBatchDTO pushBatchDTO);

    /**
     * 根据别名批量推送
     *
     * @param pushBatchDTO
     * @return
     */
    @GtPost(uri = "/push/single/batch/alias")
    ApiResult<Map<String, Map<String, String>>> pushBatchByAlias(@GtBodyParam PushBatchDTO pushBatchDTO);

    /**
     * 创建消息体
     *
     * @param pushDTO
     * @return
     */
    @GtPost(uri = "/push/list/message")
    ApiResult<TaskIdDTO> createMsg(@GtBodyParam PushDTO pushDTO);

    /**
     * 根据cid批量推送
     *
     * @param audienceDTO
     * @return
     */
    @GtPost(uri = "/push/list/cid")
    ApiResult<Map<String, Map<String, String>>> pushListByCid(@GtBodyParam AudienceDTO audienceDTO);

    /**
     * 根据别名批量推送
     *
     * @param audienceDTO
     * @return
     */
    @GtPost(uri = "/push/list/alias")
    ApiResult<Map<String, Map<String, String>>> pushListByAlias(@GtBodyParam AudienceDTO audienceDTO);

    /**
     * 执行群推
     *
     * @param pushDTO
     * @return
     */
    @GtPost(uri = "/push/all")
    ApiResult<TaskIdDTO> pushAll(@GtBodyParam PushDTO<String> pushDTO);

    /**
     * 根据条件筛选用户推送
     *
     * @param pushDTO
     * @return
     */
    @GtPost(uri = "/push/tag")
    ApiResult<TaskIdDTO> pushByTag(@GtBodyParam PushDTO<Audience> pushDTO);

    /**
     * 使用标签快速推送
     *
     * @param pushDTO
     * @return
     */
    @GtPost(uri = "/push/fast_custom_tag")
    ApiResult<TaskIdDTO> pushByFastCustomTag(@GtBodyParam PushDTO<Audience> pushDTO);

    /**
     * 停止任务
     *
     * @param taskId
     * @return
     */
    @GtDelete(uri = "/task")
    ApiResult<Void> stopPush(@GtPathParam String taskId);

    /**
     * 查询任务
     *
     * @param taskId
     * @return
     */
    @GtGet(uri = "/task/schedule/")
    ApiResult<Map<String, ScheduleTaskDTO>> queryScheduleTask(@GtPathParam String taskId);

    /**
     * 删除定时任务
     *
     * @param taskId
     * @return
     */
    @GtDelete(uri = "/task/schedule")
    ApiResult<Void> deleteScheduleTask(@GtPathParam String taskId);

}
