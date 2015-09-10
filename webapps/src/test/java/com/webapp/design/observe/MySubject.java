package com.webapp.design.observe;

// observable or subject
public interface MySubject {

	public void addListener(MyObserver o);

	public void addObserver(MyObserver o);

	public void notifyObservers();

	public void removeObserver(MyObserver o);

	public void removeObservers();

	public void notifyObserver(MyObserver o);

	public boolean hasChanged();

	public void setChanged();

	public boolean clearChanged();

	public int countObservers();

}
