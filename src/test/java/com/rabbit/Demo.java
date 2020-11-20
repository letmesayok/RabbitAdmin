package com.rabbit;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rabbit.common.domain.BusinessType;
import com.rabbit.project.mapper.DemoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class Demo {

    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void fun() {
        String name = BusinessType.INSERT.name();
        String name2 = BusinessType.INSERT.getDescription();
        System.out.println();
    }
}
