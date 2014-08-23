package com.webapp.design.command.utils;

import com.webapp.design.command.Command;

public class Boy extends CommandPattern<Command> {
    private String name;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void executeCommands() {
	for (Command c : commands) {
	    c.execute();
	    c.unDo();
	}
    }

}
