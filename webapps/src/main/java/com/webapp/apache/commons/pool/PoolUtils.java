package com.webapp.apache.commons.pool;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.webapp.utils.config.ConfigUtils;
import com.webapp.utils.config.PathUtils;

public class PoolUtils {

	public static Logger logger = LogManager.getLogger(PoolUtils.class);

	public static <T> GenericObjectPool<T> getPool(
			PooledObjectFactory<T> factory, String config) {
		CompositeConfiguration prop = ConfigUtils.addConfig(config);
		GenericObjectPool<T> pool = new GenericObjectPool<>(factory,
				getPoolConfig(prop));

		int count = prop.getInt("minIdle");
		try {
			for (int i = 0; i < count; i++) {
				pool.addObject();
			}
		} catch (Exception e) {
			logger.error("对象池生成对象出错", e);
		}
		return pool;
	}

	public static <K, V> GenericKeyedObjectPool<K, V> getKeyedPool(
			KeyedPooledObjectFactory<K, V> factory, String config, List<K> keys) {
		CompositeConfiguration prop = ConfigUtils.addConfig(config);
		GenericKeyedObjectPool<K, V> pool = new GenericKeyedObjectPool<>(
				factory, getKeyedPoolConfig(prop));
		int count = prop.getInt("minIdle");
		try {
			for (int i = 0; i < count; i++) {
				for (int j = 0; j < keys.size(); j++) {
					pool.addObject(keys.get(j));
				}
			}
		} catch (Exception e) {
			logger.error("对象池生成对象出错", e);
		}
		return pool;
	}

	public static <K, V> GenericKeyedObjectPool<K, V> getKeyedPool(
			KeyedPooledObjectFactory<K, V> factory, String config, K... keys) {
		return getKeyedPool(factory, config, Arrays.asList(keys));
	}

	private static GenericObjectPoolConfig getPoolConfig(
			CompositeConfiguration props) {
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMinIdle(props.getInt("minIdle",
				GenericObjectPoolConfig.DEFAULT_MIN_IDLE));
		config.setMaxIdle(props.getInt("maxIdle",
				GenericObjectPoolConfig.DEFAULT_MAX_IDLE));
		config.setMaxTotal(props.getInt("maxTotal",
				GenericObjectPoolConfig.DEFAULT_MAX_TOTAL));
		setBaseObjectPoolConfig(config, props);
		return config;
	}

	private static GenericKeyedObjectPoolConfig getKeyedPoolConfig(
			CompositeConfiguration props) {
		GenericKeyedObjectPoolConfig config = new GenericKeyedObjectPoolConfig();
		config.setMinIdlePerKey(props.getInt("minIdle",
				GenericKeyedObjectPoolConfig.DEFAULT_MIN_IDLE_PER_KEY));
		config.setMaxIdlePerKey(props.getInt("maxIdle",
				GenericKeyedObjectPoolConfig.DEFAULT_MAX_IDLE_PER_KEY));
		config.setMaxTotalPerKey(props.getInt("maxTotalPerKey",
				GenericKeyedObjectPoolConfig.DEFAULT_MAX_TOTAL_PER_KEY));
		config.setMaxTotal(props.getInt("maxTotal",
				GenericKeyedObjectPoolConfig.DEFAULT_MAX_TOTAL));
		setBaseObjectPoolConfig(config, props);
		return config;
	}

	private static void setBaseObjectPoolConfig(BaseObjectPoolConfig config,
			Configuration props) {
		config.setLifo(props.getBoolean("lifo",
				BaseObjectPoolConfig.DEFAULT_LIFO));
		config.setMaxWaitMillis(props.getLong("maxWaitMillis",
				BaseObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS));
		config.setJmxEnabled(props.getBoolean("jmxEnabled",
				BaseObjectPoolConfig.DEFAULT_JMX_ENABLE));
		config.setJmxNamePrefix(props.getString("jmxNamePrefix",
				BaseObjectPoolConfig.DEFAULT_JMX_NAME_PREFIX));
		config.setTestOnBorrow(props.getBoolean("testOnBorrow",
				BaseObjectPoolConfig.DEFAULT_TEST_ON_BORROW));
		config.setTestOnReturn(props.getBoolean("testOnReturn",
				BaseObjectPoolConfig.DEFAULT_TEST_ON_RETURN));
		config.setTestWhileIdle(props.getBoolean("testWhileIdle",
				BaseObjectPoolConfig.DEFAULT_TEST_WHILE_IDLE));
		config.setBlockWhenExhausted(props.getBoolean("blockWhenExhausted",
				BaseObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED));
		config.setNumTestsPerEvictionRun(props.getInt("numTestsPerEvictionRun",
				BaseObjectPoolConfig.DEFAULT_NUM_TESTS_PER_EVICTION_RUN));
		config.setEvictionPolicyClassName(props.getString(
				"evictionPolicyClassName",
				BaseObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME));
		config.setMinEvictableIdleTimeMillis(props.getLong(
				"minEvictableIdleTimeMillis",
				BaseObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS));
		config.setTimeBetweenEvictionRunsMillis(props.getLong(
				"timeBetweenEvictionRunsMillis",
				BaseObjectPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS));
		config.setSoftMinEvictableIdleTimeMillis(props
				.getLong(
						"softMinEvictableIdleTimeMillis",
						BaseObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS));

	}

}
