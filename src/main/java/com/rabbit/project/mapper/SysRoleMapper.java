package com.rabbit.project.mapper;

import com.rabbit.project.domain.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> getRoleListByUserId(Long userId);
}
