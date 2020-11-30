package com.rabbit.project.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.project.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 免登录访问接口
 * @author wangql
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ISysUserService userService;

    @PostMapping("login")
    public ApiResponse login(String username, String password) {
        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            return userService.login(username, password);
        }
        return new ApiResponse(CommonCode.FAIL);
    }

    @PostMapping("/logout")
    public ApiResponse logout() {
        StpUtil.logout();
        return new ApiResponse(CommonCode.SUCCESS);
    }
}
