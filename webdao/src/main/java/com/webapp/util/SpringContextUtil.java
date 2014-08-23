package com.webapp.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext appContext;

	@Override
	public void setApplicationContext(ApplicationContext app)
			throws BeansException {
		SpringContextUtil.appContext = app;
	}

	public static ApplicationContext getApplicationContext() {
		return appContext;
	}

	public static Object getBean(String name) throws BeansException {
		return appContext.getBean(name);
	}
}
