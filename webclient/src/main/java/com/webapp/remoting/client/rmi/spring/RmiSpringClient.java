package com.webapp.remoting.client.rmi.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.webapp.remoting.service.rmi.spring.MyRemote;

public class RmiSpringClient {

	public static void main(String[] args) {
		// 通过spring rmi代理获取
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:applicationRmiClient.xml");
		MyRemote client = context.getBean("rmiSpringService", MyRemote.class);

		// MyRemote client =
		// RmiSpringUtils.getRmi("rmi://localhost:1099/rmiSpringService",
		// MyRemote.class);
		System.out.println(client.sayHello());
	}

}
