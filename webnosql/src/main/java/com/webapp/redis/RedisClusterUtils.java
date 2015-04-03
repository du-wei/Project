package com.webapp.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;

import com.webapp.utils.config.ConfigUtils;

public class RedisClusterUtils {

	private static final Logger logger = LoggerFactory.getLogger(RedisClusterUtils.class);	
	private static String redisCfg = "redis.properties";
	private final static Configuration config = ConfigUtils.addConfig(redisCfg);
	private static ShardedJedisPool shardedJedisPool;

	public static void name() {
		// JedisShardInfo shardInfo = getShardedJedis().getShardInfo("king");
		// System.out.println(String.format("%1$s-%2$s-%3$s-%4$s-%5$s",
		// shardInfo.getHost(), shardInfo.getName(), shardInfo.getPassword(),
		// shardInfo.getPort(), shardInfo.getWeight()));
	}

	/**
	 * @Title: getShardedJedisPipe
	 * @Description: TODO 方法描述
	 * @param 参数描述
	 * @return ShardedJedisPipeline 返回类型描述
	 * @throws
	 */
	public static ShardedJedisPipeline getShardedJedisPipe() {
		return getShardedJedis().pipelined();
	}

	/**
	 * @Title: getShardedJedis
	 * @Description: TODO 方法描述
	 * @param 参数描述
	 * @return ShardedJedis 返回类型描述
	 * @throws
	 */
	public static ShardedJedis getShardedJedis() {
		ShardedJedisPool jedisPool = getShardedJedisPool();
		ShardedJedis shardedJedis = jedisPool.getResource();
		return shardedJedis;
	}

	public static ShardedJedis getShardedJedisDirect() {
		ShardedJedis shardedJedis = new ShardedJedis(getJedisShardInfos());
		return shardedJedis;
	}

	/**
	 * @Title: getShardedJedisPool
	 * @Description: TODO 方法描述
	 * @param 参数描述
	 * @return ShardedJedisPool 返回类型描述
	 * @throws
	 */
	private static ShardedJedisPool getShardedJedisPool() {
		if (shardedJedisPool == null) {
			shardedJedisPool = new ShardedJedisPool(
					RedisConfig.getJedisPoolConfig(redisCfg),
					getJedisShardInfos());
		}
		return shardedJedisPool;
	}

	/**
	 * @Title: getJedisShardInfos
	 * @Description: TODO 方法描述
	 * @param 参数描述
	 * @return List<JedisShardInfo>
	 * @throws
	 */
	private static List<JedisShardInfo> getJedisShardInfos() {
		List<String> ips = Arrays.asList(config.getStringArray("redis.ip"));
		int port = config.getInt("redis.port");
		String pwd = config.getString("redis.password");
		List<JedisShardInfo> shards = new ArrayList<>();

		ips.forEach(ip -> {
			logger.info(String.format(
					"initialize JedisShardInfo %1$s --> %2$s", ip, port));
			JedisShardInfo jedisShardInfo = new JedisShardInfo(ip, port);
			jedisShardInfo.setPassword(pwd);
			shards.add(jedisShardInfo);
		});
		return shards;
	}

	private static void returnResource(ShardedJedis shardedJedis) {
		try {
			shardedJedisPool.returnResource(shardedJedis);
		} catch (Exception e) {
			logger.error("", e);
			returnBrokenResource(shardedJedis);
		}
	}

	private static void returnBrokenResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnBrokenResource(shardedJedis);
	}

}
