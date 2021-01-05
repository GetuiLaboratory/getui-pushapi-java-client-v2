package com.getui.push.v2.sdk.dto.res;

import com.google.gson.annotations.SerializedName;

/**
 * create by getui on 2020/7/29
 *
 * @author getui
 */
public class TaskIdDTO {
    @SerializedName("taskid")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "TaskIdDTO{" +
                "taskId='" + taskId + '\'' +
                '}';
    }
}
