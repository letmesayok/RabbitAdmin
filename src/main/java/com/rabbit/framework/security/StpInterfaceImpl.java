package com.rabbit.framework.security;

import cn.dev33.satoken.stp.StpInterface;
import com.rabbit.framework.redis.RedisCache;
import com.rabbit.project.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限注解
 * @author wangql
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    ISysMenuService menuService;
    @Autowired
    RedisCache redisCache;

    @Override
    public List<Object> getPermissionCodeList(Object loginId, String loginKey) {
        // 根据 id 查询当前用户的权限列表
        String id = String.valueOf(loginId);
        List<Object> list = redisCache.getCacheList("role:" + id);
        if(list != null && list.size() > 0) {
            return list;
        }
        List<Object> roles = menuService.selectMenuListByUserId(Long.parseLong(id));
        redisCache.setCacheList("role:"+id, roles);
        return roles;
    }
}
