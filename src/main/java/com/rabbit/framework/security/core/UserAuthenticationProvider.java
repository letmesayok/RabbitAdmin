package com.rabbit.framework.security.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rabbit.project.domain.User;
import com.rabbit.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 自定义登陆验证
 * @author wangql
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IUserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user = new User();
        user.setUsername(username);
        // 查询用户名是否存在
        User one = userService.getOne(new QueryWrapper<>(user));
        if(one == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 判断密码是否正确

        // 查询是否被冻结
        if(one.getStatus() == 0){
            throw new LockedException("用户已冻结!");
        }
        // 角色 set
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
