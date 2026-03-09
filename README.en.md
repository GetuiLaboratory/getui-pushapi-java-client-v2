[简体中文](./README.md) | [English](./README.en.md)

Welcome to the [Getui **PUSH** SDK for Java](https://docs.getui.com/getui/server/rest_v2/introduction/).

The primary goal of the `Getui PUSH SDK for Java` is to improve developer efficiency when integrating Getui's push
notification service on the **server side**. Developers can use the common features of Getui's push service without
complex programming — the SDK automatically handles authentication, parameter assembly, HTTP requests, and other
non-functional requirements.

The following sections describe how to use the `Getui PUSH SDK for Java`.

## Requirements

1. Requires `JDK 1.8` or above.

2. Before using the `Getui PUSH SDK`, you need to visit the [Getui Developer Center](https://dev.getui.com) to complete
   the developer onboarding preparations and create an application.
   See [Setup Steps](https://docs.getui.com/getui/start/devcenter/#1) for details.

3. After completing the preparations, visit the [Getui Developer Center](https://dev.getui.com) to obtain your
   application credentials, which will be used as input when using the SDK.
   See [Setup Steps](https://docs.getui.com/getui/start/devcenter/#11) for details.

## Installation

### Managing dependencies via [Maven](https://mvnrepository.com/artifact/com.getui.push/restful-sdk)

It is recommended to manage dependencies via Maven. Simply declare the following dependency in your project's `pom.xml`:

```xml

<dependency>
    <groupId>com.getui.push</groupId>
    <artifactId>restful-sdk</artifactId>
    <version>1.0.8.0</version>
</dependency>
```

## Quick Start

### Basic Usage

The following code examples show the 3 main steps to call an API using the `Getui Push SDK for Java`:

1. Set parameters and create the API instance.
2. Make the API call.
3. Handle the response.

##### Example: **Create API**

```java
public class TestCreatApi {
    public void test() {
        // Set the maximum connections for httpClient. Increase this value under high concurrency,
        // or add the startup argument: -Dhttp.maxConnections=200
        System.setProperty("http.maxConnections", "200");
        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
        // Fill in your application credentials
        apiConfiguration.setAppId("xxx");
        apiConfiguration.setAppKey("xxx");
        apiConfiguration.setMasterSecret("xxx");
        // API call prefix — refer to the documentation: API Call Conventions -> API Prefix
        apiConfiguration.setDomain("https://restapi.getui.com/v2/");
        // Instantiate ApiHelper to create API objects
        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
        // Create API objects (reuse recommended). Currently supports PushApi, StatisticApi, UserApi
        PushApi pushApi = apiHelper.creatApi(PushApi.class);
    }
}
```

###### Supported Configuration Options

| Code Configuration                                                              | Startup Argument                          | Default                     | Description                                                                                                                                                                                                                                                                        |
|---------------------------------------------------------------------------------|-------------------------------------------|-----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `gtApiConfiguration.setSoTimeout(30000);`                                       | `-Dgt.socket.timeout=30000`               | 30000                       | Global socket read timeout in ms (default: 30s)                                                                                                                                                                                                                                    |
| `gtApiConfiguration.setCustomSocketTimeout(PushApi.singleCidUri, 3000);`        | `-D/push/single/cid=`                     | Same as `gt.socket.timeout` | Socket read timeout for single CID push, in ms                                                                                                                                                                                                                                     |
| `gtApiConfiguration.setCustomSocketTimeout(PushApi.singleAliasUri, 3000);`      | `-D/push/single/alias=`                   | Same as `gt.socket.timeout` | Socket read timeout for single alias push, in ms                                                                                                                                                                                                                                   |
| `gtApiConfiguration.setCustomSocketTimeout(PushApi.singleBatchCidUri, 6000);`   | `-D/push/single/batch/cid=`               | Same as `gt.socket.timeout` | Socket read timeout for batch CID single push, in ms                                                                                                                                                                                                                               |
| `gtApiConfiguration.setCustomSocketTimeout(PushApi.singleBatchAliasUri, 6000);` | `-D/push/single/batch/alias=`             | Same as `gt.socket.timeout` | Socket read timeout for batch alias single push, in ms                                                                                                                                                                                                                             |
| `gtApiConfiguration.setCustomSocketTimeout(PushApi.pushListMessageUri, 3000);`  | `-D/push/list/message=`                   | Same as `gt.socket.timeout` | Socket read timeout for saving list push message body, in ms                                                                                                                                                                                                                       |
| `gtApiConfiguration.setCustomSocketTimeout(PushApi.pushListCidUri, 6000);`      | `-D/push/list/cid=`                       | Same as `gt.socket.timeout` | Socket read timeout for CID list push, in ms                                                                                                                                                                                                                                       |
| `gtApiConfiguration.setCustomSocketTimeout(PushApi.pushListAliasUri, 6000);`    | `-D/push/list/alias=`                     | Same as `gt.socket.timeout` | Socket read timeout for alias list push, in ms                                                                                                                                                                                                                                     |
| `gtApiConfiguration.setConnectTimeout(10000);`                                  | `-Dgt.connect.timeout=10000`              | 10000                       | Connection timeout in ms (default: 10s)                                                                                                                                                                                                                                            |
| `gtApiConfiguration.setMaxHttpTryTime(1);`                                      | `-Dgt.max.http.try.times=1`               | 1                           | Retry attempts. When set to 1, the SDK retries once on failure (timeout or exception). Note: retries may cause duplicate messages — use with message idempotency. See [Message Deduplication](https://docs.getui.com/getui/server/rest_v2/advanced/). Set to 0 to disable retries. |
| `gtApiConfiguration.setOpenAnalyseStableDomainSwitch(true);`                    | `-Dgt.analyse.stable.domain.switch=true`  | true                        | Enable stable domain detection                                                                                                                                                                                                                                                     |
| `gtApiConfiguration.setMaxFailedNum(10);`                                       | `-Dgt.max.failed.num=10`                  | 10                          | Total failure threshold within a time window (triggers domain switch when reached). Requires stable domain detection enabled.                                                                                                                                                      |
| `gtApiConfiguration.setCheckMaxFailedNumInterval(3000);`                        | `-Dgt.check.max.failed.num.interval=3000` | 3000                        | Time window for the failure threshold check, in ms. Requires stable domain detection enabled.                                                                                                                                                                                      |
| `gtApiConfiguration.setContinuousFailedNum(3);`                                 | `-Dgt.continuous.failed.num=3`            | 3                           | Consecutive failure threshold (triggers domain switch when reached). Requires stable domain detection enabled.                                                                                                                                                                     |

##### Example: **Push API** — Single Push by CID

```java
public class TestPushApi {
    // For pushApi creation, see the "Create API" example above
    public PushApi pushApi;

    public void test() {
        // Single push by CID
        PushDTO<Audience> pushDTO = new PushDTO<Audience>();
        // Set push parameters
        pushDTO.setRequestId(System.currentTimeMillis() + "");

        /**** Set Getui channel parameters ****/
        PushMessage pushMessage = new PushMessage();
        pushDTO.setPushMessage(pushMessage);
        GTNotification notification = new GTNotification();
        pushMessage.setNotification(notification);
        notification.setTitle("title");
        notification.setBody("body");
        notification.setClickType("url");
        notification.setUrl("https://www.getui.com");
        /**** End Getui channel parameters — see docs or source for more options ****/

        /**** Set vendor channel parameters ****/
        PushChannel pushChannel = new PushChannel();
        pushDTO.setPushChannel(pushChannel);

        /* Android vendor parameters */
        AndroidDTO androidDTO = new AndroidDTO();
        pushChannel.setAndroid(androidDTO);
        Ups ups = new Ups();
        androidDTO.setUps(ups);
        ThirdNotification thirdNotification = new ThirdNotification();
        ups.setNotification(thirdNotification);
        thirdNotification.setTitle("vendor title");
        thirdNotification.setBody("vendor body");
        thirdNotification.setClickType("url");
        thirdNotification.setUrl("https://www.getui.com");
        // Messages with the same notify_id will overwrite older messages. Range: 0–2147483647
        // thirdNotification.setNotifyId("11177");
        /* End Android vendor parameters — see docs or source for more options */

        /* iOS vendor parameters */
        IosDTO iosDTO = new IosDTO();
        pushChannel.setIos(iosDTO);
        // Messages with the same collapseId will overwrite previous messages
        iosDTO.setApnsCollapseId("xxx");
        Aps aps = new Aps();
        iosDTO.setAps(aps);
        Alert alert = new Alert();
        aps.setAlert(alert);
        alert.setTitle("ios title");
        alert.setBody("ios body");
        /* End iOS vendor parameters — see docs or source for more options */

        /* HarmonyOS vendor parameters */
        HarmonyDTO harmonyDTO = new HarmonyDTO();
        pushChannel.setHarmony(harmonyDTO);
        HarmonyNotification harmonyNotification = new HarmonyNotification();
        harmonyDTO.setNotification(harmonyNotification);
        harmonyNotification.setTitle("harmony title");
        harmonyNotification.setBody("harmony body");
        harmonyNotification.setCategory("MARKETING");
        harmonyNotification.setClickType("want");
        harmonyNotification.setWant("{\"deviceId\":\"\",\"bundleName\":\"com.getui.push\",\"abilityName\":\"TestAbility\",\"uri\":\"https://www.test.com:8080/push/test\",\"action\":\"com.test.action\",\"parameters\":{\"name\":\"Getui\",\"age\":12}}");
        /* End HarmonyOS vendor parameters — see docs or source for more options */

        /* Set recipient */
        Audience audience = new Audience();
        pushDTO.setAudience(audience);
        audience.addCid("xxx");
        /* End recipient configuration */
        /**** End vendor channel parameters — see docs or source for more options ****/

        // Execute single push by CID
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

##### Example: **Statistics API** — Query Daily Push Data

```java
public class TestStatisticApi {
    // For StatisticApi creation, see the "Create API" example above
    public StatisticApi statisticApi;

    public void test() {
        // Query daily push data
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

##### Example: **User API** — Query User Status

```java
public class TestUserApi {
    // For UserApi creation, see the "Create API" example above
    public UserApi userApi;

    public void test() {
        Set<String> cids = new HashSet<String>();
        cids.add("xxx");
        // Query user status
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

> For more push
>
features, [refer to this link](https://github.com/GetuiLaboratory/getui-pushapi-java-client-v2/tree/main/src/test/java/com/getui/push/v2/sdk/api).

### Configuring a Proxy

> When HTTP access via a proxy is required, refer to the following configuration:

```
        GtApiConfiguration apiConfiguration = new GtApiConfiguration();
        // Set proxy: parameters are host, port, auth username, auth password (username/password are optional)
        GtHttpProxyConfig proxyConfig = new GtHttpProxyConfig("xxx",xxx,"xxx","xxx");
        apiConfiguration.setProxyConfig(proxyConfig);
        ApiHelper apiHelper = ApiHelper.build(apiConfiguration);
        PushApi pushApi = apiHelper.creatApi(PushApi.class);
```

## Supported API List

The following table maps push features to their corresponding API methods:

| API Category   | Feature                                                                                   | API Method                                                          |
|----------------|-------------------------------------------------------------------------------------------|---------------------------------------------------------------------|
| Auth API       | [Authenticate](https://docs.getui.com/getui/server/rest_v2/token/#0)                      | `com.getui.push.v2.sdk.api.AuthApi.auth`                            |
| Auth API       | [Revoke Auth](https://docs.getui.com/getui/server/rest_v2/token/#1)                       | `com.getui.push.v2.sdk.api.AuthApi.close`                           |
| Push API       | [Single Push by CID](https://docs.getui.com/getui/server/rest_v2/push/#1)                 | `com.getui.push.v2.sdk.api.PushApi.pushToSingleByCid`               |
| Push API       | [Single Push by Alias](https://docs.getui.com/getui/server/rest_v2/push/#2)               | `com.getui.push.v2.sdk.api.PushApi.pushToSingleByAlias`             |
| Push API       | [Batch Single Push by CID](https://docs.getui.com/getui/server/rest_v2/push/#3)           | `com.getui.push.v2.sdk.api.PushApi.pushBatchByCid`                  |
| Push API       | [Batch Single Push by Alias](https://docs.getui.com/getui/server/rest_v2/push/#4)         | `com.getui.push.v2.sdk.api.PushApi.pushBatchByAlias`                |
| Push API       | [Create List Push Message](https://docs.getui.com/getui/server/rest_v2/push/#5)           | `com.getui.push.v2.sdk.api.PushApi.createMsg`                       |
| Push API       | [List Push by CID](https://docs.getui.com/getui/server/rest_v2/push/#6)                   | `com.getui.push.v2.sdk.api.PushApi.pushListByCid`                   |
| Push API       | [List Push by Alias](https://docs.getui.com/getui/server/rest_v2/push/#7)                 | `com.getui.push.v2.sdk.api.PushApi.pushListByAlias`                 |
| Push API       | [Broadcast Push](https://docs.getui.com/getui/server/rest_v2/push/#8)                     | `com.getui.push.v2.sdk.api.PushApi.pushAll`                         |
| Push API       | [Push by Condition Filter](https://docs.getui.com/getui/server/rest_v2/push/#9)           | `com.getui.push.v2.sdk.api.PushApi.pushByTag`                       |
| Push API       | [Fast Tag Push](https://docs.getui.com/getui/server/rest_v2/push/#10)                     | `com.getui.push.v2.sdk.api.PushApi.pushByFastCustomTag`             |
| Push API       | [Stop Task](https://docs.getui.com/getui/server/rest_v2/push/#11)                         | `com.getui.push.v2.sdk.api.PushApi.stopPush`                        |
| Push API       | [Query Scheduled Task](https://docs.getui.com/getui/server/rest_v2/push/#12)              | `com.getui.push.v2.sdk.api.PushApi.queryScheduleTask`               |
| Push API       | [Delete Scheduled Task](https://docs.getui.com/getui/server/rest_v2/push/#13)             | `com.getui.push.v2.sdk.api.PushApi.deleteScheduleTask`              |
| Statistics API | [Query Push Results](https://docs.getui.com/getui/server/rest_v2/report/#1)               | `com.getui.push.v2.sdk.api.StatisticApi.queryPushResultByTaskIds`   |
| Statistics API | [Query by Task Group Name](https://docs.getui.com/getui/server/rest_v2/report/#2)         | `com.getui.push.v2.sdk.api.StatisticApi.queryPushResultByGroupName` |
| Statistics API | [Daily Push Statistics](https://docs.getui.com/getui/server/rest_v2/report/#3)            | `com.getui.push.v2.sdk.api.StatisticApi.queryPushResultByDate`      |
| Statistics API | [Daily User Statistics](https://docs.getui.com/getui/server/rest_v2/report/#4)            | `com.getui.push.v2.sdk.api.StatisticApi.queryUserDataByDate`        |
| Statistics API | [24-Hour Online Users](https://docs.getui.com/getui/server/rest_v2/report/#5)             | `com.getui.push.v2.sdk.api.StatisticApi.queryOnlineUserData`        |
| User API       | [Bind Alias](https://docs.getui.com/getui/server/rest_v2/user/#1)                         | `com.getui.push.v2.sdk.api.UserApi.bindAlias`                       |
| User API       | [Query Alias by CID](https://docs.getui.com/getui/server/rest_v2/user/#2)                 | `com.getui.push.v2.sdk.api.UserApi.queryAliasByCid`                 |
| User API       | [Query CID by Alias](https://docs.getui.com/getui/server/rest_v2/user/#3)                 | `com.getui.push.v2.sdk.api.UserApi.queryCidByAlias`                 |
| User API       | [Batch Unbind Aliases](https://docs.getui.com/getui/server/rest_v2/user/#4)               | `com.getui.push.v2.sdk.api.UserApi.batchUnbindAlias`                |
| User API       | [Unbind All Aliases](https://docs.getui.com/getui/server/rest_v2/user/#5)                 | `com.getui.push.v2.sdk.api.UserApi.unbindAllAlias`                  |
| User API       | [Bind Multiple Tags to One User](https://docs.getui.com/getui/server/rest_v2/user/#6)     | `com.getui.push.v2.sdk.api.UserApi.userBindTags`                    |
| User API       | [Bind One Tag to Multiple Users](https://docs.getui.com/getui/server/rest_v2/user/#7)     | `com.getui.push.v2.sdk.api.UserApi.usersBindTag`                    |
| User API       | [Unbind One Tag from Multiple Users](https://docs.getui.com/getui/server/rest_v2/user/#8) | `com.getui.push.v2.sdk.api.UserApi.deleteUsersTag`                  |
| User API       | [Query Tags](https://docs.getui.com/getui/server/rest_v2/user/#9)                         | `com.getui.push.v2.sdk.api.UserApi.queryUserTags`                   |
| User API       | [Add User to Blocklist](https://docs.getui.com/getui/server/rest_v2/user/#10)             | `com.getui.push.v2.sdk.api.UserApi.addBlackUser`                    |
| User API       | [Remove User from Blocklist](https://docs.getui.com/getui/server/rest_v2/user/#11)        | `com.getui.push.v2.sdk.api.UserApi.removeBlackUser`                 |
| User API       | [Query User Status](https://docs.getui.com/getui/server/rest_v2/user/#12)                 | `com.getui.push.v2.sdk.api.UserApi.queryUserStatus`                 |
| User API       | [Set Badge (iOS only)](https://docs.getui.com/getui/server/rest_v2/user/#13)              | `com.getui.push.v2.sdk.api.UserApi.setBadge`                        |
| User API       | [Query Total Users](https://docs.getui.com/getui/server/rest_v2/user/#14)                 | `com.getui.push.v2.sdk.api.UserApi.queryUser`                       |

> Note: More APIs are being added continuously. Stay tuned.

## Guide for Adding New API Interfaces

1. Create a new API interface class and annotate it with `com.getui.push.v2.sdk.anno.GtApi` to mark it as a Getui API
   class.

2. For each interface method, use one of the method annotations from the `com.getui.push.v2.sdk.anno.method` package —
   `GtGet`, `GtPost`, `GtPut`, or `GtDelete` — to indicate the HTTP method (`GET`, `POST`, `PUT`, `DELETE`).

3. For parameters, use the parameter annotations from the `com.getui.push.v2.sdk.anno.param` package — `GtPathParam`,
   `GtHeaderParam`, `GtQueryParam`, or `GtBodyParam` — to indicate the parameter type: path parameter, header parameter,
   query parameter, or request body parameter.

## Related Links

[Getui Developer Platform](https://docs.getui.com/)