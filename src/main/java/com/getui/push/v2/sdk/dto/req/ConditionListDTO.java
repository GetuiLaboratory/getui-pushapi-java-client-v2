package com.getui.push.v2.sdk.dto.req;

import java.util.ArrayList;
import java.util.List;

/**
 * create by getui on 2020/6/5
 *
 * @author getui
 */
public class ConditionListDTO {

    private List<Condition> tag;

    public static ConditionListDTO build() {
        return new ConditionListDTO();
    }

    public ConditionListDTO addCondition(Condition condition) {
        // 校验参数
        if (tag == null) {
            tag = new ArrayList<Condition>();
        }
        tag.add(condition);
        return this;
    }

    public List<Condition> getTag() {
        return tag;
    }

    public void setTag(List<Condition> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ConditionListDTO{" +
                "tag=" + tag +
                '}';
    }
}
