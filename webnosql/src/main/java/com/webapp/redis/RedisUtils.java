package com.webapp.redis;

import java.util.List;
import java.util.Set;

import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import com.webapp.utils.config.ConfigUtils;

public class RedisUtils {

	private static Logger logger = LogManager.getLogger(RedisUtils.class);
	private static String redisCfg = "redis.properties";
	private final static Configuration config = ConfigUtils.addConfig(redisCfg);
	private static JedisPool pool = null;
	private static ThreadLocal<Jedis> jedisLocal = new ThreadLocal<>();

	private static JedisPool getRedisPool() {
		if (pool == null) {
			int port = config.getInt("redis.port");
			int timeout = config.getInt("redis.timeout");
			String ip = config.getString("redis.ip");
			String password = config.getString("redis.password");
			pool = new JedisPool(RedisConfig.getJedisPoolConfig(redisCfg), ip,
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
			returnBrokenResource(jedis);
		} finally {
			returnResource(jedis);
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
			returnBrokenResource(jedis);
		} finally {
			returnResource(jedis);
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
			returnBrokenResource(jedis);
		} finally {
			returnResource(jedis);
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
			returnBrokenResource(jedisLocal.get());
		} finally {
			returnResource(jedisLocal.get());
		}
		return result;
	}

	public static List<Object> exec(Pipeline tx) {
		List<Object> result = null;
		try {
			result = tx.syncAndReturnAll();
		} catch (Exception e) {
			logger.error("", e);
			returnBrokenResource(jedisLocal.get());
		} finally {
			returnResource(jedisLocal.get());
		}
		return result;
	}

	private static void returnResource(Jedis jedis) {
		try {
			pool.returnResource(jedis);
		} catch (Exception e) {
			logger.error("", e);
			returnBrokenResource(jedis);
		}
	}

	private static void returnBrokenResource(Jedis jedis) {
		pool.returnBrokenResource(jedis);
	}
}
