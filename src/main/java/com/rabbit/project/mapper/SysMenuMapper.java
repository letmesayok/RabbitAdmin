package com.rabbit.project.mapper;

import com.rabbit.project.domain.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-11-25
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenuListByUserId(Long id);
}
