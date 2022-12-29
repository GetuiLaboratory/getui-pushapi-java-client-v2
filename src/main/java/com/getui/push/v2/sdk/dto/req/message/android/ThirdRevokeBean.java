package com.getui.push.v2.sdk.dto.req.message.android;

public class ThirdRevokeBean {
    /**
     * 需要撤回的taskId
     */
    private String oldTaskId;

    public String getOldTaskId() {
        return oldTaskId;
    }

    public void setOldTaskId(String oldTaskId) {
        this.oldTaskId = oldTaskId;
    }

    @Override
    public String toString() {
        return "ThirdRevokeBean{" +
                "oldTaskId='" + oldTaskId + '\'' +
                '}';
    }
}