package com.webapp.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuceneUtils {

	private static final Logger logger = LoggerFactory.getLogger(LuceneUtils.class);
	public static final Version VERSION = Version.LUCENE_47;

	public static IndexWriterConfig getWriterConfig(Analyzer analyzer) {
		IndexWriterConfig writerCfg = new IndexWriterConfig(VERSION, analyzer);
		writerCfg.setOpenMode(OpenMode.CREATE_OR_APPEND);
		return writerCfg;
	}

	public static Directory getDirectory(String indexDir) {
		Directory dir = null;
		try {
			dir = FSDirectory.open(new File(indexDir));
		} catch (IOException e) {
			logger.error("Directory create error!", e);
		}
		return dir;
	}

	public static void closeIndexWrite(IndexWriter writer) {
		try {
			if (writer != null)
				writer.close();
		} catch (IOException e) {
			logger.error("Close the IndexWrite error", e);
		}
	}

	public static void closeIndexReader(IndexReader reader) {
		try {
			if (reader != null)
				reader.close();
		} catch (IOException e) {
			logger.error("Close the IndexReader error", e);
		}
	}

	public static void closeDirectory(Directory dir) {
		try {
			if (dir != null)
				dir.close();
		} catch (IOException e) {
			logger.error("Close the Directory error", e);
		}
	}

	public static boolean isChinese(String strName, boolean hasSymbols) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c, hasSymbols)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isChinese(char c, boolean hasSymbols) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
			return true;
		}
		if (hasSymbols) {
			if (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
					|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
					|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				return true;
			}
		}
		return false;
	}

	public static void listDoc(IndexSearcher searcher, TopDocs topDocs) throws IOException {
		for (ScoreDoc doc : topDocs.scoreDocs) {
			Document d = searcher.doc(doc.doc);
			System.out.println("---" + d.get("title"));
		}
	}

}
