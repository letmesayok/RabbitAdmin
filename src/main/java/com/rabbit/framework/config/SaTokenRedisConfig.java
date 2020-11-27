package com.rabbit.framework.config;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.session.SaSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 将 token 的存储方式改为 redis
 * @author wangql
 */
@Component
public class SaTokenRedisConfig implements SaTokenDao {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    RedisTemplate<String, SaSession> redisTemplate;

    @Autowired
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        this.redisTemplate = redisTemplate;
    }

    /**
     * 根据key获取value ，如果没有，则返回空
     * @param key 健值
     * @return value值
     */
    @Override
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     *  写入指定key-value键值对，并设定过期时间(单位：秒)
     * @param key 键
     * @param value 值
     * @param timeout 过期时间
     */
    @Override
    public void setValue(String key, String value, long timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 删除指定缓存
     * @param key 键值
     */
    @Override
    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }


    /**
     * 获取指定session
     * @param sessionId session ID
     * @return session
     */
    @Override
    public SaSession getSaSession(String sessionId) {
        return redisTemplate.opsForValue().get(sessionId);
    }

    /**
     * 写入session
     * @param session session内容
     * @param timeout 过期时间
     */
    @Override
    public void saveSaSession(SaSession session, long timeout) {
        redisTemplate.opsForValue().set(session.getId(), session, timeout, TimeUnit.SECONDS);
    }

    /**
     * 更新session内容
     * @param session 更新对象
     */
    @Override
    public void updateSaSession(SaSession session) {
        long expire = redisTemplate.getExpire(session.getId());
        // -2 = 无此键
        if(expire == -2) {
            return;
        }
        redisTemplate.opsForValue().set(session.getId(), session, expire, TimeUnit.SECONDS);
    }

    /**
     * 删除指定session
     * @param sessionId 键值
     */
    @Override
    public void delSaSession(String sessionId) {
        redisTemplate.delete(sessionId);
    }
}
