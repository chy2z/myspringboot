package com.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.springboot.util.JsonUtil;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
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
		
		if(ops.get(key)==null) return null;
		
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
	
}
