package com.rabbit.project.mapper;

import com.rabbit.project.domain.SysRouter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rabbit.project.domain.SysRouterVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangql
 * @since 2020-12-02
 */
public interface SysRouterMapper extends BaseMapper<SysRouter> {

    List<SysRouterVo> getAllRouter(String username);
}
