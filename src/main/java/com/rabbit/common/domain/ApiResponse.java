package com.rabbit.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口通用返回类
 * @author wangql
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private Integer code;
    private String message;
    private Object data;
    private Integer total;
}
