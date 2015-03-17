package com.webapp.redis;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class RedisTest {
	
	@Test
	public void test() {
		// ShardedJedis shardedJedis = RedisClusterUtils.getShardedJedis();
		//
		// System.out.println(shardedJedis.get("key"));

		// RedisClusterUtils.name();
		JedisShardInfo jedisShardInfo = new JedisShardInfo("localhost", "6380");
		jedisShardInfo.setPassword("root");
		List<JedisShardInfo> shards = Arrays.asList(jedisShardInfo);
		ShardedJedis shardedJedis = new ShardedJedis(shards);

		shardedJedis.set("ok", "22");
		System.out.println(shardedJedis.get("ok"));
		System.out.println(shardedJedis.getAllShardInfo().iterator().next()
				.getPort());

	}

	@Test
	public void name() {
	    String key = RedisUtils.set("key", "val");
	    System.out.println(key);
	    
	    String string = RedisUtils.get("key");
	    System.out.println(string);
    }
	
}
