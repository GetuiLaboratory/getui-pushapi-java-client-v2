package com.getui.push.v2.sdk.core.domain;

import com.getui.push.v2.sdk.common.util.Utils;

import java.util.*;

/**
 * create by getui on 2020/7/21
 *
 * @author getui
 */
public class DomainCheck {

    private IDomainCheck check;

    /**
     * 检测次数
     */
    private final int checkSize;

    public DomainCheck(IDomainCheck check) {
        this(check, 20);
    }

    public DomainCheck(IDomainCheck check, int checkSize) {
        this.check = check;
        this.checkSize = checkSize;
    }

    /**
     * 优先级+稳定性 域名排序
     */
    public List<String> sort(List<DomainListBO> list) {
        Map<String, Integer> domainTOGapMap = initGap(list);
        List<String> domainList = getALlDomain(list);
        Map<String, Integer> map = doCheck(domainList, domainTOGapMap);
        return doSort(map);
    }

    /**
     * @param list
     * @return
     */
    private List<String> getALlDomain(List<DomainListBO> list) {
        List<String> domainList = new ArrayList<String>();
        for (DomainListBO domainListBO : list) {
            if (Utils.isNotEmpty(domainListBO.getDomainList())) {
                domainList.addAll(domainListBO.getDomainList());
            }
        }
        return domainList;
    }

    private Map<String, Integer> doCheck(List<String> domainList, Map<String, Integer> domainToGapMap) {
        Map<String, Integer> detectMap = new HashMap<String, Integer>(domainList.size(), 1);
        for (int i = 0; i < checkSize; i++) {
            //遍历所有的域名进行探测
            for (String domain : domainList) {
                //遍历所有的域名进行探测
                if (detectMap.get(domain) == null) {
                    detectMap.put(domain, 0);
                }
                boolean rst = false;
                try {
                    rst = check.check(domain);
                } catch (Exception e) {
                }
                if (rst) {
                    detectMap.put(domain, detectMap.get(domain) + 1);
                }
            }
            try {
                //拉长时间保证稳定性
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //是否所有域名不可用
        boolean allBad = true;
        //算出 域名-稳定分数
        for (String key : detectMap.keySet()) {
            if (detectMap.get(key) != 0) {
                allBad = false;
            }
            int score = (detectMap.get(key) * 100) / checkSize + domainToGapMap.get(key);
            detectMap.put(key, score);
        }
        if (allBad) {
            return Collections.emptyMap();
        }
        return detectMap;
    }

    /**
     * 排序
     */
    private List<String> doSort(Map<String, Integer> detectMap) {
        List<String> result = new ArrayList<String>();
        if (detectMap == null || detectMap.size() == 0) {
            return null;
        }
        String domain = null;
        Integer max = 0;
        while (detectMap.size() != 0) {
            for (Map.Entry<String, Integer> entry : detectMap.entrySet()) {
                if (entry.getValue() >= max) {
                    max = entry.getValue();
                    domain = entry.getKey();
                }
            }
            max = 0;
            result.add(domain);
            detectMap.remove(domain);
        }
        return result;
    }

    private Map<String, Integer> initGap(List<DomainListBO> list) {
        //获取所有的  域名-优先级gap
        Map<String, Integer> map = new HashMap<String, Integer>(list.size(), 1);
        int gap = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (i != list.size() - 1) {
                gap += list.get(i).getPriorityGap();
            }
            for (String t : list.get(i).getDomainList()) {
                map.put(t, gap);
            }
        }
        return map;
    }

}
