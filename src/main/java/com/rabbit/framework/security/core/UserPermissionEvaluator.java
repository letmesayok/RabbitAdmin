package com.rabbit.framework.security.core;

import com.rabbit.project.domain.SecurityUser;
import com.rabbit.project.domain.SysMenu;
import com.rabbit.project.service.ISysMenuService;
import com.rabbit.project.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义权限注解验证
 * @author wangql
 */
@Component
public class UserPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 鉴权方法
     * @param authentication 用户身份
     * @param targetUrl 请求路径
     * @param permission 请求路径权限
     * @return 是否通过
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetUrl, Object permission) {
        // 获取用户信息
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        // 查询用户权限（可以将权限放入缓存提高查询效率）
        Set<String> permissions = new HashSet<>();
        List<SysMenu> list = menuService.selectMenuListByUserId(user.getUserId());
        for(SysMenu menu : list) {
            permissions.add(menu.getPermission());
        }
        // 如果该用户包含该权限，放行
        if(permissions.contains(permission.toString())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
