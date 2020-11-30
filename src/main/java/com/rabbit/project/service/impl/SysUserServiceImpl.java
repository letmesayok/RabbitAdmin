package com.rabbit.project.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.rabbit.common.constants.CommonCode;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.project.constants.UserCode;
import com.rabbit.project.domain.SysUser;
import com.rabbit.project.mapper.SysUserMapper;
import com.rabbit.project.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public ApiResponse login(String username, String password) {
        SysUser user = baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
        // 用户名是否存在
        if(user == null) {
            return new ApiResponse(UserCode.USERNAME_NOT_FOUND);
        }
        // 验证密码是否正确
        if(!SecureUtil.md5(password + username).equals(user.getPassword())) {
            return new ApiResponse(UserCode.PASSWORD_WRONG);
        }
        // 判断是否已登录
        if(StpUtil.isLogin()) {
            return new ApiResponse(CommonCode.SUCCESS, StpUtil.getTokenValue());
        }
        // 登陆成功
        log.info(" 登陆成功");
        StpUtil.setLoginId(user.getUsername());
        return new ApiResponse(CommonCode.SUCCESS, StpUtil.getTokenValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse add(SysUser user) {
        String realPwd = SecureUtil.md5(user.getPassword()+user.getUsername());
        user.setPassword(realPwd);
        baseMapper.insert(user);
        return new ApiResponse(CommonCode.SUCCESS);
    }

    @Override
    public SysUser getUserInfo(String username) {
        SysUser userInfo = baseMapper.getUserInfo(username);
        if(StringUtils.isNotBlank(userInfo.getRoleNames())) {
            userInfo.setRoleArray(userInfo.getRoleNames().split(","));
        }
        return userInfo
    }
}
