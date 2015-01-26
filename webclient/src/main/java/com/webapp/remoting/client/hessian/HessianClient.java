package com.webapp.remoting.client.hessian;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.webapp.remoting.service.hessian.IHessian;

public class HessianClient {

	// @Test
	public static void main(String[] args) {
		String url = "http://localhost:8080/webservice/hessian";
		IHessian hessianServer = getObject(IHessian.class, url);
		String ret = hessianServer.sayHello();
		System.out.print(ret);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getObject(Class<T> api, String url) {
		HessianProxyFactory factory = new HessianProxyFactory();
		T t = null;
		try {
			t = (T) factory.create(api, url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return t;
	}

}
