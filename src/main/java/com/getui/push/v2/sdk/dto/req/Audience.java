package com.getui.push.v2.sdk.dto.req;

import java.util.ArrayList;
import java.util.List;

/**
 * 接收人
 */
public class Audience {
    private List<String> cid;
    private List<String> alias;
    private String fastCustomTag;
    private List<Condition> tag;
    private String all;

    public void addCid(String cid) {
        if (this.cid == null) {
            this.cid = new ArrayList<String>();
        }
        this.cid.add(cid);
    }

    public void addAlias(String alias) {
        if (this.alias == null) {
            this.alias = new ArrayList<String>();
        }
        this.alias.add(alias);
    }

    public void addCondition(Condition condition) {
        if (this.tag == null) {
            this.tag = new ArrayList<Condition>();
        }
        this.tag.add(condition);
    }

    public List<String> getCid() {
        return cid;
    }

    public void setCid(List<String> cid) {
        this.cid = cid;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public String getFastCustomTag() {
        return fastCustomTag;
    }

    public void setFastCustomTag(String fastCustomTag) {
        this.fastCustomTag = fastCustomTag;
    }

    public List<Condition> getTag() {
        return tag;
    }

    public void setTag(List<Condition> tag) {
        this.tag = tag;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Audience{" +
                "cid=" + cid +
                ", alias=" + alias +
                ", fastCustomTag='" + fastCustomTag + '\'' +
                ", tag=" + tag +
                ", all='" + all + '\'' +
                '}';
    }
}