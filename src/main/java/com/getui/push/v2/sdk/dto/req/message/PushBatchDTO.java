package com.getui.push.v2.sdk.dto.req.message;

import com.getui.push.v2.sdk.dto.req.Audience;

import java.util.ArrayList;
import java.util.List;

/**
 * create by getui on 2020/8/7
 *
 * @author getui
 */
public class PushBatchDTO {

    private Boolean isAsync;

    private List<PushDTO<Audience>> msgList;

    public PushBatchDTO addPushDTO(PushDTO pushDTO) {
        if (msgList == null) {
            msgList = new ArrayList<PushDTO<Audience>>();
        }
        msgList.add(pushDTO);
        return this;
    }

    public Boolean getAsync() {
        return isAsync;
    }

    public void setAsync(Boolean async) {
        isAsync = async;
    }

    public List<PushDTO<Audience>> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<PushDTO<Audience>> msgList) {
        this.msgList = msgList;
    }

    @Override
    public String toString() {
        return "PushBatchDTO{" +
                "isAsync=" + isAsync +
                ", msgList=" + msgList +
                '}';
    }
}
