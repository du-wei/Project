package com.webapp.database;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.stereotype.Component;

import com.webapp.modules.ContextPropsUtils;

@Component
public class ContextBeanUtils implements ApplicationContextAware {

	private static ApplicationContext ctx = null;
	
	@Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
		ctx = context;
		
		Properties props = new Properties();
	    try {
	        Map<String, PropertiesFactoryBean> beansOfType = ctx.getBeansOfType(PropertiesFactoryBean.class);
	        Iterator<String> iterator = beansOfType.keySet().iterator();
	        for(;iterator.hasNext();){
	        	String next = iterator.next();
	        	props.putAll(beansOfType.get(next).getObject());
	        }
	        
	        Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
	        mergeProperties.setAccessible(true);
	        Properties prop = (Properties) mergeProperties.invoke(ctx.getBean(PropertyPlaceholderConfigurer.class));
	        
	        props.putAll(prop);
	        
	        Field[] fields = ContextPropsUtils.class.getDeclaredFields();
	        for(Field field : fields){
	        	if(field.getType().isAssignableFrom(Properties.class)){
	        		field.setAccessible(true);
	        		field.set(null, props);
	        	}
	        }
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }
	
	public static <T> T getBean(Class<T> clz) {
		checkAppContext();
		return ctx.getBean(clz);
    }
	
	public static Object getBean(String name) {
		checkAppContext();
		return ctx.getBean(name);
    }
	
	public static ApplicationContext getApplicationContext() {
		checkAppContext();
		return ctx;
	}

	public static void cleanApplicationContext() {
		ctx = null;
	}

	private static void checkAppContext() {
		if (ctx == null) {
			throw new IllegalStateException("applicaitonContext is null");
		}
	}

}
