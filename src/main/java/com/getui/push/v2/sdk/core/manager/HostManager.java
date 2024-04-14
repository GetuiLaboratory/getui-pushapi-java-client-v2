package com.getui.push.v2.sdk.core.manager;

import com.getui.push.v2.sdk.GtApiConfiguration;
import com.getui.push.v2.sdk.common.http.HttpManager;
import com.getui.push.v2.sdk.common.util.Utils;
import com.getui.push.v2.sdk.core.Configs;
import com.getui.push.v2.sdk.core.domain.DomainCheck;
import com.getui.push.v2.sdk.core.domain.DomainListBO;
import com.getui.push.v2.sdk.core.domain.IDomainCheck;
import com.getui.push.v2.sdk.core.domain.RasDomainBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by getui on 2020/9/28
 *
 * @author getui
 */
public class HostManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    volatile ThreadPoolExecutor analyseStableDomainExecutor;

    public HostManager(GtApiConfiguration configuration, HttpManager httpManager) {
        this.configuration = configuration;
        this.using = configuration.getDomain();
        this.httpManager = httpManager;
    }

    private final HttpManager httpManager;

    private final GtApiConfiguration configuration;

    /**
     * 正在使用的域名
     */
    private volatile String using;
    /**
     * 切换域名的锁
     */
    private final Lock switchUsingLock = new ReentrantLock();

    /**
     * 天上拉下来的域名
     */
    private volatile RasDomainBO rasDomain;
    private final Object setRasDomainLock = new Object();

    /**
     * {@link #rasDomain} 经过优先级算法排序后的结果
     */
    private volatile List<String> sortedDomains;

    /**
     * 域名优先级队列
     */
    private volatile BlockingQueue<String> sortedHostQueue;

    /**
     * 增量，域名切换次数
     */
    private final AtomicInteger switchIncr = new AtomicInteger(0);
    /**
     * 总量，域名切换次数
     */
    private final AtomicInteger switchTotal = new AtomicInteger(0);

    public String getUsing() {
        return this.using;
    }

    /**
     * 处理天上返回的域名
     *
     * @param domainBO
     */
    public void handleDomain(RasDomainBO domainBO) {
        if (domainBO == null || Utils.isEmpty(domainBO.getHostList())) {
            return;
        }
        if (setRasDomainBO(domainBO)) {
            // 清空已有域名，确保域名队列中天上域名在最前面(本地域名可能存在跨机房等问题)
            if (!Utils.isEmpty(sortedDomains)) {
                sortedDomains.clear();
            }
            resetHostQueue();
            if (Utils.isEmpty(domainBO.getHostList().get(0).getDomainList())) {
                logger.warn("domain list is empty, please check");
                return;
            }
            // 不确定是域名改变还是跨机房，所以必须切域名(如果天上域名不可用，重试会换域名)
            switchTo(domainBO.getHostList().get(0).getDomainList().get(0));
        }
    }

    /**
     * @param willUse
     */
    private void switchTo(String willUse) {
        if (Utils.isNotEmpty(willUse) && willUse.equals(this.using)) {
            return;
        }
        logger.debug("switch host. old: {}, new: {}.", this.using, willUse);
        switchIncr.incrementAndGet();
        switchTotal.incrementAndGet();
        this.using = willUse;
    }

    /**
     * 切换域名
     *
     * @param oldHost 上次使用的域名
     */
    public void switchHost(String oldHost) {
        if (oldHost != null && !oldHost.equalsIgnoreCase(using)) {
            // 表示已切换
            return;
        }
        switchHost();
    }

    /**
     * 切换域名
     */
    public void switchHost() {
        if (Utils.isEmpty(sortedHostQueue)) {
            resetHostQueue();
        }
        if (!switchUsingLock.tryLock()) {
            return;
        }
        try {
            final String host = sortedHostQueue.poll();
            if (Utils.isNotEmpty(host)) {
                switchTo(host);
            } else {
                logger.debug("switchHost. hostQueue.poll() return null. host: {}.", host);
                switchTo(configuration.getDomain());
            }
        } finally {
            switchUsingLock.unlock();
        }
    }

    /**
     * 当 {@link #sortedHostQueue}为空时重置
     */
    private synchronized void resetHostQueue() {
        if (Utils.isNotEmpty(sortedHostQueue)) {
            return;
        }
        resetHostQueueHard();
    }

    /**
     * 获取天上的域名列表
     *
     * @return
     */
    public List<String> getCustomizedDomains() {
        RasDomainBO rasDomain = this.rasDomain;
        if (rasDomain == null || rasDomain.getHostList() == null) {
            return Collections.emptyList();
        }
        List<String> domains = new ArrayList<String>();
        for (DomainListBO domainListBO : rasDomain.getHostList()) {
            if (domainListBO == null || domainListBO.getDomainList() == null) {
                continue;
            }
            domains.addAll(domainListBO.getDomainList());
        }
        return domains;
    }

    private void resetHostQueueHard() {
        RasDomainBO rasDomain = this.rasDomain;
        BlockingQueue<String> queue = new LinkedBlockingDeque<String>();
        if (Utils.isEmpty(sortedDomains)) {
            // 优先使用天上的域名，如果跨机房，客户设置的域名就会有问题
            if (rasDomain != null &&
                    Utils.isNotEmpty(rasDomain.getHostList())) {
                for (DomainListBO domainListBO : rasDomain.getHostList()) {
                    if (Utils.isNotEmpty(domainListBO.getDomainList())) {
                        queue.addAll(domainListBO.getDomainList());
                    }
                }
            }
        } else {
            queue.addAll(sortedDomains);
        }
        if (Utils.isNotEmpty(configuration.getDomain())) {
            queue.add(configuration.getDomain());
        }
        if (Utils.isNotEmpty(Configs.URLS)) {
            queue.addAll(Configs.URLS);
        }
        this.sortedHostQueue = queue;
    }

    public RasDomainBO getRasDomain() {
        return rasDomain;
    }

    public boolean setRasDomainBO(RasDomainBO rasDomainBO) {
        if (rasDomainBO == null ||
                Utils.isEmpty(rasDomainBO.getHostList()) ||
                Utils.isEmpty(rasDomainBO.getDomainHash())) {
            return false;
        }
        if (this.rasDomain != null &&
                rasDomainBO.getDomainHash().equalsIgnoreCase(this.rasDomain.getDomainHash())) {
            return false;
        }
        synchronized (setRasDomainLock) {
            if (this.rasDomain != null &&
                    rasDomainBO.getDomainHash().equalsIgnoreCase(this.rasDomain.getDomainHash())) {
                return false;
            }
            this.rasDomain = rasDomainBO;
            return true;
        }
    }

    public BlockingQueue<String> getSortedHostQueue() {
        return sortedHostQueue;
    }

    public void setSortedHostQueue(BlockingQueue<String> sortedHostQueue) {
        this.sortedHostQueue = sortedHostQueue;
    }

    public String domainHash() {
        return rasDomain == null ? null : rasDomain.getDomainHash();
    }

    /**
     * 分析最稳定域名
     */
    public void analyseStableDomain() {
        if (rasDomain == null || Utils.isEmpty(rasDomain.getHostList())) {
            logger.debug("Analysing stopped because the hostList is empty.");
            return;
        }
        if (analyseStableDomainExecutor == null) {
            analyseStableDomainExecutor = new ThreadPoolExecutor(4, 4, 1, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>(10), new ThreadFactory() {
                private final AtomicInteger threadNumber = new AtomicInteger(1);
                private final String namePrefix = "analyseStableDomain-";

                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
                    t.setDaemon(true);
                    return t;
                }
            }, new ThreadPoolExecutor.DiscardPolicy());
        }
        final List<String> sortedHost = new DomainCheck(new IDomainCheck() {
            @Override
            public boolean check(final String url) {
                int socketTimeout = configuration.getHttpCheckTimeout();
                Future<Boolean> future = analyseStableDomainExecutor.submit(() -> {
                    httpManager.syncHttps(url + "/v2/check", null, socketTimeout, "head", null, null, null);
                    return true;
                });
                try {
                    return future.get(socketTimeout, TimeUnit.MILLISECONDS);
                } catch (TimeoutException e) {
                    future.cancel(true);
                } catch (Exception e) {
                }
                return false;
            }
        }).sort(rasDomain.getHostList());
        if (Utils.isNotEmpty(sortedHost)) {
            this.sortedDomains = sortedHost;
        }
        logger.debug("analyseStableDomain finished. domains: {}, result: {}.", rasDomain.getHostList(), sortedHost);
        resetHostQueueHard();
        switchHost();
    }

    public int getSwitchIncrNum() {
        return switchIncr.getAndSet(0);
    }

    public int getSwitchTotalNum() {
        return switchTotal.get();
    }
}
