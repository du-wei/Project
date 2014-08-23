package com.webapp.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;

import com.chenlb.mmseg4j.Dictionary;
import com.chenlb.mmseg4j.SimpleSeg;
import com.chenlb.mmseg4j.analysis.MMSegTokenizer;

public class MySameAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName,
			Reader reader) {

		MMSegTokenizer mmSegTokenizer = new MMSegTokenizer(new SimpleSeg(
				Dictionary.getInstance()), reader);

		return new TokenStreamComponents(mmSegTokenizer);
	}

}
