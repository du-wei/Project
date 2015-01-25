package com.webapp.modules;

import java.util.Iterator;

import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;

/**
* @Description: spring容器初始化时 ContextBeanUtils会收集所有配置到该类中
*/
public class ContextPropsUtils {

	private static PropertySourcesPropertyResolver resolver = null;
	private static MutablePropertySources props = null;

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
