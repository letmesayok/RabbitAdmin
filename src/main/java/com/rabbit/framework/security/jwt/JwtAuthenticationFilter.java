package com.rabbit.framework.security.jwt;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.rabbit.common.domain.ApiResponse;
import com.rabbit.common.exception.CommonException;
import com.rabbit.common.utils.ResultUtil;
import com.rabbit.project.constants.UserCode;
import com.rabbit.project.domain.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JWT 接口请求校验拦截器
 *
 * @author wangql
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取请求头中的 Token
        String tokenHeader = request.getHeader(JwtConfig.tokenHeader);
        if (null != tokenHeader && tokenHeader.startsWith(JwtConfig.tokenPrefix)) {
            try {
                String token = tokenHeader.replace(JwtConfig.tokenPrefix, "");
                // 解析 JWT
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtConfig.secret)
                        .parseClaimsJws(token)
                        .getBody();
                // 获取用户名
                String username = claims.getSubject();
                String userId = claims.getId();
                if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(username)) {
                    // 获取角色
                    List<GrantedAuthority> list = new ArrayList<>();
                    String authority = claims.get("authorities").toString();
                    if (!StringUtils.isEmpty(authority)) {
                        List<Map<String, String>> lists = JSONObject.parseObject(authority, List.class);
                        for (Map<String, String> map : lists) {
                            if (map != null && !StringUtils.isEmpty(map.get("authority"))) {
                                list.add(new SimpleGrantedAuthority(map.get("authority")));
                            }
                        }
                    }
                    // 组装参数
                    SecurityUser user = new SecurityUser();
                    user.setUsername(username);
                    user.setUserId(Long.parseLong(userId));
                    user.setAuthorities(list);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, userId, list);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                chain.doFilter(request, response);
            } catch (ExpiredJwtException e) {
                log.info("token 过期");
                ResultUtil.responseJson(response, new ApiResponse(UserCode.TOKEN_EXPIRE));
            } catch (Exception e) {
                log.info("token 无效");
                ResultUtil.responseJson(response, new ApiResponse(UserCode.TOKEN_CLOSE));
            }
        }else {
            chain.doFilter(request, response);
        }
    }
}
