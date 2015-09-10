package com.webapp.design.observe;

import java.util.Observable;

public class Subject extends Observable {

	private String msg;

	public void changed(String msg) {
		setChanged();
		this.msg = msg;
		notifyObservers(msg);
	}

}
