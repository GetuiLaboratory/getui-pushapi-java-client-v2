package com.getui.push.v2.sdk.dto.req;

import com.getui.push.v2.sdk.common.ApiException;
import com.getui.push.v2.sdk.dto.BaseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * create by getui on 2020/6/3
 *
 * @author getui
 */
public class CidAliasListDTO implements BaseReqDTO {

    private List<CidAlias> dataList;

    public CidAliasListDTO add(CidAlias cidAlias) {
        if (dataList == null) {
            dataList = new ArrayList<CidAlias>();
        }
        dataList.add(cidAlias);
        return this;
    }

    @Override
    public void check() throws ApiException {
    }

    public static class CidAlias implements BaseDTO {
        private String cid;
        private String alias;

        @Override
        public void check() throws ApiException {
        }

        public CidAlias(String cid, String alias) {
            this.cid = cid;
            this.alias = alias;
        }

        public CidAlias() {
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }
    }

    public List<CidAlias> getDataList() {
        return dataList;
    }

    public void setDataList(List<CidAlias> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        return "CidAliasListDTO{" +
                "dataList=" + dataList +
                '}';
    }
}
