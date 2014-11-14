package com.webapp.design.observe;

import java.util.Observable;
import java.util.Observer;

public class JDKObserver extends Observable {

	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		super.setChanged();
		super.notifyObservers(age);
		this.age = age;
	}
	public static void main(String[] args) {
		JDKObserver observer = new JDKObserver();
	    Man man1 = new Man("a");
	    Man man2 = new Man("b");
	    Man man3 = new Man("c");
	    observer.addObserver(man1);
	    observer.addObserver(man2);
	    observer.addObserver(man3);
//	    System.out.println(observer.getAge());
	    observer.setAge(100);
//	    System.out.println(observer.getAge());
    }

}

class Man implements Observer{
	
	private String name;

    public Man(String name) {
	    this.name = name;
    }

	public void update(Observable o, Object arg) {
	    if(arg instanceof Integer){
	    	System.out.println(this.name);
	    	System.out.println(((int)arg));
	    }
	    
    }
	
}
