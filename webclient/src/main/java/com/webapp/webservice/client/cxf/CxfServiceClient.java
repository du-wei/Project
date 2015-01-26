package com.webapp.webservice.client.cxf;

import com.webapp.webservice.service.utils.WsCxfUtils;

public class CxfServiceClient {

	public static void main(String[] args) {
		// WebServiceUtils.getServiceByCxfWs();
		testName();
	}

	public static void testName() {
		WsCxfUtils.WSDL2Java("http://localhost:8080/ws/cxf?wsdl", "");
	}
}
