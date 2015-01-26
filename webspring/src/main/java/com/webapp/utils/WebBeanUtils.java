package com.webapp.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

@Component
public class WebBeanUtils implements ApplicationContextAware {

	private static ApplicationContext ctx = null;

	@Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
		ctx = context;

	    try {
	    	MutablePropertySources mps = WebPropsResolver.initPropertySources(true);
	    	WebPropsResolver.resolverPropertySourcesPlaceholder(ctx, mps);
	    	WebPropsResolver.resolverPropertyPlaceholder(ctx, mps);
	    	WebPropsResolver.resolverPropertyFactory(ctx, mps);
	    	WebPropsResolver.inject(ctx, mps);
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
