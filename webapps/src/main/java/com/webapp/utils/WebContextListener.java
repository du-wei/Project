package com.webapp.utils;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebContextListener implements ServletContextListener,
        ServletContextAttributeListener {

	private static final Logger logger = LoggerFactory.getLogger(WebContextListener.class);
	// 当应用启动时将执行此方法
	@Override
	public void contextInitialized(ServletContextEvent event) {
		String webapp = event.getServletContext().getContextPath();
		logger.info("【监听到】" + webapp + "应用被启动!");
	}

	// 当应用关闭时将执行此方法
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		String webapp = event.getServletContext().getContextPath();
		logger.info("【监听到】" + webapp + "应用被关闭!");
	}

	// 当ServletContext对象中新增属性时将执行此方法
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		logger.info("【监听到】ServletContext对象中新增一名为" + event.getName()
		        + "的属性，其属性值为" + event.getValue());
	}

	// 当ServletContext对象中删除属性时将执行此方法
	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		logger.info("【监听到】ServletContext对象中一名为" + event.getName() + "的属性被删除!");
	}

	// 当ServletContext对象中某属性被更新时将执行此方法
	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		logger.info("【监听到】ServletContext对象中一名为" + event.getName() + "的属性被更新!");
	}

}
