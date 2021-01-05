package com.getui.push.v2.sdk.dto.req.message;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.dto.req.Audience;
import com.getui.push.v2.sdk.dto.req.BaseReqDTO;
import com.getui.push.v2.sdk.dto.req.Settings;

/**
 * create by getui on 2020/6/3
 *
 * @param <T> 接收人的泛型 {@link Audience}
 * @author getui
 */
public class PushDTO<T> implements BaseReqDTO {

    /**
     * 请求唯一标识号(10-32位之间)
     */
    private String requestId;

    private String taskName;
    private String groupName;

    private Settings settings;

    private T audience;

    private PushMessage pushMessage;
    private PushChannel pushChannel;

    @Override
    public void check() throws ApiException {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public T getAudience() {
        return audience;
    }

    public void setAudience(T audience) {
        this.audience = audience;
    }

    public PushMessage getPushMessage() {
        return pushMessage;
    }

    public void setPushMessage(PushMessage pushMessage) {
        this.pushMessage = pushMessage;
    }

    public PushChannel getPushChannel() {
        return pushChannel;
    }

    public void setPushChannel(PushChannel pushChannel) {
        this.pushChannel = pushChannel;
    }

    @Override
    public String toString() {
        return "PushDTO{" +
                "requestId='" + requestId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", settings=" + settings +
                ", audience=" + audience +
                ", pushMessage=" + pushMessage +
                ", pushChannel=" + pushChannel +
                '}';
    }
}
