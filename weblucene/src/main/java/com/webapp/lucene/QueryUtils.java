package com.webapp.lucene;

import org.ansj.lucene4.AnsjAnalysis;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.BoostingQuery;
import org.apache.lucene.queries.CustomScoreQuery;
import org.apache.lucene.queries.mlt.MoreLikeThisQuery;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;

public class QueryUtils {
	
	public static Query getTermQuery(String field, String value) {
	    return new TermQuery(getTerm(field, value));
    }
	
	public static Query getPrefixQuery(String field, String value) {
	    return new PrefixQuery(getTerm(field, value));
    }
	
	public static Query getWildcardQuery(String field, String value) {
	    return new WildcardQuery(getTerm(field, value));
    }
	
	public static Query getFuzzyQuery(String field, String value) {
		return new FuzzyQuery(getTerm(field, value));
    }
	
	public static Query getParserQuery(String query, String field, Analyzer analyzer) {
	    QueryParser parser = new QueryParser(Version.LUCENE_47, field, analyzer);
	    Query q = null;
	    try {
	        q = parser.parse(query);
        } catch (ParseException e) {
	        e.printStackTrace();
        }
	    return q;
    }
	
	private static Term getTerm(String field, String value){
		return new Term(field, value);
	}
	
	
	public static Query getNumericQuery() {
	    return null;
    }
	
	public static Query getBooleanQuery() {
	    return null;
    }
	
	public static Query getBoostingQuery() {
		BoostingQuery query = new BoostingQuery(new TermQuery(new Term("title", "中国")), new TermQuery(new Term("title", "电视台")), 2f);
	    return null;
    }
	
	public static Query getCustomScoreQuery() {
		TermQuery subQuery = new TermQuery(new Term("title", "中国"));
		CustomScoreQuery query = new CustomScoreQuery(subQuery);
	    return null;
    }
	
	public static Query getMoreLikeThisQuery() {
		MoreLikeThisQuery query = new MoreLikeThisQuery("中", new String[]{"title"}, new AnsjAnalysis(), "title");
	    return null;
    }
	
	
}
