package com.rabbit.framework.security.handler;

import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.common.exception.CommonException;
import com.rabbit.common.utils.ResultUtil;
import com.rabbit.project.constants.UserCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理类
 * @author wangql
 */
@Component
@Slf4j
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof UsernameNotFoundException) {
            log.info("登录失败" + exception.getMessage());
//            throw new CommonException(UserCode.USERNAME_NOT_FOUND);
            ResultUtil.responseJson(response, new ApiResponse(UserCode.USERNAME_NOT_FOUND));
        }
        if(exception instanceof LockedException) {
            log.info("登录失败" + exception.getMessage());
            ResultUtil.responseJson(response, new ApiResponse(UserCode.USER_IS_LOCKED));
        }
        if(exception instanceof BadCredentialsException) {
            log.info("登录失败" + exception.getMessage());
            ResultUtil.responseJson(response, new ApiResponse(UserCode.PASSWORD_WRONG));
        }
        ResultUtil.responseJson(response, new ApiResponse(CommonCode.FAIL));
    }
}
