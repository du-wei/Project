package com.webapp.webservice.service.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.webapp.webservice.service.cxf.CxfHandler;
import com.webapp.webservice.service.cxf.CxfHello;
import com.webapp.webservice.service.cxf.CxfHelloImpl;
import com.webapp.webservice.service.cxf.CxfInterceptor;
import com.webapp.webservice.service.jaxws.HelloService;

public class WebServiceUtils {
	// SEI Service Endpoint Interface
	// SIB Service Implemention Bean
	// JAX-WS 2.0 JAX-RPC 1.1 JSR224 JAXB JSR the Streaming API for XML (StAX,
	// JSR 173)
	// SOAP 1.1 simple object access protocol
	// jaxws2.0

	// 通过JAX-WS发布WebServcie
	public static void pubServiceByJaxWs(String url, Object implementor) {
		Endpoint.publish(url, implementor);
	}

	// JAX-WS客户端 //但是必须要有服务器端的接口
	public static void getServiceByJaxWs() throws Exception {
		QName service_name = new QName(
				"http://jaxws.service.webservice.webapp.com/",
				"HelloServiceImplService");
		String endpointAddress = "http://localhost:8080/webservice/service?wsdl";
		URL url = new URL(endpointAddress);
		Service service = Service.create(url, service_name);
		HelloService helloService = service.getPort(HelloService.class);

		// 标准的JAX-WS客户端
		// QName service_name = new
		// QName("http://jaxws.service.webservice.webapp.com/",
		// "HelloServiceImplService");
		// QName port_name = new
		// QName("http://jaxws.service.webservice.webapp.com/", "HelloService");
		// String endpointAddress = "http://localhost:8080/webservice/service";
		// Service service = Service.create(service_name);
		// service.addPort(port_name, SOAPBinding.SOAP11HTTP_BINDING ,
		// endpointAddress);
		// HelloService helloService = service.getPort(HelloService.class);

		System.out.println(helloService.sayHello("hello"));

	}

	// CXF客户端 JaxWs实现 是利用cxf的反向代理工厂
	public static void pubServiceByCxfWs(String url, Object implementor) {
		JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
		serverFactory.setServiceClass(CxfHello.class);
		serverFactory.setAddress(url);
		serverFactory.setServiceBean(implementor);
		serverFactory.getInInterceptors().add(new CxfInterceptor("xx"));
		List<Handler> handlers = new ArrayList<>();
		handlers.add(new CxfHandler());

		serverFactory.setHandlers(handlers);
		// serverFactory.getFeatures().add(new LoggingFeature());
		// serverFactory.getFeatures().add(new CxfFeatures());
		//
		// Map<String, Object> props = new HashMap<String, Object>();
		// props.put(WSHandlerConstants.ACTION,
		// WSHandlerConstants.USERNAME_TOKEN);
		// props.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		// props.put(WSHandlerConstants.PW_CALLBACK_CLASS,
		// CxfServiceSecurity.class.getName());
		// serverFactory.getInInterceptors().add(new WSS4JInInterceptor(props));

		serverFactory.create();
		// Server server = (Server) factoryBean.create();
		// server.start();
		System.out.println("Server start...");
	}

	public static void getServiceByCxfWs() {
		// 客户端测试
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setServiceClass(CxfHello.class);
		factoryBean.setAddress("http://localhost:8080/ws/cxf");

		// Map<String,Object> prop = new HashMap<String,Object>();
		// prop.put(WSHandlerConstants.ACTION,
		// WSHandlerConstants.USERNAME_TOKEN);
		// prop.put(WSHandlerConstants.USER, "admin");
		// prop.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		// prop.put(WSHandlerConstants.PW_CALLBACK_CLASS,
		// CxfClientSecurity.class.getName());
		// // 添加cxf安全验证拦截器，必须
		// factoryBean.getOutInterceptors().add(new WSS4JOutInterceptor(prop));

		CxfHello cxf = (CxfHello) factoryBean.create();
		System.out.println(cxf.sayHello(" jjjj "));

	}

	public static void pubServiceByCxfPojo() {
		// 服务器端访问 服务器端设置setServiceBean是实现类
		ServerFactoryBean factoryBean = new ServerFactoryBean();
		factoryBean.setServiceClass(CxfHello.class);
		factoryBean.setAddress("http://localhost:8080/ws/cxf");
		factoryBean.setServiceBean(new CxfHelloImpl());
		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
		factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
		factoryBean.create();
	}

	public static void getServiceByCxfPojo() {
		// 客户端访问 客户端设置setServiceClass是接口
		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		factory.setServiceClass(CxfHello.class);
		factory.setAddress("http://localhost:8080/ws/cxf");
		CxfHello client = (CxfHello) factory.create();
		System.out.println(client.sayHello("hello"));
	}

	public static void getServiceByCxfDynamic() {
		// 动态
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient("http://localhost:8080/ws/cxf?wsdl");
		Object[] objects = null;
		try {
			objects = client.invoke("sayHello", "张三");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(objects[0].toString());
	}

}
