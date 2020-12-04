package com.rabbit.project.service;

import com.rabbit.project.domain.SysRouter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rabbit.project.domain.SysRouterVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangql
 * @since 2020-12-02
 */
public interface ISysRouterService extends IService<SysRouter> {

    List<SysRouterVo> getAllRouter(String username);
}
