package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.anno.method.GtDelete;
import com.getui.push.v2.sdk.anno.method.GtGet;
import com.getui.push.v2.sdk.anno.method.GtPost;
import com.getui.push.v2.sdk.anno.method.GtPut;
import com.getui.push.v2.sdk.anno.param.GtBodyParam;
import com.getui.push.v2.sdk.anno.param.GtPathParam;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.req.*;
import com.getui.push.v2.sdk.dto.res.AliasResDTO;
import com.getui.push.v2.sdk.dto.res.CidStatusDTO;
import com.getui.push.v2.sdk.dto.res.QueryCidResDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * create by getui on 2020/6/4
 *
 * @author getui
 */
public interface UserApi {

    /**
     * 绑定别名
     *
     * @param cidAliasListDTO
     * @return
     */
    @GtPost(uri = "/user/alias")
    ApiResult<Void> bindAlias(@GtBodyParam CidAliasListDTO cidAliasListDTO);

    /**
     * 根据cid查询别名
     *
     * @param cid
     * @return
     */
    @GtGet(uri = "/user/alias/cid/")
    ApiResult<AliasResDTO> queryAliasByCid(@GtPathParam String cid);

    /**
     * 根据别名查询cid
     *
     * @param alias
     * @return
     */
    @GtGet(uri = "/user/cid/alias/")
    ApiResult<QueryCidResDTO> queryCidByAlias(@GtPathParam String alias);

    /**
     * 批量解绑别名
     *
     * @param cidAliasListDTO
     * @return
     */
    @GtDelete(uri = "/user/alias")
    ApiResult<Void> batchUnbindAlias(@GtBodyParam CidAliasListDTO cidAliasListDTO);

    /**
     * 解绑所有别名
     *
     * @param alias 别名
     * @return
     */
    @GtDelete(uri = "/user/alias")
    ApiResult<Void> unbindAllAlias(@GtPathParam String alias);

    /**
     * 一个用户绑定一批标签
     *
     * @param cid
     * @param tagDTO
     * @return
     */
    @GtPost(uri = "/user/custom_tag/cid/")
    ApiResult<Void> userBindTags(@GtPathParam String cid, @GtBodyParam TagDTO tagDTO);

    /**
     * 一批用户绑定一个标签
     *
     * @param customerTag 标签
     * @param userDTO
     * @return
     */
    @GtPut(uri = "/user/custom_tag/batch/")
    ApiResult<Map<String, String>> usersBindTag(@GtPathParam String customerTag, @GtBodyParam UserDTO userDTO);

    /**
     * 删除标签
     *
     * @param customerTag 标签
     * @param userDTO
     * @return {@link ApiResult#getData()} map, k: cid; v: 删除状态
     */
    @GtDelete(uri = "/user/custom_tag/batch/")
    ApiResult<Map<String, String>> deleteUsersTag(@GtPathParam String customerTag, @GtBodyParam UserDTO userDTO);

    /**
     * 查询用户标签
     *
     * @param cid
     * @return
     */
    @GtGet(uri = "/user/custom_tag/cid/")
    ApiResult<Map<String, List<String>>> queryUserTags(@GtPathParam String cid);

    /**
     * 添加黑名单用户
     *
     * @param cidSet
     * @return
     */
    @GtPost(uri = "/user/black/cid")
    ApiResult<Void> addBlackUser(@GtPathParam Set<String> cidSet);

    /**
     * 移除黑名单用户
     *
     * @param cidSet
     * @return
     */
    @GtDelete(uri = "/user/black/cid")
    ApiResult<Void> removeBlackUser(@GtPathParam Set<String> cidSet);

    /**
     * 查询用户状态
     *
     * @param cidSet
     * @return
     */
    @GtGet(uri = "/user/status")
    ApiResult<Map<String, CidStatusDTO>> queryUserStatus(@GtPathParam Set<String> cidSet);

    /**
     * 设置角标
     *
     * @param cidSet
     * @param badgeDTO
     * @return
     */
    @GtPost(uri = "/user/badge/cid/")
    ApiResult<Void> setBadge(@GtPathParam Set<String> cidSet, @GtBodyParam BadgeDTO badgeDTO);

    /**
     * 查询符合条件的用户总量
     *
     * @param conditionListDTO 查询条件
     * @return
     */
    @GtPost(uri = "/user/count/")
    ApiResult<Map<String, Integer>> queryUser(@GtBodyParam ConditionListDTO conditionListDTO);

}
