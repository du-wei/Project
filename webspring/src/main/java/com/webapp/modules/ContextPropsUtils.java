package com.webapp.modules;

import java.util.Properties;

/**   
* @Description: spring容器初始化时 ContextBeanUtils会收集所有配置到该类中
*/
public class ContextPropsUtils {

	private static Properties props = new Properties();

	public static Properties get() {
	    return props;
    }
	
	public static Object get(Object key){
		return props.get(key);
	}
	
	public static String get(String key){
		return props.getProperty(key);
	}
	
	public static boolean hasKey(Object key){
		return props.containsKey(key);
	}
	
}
