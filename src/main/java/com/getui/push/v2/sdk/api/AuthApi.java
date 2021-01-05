package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.anno.GtApi;
import com.getui.push.v2.sdk.anno.method.GtDelete;
import com.getui.push.v2.sdk.anno.method.GtPost;
import com.getui.push.v2.sdk.anno.param.GtBodyParam;
import com.getui.push.v2.sdk.anno.param.GtPathParam;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.req.AuthDTO;
import com.getui.push.v2.sdk.dto.res.TokenDTO;

/**
 * 鉴权api
 * create by getui on 2020/6/4
 *
 * @author getui
 */
@GtApi
public interface AuthApi {

    /**
     * 获取鉴权token接口
     *
     * @param authDTO
     * @return
     */
    @GtPost(uri = "/auth", needToken = false)
    ApiResult<TokenDTO> auth(@GtBodyParam AuthDTO authDTO);

    /**
     * 关闭鉴权token
     *
     * @param token
     * @return
     */
    @GtDelete(uri = "/auth")
    ApiResult<Void> close(@GtPathParam String token);

}
