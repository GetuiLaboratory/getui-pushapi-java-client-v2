package com.getui.push.v2.sdk.common;

import com.getui.push.v2.sdk.common.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by getui on 2024/4/8
 *
 * @author getui
 */
public class Monitor {
    private static Logger log = LoggerFactory.getLogger(Monitor.class);
    /**
     * 单位时间内失败总数
     */
    static volatile Map<String, AtomicInteger> hostToFailedNumMap;
    static volatile boolean MONITOR_ENABLE = false;

    public static void init(long refreshTimes) {
        hostToFailedNumMap = new ConcurrentHashMap<>(16);
        MONITOR_ENABLE = true;
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(refreshTimes);
                    log.debug("will reset monitor|{}", hostToFailedNumMap);
                    Iterator<Map.Entry<String, AtomicInteger>> iterator = hostToFailedNumMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        iterator.next().getValue().set(0);
                    }
                } catch (Throwable e) {
                }
            }
        });
        thread.setDaemon(true);
        thread.setName("gtResetMonitor");
        thread.start();
    }

    public static int get(String host) {
        if (!MONITOR_ENABLE || host == null) {
            return 0;
        }
        AtomicInteger num = hostToFailedNumMap.computeIfAbsent(Utils.v2UrlToHost(host), (k) -> new AtomicInteger());
        return num.get();
    }

    public static void reset(String host) {
        if (!MONITOR_ENABLE || host == null) {
            return;
        }
        AtomicInteger num = hostToFailedNumMap.computeIfAbsent(Utils.v2UrlToHost(host), (k) -> new AtomicInteger());
        num.set(0);
    }

    public static void incrementFailedNum(String host) {
        if (!MONITOR_ENABLE || host == null) {
            return;
        }
        AtomicInteger num = hostToFailedNumMap.computeIfAbsent(Utils.v2UrlToHost(host), (k) -> new AtomicInteger());
        num.incrementAndGet();
    }

}
