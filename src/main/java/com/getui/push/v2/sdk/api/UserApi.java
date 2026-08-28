package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.anno.method.GtDelete;
import com.getui.push.v2.sdk.anno.method.GtGet;
import com.getui.push.v2.sdk.anno.method.GtPost;
import com.getui.push.v2.sdk.anno.method.GtPut;
import com.getui.push.v2.sdk.anno.param.GtBodyParam;
import com.getui.push.v2.sdk.anno.param.GtPathParam;
import com.getui.push.v2.sdk.anno.param.GtQueryParam;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.req.*;
import com.getui.push.v2.sdk.dto.res.*;

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
     * 根据cid查询指定别名类型下的别名
     *
     * @param cid
     * @param aliasType 别名类型
     * @return
     */
    @GtGet(uri = "/user/alias/cid/")
    ApiResult<AliasResDTO> queryAliasByCid(@GtPathParam String cid, @GtQueryParam(name = "alias_type") String aliasType);

    /**
     * 根据别名查询cid
     *
     * @param alias
     * @return
     */
    @GtGet(uri = "/user/cid/alias/")
    ApiResult<QueryCidResDTO> queryCidByAlias(@GtPathParam String alias);

    /**
     * 根据别名类型与别名查询cid
     *
     * @param alias
     * @param aliasType
     * @return
     */
    @GtGet(uri = "/user/cid/alias/")
    ApiResult<QueryCidResDTO> queryCidByAlias(@GtPathParam String alias, @GtQueryParam(name = "alias_type") String aliasType);

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
     * 解绑指定别名类型下的别名
     *
     * @param alias
     * @param aliasType
     * @return
     */
    @GtDelete(uri = "/user/alias")
    ApiResult<Void> unbindAllAlias(@GtPathParam String alias, @GtQueryParam(name = "alias_type") String aliasType);

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

    /**
     * 批量绑定或解绑cid和deviceToken。
     * deviceToken有值时绑定，为空时解绑；单次最多提交1000条数据。
     *
     * @param type                  通道类型，微信小程序使用wx；其他厂商通道需开通权限
     * @param cidDeviceTokenListDTO cid与deviceToken关系列表
     * @return 操作失败的用户列表；全部成功时data为空
     */
    @GtPost(uri = "/user/bind_dt/")
    ApiResult<BindDeviceTokenResDTO> bindOrUnbindDeviceToken(@GtPathParam String type,
                                                             @GtBodyParam CidDeviceTokenListDTO cidDeviceTokenListDTO);

    /**
     * 【用户】查询用户基础信息
     *
     * @param cidSet cid集合
     * @return 有效cid的基础信息和无效cid集合
     */
    @GtGet(uri = "/user/info/base/")
    ApiResult<UserInfoResDTO> queryUserBaseInfo(@GtPathParam Set<String> cidSet);

    /**
     * 【用户】查询用户高级信息
     *
     * @param cidSet cid集合
     * @return 有效cid的高级信息和无效cid集合
     */
    @GtGet(uri = "/user/info/advanced/")
    ApiResult<UserInfoResDTO> queryUserAdvancedInfo(@GtPathParam Set<String> cidSet);

    /**
     * 【测试设备】新增测试CID
     *
     * @param testCidAddDTO 测试cid列表
     * @return 添加成功和失败的cid列表
     */
    @GtPost(uri = "/user/test/cid/add")
    ApiResult<TestCidAddResDTO> addTestCid(@GtBodyParam TestCidAddDTO testCidAddDTO);

    /**
     * 【测试设备】修改测试CID
     *
     * @param testCidUpdateDTO cid和新备注
     * @return 修改结果
     */
    @GtPost(uri = "/user/test/cid/update")
    ApiResult<Void> updateTestCid(@GtBodyParam TestCidUpdateDTO testCidUpdateDTO);

    /**
     * 【测试设备】删除测试CID
     *
     * @param testCidDeleteDTO 待删除的cid列表
     * @return 删除结果
     */
    @GtPost(uri = "/user/test/cid/delete")
    ApiResult<Void> deleteTestCid(@GtBodyParam TestCidDeleteDTO testCidDeleteDTO);

    /**
     * 【测试设备】查询测试CID列表
     * 所有查询参数均可为null，pageNum和pageSize为null时服务端默认为1和10。
     *
     * @param cid      cid精确查询条件
     * @param remark   备注，模糊查询；空字符串或纯空白不参与筛选
     * @param pageNum  页码；小于 1 时按 1 处理
     * @param pageSize 每页数量；小于 1 时按 10 处理
     * @return 测试cid列表和分页信息
     */
    @GtGet(uri = "/user/test/cid/list")
    ApiResult<TestCidListResDTO> queryTestCidList(@GtQueryParam(name = "cid", required = false) String cid,
                                                  @GtQueryParam(name = "remark", required = false) String remark,
                                                  @GtQueryParam(name = "pageNum", required = false) Integer pageNum,
                                                  @GtQueryParam(name = "pageSize", required = false) Integer pageSize);

}
