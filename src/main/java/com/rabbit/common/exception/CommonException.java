package com.rabbit.common.exception;

import com.rabbit.common.domain.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangql
 * 统一异常返回类
 */
@AllArgsConstructor
public class CommonException extends RuntimeException {
    ResultCode resultCode;

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
