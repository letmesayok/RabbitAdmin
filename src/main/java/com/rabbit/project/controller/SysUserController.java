package com.rabbit.project.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rabbit.common.constants.BusinessType;
import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.framework.annotation.Log;
import com.rabbit.project.constants.UserCode;
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
@SaCheckLogin
@CrossOrigin
@RestController
@RequestMapping("/user")
public class SysUserController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/info")
    public ApiResponse getUserInfo() {
        String username = StpUtil.getLoginIdAsString();
        SysUser user = userService.getUserInfo(username);
        return new ApiResponse(CommonCode.SUCCESS, user);
    }

    @PostMapping("/add")
    public ApiResponse register(SysUser user) {
        SysUser entity = userService.getOne(new QueryWrapper<SysUser>().eq("username", user.getUsername()));
        if(entity != null) {
            return new ApiResponse(UserCode.USERNAME_IS_EXIST);
        }
        return userService.add(user);
    }
}
