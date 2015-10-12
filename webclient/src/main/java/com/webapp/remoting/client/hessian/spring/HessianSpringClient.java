package com.webapp.remoting.client.hessian.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.webapp.remoting.service.hessian.IHessian;

public class HessianSpringClient {

	// @Test
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath*:applicationHessianClient.xml");
		IHessian client = context.getBean("hessianProxy", IHessian.class);

		// String url =
		// "http://localhost:8080/webservice/springHessian/hessianService";
		// IHessian client = HessianSpringUtils.getHessian(IHessian.class, url);
		System.out.println(client.sayHello());

	}

}
