package com.rabbit.framework.config;

import cn.dev33.satoken.annotation.SaCheckInterceptor;
import cn.dev33.satoken.config.SaTokenConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * sa-token 的配置
 * @author wangql
 */
@Configuration
public class MySaTokenConfig implements WebMvcConfigurer {

    @Primary
    @Bean(name="MySaTokenConfig")
    public SaTokenConfig getSaTokenConfig() {
        SaTokenConfig config = new SaTokenConfig();
        // token名称 (同时也是cookie名称)
        config.setTokenName("rabbit");
        // token有效期，单位s 默认1h
        config.setTimeout(60 * 60);
        // 在多人登录同一账号时，是否共享会话 (为true时共用一个，为false时新登录挤掉旧登录)
        config.setIsShare(true);
        // 是否尝试从请求体里读取token
        config.setIsReadBody(true);
        // 是否尝试从header里读取token
        config.setIsReadHead(true);
        // 是否尝试从cookie里读取token
        config.setIsReadCookie(true);
        // 是否在初始化配置时打印版本字符画
        config.setIsV(true);
        return config;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaCheckInterceptor())
                .addPathPatterns("/**");
    }
}
