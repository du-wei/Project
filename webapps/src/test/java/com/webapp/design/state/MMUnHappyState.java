package com.webapp.design.state;

public class MMUnHappyState extends MMState {

	@Override
	public void cry() {
		System.out.println("uphappy cry");
	}

	@Override
	public void say() {
		System.out.println("uphappy say");
	}

	@Override
	public void smile() {
		System.out.println("uphappy smile");
	}

}
