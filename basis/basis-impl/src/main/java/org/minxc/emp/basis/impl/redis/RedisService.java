package org.minxc.emp.basis.impl.redis;

import org.minxc.emp.basis.api.redis.IRedisService;
import org.minxc.emp.core.util.SerializeUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.annotation.Resource;

@Service
public class RedisService implements IRedisService {

    private static String redisCode = "utf-8";

    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public long del(final String... keys) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }

    /* (non-Javadoc)
     */
    @Override
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /* (non-Javadoc)
     */
    @Override
    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }

    @Override
    public void set(String key, String value) {
        this.set(key, value, 0L);
    }

    /* (non-Javadoc)
     */
    @Override
    public void set(String key, Object object) {
        this.set(key, object, 0L);
    }

    /* (non-Javadoc)
     */
    @Override
    public void set(String key, Object object, long liveTime) {
        byte[] value = SerializeUtil.serialize(object);
        this.set(key.getBytes(), value, liveTime);
    }

    /* (non-Javadoc)
     */
    @Override
    public void set(String key, byte[] value) {
        this.set(key.getBytes(), value, 0L);
    }

    /* (non-Javadoc)
     */
    @Override
    public void set(byte[] key, byte[] value) {
        this.set(key, value, 0L);
    }

    /* (non-Javadoc)
     */
    @Override
    public String get(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    if (connection.exists(key.getBytes())) {
                        return new String(connection.get(key.getBytes()), redisCode);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    /* (non-Javadoc)
     * @see com.dstz.sys.redis.IRedisService#getObject(java.lang.String)
     */
    @Override
    public Object getObject(final String key) {
        byte[] bytes = this.getBytes(key);
        if (bytes == null) {
            return null;
        }
        return SerializeUtil.unserialize(bytes);
    }

    /* (non-Javadoc)
     * @see com.dstz.sys.redis.IRedisService#getBytes(java.lang.String)
     */
    @Override
    public byte[] getBytes(final String key) {
        return redisTemplate.execute(new RedisCallback<byte[]>() {
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    if (connection.exists(key.getBytes())) {
                        return connection.get(key.getBytes());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    /* (non-Javadoc)
     * @see com.dstz.sys.redis.IRedisService#get(java.lang.String, java.lang.String)
     */
    @Override
    public String get(final String key, final String charsetName) {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    if (connection.exists(key.getBytes())) {
                        return new String(connection.get(key.getBytes(charsetName)), redisCode);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    /* (non-Javadoc)
     * @see com.dstz.sys.redis.IRedisService#keys(java.lang.String)
     */
    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);

    }

    /* (non-Javadoc)
     * @see com.dstz.sys.redis.IRedisService#exists(java.lang.String)
     */
    @Override
    public boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }

    /* (non-Javadoc)
     */
    @Override
    public String flushDB() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    /* (non-Javadoc)
     */
    @Override
    public long dbSize() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
    }

    /* (non-Javadoc)
     */
    @Override
    public String ping() {
        return redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.ping();
            }
        });
    }
}