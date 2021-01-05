package com.getui.push.v2.sdk.core.status;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StateWrapper {

    final ConcurrentMap<String, ServiceState> uriToServiceState = new ConcurrentHashMap<String, ServiceState>();

    private String host;

    public StateWrapper(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public ConcurrentMap<String, ServiceState> getUriToServiceState() {
        return uriToServiceState;
    }

    public ServiceState get(String uri) {
        ServiceState serviceState = uriToServiceState.get(uri);
        if (serviceState != null) {
            return serviceState;
        }
        synchronized (uriToServiceState) {
            serviceState = uriToServiceState.get(uri);
            if (serviceState != null) {
                return serviceState;
            }
            serviceState = new ServiceState();
            uriToServiceState.put(uri, serviceState);
            return serviceState;
        }
    }

}