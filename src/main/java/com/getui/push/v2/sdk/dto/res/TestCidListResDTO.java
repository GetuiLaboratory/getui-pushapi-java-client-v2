package com.getui.push.v2.sdk.dto.res;

import java.util.List;

/**
 * 测试cid列表查询结果。
 */
public class TestCidListResDTO {

    private List<TestCid> list;
    private Pagination pagination;

    public List<TestCid> getList() {
        return list;
    }

    public void setList(List<TestCid> list) {
        this.list = list;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "TestCidListResDTO{" +
                "list=" + list +
                ", pagination=" + pagination +
                '}';
    }

    public static class TestCid {
        private String cid;
        private String remark;
        private String phoneType;
        private String updateTime;
        private String createTime;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPhoneType() {
            return phoneType;
        }

        public void setPhoneType(String phoneType) {
            this.phoneType = phoneType;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        @Override
        public String toString() {
            return "TestCid{" +
                    "cid='" + cid + '\'' +
                    ", remark='" + remark + '\'' +
                    ", phoneType='" + phoneType + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", createTime='" + createTime + '\'' +
                    '}';
        }
    }

    public static class Pagination {
        private Integer totalPage;
        private Integer pageSize;
        private Integer total;
        private Integer currentPage;

        public Integer getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(Integer totalPage) {
            this.totalPage = totalPage;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        @Override
        public String toString() {
            return "Pagination{" +
                    "totalPage=" + totalPage +
                    ", pageSize=" + pageSize +
                    ", total=" + total +
                    ", currentPage=" + currentPage +
                    '}';
        }
    }
}
