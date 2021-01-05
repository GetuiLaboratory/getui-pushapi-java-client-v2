package com.getui.push.v2.sdk.core.domain;

import java.util.ArrayList;
import java.util.List;

public class DomainListBO {
    private List<String> domainList;
    private Integer priorityGap = 30;

    public List<String> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }

    public Integer getPriorityGap() {
        return priorityGap;
    }

    public void setPriorityGap(Integer priorityGap) {
        this.priorityGap = priorityGap;
    }

    public void addDomain(String domainBO) {
        if (domainList == null) {
            domainList = new ArrayList<String>();
        }
        domainList.add(domainBO);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DomainListBO{");
        sb.append("domainList=").append(domainList);
        sb.append(", priorityGap=").append(priorityGap);
        sb.append('}');
        return sb.toString();
    }
}