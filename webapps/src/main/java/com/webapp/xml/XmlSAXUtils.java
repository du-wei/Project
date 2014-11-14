package com.webapp.xml;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.webapp.utils.config.PathUtils;

public class XmlSAXUtils {

	// sax

	private static Logger logger = LogManager.getLogger(XmlSAXUtils.class);

	public static void main(String[] args) throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();

		MyHandle myHandle = new MyHandle();

		sp.parse(new File(PathUtils.getCurPath(XmlSAXUtils.class)
		        + "/schema.xml"), myHandle);
		System.out.println(myHandle.result.toString());

	}

}

class MyHandle extends DefaultHandler {

	public StringBuffer result = new StringBuffer();

	@Override
	public void startElement(String uri, String localName, String tagName,
	        Attributes attr) throws SAXException {
		result.append("<" + tagName);
		for (int i = 0; i < attr.getLength(); i++) {
			String attrName = attr.getQName(i);
			String attrVal = attr.getValue(i);

			result.append(" " + attrName + "=\"" + attrVal + "\"");
		}
		result.append(">");
	}

	@Override
	public void endElement(String uri, String localName, String tagName)
	        throws SAXException {
		result.append("</" + tagName + ">");
	}

	@Override
	public void characters(char[] ch, int start, int length)
	        throws SAXException {
		result.append(new String(ch, start, length));
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
	}

}
