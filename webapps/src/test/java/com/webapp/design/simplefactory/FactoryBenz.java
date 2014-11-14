package com.webapp.design.simplefactory;

/*
 * 工厂方法 实体工厂
 */
public class FactoryBenz implements Factory {

	@Override
	public Movable create() {
		return new MoveBenz();
	}

}
