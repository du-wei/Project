package com.webapp.design.state;

public class MMHappyState extends MMState {

	@Override
	public void cry() {
		System.out.println("not cry");
	}

	@Override
	public void say() {
		System.out.println("happy say");
	}

	@Override
	public void smile() {
		System.out.println("happy smile");
	}

}
