package com.webapp.lucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

public class MyTika {

	public static void main(String[] args) {
		Parser parser = new AutoDetectParser();

		try {
			InputStream input = new FileInputStream("V://myself/个人简历.doc");
			ContentHandler handler = new BodyContentHandler();

			ParseContext context = new ParseContext();
			context.set(Parser.class, parser);

			Metadata metadata = new Metadata();

			parser.parse(input, handler, metadata, context);

			// System.out.println(handler.toString());
			Tika tika = new Tika();
			String str = tika.parseToString(new File("V://myself/个人简历.doc"));
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
