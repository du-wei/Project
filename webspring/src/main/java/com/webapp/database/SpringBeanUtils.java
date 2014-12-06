package com.webapp.database;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanUtils implements ApplicationContextAware {

	private static ApplicationContext ctx = null;
	
	@Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
		ctx = context;
    }

	public static <T> T getBean(Class<T> clz) {
		return ctx.getBean(clz);
    }
	
	public static Object getBean(String name) {
		return ctx.getBean(name);
    }

}
