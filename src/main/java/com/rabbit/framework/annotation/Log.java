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
    public String title() default "";
    public BusinessType businessType() default BusinessType.INSERT;
    public boolean isSaveRequestArgs() default false;
}
