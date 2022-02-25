# Release Notes

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
