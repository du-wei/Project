package com.webapp.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

public class MyCustomParse extends QueryParser {

	public MyCustomParse(Version matchVersion, String f, Analyzer a) {
		super(matchVersion, f, a);
	}

	@Override
	protected Query getWildcardQuery(String field, String termStr)
			throws ParseException {
		System.out.println("wildcard");
		return super.getWildcardQuery(field, termStr);
	}

	@Override
	protected Query getFuzzyQuery(String field, String termStr,
			float minSimilarity) throws ParseException {
		System.out.println("fuzzy");
		return super.getFuzzyQuery(field, termStr, minSimilarity);
	}

}
