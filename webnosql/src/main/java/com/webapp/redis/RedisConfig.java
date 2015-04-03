package com.webapp.redis;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;

import com.webapp.utils.config.ConfigUtils;

public class RedisConfig {

	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

	public static JedisPoolConfig getJedisPoolConfig(String redisCfg) {
		Configuration config = ConfigUtils.addConfig(redisCfg);
		JedisPoolConfig jedisCfg = new JedisPoolConfig();

		Map<String, String> cfg = new HashMap<>();
		config.getKeys().forEachRemaining(m -> {
			cfg.put(m.toLowerCase(), config.getString(m));
		});

		logger.info("属性prop --> 设置值set --> 默认值default");
		logger.info("----------------------------------");
		Method[] methods = JedisPoolConfig.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String methodName = method.getName();

			if (methodName.startsWith("set")) {
				String prop = methodName.replaceFirst("set", "").toLowerCase();
				if (cfg.containsKey(prop)) {
					String typeName = method.getParameterTypes()[0].getSimpleName();
					
					try {
						Method getMethod = JedisPoolConfig.class.getMethod("get" + methodName.replaceFirst("set", ""));
						logger.info("{} --> {} --> {}", prop, cfg.get(prop), getMethod.invoke(jedisCfg));
						
						if (typeName.equals(String.class.getSimpleName())) {
							method.invoke(jedisCfg, String.valueOf(cfg.get(prop)));
						} else if (typeName.equals(Integer.class.getSimpleName())) {
							method.invoke(jedisCfg, Integer.parseInt(cfg.get(prop)));
						} else if (typeName.equals(Long.class.getSimpleName())) {
							method.invoke(jedisCfg, Long.parseLong(cfg.get(prop)));
						} else if (typeName.equals(Boolean.class.getSimpleName())) {
							method.invoke(jedisCfg, Boolean.parseBoolean(cfg.get(prop)));
						}
					} catch (Exception e) {
						logger.error("调用redis配置出错", e);
					}
				}
			}
		}
		return jedisCfg;
	}

	public static void viewJedisPoolConfig() {
		logger.warn("--> 属性参数忽略大小写");

		Method[] methods = JedisPoolConfig.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (method.getName().startsWith("set")) {
				String name = method.getName().replaceFirst("set", "");
				logger.info("--> {}", name);
			}
		}
	}

}
