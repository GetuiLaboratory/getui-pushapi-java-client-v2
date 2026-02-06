# Release Notes

## 1.0.8.0

### update

* 统计API(StatisticApi) Query参数完善
* 执行别名批量推API(/push/list/alias) 新增适配需要返回别名详情响应体的方法
* 新增active_push_platform参数控制需要启用亮屏推送的平台

## 1.0.7.0

### update

* 修改定时任务查询返回状态字段
* strategy支持设置荣耀ho、鸿蒙华为hoshw
* settings支持亮屏推送参数active_push、lst

## 1.0.6.0

### update

* 鸿蒙厂商通道通知消息支持自定义消息、消息覆盖和消息撤回
* 关闭CloseableHttpClient内默认的重试机制

## 1.0.5.0

### update

* 鸿蒙厂商通道通知消息click_type新增支持payload
* 鸿蒙个推通道通知消息支持设置通知渠道类型slotType
* GtHttpClient支持设置maxConnTotal和maxConnPerRoute

## 1.0.4.0

### update

* 支持活跃行为过滤功能
* 新增pushToSingleCidByTag和pushListCidByTag推送接口

## 1.0.3.0

### update

* 支持灵动岛

## 1.0.2.1

### update

* 升级依赖，修复检测漏洞

## 1.0.2.0

### update

* 鸿蒙离线支持传参want

## 1.0.1.0

### update

* 支持jdk版本 1.8及以上版本，不再支持jdk1.6
* 重试支持切换域名
* 支持设置接口维度的超时时间
* 修改默认的连接超时时间，60s改为10s
* 内部异常，会拼接到apiResult的msg上，eg. 原来接口超时返回`{"code":5000, "message":"http error::5000"}`，修改后返回`{"
  code":5000, "message":"http error:Read timed out::5000"}

## 1.0.0.17

### update

* 鸿蒙在线支持传参want

## 1.0.0.16

### update

* 支持别名类型
* 增加鸿蒙平台厂商参数

## 1.0.0.15

### update

* 支持本地消息分类
* 最稳定域名检测开关支持通过启动项配置
* 设置非个推域名时强制关闭稳定域名检测

## 1.0.0.14

### update

* 支持消息重弹功能

## 1.0.0.13

### update

* 厂商智能配额策略支持兜底
* 修复反参数据类型解析问题

## 1.0.0.12

### update

* 支持厂商智能配额策略
* 支持通知关闭过滤

## 1.0.0.11

### update

* 支持安卓厂商消息撤回
* HTTPS协议更换(SSLv3 -> TLSv1.2)

## 1.0.0.10

### update

* 新增查询推送量和获取推送实时结果接口
* 报表相关接口反参解析问题修复
* 部分接口入参规范

## 1.0.0.9

### update

* 新增自定义回执字段SVIP功能

## 1.0.0.8

### update

* ApiHelper和DefaultApiClient 缓存key支持设置自定义前缀，满足客户特殊使用场景
* 完善参数和注释

## 1.0.0.7

### Fix

* 修复超时重试http连接未释放问题

### update

* 移除guava依赖
* 支持设置获取连接池中http连接超时时间

## 1.0.0.6

### Fix

修复并发请求消息体异常问题

## 1.0.0.5

### Fix

修复长连接单位错误问题 由分钟改为秒

## 1.0.0.4

### Features

支持设置长连接有效期

## 1.0.0.3

### Features

1. ios支持自定义参数

### Fix

1. 修复jdk11 json反序列化报错问题： ParameterizedTypeImpl 类找不到
2. 修复jdk1.6 https证书错误问题
3. 服务端返回500支持重试
4. 修复首次鉴权失败后空指针异常

## 1.0.0.2

### Features

1. 支持设置httpclient最大连接数，解决并发大时获取连接慢问题
