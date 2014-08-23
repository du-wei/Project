package com.webapp.webservice.client.cxf;

import static org.junit.Assert.*;

import org.junit.Test;

import com.webapp.webservice.service.utils.WebServiceUtils;
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
