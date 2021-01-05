package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.dto.CommonEnum;

import java.util.HashSet;
import java.util.Set;

/**
 * 标签
 */
public class Condition {
    private String key;
    private Set<String> values;
    /**
     * 条件关联方式
     *
     * @see CommonEnum.OptTypeEnum
     */
    private String optType;

    public Condition addValue(String value) {
        if (this.values == null) {
            this.values = new HashSet<String>();
        }
        this.values.add(value);
        return this;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "key='" + key + '\'' +
                ", values=" + values +
                ", optType='" + optType + '\'' +
                '}';
    }
}