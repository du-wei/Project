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
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;
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

		MutablePropertySources mps = new MutablePropertySources();
	    try {

	    	//获取以PropertyPlaceholderConfigurer配置的属性
	        Map<String, PropertySourcesPlaceholderConfigurer> propSource = ctx.getBeansOfType(PropertySourcesPlaceholderConfigurer.class);
	        for(Iterator<String> it = propSource.keySet().iterator(); it.hasNext();){
	        	String propId = it.next();

	        	Iterator<PropertySource<?>> iterator = propSource.get(propId).getAppliedPropertySources().iterator();
	        	for(;iterator.hasNext();){
	        		PropertySource<?> prop = iterator.next();
	        		if(prop.getName().equalsIgnoreCase("localProperties")){
	        			mps.addFirst(new PropertiesPropertySource(propId, (Properties)prop.getSource()));
	        		}
	        	}
	        }

	    	//获取PropertiesFactoryBean类型的配置  utils
	        Map<String, PropertiesFactoryBean> propMap = ctx.getBeansOfType(PropertiesFactoryBean.class);
	        for(Iterator<String> it = propMap.keySet().iterator();it.hasNext();){
	        	String propId = it.next();
	        	mps.addFirst(new PropertiesPropertySource(propId.substring(1), propMap.get(propId).getObject()));
	        }

	        //获取以PropertyPlaceholderConfigurer配置的属性
	        Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
	        mergeProperties.setAccessible(true);
	        String[] propConfigurers = ctx.getBeanNamesForType(PropertyPlaceholderConfigurer.class);
	        for(String prop : propConfigurers){
	        	Properties addProp = (Properties) mergeProperties.invoke((PropertyPlaceholderConfigurer) ctx.getBean(prop));
	        	mps.addFirst(new PropertiesPropertySource(prop, addProp));
	        }

	        PropertySourcesPropertyResolver resolver = new PropertySourcesPropertyResolver(mps);

	        Field[] fields = ContextPropsUtils.class.getDeclaredFields();
	        for(Field field : fields){
	        	if(field.getType().isAssignableFrom(PropertySourcesPropertyResolver.class)){
	        		field.setAccessible(true);
	        		field.set(null, resolver);
	        	}else if(field.getType().isAssignableFrom(MutablePropertySources.class)){
	        		field.setAccessible(true);
	        		field.set(null, mps);
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
