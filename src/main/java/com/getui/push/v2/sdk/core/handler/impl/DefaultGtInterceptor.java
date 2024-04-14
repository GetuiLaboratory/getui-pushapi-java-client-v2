package com.getui.push.v2.sdk.core.handler.impl;

import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.common.Monitor;
import com.getui.push.v2.sdk.core.factory.GtApiProxyFactory;
import com.getui.push.v2.sdk.core.handler.GtInterceptor;
import com.getui.push.v2.sdk.core.manager.HostManager;
import com.getui.push.v2.sdk.core.status.ServiceState;
import com.getui.push.v2.sdk.core.status.StateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by getui on 2020/9/28
 *
 * @author getui
 */
public class DefaultGtInterceptor implements GtInterceptor {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    private final ConcurrentMap<String, StateWrapper> stateWrapperMap = new ConcurrentHashMap<String, StateWrapper>();

    private final AtomicInteger failNum = new AtomicInteger(0);

    private final HostManager hostManager;
    private final BlockingQueue<StateWrapper> reportDataQueue;
    private final GtApiConfiguration configuration;

    /**
     * 域名切换时加锁，防止短时间内重复切换
     */
    final Lock switchLock = new ReentrantLock();

    public DefaultGtInterceptor(HostManager hostManager, BlockingQueue<StateWrapper> reportDataQueue, GtApiConfiguration configuration) {
        this.hostManager = hostManager;
        this.reportDataQueue = reportDataQueue;
        this.configuration = configuration;
        if (configuration.isOpenAnalyseStableDomainSwitch()) {
            Monitor.init(configuration.getCheckMaxFailedNumInterval());
        }
    }

    @Override
    public void pre(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body) {
        START_TIME.set(System.currentTimeMillis());
    }

    @Override
    public void post(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, String result) {
        log.debug("success. param: {}, result: {}.", apiParam, result);
        failNum.set(0);
    }

    @Override
    public void handleException(String host, GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, ApiException e) {
        log.error("http error. param: {}, body: {}.", apiParam, body, e);
        if (configuration.isOpenCheckHealthDataSwitch()) {
            ServiceState serviceState = get(hostManager.getUsing()).get(apiParam.getUri());
            serviceState.incrFailedTimes();
        }
        // 连续失败次数达到阈值
        int num = failNum.incrementAndGet();
        if (num > configuration.getContinuousFailedNum()) {
            resetAndSwitchHost(host, "continuous", num);
        }
    }

    @Override
    public void afterCompletion(String host, GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, String result) {
        try {
            if (configuration.isOpenCheckHealthDataSwitch()) {
                long cost = System.currentTimeMillis() - START_TIME.get();
                final ServiceState serviceState = get(host).get(apiParam.getUri());
                serviceState.addCallTime(cost);
                serviceState.incrCallTimes();
            }
            // 单位时间内失败次数达到阈值，切换域名
            long failedTotal = Monitor.get(host);
            if (failedTotal > configuration.getMaxFailedNum()) {
                resetAndSwitchHost(host, "total", failedTotal);
            }
        } finally {
            START_TIME.remove();
        }
    }

    void resetAndSwitchHost(String host, String reason, long failedNum) {
        boolean locked = switchLock.tryLock();
        if (!locked) {
            return;
        }
        try {
            // 失败次数重置
            Monitor.reset(host);
            failNum.set(0);
            if (configuration.isOpenCheckHealthDataSwitch()) {
                this.reportDataQueue.offer(getAndRemove(host));
            }
            if (configuration.isOpenAnalyseStableDomainSwitch()) {
                log.debug("The number of failures has reached the threshold, will switch host. reason:{}, failedNum:{}", reason, failedNum);
                // 切换域名
                hostManager.switchHost(host);
            }
        } finally {
            switchLock.unlock();
        }
    }

    private StateWrapper get(String host) {
        StateWrapper stateWrapper = stateWrapperMap.get(host);
        if (stateWrapper != null) {
            return stateWrapper;
        }
        synchronized (stateWrapperMap) {
            stateWrapper = stateWrapperMap.get(host);
            if (stateWrapper != null) {
                return stateWrapper;
            }
            stateWrapper = new StateWrapper(host);
            stateWrapperMap.put(host, stateWrapper);
            return stateWrapper;
        }
    }

    public StateWrapper getAndRemove(String host) {
        return stateWrapperMap.remove(host);
    }

}
