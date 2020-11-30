package com.rabbit.framework.annotation;

import com.rabbit.common.constants.BusinessType;

import java.lang.annotation.*;

/**
 * @author wangql
 * 日志注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    public String title() default "";
    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;
    /**
     * 是否保存请求参数
     */
    public boolean isSaveRequestArgs() default false;
}
