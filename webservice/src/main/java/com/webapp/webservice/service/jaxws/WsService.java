package com.webapp.webservice.service.jaxws;

import com.webapp.webservice.service.utils.WebServiceUtils;

public class WsService {

	public static void main(String[] args) {
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
		// CxfHello cxfService = (CxfHello)context.getBean("cxfClient");
		// System.out.println(cxfService.sayHello(" hello "));

		WebServiceUtils.pubServiceByJaxWs(
				"http://localhost:8080/webservice/service",
				new HelloServiceImpl());

	}

}
