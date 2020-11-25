package com.rabbit;

import com.rabbit.common.constants.BusinessType;
import com.rabbit.project.domain.SysUser;
import com.rabbit.project.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class Demo {

    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void fun() {
        SysUser user = new SysUser();
        user.setUsername("wql");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        userMapper.insert(user);
    }
}
