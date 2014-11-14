package com.webapp.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class TimeProxy implements InvocationHandler {

	private Object object;

	public TimeProxy() {
	}

	public TimeProxy(Object object) {
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
	        throws Throwable {
		System.out.println(method.getName() + " " + System.currentTimeMillis());
		Object obj = method.invoke(object, args);
		System.out.println(method.getName() + " " + System.currentTimeMillis());
		return obj;
	}

	public static <T> Object getProxy(T clz) {
		TimeProxy timeProxy = new TimeProxy();
		timeProxy.object = clz;

		return Proxy.newProxyInstance(clz.getClass().getClassLoader(), clz
		        .getClass().getInterfaces(), timeProxy);
	}

}
