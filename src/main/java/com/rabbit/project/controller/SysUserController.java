package com.rabbit.project.controller;


import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.project.domain.SysUser;
import com.rabbit.project.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/info/{id}")
    public ApiResponse getUserInfo(@PathVariable("id") Integer id) {
        SysUser user = userService.getById(id);
        return new ApiResponse(CommonCode.SUCCESS, user);
    }
}
