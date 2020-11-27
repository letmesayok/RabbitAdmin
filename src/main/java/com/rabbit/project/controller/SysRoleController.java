package com.rabbit.project.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.project.domain.SysRole;
import com.rabbit.project.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
@SaCheckLogin
@RestController
@RequestMapping("/role")
public class SysRoleController {
    @Autowired
    ISysRoleService roleService;

    @GetMapping("/list")
    public ApiResponse getRoleList() {
        List<SysRole> list = roleService.list();
        return new ApiResponse(CommonCode.SUCCESS, list);
    }
}
