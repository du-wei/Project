package com.webapp.webservcie.soap;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class SoapUtils {

	public static void main(String[] args) throws Exception {
		testName1();
	}

	private static void test() throws SOAPException, IOException {
		// 创建消息工厂
		MessageFactory factory = MessageFactory.newInstance();
		// 根据工厂创建SOAPMessage
		SOAPMessage msg = factory.createMessage();
		// 创建SOAPPart
		SOAPPart part = msg.getSOAPPart();
		// 创建SOAPEnvelope
		SOAPEnvelope envelope = part.getEnvelope();
		// 获取相应的Body和header等信息
		SOAPBody body = envelope.getBody();

		// <ns:sayHello xmlns="http://jaxws.servcice.com">
		QName qName = new QName("http://jaxws.servcice.com", "sayHello", "ns");

		SOAPBodyElement ele = body.addBodyElement(qName);
		ele.addChildElement("a").setValue("hello");
		ele.addChildElement("b").setValue("world");

		msg.writeTo(System.out);
	}

	// soap message方式
	public static void testName() throws Exception {
		String wsdlUrl = "http://localhost:8080/webservice/service?wsdl";
		String ns = "http://jaxws.service.webservice.webapp.com/";

		URL url = new URL(wsdlUrl);
		QName sName = new QName(ns, "HelloServiceImplService");
		// 创建服务
		Service service = Service.create(url, sName);
		// 创建Dispatch
		Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,
				"HelloServiceImplPort"), SOAPMessage.class,
				Service.Mode.MESSAGE);
		// 创建SOAPMessage
		SOAPMessage message = MessageFactory.newInstance().createMessage();
		SOAPPart part = message.getSOAPPart();
		SOAPEnvelope envelope = part.getEnvelope();
		SOAPBody body = envelope.getBody();

		SOAPHeader header = envelope.getHeader();
		if (header == null) {
			header = envelope.addHeader();
		}
		QName hName = new QName(ns, "header");
		header.addHeaderElement(hName);

		// 创建QName来指定消息传递的数据
		QName eName = new QName(ns, "sayHello", "ns");
		SOAPBodyElement ele = body.addBodyElement(eName);
		ele.addChildElement("strImpl").setValue("soap info");

		message.writeTo(System.out);
		System.out.println();
		System.out.println("---------");
		SOAPMessage msgResult = dispatch.invoke(message);
		msgResult.writeTo(System.out);

		Document doc = msgResult.getSOAPPart().getEnvelope().getBody()
				.extractContentAsDocument();
	}

	// soap payload方式
	public static void testName1() throws Exception {
		String wsdlUrl = "http://localhost:8080/webservice/service?wsdl";
		String ns = "http://jaxws.service.webservice.webapp.com/";

		URL url = new URL(wsdlUrl);
		QName sName = new QName(ns, "HelloServiceImplService");
		// 创建服务
		Service service = Service.create(url, sName);
		// 创建Dispatch
		Dispatch<Source> dispatch = service.createDispatch(new QName(ns,
				"HelloServiceImplPort"), Source.class, Service.Mode.PAYLOAD);

		// 封装相应的part
		String payload = "<nn:sayHello xmlns:nn=\"" + ns
				+ "\"><strImpl>hello</strImpl></nn:sayHello>";

		System.out.println(payload);
		StreamSource source = new StreamSource(new StringReader(payload));

		// 通过dispatch传递相应的payload
		Source sourceReslut = dispatch.invoke(source);

		Transformer tran = TransformerFactory.newInstance().newTransformer();
		DOMResult domResult = new DOMResult();
		tran.transform(sourceReslut, domResult);

		XPath xPath = XPathFactory.newInstance().newXPath();

		NodeList nodeList = (NodeList) xPath.evaluate("//sayHelloResult",
				domResult.getNode(), XPathConstants.NODESET);
		System.out.println(nodeList.item(0).getNodeName());
	}

}
