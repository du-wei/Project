package com.webapp.remoting.service.rmi;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class RmiUtils {

	public static Logger logger = Logger.getLogger(RmiUtils.class);
	private static RmiUtils instance = null;
	private int curPort = 1099;
	private String curHost;
	private List<Remote> hasRmi = new ArrayList<Remote>();
	private List<Integer> hasPort = new ArrayList<Integer>();
	private Registry registry;

	public String getCurHost() {
		return curHost;
	}

	public int getCurPort() {
		return curPort;
	}

	public RmiUtils setCurPort(int curPort) {
		this.curPort = curPort;
		return this;
	}

	public static void setSecurityPolicy(String policyFile) {
		System.setProperty("java.security.policy",
				RmiUtils.class.getResource(policyFile).toString());
	}

	public RmiUtils() {
	}

	private RmiUtils(String host, int port) {
		this.curHost = host;
		this.curPort = port;
	}

	public static RmiUtils bind(String host) {
		return bind(host, 1099);
	}

	public static RmiUtils bind(String host, int port) {
		if (instance == null) {
			instance = new RmiUtils(host, port);
		} else {
			instance.curHost = host;
			instance.curPort = port;
		}
		return instance;
	}

	public void addRemote(String name, Remote remote) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			if (!(remote instanceof UnicastRemoteObject)
					&& !hasRmi.contains(remote)) {
				UnicastRemoteObject.exportObject(remote, curPort);
				hasRmi.add(remote);
			}
			if (!hasPort.contains(curPort)) {
				LocateRegistry.createRegistry(curPort);
				hasPort.add(curPort);
			}
			registry = LocateRegistry.getRegistry(curHost, curPort);
			registry.bind(name, remote);
		} catch (Exception e) {
			logger.error("发布RMI对象出错", e);
		}
	}

	public static <T> T getObject(String rmiUrl, Class<T> rmiInterface) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		if (rmiInterface != null && !rmiInterface.isInterface()) {
			throw new IllegalArgumentException(
					"'serviceInterface' must be an interface");
		}
		T t = null;
		try {
			t = rmiInterface.cast(Naming.lookup(rmiUrl));
		} catch (Exception e) {
			logger.error("不能正确获取RMI对象", e);
		}
		return t;
	}

	public static <T> T getObject(String host, int port, String name,
			Class<T> rmiInterface) {
		return getObject("rmi://" + host + ":" + port + "/" + name,
				rmiInterface);
	}

	public boolean hasBind(String name) {
		String[] hasBind = getAllBind();
		for (int i = 0; i < hasBind.length; i++) {
			if (hasBind[i].equals(name)) {
				return true;
			}
		}
		return false;
	}

	public String[] getAllBind() {
		String[] hasBind = {};
		try {
			hasBind = registry.list();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return hasBind;
	}

	public boolean hasPort(int port) {
		for (int i = 0; i < hasPort.size(); i++) {
			if (hasPort.get(i) == port) {
				return true;
			}
		}
		return false;
	}

	public Integer[] getAllPort() {
		return hasPort.toArray(new Integer[] {});
	}

}
