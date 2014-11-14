package com.webapp.design.simplefactory;

public class Test {

	/*
	 * 简单工厂产生系列产品时会有工厂泛滥情况 抽象工厂产生新产品品种时改动的地方有很大 抽象工厂就是简单工厂的多产品组合
	 */
	public static void main(String[] args) {
		try {
			Factory driver = new FactoryBenz();
			Movable car = driver.create();
			car.run();
		} catch (Exception e) {

		}
	}
}
