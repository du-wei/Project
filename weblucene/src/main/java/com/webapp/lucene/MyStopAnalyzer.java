package com.webapp.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LetterTokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;

public class MyStopAnalyzer extends Analyzer {

	public CharArraySet stop;

	public MyStopAnalyzer(String[] stops) {
		stop = StopFilter.makeStopSet(Version.LUCENE_42, stops);
		stop.addAll(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName,
			Reader reader) {
		System.out.println(StopAnalyzer.ENGLISH_STOP_WORDS_SET);
		LetterTokenizer letter = new LetterTokenizer(Version.LUCENE_42, reader);

		LowerCaseFilter caseFilter = new LowerCaseFilter(Version.LUCENE_42,
				letter);
		TokenStream stopFilter = new StopFilter(Version.LUCENE_42, caseFilter,
				stop);

		return new TokenStreamComponents(letter, stopFilter);
	}

}
