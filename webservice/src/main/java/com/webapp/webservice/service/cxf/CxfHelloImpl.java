package com.webapp.webservice.service.cxf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

@WebService(endpointInterface = "com.webapp.webservice.service.cxf.CxfHello")
public class CxfHelloImpl implements CxfHello {

	public String sayHello(String str) {
		System.out.println(" service " + str);
		return "hello service === " + str;
	}

	@Override
	public String sayFault(String str) throws Exception {
		if (str.equals(""))
			throw new Exception(" hello fault");
		return "hello " + str;
	}

	@Override
	public List<String> sayList(String str) {
		List<String> list = new ArrayList<>();
		list.add("hello");
		list.add("world");
		return list;
	}

	public List<CxfUser> getUsers() {
		CxfUser cxfUser = new CxfUser();
		cxfUser.setName("users");
		List<CxfUser> users = new ArrayList<>();
		users.add(cxfUser);
		return users;
	}

	public Map<String, CxfUser> getMapUser() {

		CxfUser cxfUser = new CxfUser();
		cxfUser.setName("map");
		Map<String, CxfUser> map = new HashMap<>();
		map.put("user", cxfUser);
		return map;

	}

}
