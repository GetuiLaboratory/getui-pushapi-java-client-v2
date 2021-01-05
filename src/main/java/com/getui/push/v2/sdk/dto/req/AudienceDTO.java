package com.getui.push.v2.sdk.dto.req;

/**
 * create by getui on 2020/7/29
 *
 * @author getui
 */
public class AudienceDTO {

    /**
     * 推送目标用户
     */
    private Audience audience;

    /**
     * 使用创建消息接口返回的taskId
     */
    private String taskid;
    /**
     * 是否异步推送，异步推送不会返回data
     */
    private boolean isAsync;

    public Audience getAudience() {
        return audience;
    }

    public void setAudience(Audience audience) {
        this.audience = audience;
    }

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public boolean isAsync() {
        return isAsync;
    }

    public void setAsync(boolean async) {
        isAsync = async;
    }

    @Override
    public String toString() {
        return "AudienceDTO{" +
                "audience=" + audience +
                ", taskid='" + taskid + '\'' +
                ", isAsync=" + isAsync +
                '}';
    }
}
