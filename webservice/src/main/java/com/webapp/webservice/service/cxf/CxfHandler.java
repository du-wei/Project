package com.webapp.webservice.service.cxf;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class CxfHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext messagecontext) {
		System.out.println("handleMessage");
		return true;
	}

	@Override
	public boolean handleFault(SOAPMessageContext messagecontext) {
		return false;
	}

	@Override
	public void close(MessageContext messagecontext) {

	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}

}
