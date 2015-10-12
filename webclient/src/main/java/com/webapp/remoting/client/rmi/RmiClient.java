package com.webapp.remoting.client.rmi;

import com.webapp.remoting.service.rmi.MyRemote;
import com.webapp.remoting.service.rmi.RmiUtils;

public class RmiClient {

	public static void main(String[] args) throws Exception {
		RmiUtils.setSecurityPolicy("/RMIpolicy.policy");
		MyRemote client = RmiUtils.getObject("rmi://localhost:1099/rmiService",
				MyRemote.class);
		System.out.println(client.sayHello());
	}
}
