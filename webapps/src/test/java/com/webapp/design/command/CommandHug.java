package com.webapp.design.command;

public class CommandHug extends Command {

	@Override
	public void execute() {
		System.out.println("hug");
	}

	@Override
	public void unDo() {
		System.out.println("open your arms");
	}

}
