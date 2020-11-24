package com.rabbit.project.constants;

import com.rabbit.common.constants.ResultCode;

/**
 * 用户相关操作返回枚举
 * @author wangql
 */
public enum UserCode implements ResultCode {
    // 用户状态
    LOGOUT_SUCCESS(true, 2000, "注销成功"),
    USERNAME_NOT_FOUND(false, 2333, "用户名不存在"),
    PASSWORD_WRONG(false, 2444, "密码错误"),
    USER_IS_LOCKED(false, 2444, "用户已冻结"),
    NOT_LOGIN(false, 2111, "未登录！"),
    UNAUTHORIZED(false, 2222, "未授权！");

    private boolean success;
    private int code;
    private String message;

    private UserCode(boolean success, int code, String message) {
        this.success = success;
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
