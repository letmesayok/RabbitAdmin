package com.rabbit.common.constants;

/**
 * @author wangql
 * 公共状态码
 */
public enum CommonCode implements ResultCode{
    // 全局状态码
    SUCCESS(1000, "操作成功！"),
    FAIL(1111, "操作失败！");

    boolean success;
    int code;
    String message;

    CommonCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
