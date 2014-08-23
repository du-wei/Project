package com.webapp.webservice.service.cxf;

import com.webapp.webservice.service.utils.WebServiceUtils;

public class CxfService {
	public static void main(String[] args) {

		WebServiceUtils.pubServiceByCxfWs("http://localhost:8080/ws/cxf",
				new CxfHelloImpl());
	}
}
