package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.common.util.Utils;

import java.util.HashSet;
import java.util.Set;

/**
 * create by getui on 2020/6/4
 *
 * @author getui
 */
public class TagDTO implements BaseReqDTO {

    private Set<String> customTag;

    public static TagDTO build() {
        return new TagDTO();
    }

    public TagDTO addTag(String tag) {
        if (Utils.isEmpty(tag)) {
            return this;
        }
        if (customTag == null) {
            customTag = new HashSet<String>();
        }
        customTag.add(tag);
        return this;
    }

    @Override
    public void check() throws ApiException {
    }

    public Set<String> getCustomTag() {
        return customTag;
    }

    public void setCustomTag(Set<String> customTag) {
        this.customTag = customTag;
    }

    @Override
    public String toString() {
        return "TagDTO{" +
                "customTag=" + customTag +
                '}';
    }
}
