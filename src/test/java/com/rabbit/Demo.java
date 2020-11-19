package com.rabbit;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        PageHelper.startPage(1, 10);
        List<Map<String, Object>> select = demoMapper.select();
        PageInfo pageInfo = new PageInfo<>(select);
        List list = pageInfo.getList();
        System.out.println(list);
    }
}
