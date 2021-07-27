package com.getui.push.v2.sdk.api;

import com.getui.push.v2.sdk.ApiHelper;
import com.getui.push.v2.sdk.api.env.ApiContext;
import com.getui.push.v2.sdk.common.ApiResult;
import com.getui.push.v2.sdk.dto.CommonEnum;
import com.getui.push.v2.sdk.dto.req.*;
import com.getui.push.v2.sdk.dto.req.message.PushBatchDTO;
import com.getui.push.v2.sdk.dto.req.message.PushChannel;
import com.getui.push.v2.sdk.dto.req.message.PushDTO;
import com.getui.push.v2.sdk.dto.req.message.PushMessage;
import com.getui.push.v2.sdk.dto.req.message.android.AndroidDTO;
import com.getui.push.v2.sdk.dto.req.message.android.GTNotification;
import com.getui.push.v2.sdk.dto.res.ScheduleTaskDTO;
import com.getui.push.v2.sdk.dto.req.message.android.ThirdNotification;
import com.getui.push.v2.sdk.dto.req.message.android.Ups;
import com.getui.push.v2.sdk.dto.req.message.ios.Alert;
import com.getui.push.v2.sdk.dto.req.message.ios.Aps;
import com.getui.push.v2.sdk.dto.req.message.ios.IosDTO;
import com.getui.push.v2.sdk.dto.res.TaskIdDTO;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * create by getui on 2020/8/8
 *
 * @author getui
 */
public class PushApiTest {

    PushApi pushApi;
    ApiContext apiContext;
    String cid = null;

    @Before
    public void before() {
        // 设置httpClient最大连接数，当并发较大时建议调大此参数。或者启动参数加上 -Dhttp.maxConnections=200
        System.setProperty("http.maxConnections", "200");
        apiContext = ApiContext.build();
        apiContext.configuration.setAnalyseStableDomainInterval(500);
//        apiContext.configuration.setOpenCheckHealthDataSwitch(true);
        apiContext.configuration.setCheckHealthInterval(500);
        apiContext.configuration.setOpenAnalyseStableDomainSwitch(false);  //关闭
        ApiHelper apiHelper = ApiHelper.build(apiContext.configuration);
        cid = apiContext.cid;
        pushApi = apiHelper.creatApi(PushApi.class);
    }

    @Test
    public void pushToSingleByCid() throws InterruptedException {
        PushDTO<Audience> pushDTO = pushDTO();
        int num = 0;
        fullCid(pushDTO);
        while (true) {
            pushDTO.setRequestId(System.currentTimeMillis() + "");
            ApiResult<Map<String, Map<String, String>>> apiResult = pushApi.pushToSingleByCid(pushDTO);
            if (apiResult.isSuccess()) {
                // success
            } else {
                // failed
            }
            System.err.println(apiResult);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            if (num++ == 50) {
                ApiHelper.close(apiContext.configuration);
            }
            break;
        }
    }

    @Test
    public void pushToSingleByAlias() {
        PushDTO<Audience> pushDTO = pushDTO();
        fullAlias(pushDTO);
        ApiResult<Map<String, Map<String, String>>> apiResult = pushApi.pushToSingleByAlias(pushDTO);
        System.out.println(apiResult);
    }

    @Test
    public void pushBatchByCid() {
        PushBatchDTO pushBatchDTO = new PushBatchDTO();
        PushDTO<Audience> pushDTO = pushDTO();
        fullCid(pushDTO);
        pushBatchDTO.addPushDTO(pushDTO);

        System.out.println(pushApi.pushBatchByCid(pushBatchDTO));
    }

    @Test
    public void pushBatchByAlias() {
        PushBatchDTO pushBatchDTO = new PushBatchDTO();
        PushDTO<Audience> pushDTO = pushDTO();
        fullAlias(pushDTO);
        pushBatchDTO.addPushDTO(pushDTO);

        System.out.println(pushApi.pushBatchByAlias(pushBatchDTO));
    }

    @Test
    public void createMsg() {
        PushDTO<Audience> pushDTO = pushDTO();
        pushDTO.setGroupName("group1");
        System.out.println(pushApi.createMsg(pushDTO));
    }

    @Test
    public void pushListByCid() {
        while (true) {
            String taskId = "taskId";
            AudienceDTO audienceDTO = new AudienceDTO();
            audienceDTO.setTaskid(taskId);
            Audience audience = new Audience();
            audience.addCid(cid);
            audience.addCid(cid);
            audienceDTO.setAudience(audience);
            System.out.println(pushApi.pushListByCid(audienceDTO));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            return;
        }
    }

    @Test
    public void pushListByAlias() {
        String taskId = "taskId";
        AudienceDTO audienceDTO = new AudienceDTO();
        audienceDTO.setTaskid(taskId);
        Audience audience = new Audience();
        String alias = "";
        audience.addAlias(alias);
        audienceDTO.setAudience(audience);
        System.out.println(pushApi.pushListByAlias(audienceDTO));
    }

    @Test
    public void pushAll() {
        PushDTO<String> pushDTO = pushDTOAll();
        System.out.println(pushApi.pushAll(pushDTO));
    }

    @Test
    public void pushByTag() {
        final PushDTO<Audience> pushDTO = pushDTO();
        Audience audience = new Audience();
        final Condition condition = new Condition();
        condition.setOptType(CommonEnum.OptTypeEnum.TYPE_OR.type);
        condition.setKey("portrait");
        condition.addValue("si0901").addValue("025000");
        audience.addCondition(condition);
        pushDTO.setAudience(audience);
        final ApiResult<TaskIdDTO> result = pushApi.pushByTag(pushDTO);
        System.out.println(result);
    }

    @Test
    public void pushByFastCustomTag() {
        final PushDTO<Audience> pushDTO = pushDTO();
        Audience audience = new Audience();
        audience.setFastCustomTag("tag4");
        pushDTO.setAudience(audience);
        final ApiResult<TaskIdDTO> result = pushApi.pushByFastCustomTag(pushDTO);
        System.out.println(result);
    }

    @Test
    public void stopPush() {
        final ApiResult<Void> result = pushApi.stopPush("taskId");
        System.out.println(result);
    }

    @Test
    public void query() {
        final ApiResult<Map<String, ScheduleTaskDTO>> result = pushApi.queryScheduleTask("taskId");
        System.out.println(result);
    }

    @Test
    public void delete() {
        final ApiResult<Void> result = pushApi.deleteScheduleTask("taskId");
        System.out.println(result);
    }

    private PushDTO<Audience> pushDTO() {
        PushDTO<Audience> pushDTO = new PushDTO<Audience>();
        pushDTO.setRequestId(System.currentTimeMillis() + "");
        pushDTO.setGroupName("g-name1");

        Settings settings = new Settings();
        settings.setTtl(3600000);
        Strategy strategy = new Strategy();
        strategy.setSt(1);
        settings.setStrategy(strategy);

        pushDTO.setSettings(settings);

        PushMessage pushMessage = new PushMessage();
        GTNotification notification = new GTNotification();
        // notification.setBigImage("https://url");
        // notification.setLogo("https://url");
        notification.setLogoUrl("https://url");
        notification.setTitle("title-" + System.currentTimeMillis());
        notification.setBody("body1");
        notification.setClickType(CommonEnum.ClickTypeEnum.TYPE_URL.type);
        notification.setUrl("https//:www.getui.com");

        pushMessage.setNotification(notification);
        pushDTO.setPushMessage(pushMessage);

        PushChannel pushChannel = new PushChannel();
        AndroidDTO androidDTO = new AndroidDTO();
        Ups ups = new Ups();
        ThirdNotification thirdNotification = new ThirdNotification();
        thirdNotification.setClickType(CommonEnum.ClickTypeEnum.TYPE_STARTAPP.type);
        thirdNotification.setTitle("title-" + System.currentTimeMillis());
        thirdNotification.setBody("content");
        ups.setNotification(thirdNotification);


        //设置options 方式一
        ups.addOption("HW","badgeAddNum",3);
        ups.addOption("HW","badgeClass","com.getui.demo.GetuiSdkDemoActivity");
        ups.addOption("OP","app_message_id",11);
        ups.addOption("VV","message_sort",1);
        ups.addOptionAll("channel","default");


        //设置options 方式二
        Map<String, Map<String,Object>> options = new HashMap<String, Map<String, Object>>();
        Map<String,Object> all = new HashMap<String, Object>();
        all.put("channel","default");
        options.put("ALL",all);
        Map<String,Object> hw = new HashMap<String, Object>();
        all.put("badgeAddNum",3);
        all.put("badgeClass","com.getui.demo.GetuiSdkDemoActivity");
        options.put("HW",hw);
        ups.setOptions(options);


        androidDTO.setUps(ups);
        pushChannel.setAndroid(androidDTO);

        IosDTO iosDTO = new IosDTO();
        Aps aps = new Aps();
        Alert alert = new Alert();
        alert.setTitle("title-" + System.currentTimeMillis());
        alert.setBody("ios_body");
        aps.setAlert(alert);
        iosDTO.setAps(aps);
        pushChannel.setIos(iosDTO);
        pushDTO.setPushChannel(pushChannel);

        return pushDTO;
    }

    private PushDTO<String> pushDTOAll() {
        PushDTO<String> pushDTO = new PushDTO<String>();
        pushDTO.setRequestId(UUID.randomUUID().toString().substring(0, 16));
        pushDTO.setGroupName("g-name");

        Settings settings = new Settings();
        settings.setTtl(3600000);

        pushDTO.setSettings(settings);
        pushDTO.setAudience("all");

        PushMessage pushMessage = new PushMessage();
        GTNotification notification = new GTNotification();
        notification.setTitle("title" + System.currentTimeMillis());
        notification.setBody("body" + System.currentTimeMillis());
        notification.setClickType(CommonEnum.ClickTypeEnum.TYPE_URL.type);
        notification.setUrl("https//:www.getui.com");
        pushMessage.setNotification(notification);
        pushDTO.setPushMessage(pushMessage);
        return pushDTO;
    }

    private void fullCid(PushDTO<Audience> pushDTO) {
        Audience audience = new Audience();
        audience.addCid(cid);
        pushDTO.setAudience(audience);
    }

    private void fullAlias(PushDTO<Audience> pushDTO) {
        Audience audience = new Audience();
        String alias = "";
        audience.addAlias(alias);
        pushDTO.setAudience(audience);
    }
}