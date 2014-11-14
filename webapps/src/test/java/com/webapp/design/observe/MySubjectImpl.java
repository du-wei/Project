package com.webapp.design.observe;

import java.util.ArrayList;
import java.util.List;

public class MySubjectImpl implements MySubject {

	private List<MyObserver> observers = new ArrayList<>();
	private String msg;
	private boolean changed = false;

	public void changed(String msg) {
		changed = true;
		this.msg = msg;
		notifyObservers();
	}

	/* -------------------- 实现1 ------------------- */
	@Override
	public void addListener(MyObserver o) {
		observers.add(o);
	}

	/* -------------------- 实现2 ------------------- */
	@Override
	public void addObserver(MyObserver o) {
		this.observers.add(o);
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			MyObserver myObserver = observers.get(i);
			myObserver.update(msg);
		}
	}

	/* -------------------- 辅助功能 ------------------- */

	@Override
	public void removeObserver(MyObserver o) {
		observers.remove(o);
	}

	@Override
	public void removeObservers() {
		observers.clear();
	}

	@Override
	public void notifyObserver(MyObserver o) {
		int index = observers.indexOf(o);
		observers.get(index).update(msg);
	}

	@Override
	public boolean hasChanged() {
		return changed;
	}

	@Override
	public void setChanged() {
		changed = true;
	}

	@Override
	public int countObservers() {
		return observers.size();
	}

	@Override
	public boolean clearChanged() {
		changed = false;
		return true;
	}

}
