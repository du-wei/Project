package com.webapp.webservice.service.jaxws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "com.webapp.webservice.service.jaxws.HelloService")
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String str) {
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

}
