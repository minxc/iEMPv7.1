/**
 * 
 */
package org.minxc.core.cache.impl;

import javax.annotation.Resource;

import org.minxc.core.cache.Cache;
import org.minxc.emp.basis.api.redis.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**      
* 项目名称：core-cache   
* 类名称：RedisCache   
* 类描述：   
* 创建人：Xianchang.min   
* 创建时间：2018年10月1日 下午3:21:32   
* 修改人：Xianchang.min   
* 修改时间：2018年10月1日 下午3:21:32   
* 修改备注：   
* @version  1.0  
**/

public class RedisCache<T extends Object> implements Cache<T>  {
	private Logger logger = LoggerFactory.getLogger(RedisCache.class);

    @Resource
    private IRedisService redisService;

    @Override
    public synchronized void add(String key, T obj) {
        logger.info("key=" + key);
        redisService.set(key, obj);
    }

    @Override
    public synchronized void add(String key, T obj, int timeout) {
        logger.info("key=" + key);
        redisService.set(key, obj, timeout);
    }

    @Override
    public synchronized void delByKey(String key) {
        logger.info("key=" + key);
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
