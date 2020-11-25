package com.rabbit.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbit.project.domain.SecurityUser;
import com.rabbit.project.domain.SysUser;
import com.rabbit.project.mapper.SysUserMapper;
import com.rabbit.project.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SecurityUser loadUserByUsername(String username) {
        SysUser user = baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
        if(user != null) {
            SecurityUser securityUser = new SecurityUser();
            BeanUtils.copyProperties(user, securityUser);
            return securityUser;
        }
        return null;
    }
}
