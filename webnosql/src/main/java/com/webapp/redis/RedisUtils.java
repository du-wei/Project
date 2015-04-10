package com.webapp.redis;

import java.util.List;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import com.webapp.utils.config.ConfigUtils;

@Component
public class RedisUtils {

	private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);
	
	private static String redisCfg = "redis.properties";
	private final static Configuration config = ConfigUtils.addConfig(redisCfg);
	private static JedisPool pool = null;
	private static ThreadLocal<Jedis> jedisLocal = new ThreadLocal<>();

	private static JedisPool getRedisPool() {
		if (pool == null) {
			String ip = config.getString("redis.ip");
			Integer port = config.getInteger("redis.port", null);
			Integer timeout = config.getInteger("redis.timeout", null);
			String password = config.getString("redis.password", null);
			pool = new JedisPool(RedisConfig.getJedisPoolConfig(config), ip,
					port, timeout, password);
		}
		return pool;
	}

	public static Jedis getJedis() {
		Jedis jedis = getRedisPool().getResource();
		return jedis;
	}

	public static Jedis getJedis(int index) {
		Jedis jedis = getJedis();
		jedis.select(index);
		return jedis;
	}

	public static String get(String key) {
		Jedis jedis = null;
		String result = "";
		try {
			jedis = getJedis();
			result = jedis.get(key);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			closeJedis(jedis);
		}
		return result;
	}

	public static Set<String> keys(String pattern) {
		Jedis jedis = null;
		Set<String> result = null;
		try {
			jedis = getJedis();
			result = jedis.keys(pattern);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			closeJedis(jedis);
		}
		return result;
	}

	public static String set(String key, String val) {
		Jedis jedis = null;
		String result = "";
		try {
			jedis = getJedis();
			result = jedis.set(key, val);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			closeJedis(jedis);
		}
		return result;
	}

	public static Transaction ByTrans() {
		Jedis jedis = getJedis();
		Transaction tx = jedis.multi();
		jedisLocal.set(jedis);
		return tx;
	}

	public static Pipeline ByPipe() {
		Pipeline pipeline = getJedis().pipelined();
		return pipeline;
	}

	public static List<Object> exec(Transaction tx) {
		List<Object> result = null;
		try {
			result = tx.exec();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
		}
		return result;
	}

	public static List<Object> exec(Pipeline tx) {
		List<Object> result = null;
		try {
			result = tx.syncAndReturnAll();
		} catch (Exception e) {
			logger.error("", e);
		} finally {
		}
		return result;
	}

	public static void closeJedis(Jedis jedis){
		if(jedis != null) jedis.close();
	}
	public static void returnResource(Jedis jedis) {
	    if(jedis != null) pool.returnResourceObject(jedis);
    }
	
	
}
