package com.webapp.design.state;

public class MM {
	private String name;

	// TODO: Found non-transient, non-static member. Please mark as transient or
	// provide accessors.
	private MMState state;

	public MM(MMState state) {
		super();
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void smile() {
		state.smile();
	}

	public void cry() {
		state.cry();
	}

	public void say() {
		state.say();
	}

}
