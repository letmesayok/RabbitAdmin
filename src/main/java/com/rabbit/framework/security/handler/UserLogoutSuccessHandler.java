package com.rabbit.framework.security.handler;

import com.rabbit.common.domain.ApiResponse;
import com.rabbit.common.utils.ResultUtil;
import com.rabbit.project.constants.UserCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注销成功处理类
 * @author wangql
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        ResultUtil.responseJson(httpServletResponse, new ApiResponse(UserCode.LOGOUT_SUCCESS));
    }
}
