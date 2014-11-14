package com.webapp.design.state;

public class Boy {
	private int status = 1;
	private int status1 = 1;
	private int status2 = 2;
	private int status3 = 3;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void pursue(MM mm) {
		if (status == 1) {
			mm.cry();
		}
		if (status == 2) {
			mm.say();
		}
		if (status == 3) {
			mm.smile();
		}
	}

	public void doSomeThing(int i) {
		status = i;
	}

}
