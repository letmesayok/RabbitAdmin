package com.rabbit.framework.handler;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.common.exception.CommonException;
import com.rabbit.project.constants.UserCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangql
 * 统一异常捕获类
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ApiResponse handler(CommonException exception) {
        // 自定义异常
        return new ApiResponse(exception.getResultCode());
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public ApiResponse handlerNotLoginException() {
        // 未登录
        return new ApiResponse(UserCode.NOT_LOGIN);
    }

    @ExceptionHandler(NotPermissionException.class)
    @ResponseBody
    public ApiResponse handlerNotPermissionException() {
        // 未授权
        return new ApiResponse(UserCode.NOT_LOGIN);
    }
}
