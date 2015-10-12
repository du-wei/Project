package com.webapp.redis;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisSpringUtils {

	private RedisTemplate<String, String> masterTemplate;

	private RedisTemplate<String, String> slaveTemplate;

	// 设置默认属性,可以通过set方法进行注册，同时是public保证可以进行函数注入
	public RedisSpringUtils() {

	}

	public RedisSpringUtils(RedisTemplate<String, String> masterTemplate,
			RedisTemplate<String, String> slaveTemplate) {
		this.masterTemplate = masterTemplate;
		this.slaveTemplate = slaveTemplate;
	}

	public void set(String key, String value) {
		masterTemplate.opsForValue().set(key, value);
	}

	public String getValue(String key) {
		return slaveTemplate.opsForValue().get(key);
	}

	public void delete(String key) {
		masterTemplate.opsForValue().getOperations().delete(key);
	}

	public void setMasterTemplate(RedisTemplate<String, String> masterTemplate) {
		this.masterTemplate = masterTemplate;
	}

	public void setSlaveTemplate(RedisTemplate<String, String> slaveTemplate) {
		this.slaveTemplate = slaveTemplate;
	}

}