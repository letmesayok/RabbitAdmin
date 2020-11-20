package com.rabbit.common.constants;

/**
 * @author wangql
 * 业务操作类型
 */

public enum BusinessType {
    // 操作类型
    OTHER("其他"),
    EXPORT("导出"),
    IMPORT("导入"),
    LOGIN("登陆"),
    LOGOUT("注销"),
    DELETE("删除"),
    INSERT("新增"),
    UPDATE("修改");

    String description;

    BusinessType(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }
}
