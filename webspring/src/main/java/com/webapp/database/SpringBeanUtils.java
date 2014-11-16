package com.webapp.database;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtils extends ApplicationObjectSupport /*implements ApplicationContextAware */ {

	private static ApplicationContext ctx;
	
	@Override
    protected void initApplicationContext(ApplicationContext context)
            throws BeansException {
	    super.initApplicationContext(context);
	    ctx = context;
    }
	
	public static <T> T getBean(Class<T> clz) {
		return ctx.getBean(clz);
    }
	
	public static Object getBean(String name) {
		return ctx.getBean(name);
    }

}
