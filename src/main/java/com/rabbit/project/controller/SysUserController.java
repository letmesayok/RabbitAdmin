package com.rabbit.project.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.project.domain.SysUser;
import com.rabbit.project.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @SaCheckLogin
    @SaCheckPermission("sys:user:info")
    @GetMapping("/info/{id}")
    public ApiResponse getUserInfo(@PathVariable("id") Integer id) {
        SysUser user = userService.getById(id);
        return new ApiResponse(CommonCode.SUCCESS, user);
    }

    @PostMapping("login")
    public ApiResponse login(String username, String password) {
        if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
            return userService.login(username, password);
        }
        return new ApiResponse(CommonCode.FAIL);
    }

    @PostMapping("/logout")
    public void logout() {
        StpUtil.logout();
        System.out.println("注销登录");
    }
}
