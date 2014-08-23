package com.webapp.remoting.service.rmi.spring;

public class RmiSpringService {

	public static void main(String[] args) {
		// spring rmi客户端， 通过配置客户端获取rmi
		// ApplicationContext applicationContext = new
		// ClassPathXmlApplicationContext("classpath*:applicationRmiService.xml");

		// 通过spring Exporter发布rmi
		RmiSpringUtils.pubRmiService("rmiSpringService", MyRemote.class,
				new MyRemoteImpl());

	}

}
