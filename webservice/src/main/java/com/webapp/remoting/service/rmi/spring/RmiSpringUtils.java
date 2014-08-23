package com.webapp.remoting.service.rmi.spring;

import java.rmi.RemoteException;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

public class RmiSpringUtils {

	public static <T> void pubRmiService(String serviceName,
			Class<T> serviceApi, T serviceImpl) {
		pubRmiService(serviceName, serviceApi, serviceImpl, 1099);
	}

	public static <T> void pubRmiService(String serviceName,
			Class<T> serviceApi, T serviceImpl, int port) {
		RmiServiceExporter rmiService = new RmiServiceExporter();
		rmiService.setServiceName(serviceName);
		rmiService.setService(serviceImpl);
		rmiService.setServiceInterface(serviceApi);
		rmiService.setRegistryPort(port);
		rmiService.setServicePort(port);
		try {
			rmiService.afterPropertiesSet();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T getRmi(String url, Class<T> serviceApi) {
		RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
		rmiProxy.setServiceInterface(serviceApi);
		rmiProxy.setServiceUrl(url);
		rmiProxy.setLookupStubOnStartup(false);
		rmiProxy.setRefreshStubOnConnectFailure(true);
		rmiProxy.afterPropertiesSet();
		return (T) rmiProxy.getObject();
	}

	public static <T> T getRmi(String host, int port, String name,
			Class<T> serviceApi) {
		return getRmi("rmi://" + host + ":" + port + "/" + name, serviceApi);
	}

	public static <T> T getRmi(String host, String name, Class<T> serviceApi) {
		return getRmi(host, 1099, name, serviceApi);
	}

}
