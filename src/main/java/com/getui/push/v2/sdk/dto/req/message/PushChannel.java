package com.getui.push.v2.sdk.dto.req.message;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.dto.BaseDTO;
import com.getui.push.v2.sdk.dto.req.message.android.AndroidDTO;
import com.getui.push.v2.sdk.dto.req.message.harmony.HarmonyDTO;
import com.getui.push.v2.sdk.dto.req.message.ios.IosDTO;

/**
 * create by getui on 2020/6/3
 *
 * @author getui
 */
public class PushChannel implements BaseDTO {

    /**
     * ios通道推送消息内容
     */
    private IosDTO ios;
    /**
     * android通道推送消息内容
     */
    private AndroidDTO android;
    /**
     * harmony通道推送消息内容
     */
    private HarmonyDTO harmony;

    @Override
    public void check() throws ApiException {
    }

    public static class AndroidMessage {

    }

    public IosDTO getIos() {
        return ios;
    }

    public void setIos(IosDTO ios) {
        this.ios = ios;
    }

    public AndroidDTO getAndroid() {
        return android;
    }

    public void setAndroid(AndroidDTO android) {
        this.android = android;
    }

    public HarmonyDTO getHarmony() {
        return harmony;
    }

    public void setHarmony(HarmonyDTO harmony) {
        this.harmony = harmony;
    }

    @Override
    public String toString() {
        return "PushChannel{" +
                "ios=" + ios +
                ", android=" + android +
                ", harmony=" + harmony +
                '}';
    }
}
