package com.springboot.redis;

import com.springboot.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RedisDao {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}


	/**
	 * 存储字符串
	 * @param tableName
	 * @param key
	 * @param value
	 */
	public void putString(String tableName, String key, String value) {
		BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(tableName);
		ops.put(key, value);
	}

	/**
	 * 存储集合
	 * @param tableName
	 * @param keys
	 * @param values
	 * @throws SizeNotEqualException
	 */
	public void putList(String tableName, List<String> keys, List<Object> values) throws SizeNotEqualException {
		if (keys.size() != values.size()) {
			throw new SizeNotEqualException("keys size is not equal values size");
		}

		BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(tableName);
		Map<String, String> kvs = new HashMap<String, String>();
		for (int i = 0; i < keys.size(); i++) {
			kvs.put(keys.get(i), JsonUtil.writeValueAsString(values.get(i)));
		}
		ops.putAll(kvs);
	}

	/**
	 * 存储map
	 * @param tableName
	 * @param kvs
	 */
	public void putMap(String tableName, Map<String, String> kvs) {
		BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(tableName);
		ops.putAll(kvs);
	}

    /**
     * 返回对象
     * @param tableName
     * @param key
     * @param type
     * @return
     */
	public <T> T getObject(String tableName, String key, Class<T> type) {
		BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(tableName);
		if(ops.get(key)==null) {
			return null;
		}
		return  JsonUtil.jsonToBean(ops.get(key), type);
	}
    
	/**
	 * 返回字符串
	 * @param tableName
	 * @param key
	 * @return
	 */
	public String getString(String tableName, String key) {
		BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(tableName);
		return ops.get(key);
	}

	/**
	 * 返回字符串
	 * @param key
	 * @return
	 */
	public String getValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * 自增
	 * @param tableName
	 * @param key
	 * @return
	 */
	public Long increment(String tableName, String key) {
		BoundHashOperations<String, String, String> ops = stringRedisTemplate.boundHashOps(tableName);
		return ops.increment(key, 1);
	}

	/**
	 * 设置key的value(字符串)在offset处的bit值(只能只0或者1)
	 * bitmap本身offset的限制就是0到2^32，内存限制为521MB，分配所需时间才几百ms，
	 * 刚刚好是2^32个bit，也就是4294967296，offset最大能取到4294967296-1去。有四十二亿
	 * @param key
	 * @param offset
	 * @param result
	 * @return
	 */
	public boolean setBit(String key,long offset,boolean result) {
		return stringRedisTemplate.opsForValue().setBit(key, offset, result);
	}

	/**
	 * 获取key的value(字符串)在offset处的bit值
	 * @param key
	 * @param offset
	 * @return
	 */
	public boolean getBit(String key,long offset){
		return stringRedisTemplate.opsForValue().getBit(key, offset);
	}

    /*
	public long bitCount(byte[] b) {
		RedisConnection con = stringRedisTemplate.getConnectionFactory().getConnection();
		return con.bitCount(b);
	}
	*/
}
