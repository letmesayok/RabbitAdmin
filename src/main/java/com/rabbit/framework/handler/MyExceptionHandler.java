package com.rabbit.framework.handler;

import com.rabbit.common.domain.ApiResponse;
import com.rabbit.common.exception.CommonException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangql
 * 统一异常捕获类
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(CommonException.class)
    @ResponseBody
    public ApiResponse handler(CommonException exception) {
        return new ApiResponse(exception.getResultCode());
    }
}
