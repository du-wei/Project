package com.webapp.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.ansj.lucene4.AnsjAnalysis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.BoostingQuery;
import org.apache.lucene.queries.CustomScoreQuery;
import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.queries.function.valuesource.QueryValueSource;
import org.apache.lucene.queries.mlt.MoreLikeThisQuery;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.search.spans.SpanFirstQuery;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.util.BytesRef;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;

/** @ClassName: Luncene.java
 * @Package com.webapp.lucene
 * @Description: TODO 类型描述
 * @author king
 * @date 2014年5月10日 上午12:29:53
 * @version V1.0 */
public class SearcherUtils {

	private static Logger logger = LogManager.getLogger(SearcherUtils.class);
	public static SearcherManager sm = null;
	static {
		try {
			SearcherFactory factory = new SearcherFactory();
			sm = new SearcherManager(LuceneUtils.getDirectory(IndexUtils.paths), factory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File getIndexPath(String path) {
		return Paths.get(path).toFile();
	}

	public static IndexSearcher getIndexSearcher() throws Exception {
		if (!sm.isSearcherCurrent()) {
			System.out.println("has changed");
		}
		return sm.acquire();
	}

	public SearcherUtils() {
	}

	public static void highligher() throws Exception {
		// IndexReader reader = getIndexReader();
		// IndexSearcher searcher = new IndexSearcher(getIndexReader());

		String text = "我们去北京河北，河北省承德市隆化县 阿斯发射点发 啊短发散发的we范围我发挨打据啊累计跌幅i额";
		// TermQuery query = new TermQuery(new Term("name", "中国"));
		Query query = new QueryParser(LuceneUtils.VERSION, "name", new MMSegAnalyzer()).parse("北京-短发");
		QueryScorer scorer = new QueryScorer(query);
		Formatter formatter = new SimpleHTMLFormatter("<span>", "</span>");
		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		Highlighter highlighter = new Highlighter(formatter, scorer);
		highlighter.setTextFragmenter(fragmenter);
		String ok = highlighter.getBestFragment(new MMSegAnalyzer(), "name", text);
		System.out.println(ok);

	}

	public static void searchByScoreQuery(String field, String value, int num) throws Exception {
		DirectoryReader reader = DirectoryReader.open(LuceneUtils.getDirectory(IndexUtils.paths));
		IndexSearcher searcher = new IndexSearcher(reader);
		Query termQuery = new TermQuery(new Term(field, value));

		// ValueSource.newContext(searcher);
		ValueSource valueSource = new QueryValueSource(new TermQuery(new Term(field, value)), 20);
//		 FunctionQuery query = new FunctionQuery(valueSource);

		// termQuery.toString(field);
		// 查看评分细节
		// Explanation explain = searcher.explain(termQuery, 10);
		// System.out.println(explain.toString());

		// MyScore myScore = new MyScore(termQuery, query);
		// TopDocs topDocs = searcher.search(myScore, num);
		// listDoc(searcher, topDocs);

		searchByTerm(field, value, num);
	}

	// 关键词查询
	public static TopDocs searchByTerm(String field, String value, int num) throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		Query termQuery = QueryUtils.getTermQuery(field, value);
		TopDocs topDocs = searcher.search(termQuery, num);
		return topDocs;
	}

	// 范围查询
	public static TopDocs searchByTermRange(String field, String start, String end, int num) throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		Query termQuery = new TermRangeQuery(field, new BytesRef(start), new BytesRef(end), true, true);
		TopDocs topDocs = searcher.search(termQuery, num);
		return topDocs;
	}

	// 数字范围查询
	public static TopDocs searchByNumricRange(String field, int start, int end, int num) throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		Query termQuery = NumericRangeQuery.newIntRange(field, start, end, true, true);
		TopDocs topDocs = searcher.search(termQuery, num);
		return topDocs;
	}

	// 前缀查询
	public static TopDocs searchByPrefix(String field, String value, int num) throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		Query termQuery = QueryUtils.getPrefixQuery(field, value);
		TopDocs topDocs = searcher.search(termQuery, num);
		return topDocs;
	}

	// 通配符查询
	public static TopDocs searchByWildcard(String field, String value, int num) throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		Query termQuery = QueryUtils.getWildcardQuery(field, value);
		TopDocs topDocs = searcher.search(termQuery, num);
		return topDocs;
	}

	// 可以联合多个条件
	public static TopDocs searchByBoolean(String field, String value, int num) throws Exception {

		IndexSearcher searcher = IndexUtils.getIndexSearcher();

		BooleanQuery termQuery = new BooleanQuery();
		termQuery.add(new TermQuery(new Term(field, value)), Occur.MUST);
		termQuery.add(new TermQuery(new Term("title", "中国")), Occur.MUST);

		TopDocs topDocs = searcher.search(termQuery, num);
		return topDocs;
	}

	public static void searchByBoosting(String field, String value, int num) throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		BoostingQuery query = new BoostingQuery(new TermQuery(new Term("title", "中国")), new TermQuery(new Term("title", "电视台")), 2f);
		TopDocs topDocs = searcher.search(query, 10);
    }

	public static void searchByCustomScore() throws Exception{
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		TermQuery subQuery = new TermQuery(new Term("title", "中国"));
		CustomScoreQuery query = new CustomScoreQuery(subQuery);
		TopDocs topDocs = searcher.search(query, 10);

	}

	public static void searchByMoreLikeThis() throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
	    MoreLikeThisQuery query = new MoreLikeThisQuery("中", new String[]{"title"}, new AnsjAnalysis(), "title");
		TopDocs topDocs = searcher.search(query, 10);
    }

	// 短语查询
	public static void searchByPhrase(String field, String value, int num) throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		PhraseQuery termQuery = new PhraseQuery();
		termQuery.setSlop(2);
		termQuery.add(new Term(field, "中国"));
		termQuery.add(new Term(field, "电视台"));
		TopDocs topDocs = searcher.search(termQuery, num);
//		MultiPhraseQuery query = new MultiPhraseQuery();
	}

	public static void searchBySpan() throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
	    SpanQuery query = new SpanFirstQuery(new SpanTermQuery(new Term("title", "国")), 3);

	    TopDocs topDocs = searcher.search(query, 10);
    }



	// 模糊查询
	public static TopDocs searchByFuzzy(String field, String value, int num) throws Exception {
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		Query termQuery = QueryUtils.getFuzzyQuery(field, value);
		TopDocs topDocs = searcher.search(termQuery, num);
		return topDocs;
	}



}
