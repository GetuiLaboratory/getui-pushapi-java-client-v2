package com.getui.push.v2.sdk.dto.req.message.mp;

public class MpDTO {

    /**
     * 微信小程序通道推送消息内容。
     * 推送前需调用 {@link com.getui.push.v2.sdk.api.UserApi#bindOrUnbindDeviceToken(
     *String, com.getui.push.v2.sdk.dto.req.CidDeviceTokenListDTO)}，将 cid 与微信小程序用户的 openId
     * 建立绑定关系，其中通道类型传 {@code wx}，deviceToken 传用户的 openId。绑定成功后，后续无需重复绑定。
     */
    private WxDTO wx;

    public WxDTO getWx() {
        return wx;
    }

    public void setWx(WxDTO wx) {
        this.wx = wx;
    }

    @Override
    public String toString() {
        return "MpDTO{" +
                "wx=" + wx +
                '}';
    }
}
