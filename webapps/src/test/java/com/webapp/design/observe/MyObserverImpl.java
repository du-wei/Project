package com.webapp.design.observe;

public class MyObserverImpl implements MyObserver {

    // 可以拥有 被观察者对象 observable or subject
    private MySubject subject = null;

    public MyObserverImpl() {
    }

    @Override
    public void update(String msg) {
	System.out.println(msg);
    }

}
