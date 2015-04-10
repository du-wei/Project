package com.webapp.redis;

import java.util.concurrent.atomic.AtomicInteger;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.timer.RandomTimer;
import org.junit.Rule;
import org.junit.Test;

public class RedisTest extends RunnerWeb {
	
	@Test
	public void test() {
		// ShardedJedis shardedJedis = RedisClusterUtils.getShardedJedis();
		//
		// System.out.println(shardedJedis.get("key"));

		// RedisClusterUtils.name();
//		JedisShardInfo jedisShardInfo = new JedisShardInfo("localhost", "6380");
//		jedisShardInfo.setPassword("root");
//		List<JedisShardInfo> shards = Arrays.asList(jedisShardInfo);
//		ShardedJedis shardedJedis = new ShardedJedis(shards);
//
//		shardedJedis.set("ok", "22");
//		System.out.println(shardedJedis.get("ok"));
//		System.out.println(shardedJedis.getAllShardInfo().iterator().next()
//				.getPort());

	}
	
//	@Rule
//	public ContiPerfRule rule = new ContiPerfRule();
	private AtomicInteger index = new AtomicInteger();
	
//	@PerfTest(invocations=200, threads = 1, timer = RandomTimer.class, 
//			timerParams = {1, 5})
	@Test
	public void get() {
		String string = RedisUtils.get("key");
//	    System.out.println(index.incrementAndGet() + " --> " + string);
    }
	
	@Test
	public void viewConfig(){
		RedisConfig.viewJedisPoolConfig();
	}
	
}
