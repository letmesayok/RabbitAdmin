package com.rabbit.project.service;

import com.rabbit.common.domain.ApiResponse;
import com.rabbit.project.domain.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
public interface ISysUserService extends IService<SysUser> {

    ApiResponse login(String username, String password);

    ApiResponse add(SysUser user);
}
