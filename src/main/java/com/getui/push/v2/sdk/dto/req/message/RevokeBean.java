package com.getui.push.v2.sdk.dto.req.message;

/**
 * create by getui on 2020/9/23
 *
 * @author getui
 */
public class RevokeBean {
    /**
     * 在没有找到对应的taskid，是否把对应appid下所有的通知都撤回
     */
    private Boolean force = false;
    /**
     * 根据oldTaskId进行撤回
     */
    private String oldTaskId;

    public Boolean getForce() {
        return force == null ? Boolean.FALSE : force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public String getOldTaskId() {
        return oldTaskId;
    }

    public void setOldTaskId(String oldTaskId) {
        this.oldTaskId = oldTaskId;
    }

    @Override
    public String toString() {
        return "RevokeBean{" +
                "force=" + force +
                ", oldTaskId='" + oldTaskId + '\'' +
                '}';
    }
}
