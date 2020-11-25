package com.rabbit.project.service;

import com.rabbit.project.domain.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<SysMenu> selectMenuListByUserId(Long id);

}
