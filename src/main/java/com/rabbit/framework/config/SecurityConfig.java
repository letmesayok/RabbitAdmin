package com.rabbit.framework.config;

import com.rabbit.framework.security.core.UserAuthenticationProvider;
import com.rabbit.framework.security.core.UserPermissionEvaluator;
import com.rabbit.framework.security.handler.*;
import com.rabbit.framework.security.jwt.JwtAuthenticationFilter;
import com.rabbit.framework.security.jwt.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * Security 核心配置类
 *
 * @author wangql
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启权限注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 登录失败处理
     */
    @Autowired
    private UserLoginFailureHandler loginFailureHandler;
    /**
     * 登录成功处理
     */
    @Autowired
    private UserLoginSuccessHandler loginSuccessHandler;
    /**
     * 注销成功处理
     */
    @Autowired
    private UserLogoutSuccessHandler logoutSuccessHandler;
    /**
     * 权限不足处理
     */
    @Autowired
    private UserAuthAccessDeniedHandler userAuthAccessDeniedHandler;
    /**
     * 未登录处理
     */
    @Autowired
    private UserAuthenticationEntryPointHandler userAuthenticationEntryPointHandler;
    /**
     * 自定义登陆验证逻辑
     */
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    /**
     * 加密方式
     *
     * @return 加密对象
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义 权限注解
     */
    @Bean
    public DefaultWebSecurityExpressionHandler userSecurityExpressHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new UserPermissionEvaluator());
        return handler;
    }

    /**
     * 配置登陆验证逻辑
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 注入自定义的登陆验证逻辑
        auth.authenticationProvider(userAuthenticationProvider);
    }

    /**
     * 配置 security 的控制逻辑
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 不进行权限验证的请求或资源（从配置文件中读取）
                .antMatchers(JwtConfig.antMatchers.split(",")).permitAll()
                // 其他请求需要认证
                .anyRequest().authenticated()
                .and()
                // 配置未登录自定义处理类
                .httpBasic().authenticationEntryPoint(userAuthenticationEntryPointHandler)
                .and()
                .formLogin()
                // 登陆url
                .loginProcessingUrl("/login")
                // 自定义登录成功处理
                .successHandler(loginSuccessHandler)
                // 自定义登录失败处理
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                // 注销 url
                .logoutSuccessUrl("/logout")
                // 自定义注销成功处理
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                // 自定义权限不足处理
                .exceptionHandling().accessDeniedHandler(userAuthAccessDeniedHandler)
                .and()
                // 开启跨域
                .cors()
                .and()
                // 关闭跨站请求防护
                .csrf().disable();
        // 基于 token 不需要 Session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 禁用缓存
        http.headers().cacheControl();
        // 添加 JWT 过滤器
        http.addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }
}
