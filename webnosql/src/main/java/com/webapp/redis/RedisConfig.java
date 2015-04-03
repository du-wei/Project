package com.webapp.redis;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;

import com.webapp.utils.config.ConfigUtils;

/**
 * @ClassName: RedisConfig.java
 * @Package com.webapp.redis
 * @Description: Redis Config
 * @author king king
 * @date 2014年4月5日 下午10:37:59
 * @version V1.0
 */
public class RedisConfig {

	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	/**
	 * @Title: getJedisPoolConfig
	 * @Description: TODO 方法描述
	 * @param redis
	 *            config file
	 * @return JedisPoolConfig
	 * @throws
	 */
	public static JedisPoolConfig getJedisPoolConfig(String redisCfg) {
		Configuration config = ConfigUtils.addConfig(redisCfg);
		JedisPoolConfig jedis = new JedisPoolConfig();

		Map<String, String> cfg = new HashMap<>();
		config.getKeys().forEachRemaining(m -> {
			cfg.put(m.toLowerCase(), config.getString(m));
		});

		Method[] methods = JedisPoolConfig.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String name = method.getName().replace("set", "").toLowerCase();
			if (method.getName().startsWith("set") && cfg.containsKey(name)) {
				logger.info(String.format("%1$s --> %2$s", method.getName(),
						cfg.get(name)));
				String type = method.getParameterTypes()[0].getSimpleName();
				try {
					if (type.equals(String.class.getSimpleName())) {
						method.invoke(jedis, String.valueOf(cfg.get(name)));
					} else if (type.equals(Integer.class.getSimpleName())) {
						method.invoke(jedis, Integer.parseInt(cfg.get(name)));
					} else if (type.equals(Long.class.getSimpleName())) {
						method.invoke(jedis, Long.parseLong(cfg.get(name)));
					} else if (type.equals(Boolean.class.getSimpleName())) {
						method.invoke(jedis,
								Boolean.parseBoolean(cfg.get(name)));
					}
				} catch (Exception e) {
					logger.error("调用redis配置出错", e);
				}
			}
		}
		return jedis;
	}

}
