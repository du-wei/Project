package com.webapp.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import com.webapp.utils.config.PathUtils;

public class XmlDom4jUtils {

	private static Logger logger = LogManager.getLogger(XmlDom4jUtils.class);
	private static XmlDom4jUtils instance = null;
	private Document doc = null;
	private File file = null;

	@Test
	public void testName() throws Exception {
		File file = new File(PathUtils.getCurPath(XmlDom4jUtils.class)
		        + "/schema.xml");

		String path = "F:/360云盘/project/webapps/src/main/java/com/webapp/xml/schema.xml";
		File files = new File(path);

		XmlDom4jUtils xmlUtils = XmlDom4jUtils.newInstance(files);

		xmlUtils.updateText("teacher", "king").toSave();

		// Document doc = xmlUtils.getDoc();

		// System.out.println(node.getText());

	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	private static XmlDom4jUtils newInstance() {
		if (instance == null) {
			synchronized (XmlDom4jUtils.class) {
				instance = new XmlDom4jUtils();
			}
		} else {
			instance.doc = null;
			instance.file = null;
		}
		return instance;
	}

	public static XmlDom4jUtils newInstance(String xmlStr) {
		newInstance();
		instance.doc = read(xmlStr);
		return instance;
	}

	public static XmlDom4jUtils newInstance(File xmlFile) {
		newInstance();
		instance.doc = read(xmlFile);
		instance.file = xmlFile;
		return instance;
	}

	public XmlDom4jUtils updateNode(String element, String targetText) {
		List<?> list = doc.selectNodes("//" + element);
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			Element el = (Element) it.next();
			el.setText(targetText);
		}
		return this;
	}

	public XmlDom4jUtils updateText(String sourceText, String targetText) {
		List<?> list = doc.selectNodes("//*[contains(text(), '" + sourceText
		        + "')]");
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			Element el = (Element) it.next();
			el.setText(targetText);
		}
		return this;
	}

	public void toSave(String file) {
		XmlDom4jUtils.saveXml(file, doc);
	}

	public void toSave(File file) {
		XmlDom4jUtils.saveXml(file, doc);
	}

	public void toSave() {
		XmlDom4jUtils.saveXml(file, doc);
	}

	public String getEleVal(String nodeName) {
		Node node = getNode(nodeName);
		return node.getText();
	}

	public String getEleVal(String nodeName, String childNodeName) {
		Node node = doc.selectSingleNode("//" + nodeName + "/" + childNodeName);
		return node.getText();
	}

	public Node getNode(String nodeName) {
		return doc.selectSingleNode("//" + nodeName);
	}

	@SuppressWarnings("unchecked")
	public List<Node> getNodes(String nodeName) {
		return doc.selectNodes("//" + nodeName);
	}

	public static Document create(String outPath, String xmlStr)
	        throws Exception {
		Document document = XmlDom4jUtils.read(xmlStr);
		saveXml(outPath, document);
		return document;
	}

	public static Document create(String outPath, Document document)
	        throws Exception {
		saveXml(outPath, document);
		return document;
	}

	public static Document saveXml(String file, Document document) {
		return saveXml(new File(file), document);
	}

	public static Document saveXml(File file, Document document) {
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setIndent("\t");
		return saveXml(file, document, format);
	}

	public static Document saveXml(File file, Document document,
	        OutputFormat format) {
		try {
			XMLWriter output = new XMLWriter(new FileOutputStream(file), format);
			output.write(document);
			output.close();
		} catch (UnsupportedEncodingException e) {
			logger.error("文件编码错误！", e);
		} catch (FileNotFoundException e) {
			logger.error(file + " 文件没有被发现！", e);
		} catch (IOException e) {
			logger.error("保存出错！", e);
		}
		return document;
	}

	// SAX open
	public static Document read(String xmlStr) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(xmlStr);
		} catch (DocumentException e) {
			logger.error("解析xml字符串出错！", e);
		}
		return doc;
	}

	public static Document read(File xmlFile) {
		SAXReader saxReader = new SAXReader();
		Document doc = null;
		try {
			doc = saxReader.read(xmlFile);
		} catch (DocumentException e) {
			logger.error("读取文件" + xmlFile.getAbsolutePath() + "出错！", e);
		}
		return doc;
	}

	// test
	public static <T extends Node> String viewDoc(T ele) {
		return ele.asXML();
	}

	public static <T extends Node> String viewAll(T ele) {
		return ele.selectSingleNode("/*").asXML();
	}

	public static StringBuffer treeWalk(Element element, StringBuffer result) {
		// 会读取本元素的属性

		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			short type = node.getNodeType();
			if (type == Node.NAMESPACE_NODE) {

			} else if (type == Node.DOCUMENT_NODE) {
				result.append("<" + node.getName());
			} else if (type == Node.ELEMENT_NODE) {
				result.append("<" + element.getName());
				Element ele = (Element) node;
				for (int k = 0; k < ele.attributeCount(); k++) {
					Attribute attr = ele.attribute(k);
					result.append(" " + attr.getName() + "=\""
					        + attr.getValue() + "\"");
				}

				result.append(">");
				treeWalk((Element) node, result);
				result.append("</" + node.getName() + ">");
			} else if (type == Node.TEXT_NODE) {
				result.append(node.getText());
			} else if (type == Node.COMMENT_NODE) {
				result.append("<--" + node.getText() + "-->");
			} else {
				System.out.println(node.getText());
			}
		}

		// result.append("</" + tagName + ">");
		return result;
	}

	private Document demo(String file) throws Exception {
		// Element root = DocumentHelper.createElement("linkmans");
		// Document document = DocumentHelper.createDocument(root);

		// Document document = DocumentHelper.createDocument();
		// Element root = document.addElement("linkmans");
		// document.setRootElement(root);

		// DOMReader reader = new DOMReader();
		// reader.read(org.w3c.dom.Document);

		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("linkmans");
		root.addAttribute("id", "id");

		Element man = root.addElement("linkman");
		Element e1 = man.addElement("name");
		e1.setText("ok");

		saveXml(file, document);

		return document;
	}

	private Document update(String file, String xpath, String element,
	        String tagText) throws Exception {

		Document doc = read(file);

		List<?> list = doc.selectNodes(xpath);

		System.out.println(list.get(0));
		Iterator<?> it = list.iterator();
		while (it.hasNext()) {
			Element el = (Element) it.next();

			Iterator<?> itl = el.elementIterator(element);
			while (itl.hasNext()) {
				Element title = (Element) itl.next();
				System.out.println(title.getName());
				title.setText(tagText);
			}
		}

		saveXml(file, doc);
		return doc;
	}

}
