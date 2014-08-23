package com.webapp.redis;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.Transaction;

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

	public static void main(String[] args) {
		Transaction tx = RedisUtils.ByTrans();
		tx.set("ok", "xxx");
		System.out.println(RedisUtils.exec(tx));
		System.out.println(RedisUtils.get("ok"));
	}

}
