package com.getui.push.v2.sdk.dto.res;

/**
 * create by getui on 2020/6/3
 *
 * @author getui
 */
public class AliasResDTO {
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "AliasResDTO{" +
                "alias='" + alias + '\'' +
                '}';
    }
}
