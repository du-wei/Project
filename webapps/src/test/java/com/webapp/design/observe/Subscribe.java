package com.webapp.design.observe;

import java.util.Observable;
import java.util.Observer;

public class Subscribe implements Observer {

    @Override
    public void update(Observable sub, Object msg) {
	if (sub instanceof Subject) {
	    Subject s = (Subject) sub;

	    System.out.println(msg);
	}
    }

}
