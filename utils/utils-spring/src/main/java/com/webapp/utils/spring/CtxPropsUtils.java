package com.webapp.utils.spring;

import java.util.Iterator;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;

import com.alibaba.fastjson.JSON;

/**
* @Description: spring容器初始化时 CtxPropsUtils会收集所有配置到该类中
* CtxBeanUtils
*/
public final class CtxPropsUtils {

	private static final Logger logger = LoggerFactory.getLogger(CtxPropsUtils.class);
	private static PropertySourcesPropertyResolver resolver = null;
	private static MutablePropertySources props = null;

	public static Properties getSysEnv(){
		PropertySource<?> ps = props.get("systemEnvironment");
		if(ps == null){
			logger.warn("There is no resolve systemEnvironment");
			return new Properties();
		}
		return (Properties)ps.getSource();
	}

	public static Properties getSysProps(){
		PropertySource<?> ps = props.get("systemProperties");
		if(ps == null){
			logger.warn("There is no resolve systemProperties");
			return new Properties();
		}
		return (Properties)ps.getSource();
	}

	public static Properties getProp(String beanId){
		PropertySource<?> ps = props.get(beanId);
		if(ps == null){
			logger.warn("There is no bean id " + beanId);
			return new Properties();
		}
		return (Properties)ps.getSource();
	}

	public static String get(String key, String defaultValue){
		return resolver.getProperty(key, defaultValue);
	}

	public static String get(String key){
		return resolver.getProperty(key);
	}

	public static boolean hasKey(String key){
		return resolver.containsProperty(key);
	}

	public static void showProp(){
		Iterator<PropertySource<?>> iterator = props.iterator();
		for(;iterator.hasNext();){
			PropertySource<?> next = iterator.next();
			System.out.println(next.getName() + "-----------" + JSON.toJSONString(next.getSource(), true));
		}
	}

}
