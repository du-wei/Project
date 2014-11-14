package com.webapp.design.command.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandPattern<T> {

	public List<T> commands = new ArrayList<T>();

	public void addCommand(T t) {
		this.commands.add(t);
	}

	public abstract void executeCommands();

}
