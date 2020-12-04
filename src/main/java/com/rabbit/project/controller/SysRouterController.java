package com.rabbit.project.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.project.domain.SysRouterVo;
import com.rabbit.project.domain.SysUser;
import com.rabbit.project.service.ISysRouterService;
import com.rabbit.project.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangql
 * @since 2020-12-02
 */
@SaCheckLogin
@CrossOrigin
@RestController
@RequestMapping("/router")
public class SysRouterController {

    @Autowired
    private ISysRouterService routerService;

    @Autowired
    private ISysUserService userService;

    @GetMapping("/getAll")
    public ApiResponse getAll() {
        String username = StpUtil.getLoginIdAsString();
        List<SysRouterVo> list = routerService.getAllRouter(username);
        return new ApiResponse(CommonCode.SUCCESS, list);
    }
}
