package com.webapp.utils;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	// 在线人数计数器
	int userCount = 0;

	// 当产生一个新的HttpSession对象(新用户上线)时执行此方法
	public void sessionCreated(HttpSessionEvent event) {
		// 在线人数加1
		event.getSession().getServletContext()
				.setAttribute("count", new Integer(++userCount));
		System.out.println("【监听到】新用户" + event.getSession().getId() + "上线!");
	}

	// 当一个HttpSession对象销毁(用户下线)时执行此方法
	public void sessionDestroyed(HttpSessionEvent event) {
		// 人数减1
		event.getSession().getServletContext()
				.setAttribute("count", new Integer(--userCount));
		System.out.println("【监听到】用户" + event.getSession().getId() + "下线!");
	}

	// 当HttpSession对象中新增属性时将执行此方法
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("【监听到】HttpSession对象中新增一名为" + event.getName()
				+ "的属性，其属性值为" + event.getValue());
	}

	// 当HttpSession对象中删除属性时将执行此方法
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("【监听到】HttpSession对象中一名为" + event.getName()
				+ "的属性被删除!");
	}

	// 当HttpSession对象中更新属性时将执行此方法
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("【监听到】HttpSession对象中一名为" + event.getName()
				+ "的属性被更新!");
	}

}
