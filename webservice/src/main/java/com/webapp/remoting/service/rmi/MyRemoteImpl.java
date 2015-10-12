package com.webapp.remoting.service.rmi;

import java.rmi.RemoteException;

public class MyRemoteImpl implements MyRemote {

	@Override
	public String sayHello() throws RemoteException {
		return "say hello";
	}

}
