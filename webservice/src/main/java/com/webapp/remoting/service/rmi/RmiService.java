package com.webapp.remoting.service.rmi;

public class RmiService {

	public static void main(String[] args) throws Exception {
		RmiUtils.setSecurityPolicy("/RMIpolicy.policy");
		RmiUtils.bind("localhost").addRemote("rmiService", new MyRemoteImpl());
		System.out.println("rmi 发布成功");
	}

}
