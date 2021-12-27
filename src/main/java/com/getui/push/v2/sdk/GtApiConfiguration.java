package com.getui.push.v2.sdk;

import com.getui.push.v2.sdk.common.Assert;
import org.apache.http.client.config.RequestConfig;

/**
 * 应用相关配置信息
 * create by getui on 2020/6/4
 *
 * @author getui
 */
public class GtApiConfiguration {

    /**
     * 应用id
     */
    private String appId;
    /**
     * 应用key
     */
    private String appKey;
    /**
     * 应用秘钥
     */
    private String masterSecret;
    /**
     * 接口调用前缀, 可不包含{@link #appId}
     * eg. https://restapi.getui.com/v2
     */
    private String domain = "https://restapi.getui.com/v2";

    /**
     * 是否开启最稳定域名检测，默认开启
     */
    private boolean openAnalyseStableDomainSwitch = true;

    /**
     * 检测最稳定域名时间间隔，默认2分钟检测一次
     */
    private long analyseStableDomainInterval = 2 * 60 * 1000;

    /**
     * 如果遇到域名请求地址不断变化或需要排查网络耗时等问题，可以开启此接口（方法）功能后，联系个推技术支持
     * 健康度检查动态开关，true表示开启，否则关闭，不设置则取 {@link #openAnalyseStableDomainSwitch}
     */
    public final static String CHECK_HEALTH_DATA_SWITCH_KEY = "gt_healthy_switch";
    /**
     * 健康检测时间间隔
     */
    private long checkHealthInterval = 30 * 1000;

    /**
     * http请求读超时时间，单位ms
     *
     * @see RequestConfig#getSocketTimeout()
     */
    private int soTimeout = 30000;
    /**
     * http连接超时时间，单位ms
     *
     * @see RequestConfig#getConnectTimeout()
     */
    private int connectTimeout = 60000;
    /**
     * http请求失败，最大尝试次数
     */
    private int maxHttpTryTime = 1;
    /**
     * 保持长连接的时长，默认10分钟
     */
    private long keepAliveMinutes = 10;

    /**
     * http请求时是否需要信任https
     */
    private boolean trustSSL = false;

    /**
     * http请求设置代理，默认不设置
     */
    private GtHttpProxyConfig proxyConfig;

    /**
     * @param domain 接口调用前缀, 可不含{@link #appId}
     */
    public void setDomain(String domain) {
        Assert.notBlank(domain, true);
        this.domain = domain;
    }

    public void check() {
        Assert.notBlank(appId, true);
        Assert.notBlank(appKey, true);
        Assert.notBlank(masterSecret, true);
        Assert.notBlank(domain, true);
    }

    public boolean isTrustSSL() {
        return trustSSL;
    }

    public void setTrustSSL(boolean trustSSL) {
        this.trustSSL = trustSSL;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public String getDomain() {
        return domain;
    }

    public boolean isOpenAnalyseStableDomainSwitch() {
        return openAnalyseStableDomainSwitch;
    }

    public void setOpenAnalyseStableDomainSwitch(boolean openAnalyseStableDomainSwitch) {
        this.openAnalyseStableDomainSwitch = openAnalyseStableDomainSwitch;
    }

    public long getAnalyseStableDomainInterval() {
        return analyseStableDomainInterval;
    }

    public void setAnalyseStableDomainInterval(long analyseStableDomainInterval) {
        this.analyseStableDomainInterval = analyseStableDomainInterval;
    }

    public boolean isOpenCheckHealthDataSwitch() {
        return Boolean.getBoolean(CHECK_HEALTH_DATA_SWITCH_KEY);
    }

    public void setOpenCheckHealthDataSwitch(boolean openCheckHealthDataSwitch) {
        System.setProperty(CHECK_HEALTH_DATA_SWITCH_KEY, String.valueOf(openCheckHealthDataSwitch));
    }

    public long getCheckHealthInterval() {
        return checkHealthInterval;
    }

    public void setCheckHealthInterval(long checkHealthInterval) {
        this.checkHealthInterval = checkHealthInterval;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getMaxHttpTryTime() {
        return maxHttpTryTime;
    }

    public void setMaxHttpTryTime(int maxHttpTryTime) {
        this.maxHttpTryTime = maxHttpTryTime;
    }

    public long getKeepAliveMinutes() {
        return keepAliveMinutes;
    }

    public void setKeepAliveMinutes(long keepAliveMinutes) {
        this.keepAliveMinutes = keepAliveMinutes;
    }

    public GtHttpProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    public void setProxyConfig(GtHttpProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GtApiConfiguration that = (GtApiConfiguration) o;

        if (!appId.equals(that.appId)) {
            return false;
        }
        if (!appKey.equals(that.appKey)) {
            return false;
        }
        return masterSecret.equals(that.masterSecret);
    }

    @Override
    public int hashCode() {
        int result = appId.hashCode();
        result = 31 * result + appKey.hashCode();
        result = 31 * result + masterSecret.hashCode();
        return result;
    }

    /**
     * 生成缓存的key
     *
     * @return 缓存key
     */
    public String keyOfCache() {
        check();
        return String.format("%s|%s|%s", this.getAppId(), this.getAppKey(), this.getMasterSecret());
    }

    /**
     * 缓存key的前缀
     *
     * @return {@link #keyOfCache()}的前缀，用于修改masterSecret
     */
    public String prefixOfKey() {
        check();
        return String.format("%s|%s", this.getAppId(), this.getAppKey());
    }

}
