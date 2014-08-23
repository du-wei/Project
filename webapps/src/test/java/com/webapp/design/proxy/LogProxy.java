/**
 * @Title: LogProxy.java
 * @Package com.webapp.design.proxy
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-1-21 上午11:01:35
 * @version V1.0
 */
package com.webapp.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: LogProxy.java
 * @Package com.webapp.design.proxy
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-1-21 上午11:01:35
 * @version V1.0
 */
public class LogProxy implements InvocationHandler {
    // TODO: Found non-transient, non-static member. Please mark as transient or
    // provide accessors.
    private Object object;

    public LogProxy() {
    }

    public LogProxy(Object object) {
	this.object = object;
    }

    public static <T> Object getProxy(T clz) {
	LogProxy logProxy = new LogProxy();
	logProxy.object = clz;

	return Proxy.newProxyInstance(clz.getClass().getClassLoader(), clz.getClass().getInterfaces(), logProxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	System.out.println(method.getName() + " starting");
	Object obj = method.invoke(object, args);
	System.out.println(method.getName() + " stopping");
	return obj;
    }

}
