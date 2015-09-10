package com.webapp.design.proxy;

import java.lang.reflect.Proxy;

/** @ClassName: MyTest.java 代理模式
 * @Package com.webapp.design.proxy
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月22日 下午10:29:45
 * @version V1.0 */
public class MyTest {

	public static void main(String[] args) {
		MoveCar car = new MoveCar();

		Moveable moveable = (Moveable) TimeProxy.getProxy(car);
		LogProxy logProxy = new LogProxy(moveable);
		//
		Moveable move = (Moveable) Proxy.newProxyInstance(
		        MoveCar.class.getClassLoader(), MoveCar.class.getInterfaces(),
		        logProxy);
		move.move();

	}
}
