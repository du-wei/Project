package com.webapp.design.command;

public class MM {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void order(Boy b) {
		b.executeCommands();
	}
}
