package com.getui.push.v2.sdk.dto.res;

import java.util.List;

/**
 * 添加测试cid的结果。
 */
public class TestCidAddResDTO {

    private List<String> success;
    private List<String> failed;

    public List<String> getSuccess() {
        return success;
    }

    public void setSuccess(List<String> success) {
        this.success = success;
    }

    public List<String> getFailed() {
        return failed;
    }

    public void setFailed(List<String> failed) {
        this.failed = failed;
    }

    @Override
    public String toString() {
        return "TestCidAddResDTO{" +
                "success=" + success +
                ", failed=" + failed +
                '}';
    }
}
