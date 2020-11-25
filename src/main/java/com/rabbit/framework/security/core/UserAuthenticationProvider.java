package com.rabbit.framework.security.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbit.project.domain.SecurityUser;
import com.rabbit.project.domain.SysRole;
import com.rabbit.project.service.ISysRoleService;
import com.rabbit.project.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义登陆验证
 * @author wangql
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysRoleService roleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单中的数据
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        // 查询用户名是否存在
        SecurityUser user = userService.loadUserByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 判断密码是否正确
        if(!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 查询是否被冻结
        if(user.getStatus() == 0){
            throw new LockedException("用户已冻结!");
        }
        // 角色 set
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<SysRole> roleList = roleService.getRoleListByUserId(user.getUserId());
        for(SysRole role : roleList) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getRoleName()));
        }
        user.setAuthorities(authorities);
        // 登陆
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
