package com.webapp.lucene.pinyin;

import java.io.IOException;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import love.cq.domain.Forest;

import org.ansj.lucene.util.AnsjTokenizer;
import org.ansj.lucene4.AnsjAnalysis;
import org.ansj.splitWord.Analysis;
import org.ansj.splitWord.analysis.BaseAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.cjk.CJKTokenizer;
import org.apache.lucene.analysis.core.LetterTokenizer;
import org.apache.lucene.analysis.miscellaneous.EmptyTokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.CharTermAttributeImpl;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;
import org.apache.poi.poifs.storage.SmallBlockTableReader;
import org.apache.xmlbeans.impl.store.CharUtil;

import com.hp.hpl.sparta.xpath.ThisNodeTest;
import com.webapp.lucene.LuceneUtils;

public class PinYinAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName,
			Reader reader) {
		// Tokenizer source = new AnsjTokenizer(Version.LUCENE_47, reader);
		Set set = new HashSet();

		// Tokenizer source = new StandardTokenizer(Version.LUCENE_47, reader);
		Forest forests = new Forest();
		Analysis analysis = new ToAnalysis(forests);
		Tokenizer source = new AnsjTokenizer(analysis, reader, null, true);
		TokenFilter filter = new PinyinFilter(source);
		// TokenFilter reslut = new PinyinFilter(source);

		return new TokenStreamComponents(source, filter);
	}

	class PinyinTokenizer extends Tokenizer {

		Reader inputs;

		protected PinyinTokenizer(Reader input) {
			super(input);
			this.inputs = input;
		}

		@Override
		public boolean incrementToken() throws IOException {
			this.clearAttributes();

			char[] chars = new char[1024];
			while (inputs.read(chars) > 0) {
				System.out.println(String.valueOf(chars));
			}
			System.out.println("--");
			return false;
		}

	}

	class PinyinFilter extends TokenFilter {

		protected PinyinFilter(TokenStream input) {
			super(input);
		}

		@Override
		public boolean incrementToken() throws IOException {
			this.clearAttributes();
			CharTermAttribute cta = input.addAttribute(CharTermAttribute.class);
			// input.reset();
			while (input.incrementToken()) {
				System.out.println(cta);
			}
			return false;
		}
	}
}
