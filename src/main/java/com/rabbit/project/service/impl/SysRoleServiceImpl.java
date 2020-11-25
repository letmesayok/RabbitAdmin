package com.rabbit.project.service.impl;

import com.rabbit.project.domain.SysRole;
import com.rabbit.project.mapper.SysRoleMapper;
import com.rabbit.project.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRole> getRoleListByUserId(Long userId) {
        return baseMapper.getRoleListByUserId(userId);
    }
}
