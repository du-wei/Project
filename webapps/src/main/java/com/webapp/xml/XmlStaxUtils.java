package com.webapp.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.junit.Test;
import com.webapp.utils.config.PathUtils;

public class XmlStaxUtils {

	@Test
	public void main() throws Exception {
		// v1();

		// v2();

		v3();

		// 基于Filter的过滤方式
		// XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		//
		// InputStream is = new FileInputStream(new
		// File(ConfigUtils.getCurPath(XmlStaxUtils.class)+"/schema.xml"));
		//
		// XMLEventReader reader =
		// inputFactory.createFilteredReader(inputFactory.createXMLEventReader(is),
		// new EventFilter() {
		//
		// @Override
		// public boolean accept(XMLEvent event) {
		// if(event.isStartElement()) return true;
		// return false;
		// }
		// });

		// xpath
		// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// DocumentBuilder db = null;
		// Document document = null;
		// try {
		// db = dbf.newDocumentBuilder();
		// document = db.parse(new
		// File(ConfigUtils.getCurPath(XmlStaxUtils.class)+"/schema.xml"));
		// } catch (ParserConfigurationException e) {
		// }
		// XPath xPath = XPathFactory.newInstance().newXPath();
		// NodeList nList = (NodeList) xPath.evaluate("//student[@type='info']",
		// document, XPathConstants.NODESET);

		// Transformer tran = TransformerFactory.newInstance().newTransformer();
		// tran.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		// tran.setOutputProperty(OutputKeys.INDENT, "yes");
		// Result result = new StreamResult(System.out);
		// tran.transform(new DOMSource(doc), result);
	}

	// 基于迭代模型方式
	private static void v2() throws FactoryConfigurationError,
			FileNotFoundException, XMLStreamException {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		InputStream inputStream = new FileInputStream(new File(
				PathUtils.getCurPath(XmlStaxUtils.class) + "/schema.xml"));

		XMLEventReader reader = inputFactory.createXMLEventReader(inputStream);

		StringBuffer result = new StringBuffer();
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				StartElement start = event.asStartElement();
				result.append("<" + start.getName());

				Iterator attr = start.getAttributes();

				while (attr.hasNext()) {
					attr.next();

					System.out.println(" " + attr.next() + "=\"-----------"
							+ "\"");
					// result.append(" " + reader.getAttributeName(i) + "=\"" +
					// reader.getAttributeValue(i) + "\"");
				}
				result.append(">");

			} else if (event.isCharacters()) {

			} else if (event.isEndElement()) {

			} else if (event.isAttribute()) {

			}
		}

		System.out.println(result.toString());
	}

	// 基于光标方式
	private static void v1(File file) throws FactoryConfigurationError,
			FileNotFoundException, XMLStreamException {
		InputStream inputStream = new FileInputStream(file);

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		XMLStreamReader reader = inputFactory
				.createXMLStreamReader(inputStream);

		StringBuffer result = new StringBuffer();
		while (reader.hasNext()) {
			int type = reader.next();
			if (type == XMLStreamConstants.START_ELEMENT) {
				result.append("<" + reader.getName());

				for (int i = 0; i < reader.getAttributeCount(); i++) {
					System.out.println(" " + reader.getAttributeName(i)
							+ "=\"-----------" + reader.getAttributeValue(i)
							+ "\"");
					result.append(" " + reader.getAttributeName(i) + "=\""
							+ reader.getAttributeValue(i) + "\"");
				}
				result.append(">");
			} else if (type == XMLStreamConstants.CHARACTERS) {
				result.append(reader.getText());
			} else if (type == XMLStreamConstants.END_ELEMENT) {
				result.append("</" + reader.getName() + ">");
			} else if (type == XMLStreamConstants.COMMENT) {
				result.append("<!--" + reader.getText() + "-->");
			}

		}

		System.out.println(result.toString());
	}

	public static void v3() throws Exception {

		XMLOutputFactory factory = XMLOutputFactory.newInstance();

		// http://www.blogjava.net/jinn/archive/2008/07/18/215812.html
		// http://www.2cto.com/kf/201303/196647.html
		XMLStreamWriter writer = XMLOutputFactory.newInstance()
				.createXMLStreamWriter(System.out);

		writer.writeStartDocument("utf-8", "1.0");
		writer.writeEndDocument();

		writer.writeStartElement("students");

		writer.writeStartElement("id");
		writer.writeCharacters("hello");
		writer.writeEndElement();
		writer.writeEndElement();
		writer.flush();
		writer.close();
	}

}
