package com.webapp.webservice.service.jaxws;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class LicenseSoapHandler implements SOAPHandler<SOAPMessageContext> {

	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean out = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!out) {
			SOAPMessage msg = context.getMessage();
			// 判断是否存在header
			try {
				SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();

				SOAPBody body = envelope.getBody();
				if (header == null
						|| header.extractAllHeaderElements().hasNext()) {
					SOAPFault fault = body.getFault();
					fault.setFaultCode("code");
					fault.setFaultString("fault");
					return false;
				}

			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}
		System.out.println("handleMessage");
		return false;
	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("handleFault");
		return false;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("close");
	}

	@Override
	public Set<QName> getHeaders() {
		System.out.println("getHeaders");
		return null;
	}

}
