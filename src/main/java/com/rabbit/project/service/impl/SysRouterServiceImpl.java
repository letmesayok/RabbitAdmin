package com.rabbit.project.service.impl;

import com.rabbit.project.domain.SysRouter;
import com.rabbit.project.domain.SysRouterVo;
import com.rabbit.project.mapper.SysRouterMapper;
import com.rabbit.project.service.ISysRouterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangql
 * @since 2020-12-02
 */
@Service
public class SysRouterServiceImpl extends ServiceImpl<SysRouterMapper, SysRouter> implements ISysRouterService {

    @Override
    public List<SysRouterVo> getAllRouter(String username) {
        return baseMapper.getAllRouter(username);
    }
}
