package com.webapp.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.webapp.utils.config.ConfigUtils;

public class XmlDomUtils {

	// dom Document Object Model
	// jaxp

	private static Logger logger = Logger.getLogger(XmlDomUtils.class);

	public static void main(String[] args) throws Exception {

		Document document = parse(new File(
				ConfigUtils.getCurPath(XmlDomUtils.class) + "/schema.xml"));
		System.out.println(document.getXmlEncoding());

		Element root = document.getDocumentElement();
		// NodeList rootnl = root.getChildNodes();
		// System.out.println(rootnl.item(0).getNodeType());

		System.out.println(parseElement(root));

		NodeList nl = document.getElementsByTagName("student");
		for (int i = 0; i < nl.getLength(); i++) {
			Element element = (Element) nl.item(i);

			// NamedNodeMap nodeMap = nl.item(i).getAttributes();
			String name = element.getElementsByTagName("age").item(0)
					.getFirstChild().getNodeValue();
			// System.out.println("name -" +name);
		}

	}

	public static String parseElement(Element element) {
		StringBuffer result = new StringBuffer();
		parseElement(element, result);
		return result.toString();
	}

	private static StringBuffer parseElement(Element element,
			StringBuffer result) {
		String tagName = element.getTagName();
		result.append("<" + tagName);

		NamedNodeMap nodeMap = element.getAttributes();
		if (null != nodeMap) {
			for (int i = 0; i < nodeMap.getLength(); i++) {
				Attr attr = (Attr) nodeMap.item(i);
				String attrName = attr.getName();
				String attrVal = attr.getValue();

				result.append(" " + attrName + "=\"" + attrVal + "\"");
			}
		}
		result.append(">");

		NodeList nodeList = element.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			short nodeType = node.getNodeType();
			if (nodeType == Node.ELEMENT_NODE) {
				parseElement((Element) node, result);
			} else if (nodeType == Node.TEXT_NODE) {
				result.append(node.getNodeValue());
			} else if (nodeType == Node.COMMENT_NODE) {
				Comment comment = (Comment) node;
				result.append("<!--" + comment.getNodeValue() + "-->");
			}

		}
		result.append("</" + tagName + ">");
		return result;
	}

	public static Document parse(File xmlFile) {
		DocumentBuilder db = getDocumentBuilder();
		Document document = null;
		try {
			document = db.parse(xmlFile);
		} catch (SAXException | IOException e) {
			logger.error("解析获取Document实例出错", e);
		}
		return document;
	}

	private static DocumentBuilder getDocumentBuilder() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.error("获取DOM解析器出错", e);
		}
		return db;
	}

}
