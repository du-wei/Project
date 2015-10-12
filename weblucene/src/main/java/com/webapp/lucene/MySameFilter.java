package com.webapp.lucene;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class MySameFilter extends TokenFilter {

	private CharTermAttribute cta = null;

	protected MySameFilter(TokenStream input) {
		super(input);
		cta = this.addAttribute(CharTermAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		if (!input.incrementToken()) {
			return false;
		}
		return true;
	}

}
