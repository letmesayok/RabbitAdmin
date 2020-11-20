package com.rabbit;

import com.rabbit.common.constants.BusinessType;
import com.rabbit.project.mapper.DemoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
