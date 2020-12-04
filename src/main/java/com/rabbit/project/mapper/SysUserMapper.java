package com.rabbit.project.mapper;

import com.rabbit.project.domain.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser getUserInfo(String username);

    List<String> selectRoleByUser(SysUser user);
}
