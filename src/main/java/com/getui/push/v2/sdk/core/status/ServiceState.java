package com.getui.push.v2.sdk.core.status;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ServiceState {
    private AtomicInteger callTimes = new AtomicInteger(0);
    private AtomicInteger failedTimes = new AtomicInteger(0);
    private AtomicLong callAllTime = new AtomicLong(0);

    public void incrCallTimes() {
        this.callTimes.incrementAndGet();
    }

    public void addCallTime(long millis) {
        this.callAllTime.addAndGet(millis);
    }

    public void incrFailedTimes() {
        this.failedTimes.incrementAndGet();
    }

    public AtomicInteger getCallTimes() {
        return callTimes;
    }

    public void setCallTimes(AtomicInteger callTimes) {
        this.callTimes = callTimes;
    }

    public AtomicInteger getFailedTimes() {
        return failedTimes;
    }

    public void setFailedTimes(AtomicInteger failedTimes) {
        this.failedTimes = failedTimes;
    }

    public AtomicLong getCallAllTime() {
        return callAllTime;
    }

    public void setCallAllTime(AtomicLong callAllTime) {
        this.callAllTime = callAllTime;
    }
}