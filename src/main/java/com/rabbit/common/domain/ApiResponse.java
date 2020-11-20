package com.rabbit.common.domain;

import com.rabbit.common.constants.ResultCode;
import lombok.Data;

/**
 * 接口通用返回类
 * @author wangql
 */
@Data
public class ApiResponse {

    private Integer code;
    private String message;
    private Object data;
    private Integer total;

    private ApiResponse(){};

    public ApiResponse(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
    }

    public ApiResponse(ResultCode code, Object data) {
        this.code = code.code();
        this.message = code.message();
        this.data = data;
    }

    public ApiResponse(ResultCode code, Object data, Integer total) {
        this.code = code.code();
        this.message = code.message();
        this.data = data;
        this.total = total;
    }
}
