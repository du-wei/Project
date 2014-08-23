package com.webapp.webservice.client.jaxws;

import org.junit.Test;

import com.webapp.webservice.service.jaxws.HelloService;
import com.webapp.webservice.service.jaxws.HelloServiceImplService;
import com.webapp.webservice.service.utils.WebServiceUtils;

public class WebServiceClient {

	public static void main(String[] args) throws Exception {
		// JDKUtils.WSDL2Java("http://localhost:8080/webservice/service?wsdl");
		// testName2();
		WebServiceUtils.getServiceByJaxWs();
	}

	@Test
	public void testName() throws Exception {
		WebServiceUtils.getServiceByJaxWs();
	}

	// 通过生成的客户端访问
	public static void testName2() {
		HelloServiceImplService hService = new HelloServiceImplService();
		HelloService helloService = hService.getHelloServiceImplPort();
		System.out.println(helloService.sayHello("hello"));
	}

}
