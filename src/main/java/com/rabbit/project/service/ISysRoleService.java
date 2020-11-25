package com.rabbit.project.service;

import com.rabbit.project.domain.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
public interface ISysRoleService extends IService<SysRole> {

    List<SysRole> getRoleListByUserId(Long userId);
}
