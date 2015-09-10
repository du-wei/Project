package com.webapp.design.command.utils;

import com.webapp.design.command.Command;

public class MM {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void order(CommandPattern<Command> b) {
		b.executeCommands();
	}
}
