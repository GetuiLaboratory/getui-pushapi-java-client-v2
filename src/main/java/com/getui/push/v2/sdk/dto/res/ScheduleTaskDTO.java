package com.getui.push.v2.sdk.dto.res;

/**
 * create by getui on 2020/8/8
 *
 * @author getui
 */
public class ScheduleTaskDTO {
    /**
     * 定时任务创建时间，毫秒时间戳
     */
    private String createTime;
    /**
     * 定时任务状态：send_success/do_not_send/send_failed/has_delete
     */
    private String sendResult;
    /**
     * 透传内容
     */
    private String transmissionContent;
    /**
     * 定时任务推送时间，毫秒时间戳
     */
    private String pushTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSendResult() {
        return sendResult;
    }

    public void setSendResult(String sendResult) {
        this.sendResult = sendResult;
    }

    public String getTransmissionContent() {
        return transmissionContent;
    }

    public void setTransmissionContent(String transmissionContent) {
        this.transmissionContent = transmissionContent;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    @Override
    public String toString() {
        return "ScheduleTaskDTO{" +
                "createTime='" + createTime + '\'' +
                ", sendResult='" + sendResult + '\'' +
                ", transmissionContent='" + transmissionContent + '\'' +
                ", pushTime='" + pushTime + '\'' +
                '}';
    }
}
