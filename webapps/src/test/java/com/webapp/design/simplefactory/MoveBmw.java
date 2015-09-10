package com.webapp.design.simplefactory;

/*
 * 工厂方法 具体产品
 */
public class MoveBmw implements Movable {

	@Override
	public void run() {
		System.out.println("driving bmw");
	}

}
