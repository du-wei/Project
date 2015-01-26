package com.webapp.utils;

import java.util.Iterator;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;

/**
* @Description: spring容器初始化时 ContextBeanUtils会收集所有配置到该类中
*/
public class WebPropsUtils {
	
	private static final Logger logger = LogManager.getLogger(WebPropsUtils.class);
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

	public static void viewProp(){
		Iterator<PropertySource<?>> iterator = props.iterator();
		for(;iterator.hasNext();){
			PropertySource<?> next = iterator.next();
			System.out.println(next.getName() + "-----------" + next.getSource());
		}
	}

}
