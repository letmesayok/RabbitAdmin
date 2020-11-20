package com.rabbit.common.constants;

/**
 * @author wangql
 * 状态码统一接口
 */
public interface ResultCode {
    /**
     * 返回结果
     * @return 结果
     */
    boolean success();
    /**
     * 返回操作码
     * @return 操作码
     */
    int code();
    /**
     * 获取信息
     * @return 信息
     */
    String message();
}
