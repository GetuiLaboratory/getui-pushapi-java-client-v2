欢迎使用[个推**PUSH** SDK For Java](https://docs.getui.com/getui/server/rest_v2/introduction/)。

`个推PUSH SDK For Java`的主要目标是提升开发者在**服务端**集成个推推送服务的开发效率。
开发者不需要进行复杂编程即可使用个推推送服务的各项常用功能，SDK可以自动帮您满足调用过程中所需的鉴权、组装参数、发送HTTP请求等非功能性要求。

下面向您介绍`个推PUSH SDK For Java`的使用方法。


## 环境要求
1. 需要配合`JDK 1.6`或其以上版本。

2. 使用`个推PUSH SDK`前，您需要先前往[个推开发者中心](https://dev.getui.com) 完成开发者接入的一些准备工作，创建应用。详细见[操作步骤](https://docs.getui.com/getui/start/devcenter/#1)

3. 准备工作完成后，前往[个推开发者中心](https://dev.getui.com)获取应用配置，后续将作为使用SDK的输入。详细见[操作步骤](https://docs.getui.com/getui/start/devcenter/#11)


## 安装依赖
### 通过[Maven](https://mvnrepository.com/artifact/com.getui.push/restful-sdk)来管理项目依赖
推荐通过Maven来管理项目依赖，您只需在项目的`pom.xml`文件中声明如下依赖

```xml
    <dependency>
        <groupId>com.getui.push</groupId>
        <artifactId>restful-sdk</artifactId>
        <version>1.0.0.6</version>
    </dependency>
```

## 快速开始
### 普通调用
下列代码示例向您展示了使用`个推Push SDK For Java`调用一个API的3个主要步骤：

1. 设置参数，创建API。
2. 发起API调用。
3. 处理响应。

##### 使用示例：**创建API**

```java
public class TestCreatApi {
    public void test() {
        // 设置httpClient最大连接数，当并发较大时建议调大此参数。或者启动参数加上 -Dhttp.maxConnections=200
        System.setProperty("http.maxConnections", "200");
        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
        //填写应用配置
        apiConfiguration.setAppId("xxx");
        apiConfiguration.setAppKey("xxx");
        apiConfiguration.setMasterSecret("xxx");
        // 接口调用前缀，请查看文档: 接口调用规范 -> 接口前缀, 可不填写appId
        apiConfiguration.setDomain("https://restapi.getui.com/v2/");
        // 实例化ApiHelper对象，用于创建接口对象
        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
        // 创建对象，建议复用。目前有PushApi、StatisticApi、UserApi
        PushApi pushApi = apiHelper.creatApi(PushApi.class);
    }
}
```

##### 使用示例：**推送API**_根据cid进行单推

```java
public class TestPushApi {
    //pushApi的创建见上述使用示例：创建API
    public PushApi pushApi;
    public void test() {
        //根据cid进行单推
        PushDTO<Audience> pushDTO = new PushDTO<Audience>();
        // 设置推送参数
        pushDTO.setRequestId(System.currentTimeMillis() + "");
        /**** 设置个推通道参数 *****/
        PushMessage pushMessage = new PushMessage();
        pushDTO.setPushMessage(pushMessage);
        GTNotification notification = new GTNotification();
        pushMessage.setNotification(notification);
        notification.setTitle("个title");
        notification.setBody("个body");
        notification.setClickType("url");
        notification.setUrl("https://www.getui.com");
        /**** 设置个推通道参数，更多参数请查看文档或对象源码 *****/

        /**** 设置厂商相关参数 ****/
        PushChannel pushChannel = new PushChannel();
        pushDTO.setPushChannel(pushChannel);
        /*配置安卓厂商参数*/
        AndroidDTO androidDTO = new AndroidDTO();
        pushChannel.setAndroid(androidDTO);
        Ups ups = new Ups();
        androidDTO.setUps(ups);
        ThirdNotification thirdNotification = new ThirdNotification();
        ups.setNotification(thirdNotification);
        thirdNotification.setTitle("厂商title");
        thirdNotification.setBody("厂商body");
        thirdNotification.setClickType("url");
        thirdNotification.setUrl("https://www.getui.com");
        // 两条消息的notify_id相同，新的消息会覆盖老的消息，取值范围：0-2147483647
        // thirdNotification.setNotifyId("11177");
        /*配置安卓厂商参数结束，更多参数请查看文档或对象源码*/

        /*设置ios厂商参数*/
        IosDTO iosDTO = new IosDTO();
        pushChannel.setIos(iosDTO);
        // 相同的collapseId会覆盖之前的消息
        iosDTO.setApnsCollapseId("xxx");
        Aps aps = new Aps();
        iosDTO.setAps(aps);
        Alert alert = new Alert();
        aps.setAlert(alert);
        alert.setTitle("ios title");
        alert.setBody("ios body");
        /*设置ios厂商参数结束，更多参数请查看文档或对象源码*/

        /*设置接收人信息*/
        Audience audience = new Audience();
        pushDTO.setAudience(audience);
        audience.addCid("xxx");
        /*设置接收人信息结束*/
        /**** 设置厂商相关参数，更多参数请查看文档或对象源码 ****/

        // 进行cid单推
        ApiResult<Map<String, Map<String, String>>> apiResult = pushApi.pushToSingleByCid(pushDTO);
        if (apiResult.isSuccess()) {
            // success
            System.out.println(apiResult.getData());
        } else {
            // failed
            System.out.println("code:" + apiResult.getCode() + ", msg: " + apiResult.getMsg());
        }
    }
}
```
##### 使用示例：**统计API**_获取单日推送数据
```java
public class TestStatisticApi {
    //StatisticApi的创建见上述使用示例：创建API
    public StatisticApi statisticApi;
    public void test() {
        // 获取单日推送数据
        Set<String> taskIds = new HashSet<String>();
        taskIds.add("xxx");
        ApiResult<Map<String, Map<String, StatisticDTO>>> apiResult = statisticApi.queryPushResultByTaskIds(taskIds);
        if (apiResult.isSuccess()) {
            // success
            System.out.println(apiResult.getData());
        } else {
            // failed
            System.out.println("code:" + apiResult.getCode() + ", msg: " + apiResult.getMsg());
        }
    }
}
```


##### 使用示例：**用户API**_查询用户状态
```java
public class TestUserApi {
    //UserApi的创建见上述使用示例：创建API
    public UserApi userApi;
    public void test() {
        Set<String> cids = new HashSet<String>();
        cids.add("xxx");
        // 查询用户状态
        ApiResult<Map<String, CidStatusDTO>> apiResult = userApi.queryUserStatus(cids);
        if (apiResult.isSuccess()) {
            // success
            System.out.println(apiResult.getData());
        } else {
            // failed
            System.out.println("code:" + apiResult.getCode() + ", msg: " + apiResult.getMsg());
        }
    }
}
```
> 其余推送功能[可参考该链接](https://github.com/GetuiLaboratory/getui-pushapi-java-client-v2/tree/main/src/test/java/com/getui/push/v2/sdk/api)


### 设置代理
> 当需要使用代理进行http访问时，可以参考如下设置

```java
        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
        //设置代理对象，参数依次为host、端口、鉴权账户、鉴权密码。其中鉴权账户密码可选
        GtHttpProxyConfig proxyConfig = new GtHttpProxyConfig("xxx",xxx,"xxx","xxx");
        apiConfiguration.setProxyConfig(proxyConfig);
        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
        PushApi pushApi = apiHelper.creatApi(PushApi.class);
```


## 已支持的API列表
以下是消息推送功能与推送API的对应关系

| API类别      |      功能       | 调用的API名称                                              |
|-----------|-----------------|-----------------------------------------------------------|
| 鉴权API | [鉴权](https://docs.getui.com/getui/server/rest_v2/token/#0)              | com.getui.push.v2.sdk.api.AuthApi.auth                                  |
| 鉴权API | [删除鉴权](https://docs.getui.com/getui/server/rest_v2/token/#1)           | com.getui.push.v2.sdk.api.AuthApi.close                                 |
| 推送API | [cid单推](https://docs.getui.com/getui/server/rest_v2/push/#1)            | com.getui.push.v2.sdk.api.PushApi.pushToSingleByCid                     |
| 推送API | [别名单推](https://docs.getui.com/getui/server/rest_v2/push/#2)            | com.getui.push.v2.sdk.api.PushApi.pushToSingleByAlias                   |
| 推送API | [cid批量单推](https://docs.getui.com/getui/server/rest_v2/push/#3)         | com.getui.push.v2.sdk.api.PushApi.pushBatchByCid                        |
| 推送API | [别名批量单推](https://docs.getui.com/getui/server/rest_v2/push/#4)         | com.getui.push.v2.sdk.api.PushApi.pushBatchByAlias                      |
| 推送API | [tolist创建消息](https://docs.getui.com/getui/server/rest_v2/push/#5)      | com.getui.push.v2.sdk.api.PushApi.createMsg                             |
| 推送API | [cid批量推](https://docs.getui.com/getui/server/rest_v2/push/#6)           | com.getui.push.v2.sdk.api.PushApi.pushListByCid                         |                  
| 推送API | [别名批量推](https://docs.getui.com/getui/server/rest_v2/push/#7)           | com.getui.push.v2.sdk.api.PushApi.pushListByAlias                       |                    
| 推送API | [群推](https://docs.getui.com/getui/server/rest_v2/push/#8)                | com.getui.push.v2.sdk.api.PushApi.pushAll                               |                                
| 推送API | [条件筛选用户推送](https://docs.getui.com/getui/server/rest_v2/push/#9)      | com.getui.push.v2.sdk.api.PushApi.pushByTag                             |                               
| 推送API | [标签快速推送](https://docs.getui.com/getui/server/rest_v2/push/#10)        | com.getui.push.v2.sdk.api.PushApi.pushByFastCustomTag                    |                                
| 推送API | [停止任务](https://docs.getui.com/getui/server/rest_v2/push/#11)            | com.getui.push.v2.sdk.api.PushApi.stopPush                              |            
| 推送API | [查询定时任务](https://docs.getui.com/getui/server/rest_v2/push/#12)        | com.getui.push.v2.sdk.api.PushApi.queryScheduleTask                      |     
| 推送API | [删除定时任务](https://docs.getui.com/getui/server/rest_v2/push/#13)        | com.getui.push.v2.sdk.api.PushApi.deleteScheduleTask                     |
| 统计API | [获取推送结果](https://docs.getui.com/getui/server/rest_v2/report/#1)       | com.getui.push.v2.sdk.api.StatisticApi.queryPushResultByTaskIds          |                                    
| 统计API | [任务组名查报表](https://docs.getui.com/getui/server/rest_v2/report/#2)      | com.getui.push.v2.sdk.api.StatisticApi.queryPushResultByGroupName        |
| 统计API | [单日推送数据](https://docs.getui.com/getui/server/rest_v2/report/#3)       | com.getui.push.v2.sdk.api.StatisticApi.queryPushResultByDate             |
| 统计API | [单日用户数据接口](https://docs.getui.com/getui/server/rest_v2/report/#4)    | com.getui.push.v2.sdk.api.StatisticApi.queryUserDataByDate               |
| 统计API | [24小时在线用户数](https://docs.getui.com/getui/server/rest_v2/report/#5)    | com.getui.push.v2.sdk.api.StatisticApi.queryOnlineUserData              |
| 用户API | [绑定别名](https://docs.getui.com/getui/server/rest_v2/user/#1)             | com.getui.push.v2.sdk.api.UserApi.bindAlias                             |
| 用户API | [根据cid查询别名](https://docs.getui.com/getui/server/rest_v2/user/#2)       | com.getui.push.v2.sdk.api.UserApi.queryAliasByCid                       |
| 用户API | [根据别名查询cid](https://docs.getui.com/getui/server/rest_v2/user/#3)       | com.getui.push.v2.sdk.api.UserApi.queryCidByAlias                       |
| 用户API | [批量解绑别名](https://docs.getui.com/getui/server/rest_v2/user/#4)          | com.getui.push.v2.sdk.api.UserApi.batchUnbindAlias                      |
| 用户API | [解绑所有别名](https://docs.getui.com/getui/server/rest_v2/user/#5)          | com.getui.push.v2.sdk.api.UserApi.unbindAllAlias                        |
| 用户API | [一个用户绑定一批标签](https://docs.getui.com/getui/server/rest_v2/user/#6)    | com.getui.push.v2.sdk.api.UserApi.userBindTags                         |
| 用户API | [一批用户绑定一个标签](https://docs.getui.com/getui/server/rest_v2/user/#7)    | com.getui.push.v2.sdk.api.UserApi.usersBindTag                         |
| 用户API | [一批用户解绑一个标签](https://docs.getui.com/getui/server/rest_v2/user/#8)    | com.getui.push.v2.sdk.api.UserApi.deleteUsersTag                       |
| 用户API | [查询标签](https://docs.getui.com/getui/server/rest_v2/user/#9)              | com.getui.push.v2.sdk.api.UserApi.queryUserTags                        |
| 用户API | [添加黑名单用户](https://docs.getui.com/getui/server/rest_v2/user/#10)        | com.getui.push.v2.sdk.api.UserApi.addBlackUser                         |
| 用户API | [移除黑名单用户](https://docs.getui.com/getui/server/rest_v2/user/#11)        | com.getui.push.v2.sdk.api.UserApi.removeBlackUser                      |
| 用户API | [查询用户状态](https://docs.getui.com/getui/server/rest_v2/user/#12)          | com.getui.push.v2.sdk.api.UserApi.queryUserStatus                     |
| 用户API | [设置角标(仅支持IOS)](https://docs.getui.com/getui/server/rest_v2/user/#13)   | com.getui.push.v2.sdk.api.UserApi.setBadge                             |
| 用户API | [查询用户总量](https://docs.getui.com/getui/server/rest_v2/user/#14)          | com.getui.push.v2.sdk.api.UserApi.queryUser                            |

> 注：更多API持续更新中，敬请期待。


## 新API接口开发指南
1. 新建api接口类，使用类注解`com.getui.push.v2.sdk.anno.GtApi`标记为个推接口类

2. 接口，使用`com.getui.push.v2.sdk.anno.method`包下的方法注解`GtGet`/`GtPost`/`GtPut`/`GtDelete`标记请求方式，分别代表`GET`、`POST`、`PUT`、`DELETE`四种HTTP请求方式

3. 参数，使用`com.getui.push.v2.sdk.anno.param`包下的参数注解`GtPathParam`/`GtHeaderParam`/`GtQueryParam`/`GtBodyParam`标记参数类型，分别表示HTTP请求中的四种参数： 路径参数/header参数/query参数/body参数

## 其他链接
[个推开发者平台](https://docs.getui.com/)