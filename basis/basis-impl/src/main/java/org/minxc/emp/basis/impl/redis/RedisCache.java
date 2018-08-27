package org.minxc.emp.basis.impl.redis;

import javax.annotation.Resource;

import org.minxc.emp.basis.api.redis.IRedisService;

import lombok.extern.slf4j.Slf4j;


/**
 * Redis缓存实现
 */

@Slf4j
public class RedisCache<T extends Object> implements ICache<T> {


    @Resource
    private IRedisService redisService;

    @Override
    public synchronized void add(String key, T obj) {
        log.info("key=" + key);
        redisService.set(key, obj);
    }

    @Override
    public synchronized void add(String key, T obj, int timeout) {
    	log.info("key=" + key);
        redisService.set(key, obj, timeout);
    }

    @Override
    public synchronized void delByKey(String key) {
    	log.info("key=" + key);
        redisService.del(key);
    }

    @Override
    public void clearAll() {
        redisService.flushDB();
    }

    @Override
    public synchronized T getByKey(String key) {
        Object obj = redisService.getObject(key);
        return (T) obj;
    }

    @Override
    public boolean containKey(String key) {
        return redisService.exists(key);
    }
}
