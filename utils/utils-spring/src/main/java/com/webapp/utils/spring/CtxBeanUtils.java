package com.webapp.utils.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

@Component
public class CtxBeanUtils implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(CtxBeanUtils.class);
	private static ApplicationContext ctx = null;

    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
		ctx = context;

	    try {
	    	MutablePropertySources mps = CtxPropsResolver.initPropertySources(true);
	    	CtxPropsResolver.resolverPropertySourcesPlaceholder(ctx, mps);
	    	CtxPropsResolver.resolverPropertyPlaceholder(ctx, mps);
	    	CtxPropsResolver.resolverPropertyFactory(ctx, mps);
	    	CtxPropsResolver.inject(ctx, mps);
        } catch (Exception e) {
	        logger.error("Error collecting property", e);
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

	public static void viewBean() {
		checkAppContext();
		String[] beans = ctx.getBeanDefinitionNames();
		Arrays.parallelSort(beans);

		List<String> full = new ArrayList<>();
		int i=0;
		for(String bean : beans){
			if(bean.contains(".")) {
				full.add(bean);
				continue;
			}
			System.out.println(++i + " " + bean);
			System.out.println("\t|--" + ctx.getBean(bean).getClass());
		}
		System.out.println("-------------------------------------------");
		for(String bean : full){
			System.out.println(++i + " " + bean);
			System.out.println("\t|--" + ctx.getBean(bean).getClass());
		}
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
