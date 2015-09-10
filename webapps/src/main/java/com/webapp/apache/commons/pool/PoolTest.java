package com.webapp.apache.commons.pool;

import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.junit.Test;

public class PoolTest {

	public void test(GenericObjectPool<String> pool) throws Exception {

		for (int i = 0; i < 21; i++) {
			String ok = pool.borrowObject();
			System.out.println(ok);
			// pool.returnObject(ok);
		}

		System.out.println("创建实例的个数-->" + pool.getCreatedCount());
		System.out.println("借走的数量-->" + pool.getBorrowedCount());
		System.out.println("借走的数量-->" + pool.getNumActive());
		System.out.println("处于空闲状态的实例数-->" + pool.getNumIdle());
		System.out.println("处于空闲状态的最大实例数-->" + pool.getMaxIdle());
		System.out.println("处于空闲状态的最小实例数-->" + pool.getMinIdle());
		System.out.println("对象池中可分配的最大数-->" + pool.getMaxTotal());
		System.out.println("最大等待时间-->" + pool.getMaxWaitMillis());
		System.out.println("是否检查借走的对象为遗弃对象-->"
		        + pool.getRemoveAbandonedOnBorrow());
		System.out.println("是否检查运行的对象为遗弃对象-->"
		        + pool.getRemoveAbandonedOnMaintenance());
		System.out.println("获取遗弃的对象-->" + pool.getRemoveAbandonedTimeout());
		System.out.println("取出对象时检查-->" + pool.getTestOnBorrow());
		System.out.println("归还对象时检查-->" + pool.getTestOnReturn());
		System.out.println("归还对象时检查-->" + pool.getTestWhileIdle());
		System.out.println("归还对象时检查-->"
		        + pool.getTimeBetweenEvictionRunsMillis());
		System.out.println("归还对象时检查-->" + pool.getMinEvictableIdleTimeMillis());
		System.out.println("后进先出-->" + pool.getLifo());
		// System.out.println("factoryType-->"+pool.getFactoryType());
	}

	public void test(GenericKeyedObjectPool<String, String> pool)
	        throws Exception {

		for (int i = 0; i < 2; i++) {
			System.out.println(pool.borrowObject("key"));
		}

		System.out.println("创建实例的个数-->" + pool.getCreatedCount());
		System.out.println("借走的数量-->" + pool.getBorrowedCount());
		System.out.println("借走的数量-->" + pool.getNumActive());
		System.out.println("处于空闲状态的实例数-->" + pool.getNumIdle());
		System.out.println("对象池中可分配的最大数-->" + pool.getMaxTotal());
		System.out.println("最大等待时间-->" + pool.getMaxWaitMillis());
		System.out.println("取出对象时检查-->" + pool.getTestOnBorrow());
		System.out.println("归还对象时检查-->" + pool.getTestOnReturn());
		System.out.println("归还对象时检查-->" + pool.getTestWhileIdle());
		System.out.println("归还对象时检查-->"
		        + pool.getTimeBetweenEvictionRunsMillis());
		System.out.println("归还对象时检查-->" + pool.getMinEvictableIdleTimeMillis());
		System.out.println("后进先出-->" + pool.getLifo());
		// System.out.println("factoryType-->"+pool.getFactoryType());
	}

	@Test
	public void testName() throws Exception {
		// testKey();
		// GenericObjectPool<String> pool = PoolUtils.getPool(new
		// PooledFactoryImpl<String>(), "pool.properties");

		System.out.println("==========================");

		// test(pool);
		GenericKeyedObjectPool<String, String> keyPool = PoolUtils
		        .getKeyedPool(new KeyedPooledFactoryImpl<String, String>(),
		                "pool.properties", "first");
		test(keyPool);
	}
}
