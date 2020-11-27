package com.rabbit.project.service.impl;

import com.rabbit.project.domain.SysMenu;
import com.rabbit.project.mapper.SysMenuMapper;
import com.rabbit.project.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper mapper;

    @Override
    public List<Object> selectMenuListByUserId(Long id) {
        return mapper.selectMenuListByUserId(id);
    }
}
