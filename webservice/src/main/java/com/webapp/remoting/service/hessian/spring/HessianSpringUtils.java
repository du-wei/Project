package com.webapp.remoting.service.hessian.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;

public class HessianSpringUtils {

	public static Logger logger = LogManager.getLogger(HessianSpringUtils.class);
	private static String host;
	private static int port;
	private static String appName;

	// 不可用
	private static <T> void pubHessianService(Class<T> serviceApi,
			T serviceImpl, String serviceUrl) {
		HessianServiceExporter hessianService = new HessianServiceExporter();
		hessianService.setServiceInterface(serviceApi);
		hessianService.setService(serviceImpl);
		hessianService.setInterceptors(new String[] { serviceUrl });
		hessianService.afterPropertiesSet();
	}

	private static <T> void pubHessianServiceByName(Class<T> serviceApi,
			T serviceImpl, String reqPath) {
		pubHessianService(serviceApi, serviceImpl, getBaseUrl() + reqPath);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getHessian(Class<T> serviceApi, String serviceUrl) {
		HessianProxyFactoryBean hessianProxy = new HessianProxyFactoryBean();
		hessianProxy.setServiceInterface(serviceApi);
		hessianProxy.setServiceUrl(serviceUrl);
		hessianProxy.afterPropertiesSet();

		return (T) hessianProxy.getObject();
	}

	public static <T> T getHessian(Class<T> serviceApi, String host, int port,
			String appName, String reqPath) {
		return getHessian(serviceApi, getBaseUrl(host, port, appName) + reqPath);
	}

	public static <T> T getHessianByName(Class<T> serviceApi, String reqPath) {
		if (host.equals("") || port == 0 || appName.equals("")) {
			throw new RuntimeException("请调用setBaseUrl方法设置host,port和appName");
		}
		return getHessian(serviceApi, host, port, appName, reqPath);
	}

	public static void setBaseUrl(String host, int port, String appName) {
		HessianSpringUtils.host = host;
		HessianSpringUtils.port = port;
		HessianSpringUtils.appName = appName;
	}

	public static String getBaseUrl() {
		return getBaseUrl(host, port, appName);
	}

	public static String getBaseUrl(String host, int port, String appName) {
		return "http://" + host + ":" + port + "/" + appName + "/";
	}

}
