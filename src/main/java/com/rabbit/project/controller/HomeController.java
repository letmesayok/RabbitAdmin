package com.rabbit.project.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.project.domain.SysUser;
import com.rabbit.project.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 免登录访问接口
 * @author wangql
 */
@CrossOrigin
@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ISysUserService userService;

    @PostMapping("login")
    public ApiResponse login(@RequestBody SysUser user) {
        if(StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())) {
            return userService.login(user.getUsername(), user.getPassword());
        }
        return new ApiResponse(CommonCode.FAIL);
    }

    @PostMapping("/logout")
    public ApiResponse logout() {
        StpUtil.logout();
        return new ApiResponse(CommonCode.SUCCESS);
    }
}
