package com.webapp.lucene;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.ansj.lucene4.AnsjAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.util.CharArraySet;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;

public class AnalyzerUtils {

	public static Analyzer getSimpleAnalyzer() {
		return new SimpleAnalyzer(LuceneUtils.VERSION);
	}

	public static Analyzer getStandardAnalyzer() {
		return new StandardAnalyzer(LuceneUtils.VERSION);
	}

	public static Analyzer getWhitespaceAnalyzer() {
		return new WhitespaceAnalyzer(LuceneUtils.VERSION);
	}

	public static Analyzer getKeywordAnalyzer() {
		return new KeywordAnalyzer();
	}

	public static Analyzer getStopAnalyzer() {
		return new StopAnalyzer(LuceneUtils.VERSION);
	}

	public static Analyzer getMMSegAnalyzer() {
		return new MMSegAnalyzer();
	}

	public static Analyzer getSmartChineseAnalyzer() {
		return new SmartChineseAnalyzer(LuceneUtils.VERSION);
	}

	public static Analyzer getCJKAnalyzer() {
		return new CJKAnalyzer(LuceneUtils.VERSION);
	}

	public static Analyzer getAnsjAnalysis() {
		return new AnsjAnalysis();
	}

	// 自定义分词器
	public class MyStopAnalyzer extends Analyzer {

		private CharArraySet stops;

		public MyStopAnalyzer(List<String> stopWords) {
			stops = StopFilter.makeStopSet(LuceneUtils.VERSION, stopWords);
		}

		@Override
		protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
			Tokenizer source = new KeywordTokenizer(reader);
			TokenStream reslut = new StopFilter(LuceneUtils.VERSION, source, stops);
			return new TokenStreamComponents(source, reslut);
		}

	}

	public static void showToken(String str, Analyzer analyzer) {
		try {
			TokenStream tokenStream = analyzer.tokenStream("name", new StringReader(str));
			CharTermAttribute cta = tokenStream.addAttribute(CharTermAttribute.class);
			PositionIncrementAttribute pia = tokenStream.addAttribute(PositionIncrementAttribute.class);
			OffsetAttribute of = tokenStream.addAttribute(OffsetAttribute.class);
			// TypeAttribute ta = tokenStream.addAttribute(TypeAttribute.class);
			tokenStream.reset();

			StringBuffer token = new StringBuffer();
			StringBuffer posi = new StringBuffer();
			StringBuffer offset = new StringBuffer();
			while (tokenStream.incrementToken()) {
				token.append("[" + cta + "]");
				posi.append("[" + pia.getPositionIncrement() + "]");
				offset.append("[" + of.startOffset() + "-" + of.endOffset() + "]");
			}
			System.out.println(token.toString());
			// System.out.printf("%s" + ta.type() + "%s\n", "[", "]");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
