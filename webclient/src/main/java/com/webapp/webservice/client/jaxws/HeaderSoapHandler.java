package com.webapp.webservice.client.jaxws;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class HeaderSoapHandler implements SOAPHandler<SOAPMessageContext> {

	public boolean handleMessage(SOAPMessageContext context) {
		Boolean out = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (out) {
			SOAPMessage msg = context.getMessage();
			// 判断是否存在header
			try {
				SOAPEnvelope envelope = msg.getSOAPPart().getEnvelope();
				SOAPHeader header = envelope.getHeader();

				if (header == null)
					header = envelope.addHeader();

				QName qName = new QName("http://webapp.com", "auth", "ns");
				header.addHeaderElement(qName).setValue("");

			} catch (SOAPException e) {
				e.printStackTrace();
			}
		}
		System.out.println("handleMessage");
		return false;
	}

	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("handleFault");
		return false;
	}

	public void close(MessageContext context) {
		System.out.println("close");
	}

	public Set<QName> getHeaders() {
		System.out.println("getHeaders");
		return null;
	}

}
