package com.webapp.design.decorator;

public class DecoratorColor extends Decorator {

	private Dog dog;

	public DecoratorColor(Dog dog) {
		super();
		this.dog = dog;
	}

	@Override
	public String getDesc() {
		return dog.getDesc() + " color";
	}

	@Override
	public double cost() {
		return dog.cost() + 20;
	}

}
