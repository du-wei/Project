package com.webapp.redis;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import com.webapp.utils.test.ThreadUtils;

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
//	    String key = RedisUtils.set("key", "val");
//	    System.out.println(key);
//	    
//	    String string = RedisUtils.get("key");
//	    System.out.println(string);
		
    }
	
	@Test
	public void get() {
		for(int i=0; i<2000; i++){
			String string = RedisUtils.get("key");
		    System.out.println(i + " - " +string);
		}
		
    }
	
}
