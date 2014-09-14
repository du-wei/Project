package com.webapp.lucene;

import java.io.IOException;
import java.util.List;

import opensource.jpinyin.PinyinFormat;
import opensource.jpinyin.PinyinHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.suggest.InputIterator;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.CharsRef;
import org.apache.lucene.util.UnicodeUtil;
import org.apache.lucene.util.Version;

public class IndexUtils {

	private static Logger logger = LogManager.getLogger(IndexUtils.class);

	public static String paths = "./index";

	private static IndexReader indexReader;
	private static IndexSearcher indexSearcher;

//	static {
//		indexReader = getIndexReader(paths);
//		indexSearcher = getIndexSearcher(paths);
//	}

	public static IndexReader getIndexReader(){
		return getIndexReader(paths);
	}

	public static IndexSearcher getIndexSearcher(){
		return getIndexSearcher(paths);
	}

	public static void index(String path, List<Document> docs) {
		IndexWriterConfig config = LuceneUtils.getWriterConfig(AnalyzerUtils.getAnsjAnalysis());

		IndexWriter writer = getIndexWriter(path, config);
		try {
			writer.addDocuments(docs);
		} catch (IOException e) {
			logger.error("Index create error!", e);
		} finally {
			LuceneUtils.closeIndexWrite(writer);
		}
	}

	public static void indexPinyin(String readIndexDir, String writeIndexDir, String field) {
		IndexWriterConfig config = LuceneUtils.getWriterConfig(new WhitespaceAnalyzer(Version.LUCENE_47));
		config.setOpenMode(OpenMode.CREATE_OR_APPEND);

		IndexWriter indexWriter = null;
		IndexReader indexReader = null;
		try {
			indexWriter = getIndexWriter(writeIndexDir, config);

			indexReader = getIndexReader(readIndexDir);
			Dictionary dic = new LuceneDictionary(indexReader, field);
			InputIterator entry = dic.getEntryIterator();
			BytesRef spare;
			CharsRef charsSpare = new CharsRef();
			int i = 0;
			while ((spare = entry.next()) != null) {
				charsSpare.grow(spare.length);
				UnicodeUtil.UTF8toUTF16(spare.bytes, spare.offset, spare.length, charsSpare);

				Document doc = new Document();
				String word = charsSpare.toString();
				if (!LuceneUtils.isChinese(word, false)) {
					logger.debug("delete-->" + word);
					continue;
				}

				String quanpin = PinyinHelper.convertToPinyinString(word, "", PinyinFormat.WITHOUT_TONE);
				String duanpin = PinyinHelper.getShortPinyin(word);

				doc.add(new TextField(field, word, Store.YES));
				doc.add(new TextField(field + "_quanpin", quanpin, Store.NO));
				doc.add(new TextField(field + "_duanpin", duanpin, Store.NO));
				indexWriter.addDocument(doc);
				logger.debug(++i + "-->" + charsSpare.toString() + "-" + quanpin + "-" + duanpin);
			}
			indexWriter.commit();
		} catch (Exception e) {
			logger.error("Pinyin Index create error!", e);
		} finally {
			LuceneUtils.closeIndexWrite(indexWriter);
			LuceneUtils.closeIndexReader(indexReader);
		}
	}

	public static void delete(String path) throws Exception {
		IndexWriterConfig config = LuceneUtils.getWriterConfig(AnalyzerUtils.getAnsjAnalysis());

		IndexWriter writer = getIndexWriter(path, config);
		try {
			writer.deleteAll();
		} catch (IOException e) {
			logger.error("Index create error!", e);
		} finally {
			LuceneUtils.closeIndexWrite(writer);
		}
	}

	public static void delDoc() {
//		IndexWriter.deleteDocuments(term);
    }


	public static IndexWriter getIndexWriter(String indexDir, IndexWriterConfig config) {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(LuceneUtils.getDirectory(indexDir), config);
		} catch (IOException e) {
			logger.error("IndexWriter create error!", e);
		}
		return writer;
	}

	private static IndexReader getIndexReader(String indexDir) {
		IndexReader indexReader = null;
		try {
			indexReader = DirectoryReader.open(LuceneUtils.getDirectory(indexDir));
		} catch (IOException e) {
			logger.error("IndexReader create error!", e);
		}
		return indexReader;
	}

	private static IndexSearcher getIndexSearcher(String indexDir) {
	    IndexSearcher searcher = new IndexSearcher(getIndexReader(indexDir));
	    return searcher;
    }
}
