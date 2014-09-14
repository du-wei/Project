package com.webapp.remoting.service.http.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

public class HttpSpringUtils {

	public static Logger logger = LogManager.getLogger(HttpSpringUtils.class);
	private static String host;
	private static int port;
	private static String appName;

	// 不可用
	private static <T> void pubHttpService(Class<T> serviceApi, T serviceImpl,
			String serviceUrl) {
		HttpInvokerServiceExporter httpService = new HttpInvokerServiceExporter();
		httpService.setServiceInterface(serviceApi);
		httpService.setService(serviceImpl);
		httpService.setInterceptors(new String[] { serviceUrl });
		httpService.afterPropertiesSet();
	}

	private static <T> void pubHttpServiceByName(Class<T> serviceApi,
			T serviceImpl, String reqPath) {
		pubHttpService(serviceApi, serviceImpl, getBaseUrl() + reqPath);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getHttp(Class<T> serviceApi, String serviceUrl) {
		HttpInvokerProxyFactoryBean httpInvokerProxy = new HttpInvokerProxyFactoryBean();
		httpInvokerProxy.setServiceInterface(serviceApi);
		httpInvokerProxy.setServiceUrl(serviceUrl);
		httpInvokerProxy.afterPropertiesSet();

		return (T) httpInvokerProxy.getObject();
	}

	public static <T> T getHttp(Class<T> serviceApi, String host, int port,
			String appName, String reqPath) {
		return getHttp(serviceApi, getBaseUrl(host, port, appName) + reqPath);
	}

	public static <T> T getHttpByName(Class<T> serviceApi, String reqPath) {
		if (host.equals("") || port == 0 || appName.equals("")) {
			throw new RuntimeException("请调用setBaseUrl方法设置host,port和appName");
		}
		return getHttp(serviceApi, host, port, appName, reqPath);
	}

	public static void setBaseUrl(String host, int port, String appName) {
		HttpSpringUtils.host = host;
		HttpSpringUtils.port = port;
		HttpSpringUtils.appName = appName;
	}

	public static String getBaseUrl() {
		return getBaseUrl(host, port, appName);
	}

	public static String getBaseUrl(String host, int port, String appName) {
		return "http://" + host + ":" + port + "/" + appName + "/";
	}

}
