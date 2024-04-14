package com.getui.push.v2.sdk;

import com.getui.push.v2.sdk.anno.method.GtDelete;
import com.getui.push.v2.sdk.anno.method.GtGet;
import com.getui.push.v2.sdk.anno.method.GtPost;
import com.getui.push.v2.sdk.anno.method.GtPut;
import com.getui.push.v2.sdk.api.PushApi;
import com.getui.push.v2.sdk.common.Assert;
import com.getui.push.v2.sdk.core.Configs;
import org.apache.http.client.config.RequestConfig;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
     * 接口调用前缀, 不带{@link #appId}
     * eg. https://restapi.getui.com/v2
     */
    private String domain = "https://restapi.getui.com/v2";

    /**
     * 是否开启最稳定域名检测，默认开启
     */
    public final static String ANALYSE_STABLE_DOMAIN_SWITCH_KEY = "gt.analyse.stable.domain.switch";

    /**
     * 个推顶级域名列表，英文逗号分割
     */
    public final static String GT_TOP_LEVEL_DOMAIN_LIST_KEY = "gt.top.level.domain.list";

    /**
     * 个推顶级域名列表的默认值
     */
    private final static String DEFAULT_GT_TOP_LEVEL_DOMAIN_LIST = "getui.com,getui.cn";

    private Set<String> gtTopLevelDomainList;

    /**
     * 检测最稳定域名时间间隔，默认2分钟检测一次
     */
    private long analyseStableDomainInterval = 2 * 60 * 1000;

    /**
     * 如果遇到域名请求地址不断变化或需要排查网络耗时等问题，可以开启此接口（方法）功能后，联系个推技术支持
     * 健康度检查动态开关，true表示开启，否则关闭，不设置则取 {@link #ANALYSE_STABLE_DOMAIN_SWITCH_KEY}
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
    private final String SOCKET_TIMEOUT_KEY = "gt.socket.timeout";
    private int soTimeout = 30000;
    /**
     * http连接超时时间，单位ms
     *
     * @see RequestConfig#getConnectTimeout()
     */
    private final String CONNECT_TIMEOUT_KEY = "gt.connect.timeout";
    private int connectTimeout = 10000;
    /**
     * 从连接池中获取http连接的超时时间，单位ms
     */
    private int connectionRequestTimeout = 0;
    /**
     * http请求失败，最大尝试次数，默认重试1次，0表示不重试
     */
    private final String MAX_HTTP_TRY_TIME_KEY = "gt.max.http.try.times";
    private int maxHttpTryTime = 1;
    /**
     * 保持长连接的时长，最大{@link #MAX_KEEP_ALIVE_SECONDS}
     */
    private long keepAliveSeconds = 30;
    final long MAX_KEEP_ALIVE_SECONDS = 20 * 60;

    /**
     * http请求时是否需要信任https
     */
    private boolean trustSSL = false;

    /**
     * http请求设置代理，默认不设置
     */
    private GtHttpProxyConfig proxyConfig;

    /**
     * 存储uri和socketTimeout，支持设置接口维度socketTimeout
     */
    private Map<String, Integer> uriToSocketTimeoutMap = new ConcurrentHashMap<>();
    /**
     * 最大失败次数，单位时间内达到此阈值，切换域名，默认10次
     */
    public final static String MAX_FAILED_NUM_KEY = "gt.max.failed.num";
    private int maxFailedNum = 10;

    /**
     * 连续失败次数达到阈值，切换域名，默认3
     */
    public final static String CONTINUOUS_FAILED_NUM_KEY = "gt.continuous.failed.num";
    private int continuousFailedNum = Configs.MAX_FAIL_CONTINUOUSLY;

    /**
     * 重置最大失败次数的时间间隔，默认3s
     */
    public final static String CHECK_MAX_FAILED_NUM_INTERVAL_KEY = "gt.check.max.failed.num.interval";
    private long checkMaxFailedNumInterval = 3000;
    /**
     * 域名检测的超时时间，单位ms，默认100ms
     */
    public final static String HTTP_CHECK_TIMEOUT_KEY = "gt.http.check.timeout";
    private int httpCheckTimeout = 100;

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

    private boolean notGtDomain() {
        for (String gtDomain : getGtTopLevelDomainList()) {
            if (domain.contains(gtDomain)) {
                return false;
            }
        }
        return true;
    }


    public Set<String> getGtTopLevelDomainList() {
        if (gtTopLevelDomainList == null) {
            gtTopLevelDomainList = new HashSet<String>(Arrays.asList(System.getProperty(GT_TOP_LEVEL_DOMAIN_LIST_KEY, DEFAULT_GT_TOP_LEVEL_DOMAIN_LIST).split(",")));
        }
        return gtTopLevelDomainList;
    }

    public void setGtTopLevelDomainList(String list) {
        System.setProperty(GT_TOP_LEVEL_DOMAIN_LIST_KEY, list);
    }

    public boolean isOpenAnalyseStableDomainSwitch() {
        if (notGtDomain()) {
            return false;
        }
        return Boolean.parseBoolean(System.getProperty(ANALYSE_STABLE_DOMAIN_SWITCH_KEY, "true"));
    }

    public void setOpenAnalyseStableDomainSwitch(boolean openAnalyseStableDomainSwitch) {
        System.setProperty(ANALYSE_STABLE_DOMAIN_SWITCH_KEY, String.valueOf(openAnalyseStableDomainSwitch));
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
        return Integer.getInteger(SOCKET_TIMEOUT_KEY, soTimeout);
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getConnectTimeout() {
        return Integer.getInteger(CONNECT_TIMEOUT_KEY, connectTimeout);
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getMaxHttpTryTime() {
        return Integer.getInteger(MAX_HTTP_TRY_TIME_KEY, maxHttpTryTime);
    }

    public void setMaxHttpTryTime(int maxHttpTryTime) {
        this.maxHttpTryTime = maxHttpTryTime;
    }

    public long getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(long keepAliveSeconds) {
        if (keepAliveSeconds > MAX_KEEP_ALIVE_SECONDS) {
            this.keepAliveSeconds = MAX_KEEP_ALIVE_SECONDS;
        } else {
            this.keepAliveSeconds = keepAliveSeconds;
        }
    }

    /**
     * replaced by <code>getKeepAliveSeconds()</code>.
     * will be removed in the next version
     *
     * @return
     */
    @Deprecated
    public long getKeepAliveMinutes() {
        return 0;
    }

    /**
     * replaced by <code>setKeepAliveSeconds()</code>.
     * will be removed in the next version
     *
     * @param keepAliveMinutes
     */
    @Deprecated
    public void setKeepAliveMinutes(long keepAliveMinutes) {
    }

    public GtHttpProxyConfig getProxyConfig() {
        return proxyConfig;
    }

    public void setProxyConfig(GtHttpProxyConfig proxyConfig) {
        this.proxyConfig = proxyConfig;
    }

    public int getContinuousFailedNum() {
        return Integer.getInteger(CONTINUOUS_FAILED_NUM_KEY, continuousFailedNum);
    }

    public void setContinuousFailedNum(int continuousFailedNum) {
        this.continuousFailedNum = continuousFailedNum;
    }

    public int getMaxFailedNum() {
        return Integer.getInteger(MAX_FAILED_NUM_KEY, maxFailedNum);
    }

    public void setMaxFailedNum(int maxFailedNum) {
        this.maxFailedNum = maxFailedNum;
    }

    public long getCheckMaxFailedNumInterval() {
        return Long.getLong(CHECK_MAX_FAILED_NUM_INTERVAL_KEY, checkMaxFailedNumInterval);
    }

    public void setCheckMaxFailedNumInterval(long checkMaxFailedNumInterval) {
        this.checkMaxFailedNumInterval = checkMaxFailedNumInterval;
    }

    public int getHttpCheckTimeout() {
        return Integer.getInteger(HTTP_CHECK_TIMEOUT_KEY, httpCheckTimeout);
    }

    public void setHttpCheckTimeout(int httpCheckTimeout) {
        this.httpCheckTimeout = httpCheckTimeout;
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

    /**
     * 针对接口设置超时时间，可根据监控修改合理值
     *
     * @param uri           {@link GtGet#uri()}, {@link GtPost#uri()}, {@link GtDelete#uri()}, {@link GtPut#uri()}
     * @param socketTimeout {@link RequestConfig#getSocketTimeout()}，单位: ms
     */
    public void setCustomSocketTimeout(String uri, int socketTimeout) {
        this.uriToSocketTimeoutMap.put(uri, socketTimeout);
    }

    public void resetConnectAndSocketTimeout() {
        setConnectTimeout(3000);
        setCustomSocketTimeout(PushApi.singleCidUri, 3000);
        setCustomSocketTimeout(PushApi.singleAliasUri, 3000);
        setCustomSocketTimeout(PushApi.singleBatchCidUri, 6000);
        setCustomSocketTimeout(PushApi.singleBatchAliasUri, 6000);
        setCustomSocketTimeout(PushApi.singleBatchAliasUri, 6000);
        setCustomSocketTimeout(PushApi.pushListMessageUri, 3000);
        setCustomSocketTimeout(PushApi.pushListCidUri, 6000);
        setCustomSocketTimeout(PushApi.pushListAliasUri, 6000);
    }

    public int getCustomSocketTimeout(String uri) {
        return Integer.getInteger(uri, this.uriToSocketTimeoutMap.getOrDefault(uri, 0));
    }

}
