package com.rabbit;

import com.rabbit.common.constants.BusinessType;
import com.rabbit.project.domain.SysUser;
import com.rabbit.project.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Demo {

    @Autowired
    private SysUserMapper userMapper;

    @Test
    public void fun() {

    }
}
