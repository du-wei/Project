package com.webapp.remoting.service.rmi.spring;

public class MyRemoteImpl implements MyRemote {

	@Override
	public String sayHello() {
		return "say hello";
	}

}
