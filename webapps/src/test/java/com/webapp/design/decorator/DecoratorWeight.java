package com.webapp.design.decorator;

public class DecoratorWeight extends Decorator {

    private Dog dog;

    public DecoratorWeight(Dog dog) {
	super();
	this.dog = dog;
    }

    @Override
    public String getDesc() {
	return dog.getDesc() + " Weight";
    }

    @Override
    public double cost() {
	return dog.cost() + 30;
    }

}
