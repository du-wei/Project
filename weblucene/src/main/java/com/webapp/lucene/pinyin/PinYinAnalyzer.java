package com.webapp.lucene.pinyin;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.ansj.lucene.util.AnsjTokenizer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class PinYinAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName,
			Reader reader) {
		// Tokenizer source = new AnsjTokenizer(Version.LUCENE_47, reader);
		Set set = new HashSet();

		// Tokenizer source = new StandardTokenizer(Version.LUCENE_47, reader);
//		Forest forests = new Forest();
//		Analysis analysis = new ToAnalysis();
		Tokenizer source = new AnsjTokenizer(null, reader, null, true);
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
