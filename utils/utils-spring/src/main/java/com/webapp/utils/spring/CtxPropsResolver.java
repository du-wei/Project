package com.webapp.utils.spring;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.support.PropertiesLoaderSupport;

public class CtxPropsResolver {
	
	protected static MutablePropertySources initPropertySources(boolean hasEnv) {
		if(hasEnv){
			return new MutablePropertySources((new StandardEnvironment()).getPropertySources());
		}
		return new MutablePropertySources();
	}
	
	protected static void resolverPropertySourcesPlaceholder(ApplicationContext ctx, MutablePropertySources mps) {
		//获取以PropertySourcesPlaceholderConfigurer配置的属性
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
	}
	
	protected static void resolverPropertyPlaceholder(ApplicationContext ctx, MutablePropertySources mps) throws Exception {
		 //获取以PropertyPlaceholderConfigurer配置的属性
        Method mergeProperties = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
        mergeProperties.setAccessible(true);
        String[] propConfigurers = ctx.getBeanNamesForType(PropertyPlaceholderConfigurer.class);
        for(String prop : propConfigurers){
        	Properties addProp = (Properties) mergeProperties.invoke((PropertyPlaceholderConfigurer) ctx.getBean(prop));
        	mps.addFirst(new PropertiesPropertySource(prop, addProp));
        }
	}
	
	protected static void resolverPropertyFactory(ApplicationContext ctx, MutablePropertySources mps) throws Exception {
		//获取PropertiesFactoryBean类型的配置  utils
        Map<String, PropertiesFactoryBean> propMap = ctx.getBeansOfType(PropertiesFactoryBean.class);
        for(Iterator<String> it = propMap.keySet().iterator();it.hasNext();){
        	String propId = it.next();
        	mps.addFirst(new PropertiesPropertySource(propId.substring(1), propMap.get(propId).getObject()));
        }
	}
	
	protected static void inject(ApplicationContext ctx, MutablePropertySources mps) throws Exception {
		PropertySourcesPropertyResolver resolver = new PropertySourcesPropertyResolver(mps);

        Field[] fields = CtxPropsUtils.class.getDeclaredFields();
		for(Field field : fields){
			if(field.getType().isAssignableFrom(PropertySourcesPropertyResolver.class)){
				field.setAccessible(true);
				field.set(null, resolver);
			}else if(field.getType().isAssignableFrom(MutablePropertySources.class)){
				field.setAccessible(true);
				field.set(null, mps);
			}
		}
	}
	
}
