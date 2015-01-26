package com.webapp.remoting.client.http.spring;

import com.webapp.remoting.service.http.spring.HttpSpringUtils;
import com.webapp.remoting.service.http.spring.IHttp;

public class HttpSpringClient {

	public static void main(String[] args) {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("classpath*:applicationHttpClient.xml");
		// IHttp client = context.getBean("httpServiceReq", IHttp.class);

		IHttp client = HttpSpringUtils.getHttp(IHttp.class,
				"http://localhost:8088/webservice/https/httpServiceReq");

		System.out.println(client.sayHello());
	}

}
