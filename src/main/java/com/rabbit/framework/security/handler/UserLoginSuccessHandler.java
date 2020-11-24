package com.rabbit.framework.security.handler;

import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.common.utils.ResultUtil;
import com.rabbit.project.domain.User;
import com.rabbit.framework.security.jwt.JwtConfig;
import com.rabbit.framework.security.jwt.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理类
 * @author wangql
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 组装 JWT
        User user = (User) authentication.getPrincipal();
        String token = JwtTokenUtil.createAccessToken(user);
        // 加 token 前缀
        token = JwtConfig.tokenPrefix + token;
        ResultUtil.responseJson(httpServletResponse, new ApiResponse(CommonCode.SUCCESS, token));
    }
}
