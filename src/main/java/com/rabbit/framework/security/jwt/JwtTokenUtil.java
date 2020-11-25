package com.rabbit.framework.security.jwt;

import com.alibaba.fastjson.JSON;
import com.rabbit.project.domain.SecurityUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author wangql
 * jwt 工具类
 */
public class JwtTokenUtil {

    public static String createAccessToken(SecurityUser user) {
        // 登陆成功生成JWT
        String token = Jwts.builder()
                // 放入用户名和用户ID
                .setId(user.getUserId()+"")
                // 主题
                .setSubject(user.getUsername())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发者
                .setIssuer("rabbit")
                // 自定义属性 放入用户拥有权限
                .claim("authorities", JSON.toJSONString(user.getAuthorities()))
                // 失效时间
                .setExpiration(new Date(System.currentTimeMillis() + JwtConfig.expiration))
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, JwtConfig.secret)
                .compact();
        return token;
    }
}
