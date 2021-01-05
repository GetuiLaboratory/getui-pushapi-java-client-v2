package com.getui.push.v2.sdk.core.handler.impl;

import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.core.Configs;
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

/**
 * create by getui on 2020/9/28
 *
 * @author getui
 */
public class DefaultGtInterceptor implements GtInterceptor {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<Long>();

    private final ConcurrentMap<String, StateWrapper> stateWrapperMap = new ConcurrentHashMap<String, StateWrapper>();

    private final AtomicInteger failNum = new AtomicInteger(0);

    private final HostManager hostManager;
    private final BlockingQueue<StateWrapper> reportDataQueue;
    private final GtApiConfiguration configuration;

    public DefaultGtInterceptor(HostManager hostManager, BlockingQueue<StateWrapper> reportDataQueue, GtApiConfiguration configuration) {
        this.hostManager = hostManager;
        this.reportDataQueue = reportDataQueue;
        this.configuration = configuration;
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
    public void handleException(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, ApiException e) {
        log.error("http error. param: {}, body: {}.", apiParam, body, e);
        if (configuration.isOpenCheckHealthDataSwitch()) {
            ServiceState serviceState = get(hostManager.getUsing()).get(apiParam.getUri());
            serviceState.incrFailedTimes();
        }
        if (failNum.incrementAndGet() > Configs.MAX_FAIL_CONTINUOUSLY) {
            log.debug("The num of failures are frequent.");
            failNum.set(0);
            if (configuration.isOpenCheckHealthDataSwitch()) {
                this.reportDataQueue.offer(getAndRemove(hostManager.getUsing()));
            }
            if (configuration.isOpenAnalyseStableDomainSwitch()) {
                // 切换域名
                hostManager.switchHost();
            }
        }
    }

    @Override
    public void afterCompletion(GtApiProxyFactory.ApiParam apiParam, Map<String, Object> header, String body, String result) {
        try {
            if (configuration.isOpenCheckHealthDataSwitch()) {
                long cost = System.currentTimeMillis() - START_TIME.get();
                final ServiceState serviceState = get(hostManager.getUsing()).get(apiParam.getUri());
                serviceState.addCallTime(cost);
                serviceState.incrCallTimes();
            }
        } finally {
            START_TIME.remove();
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
