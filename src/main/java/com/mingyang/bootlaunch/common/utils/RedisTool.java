//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mingyang.bootlaunch.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ScanOptions.ScanOptionsBuilder;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class RedisTool {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public RedisTool() {
    }

    public boolean expire(String key, long time) {
        try {
            if (time > 0L) {
                this.redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }

            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public long getExpire(String key) {
        return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key) {
        try {
            return this.redisTemplate.hasKey(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public Object get(String key) {
        return key == null ? null : this.redisTemplate.opsForValue().get(key);
    }

    public String getKey(String key) {
        return key == null ? null : (String)this.redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisSerializer jdkSerializer = new StringRedisSerializer();
                byte[] serValue = redisConnection.get(key.getBytes());
                return jdkSerializer.deserialize(serValue);
            }
        });
    }

    public boolean set(String key, Object value) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public void setKey(String key, String value) {
        this.redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                StringRedisSerializer jdkSerializer = new StringRedisSerializer();
                byte[] serValue = jdkSerializer.serialize(value);
                redisConnection.set(key.getBytes(), serValue);
                return null;
            }
        });
    }

    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0L) {
                this.redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                this.set(key, value);
            }

            return true;
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public long incr(String key, long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递增因子必须大于0");
        } else {
            return this.redisTemplate.opsForValue().increment(key, delta);
        }
    }

    public long decr(String key, long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递减因子必须大于0");
        } else {
            return this.redisTemplate.opsForValue().increment(key, -delta);
        }
    }

    public Object hget(String key, String item) {
        return !StringUtils.isEmpty(key) && !StringUtils.isEmpty(item) ? this.redisTemplate.opsForHash().get(key, item) : null;
    }

    public Cursor<Entry<Object, Object>> blurHashKeys(String keyspace, String key) {
        ScanOptions scanOptions = (new ScanOptionsBuilder()).match(key + "*").build();
        return this.redisTemplate.opsForHash().scan(keyspace, scanOptions);
    }

    public Cursor<Entry<Object, Object>> blurHashKeys(String keyspace, String key1, String key2) {
        ScanOptions scanOptions = (new ScanOptionsBuilder()).match(key1 + "*" + key2).build();
        return this.redisTemplate.opsForHash().scan(keyspace, scanOptions);
    }

    public Map<Object, Object> hmget(String key) {
        return this.redisTemplate.opsForHash().entries(key);
    }

    public boolean hmset(String key, Map<String, Object> map) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key, String item, Object value) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key, String item, Object value, long time) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var7) {
            var7.printStackTrace();
            return false;
        }
    }

    public void hdel(String key, Object... item) {
        this.redisTemplate.opsForHash().delete(key, item);
    }

    public boolean hHasKey(String key, String item) {
        return this.redisTemplate.opsForHash().hasKey(key, item);
    }

    public double hincr(String key, String item, double by) {
        return this.redisTemplate.opsForHash().increment(key, item, by);
    }

    public double hdecr(String key, String item, double by) {
        return this.redisTemplate.opsForHash().increment(key, item, -by);
    }

    public Set<Object> sGet(String key) {
        try {
            return this.redisTemplate.opsForSet().members(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public boolean sHasKey(String key, Object value) {
        try {
            return this.redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public long sSet(String key, Object... values) {
        try {
            return this.redisTemplate.opsForSet().add(key, values);
        } catch (Exception var4) {
            var4.printStackTrace();
            return 0L;
        }
    }

    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = this.redisTemplate.opsForSet().add(key, values);
            if (time > 0L) {
                this.expire(key, time);
            }

            return count;
        } catch (Exception var6) {
            var6.printStackTrace();
            return 0L;
        }
    }

    public long sGetSetSize(String key) {
        try {
            return this.redisTemplate.opsForSet().size(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return 0L;
        }
    }

    public long setRemove(String key, Object... values) {
        try {
            Long count = this.redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception var4) {
            var4.printStackTrace();
            return 0L;
        }
    }

    public List<Object> lGet(String key, long start, long end) {
        try {
            return this.redisTemplate.opsForList().range(key, start, end);
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }

    public long lGetListSize(String key) {
        try {
            return this.redisTemplate.opsForList().size(key);
        } catch (Exception var3) {
            var3.printStackTrace();
            return 0L;
        }
    }

    public Object lGetIndex(String key, long index) {
        try {
            return this.redisTemplate.opsForList().index(key, index);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public boolean lSet(String key, Object value) {
        try {
            this.redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, Object value, long time) {
        try {
            this.redisTemplate.opsForList().leftPush(key, value);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, List<Object> value) {
        try {
            this.redisTemplate.opsForList().leftPushAll(key, value);
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public boolean lSet(String key, List<Object> value, long time) {
        try {
            this.redisTemplate.opsForList().leftPushAll(key, value);
            if (time > 0L) {
                this.expire(key, time);
            }

            return true;
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            this.redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception var6) {
            var6.printStackTrace();
            return false;
        }
    }

    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = this.redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception var6) {
            var6.printStackTrace();
            return 0L;
        }
    }

    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                this.redisTemplate.delete(key[0]);
            } else {
                this.redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }

    }

    public Set<String> getVagueKey(String pattern) {
        return this.redisTemplate.keys("*" + pattern + "*");
    }

    public Set<String> getValueKeyByPrefix(String prefixPattern) {
        return this.redisTemplate.keys(prefixPattern + "*");
    }

    public Double addZsetScore(String key, String value, double score) {
        return this.redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    public Boolean updateZsetScore(String key, String value, double score) {
        return this.redisTemplate.opsForZSet().add(key, value, score);
    }

    public Long zsetCountByscores(String key, double min, double max) {
        return this.redisTemplate.opsForZSet().count(key, min, max);
    }

    public Long removeZsetValue(String key, Object... value) {
        return this.redisTemplate.opsForZSet().remove(key, value);
    }

    public void removeZetRangeByScore(String key, double min, double max) {
        this.redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }

    public Set<Object> getZetRangeByScore(String key, double min, double max) {
        return this.redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Double getZscore(String key, Object value) {
        return this.redisTemplate.opsForZSet().score(key, value);
    }

    public Long getZcard(String key) {
        return this.redisTemplate.opsForZSet().zCard(key);
    }

    public Set<Object> getZrevrange(String key, int start, int end) {
        return this.redisTemplate.opsForZSet().reverseRange(key, (long)start, (long)end);
    }

    public byte[] getSerializeKeyBytes(String str) {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        return stringSerializer.serialize(str);
    }

    public byte[] getSerializeValueBytes(String str) {
        RedisSerializer<Object> stringSerializer = new JdkSerializationRedisSerializer();
        return stringSerializer.serialize(str);
    }

    public List<Object> executePipelined(RedisCallback<List<Object>> action) {
        return this.redisTemplate.executePipelined(action);
    }

    public String getErrorKey(String key) {
        return this.redisTemplate.boundValueOps(key).get(0L, -1L);
    }
}
