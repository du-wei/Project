package com.webapp.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.webapp.utils.config.PathUtils;

public class XmlJDomUtils {

	public static void main(String[] args) throws Exception {

		// write();

		SAXBuilder builder = new SAXBuilder();

		Document doc = builder.build(new File(PathUtils
		        .getCurPath(XmlJDomUtils.class) + "/schema.xml"));

		Element root = doc.getRootElement();
		System.out.println(root.getName());

		Element ele = root.getChild("student");
		ele.getAttributes();

	}

	private static void write() throws IOException, FileNotFoundException {
		Document doc = new Document();

		Element root = new Element("root");
		doc.addContent(root);

		Comment comment = new Comment("hello JDOM");
		root.addContent(comment);

		Element ele = new Element("hello");
		Attribute attr = new Attribute("id", "king");
		ele.setText("hello").setAttribute("type", "king").setAttribute(attr);

		root.addContent(ele);

		Format defRaw = Format.getRawFormat();
		Format compact = Format.getCompactFormat();
		Format pretty = Format.getPrettyFormat();
		pretty.setIndent("    ");
		pretty.setEncoding("utf-8");

		XMLOutputter out = new XMLOutputter(pretty);
		System.out.println(PathUtils.getUserPath()
		        + "/src/main/java/com/webapp/xml/jdom.xml");
		out.output(doc, new FileOutputStream(PathUtils.getUserPath()
		        + "/src/main/java/com/webapp/xml/jdom.xml"));
		System.out.println("--------");
	}

}
