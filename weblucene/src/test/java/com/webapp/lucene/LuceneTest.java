package com.webapp.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spell.SpellChecker;
import org.junit.Test;

import com.webapp.utils.config.PathUtils;

public class LuceneTest {

	//遗失的特性
//	Apache Commons
//	Guava
//	Gson
//	Java Tuples
//	Joda-Time
//	Lombok
//	Play framework
//	SLF4J
//	jOOQ
//	测试
//	jUnit 4
//	jMock
//	AssertJ
	//EclEmma


	private String strs = "长久以来，一颗流浪的心忽然间找到了一个可以安歇的去处。坐在窗前，我在试问我自己：你有多久没有好好看看这蓝蓝的天，"+
			"闻一闻这芬芳的花香，听一听那鸟儿的鸣唱？有多久没有回家看看，听听家人的倾诉？有多久没和他们一起吃饭了，听听那年老的欢笑？有多久没与他们谈心，"+
			"听听他门的烦恼、他们的心声呢？是不是因为一路风风雨雨， 而忘了天边的彩虹？是不是因为行色匆匆的脚步，而忽视了沿路的风景？除了一颗疲惫的心，"+
			"麻木的心，你还有一颗感恩的心吗？不要因为生命过于沉重，而忽略了感恩的心！";

	@Test
	public void testPinyin() throws Exception {
		// PinyinHelper.convertToPinyinString(str, "",
		// PinyinFormat.WITHOUT_TONE);
	}

	@Test
	public void testName() throws Exception {

		// System.out.println(PinYinUtils.getPinYin(str));

		SpellChecker sc = SpellCheckerUtils.getSpellChecker("title", "./index", "./spell");
		String[] suggestSimilar = sc.suggestSimilar("无法融入", 2, 0.02f);

		System.out.println(suggestSimilar.length);
		for (String s : suggestSimilar) {
			System.out.println(s);
		}
		sc.close();

	}

	@Test
	public void testAnalyzer() throws Exception {
		List<String> list = new ArrayList<>();
		list.add("我是中国人");
		list.add("中国真伟大");

		// hanyu
		String str1 = "中国，古时通常泛指中原地区，与中华中夏 中土中州含义相同。古代华夏族、汉族建国于黄河流域一带，以为居天下之中，故称中国";
		String str = "this is my house, i am come from yunnang";
		String pp = "古时通常泛指中原地区";

		 AnalyzerUtils.showToken(str1, AnalyzerUtils.getStandardAnalyzer());
		 AnalyzerUtils.showToken(str1, AnalyzerUtils.getSimpleAnalyzer());
		// AnalyzerUtils.showToken(str, AnalyzerUtils.getKeywordAnalyzer());
		// AnalyzerUtils.showToken(str, AnalyzerUtils.getWhitespaceAnalyzer());
		//
		// AnalyzerUtils.showToken(str, AnalyzerUtils.getCJKAnalyzer());
		 AnalyzerUtils.showToken(str1, AnalyzerUtils.getAnsjAnalysis());
		// AnalyzerUtils.showToken(str,
		// AnalyzerUtils.getSmartChineseAnalyzer());
		// AnalyzerUtils.showToken(str, AnalyzerUtils.getMMSegAnalyzer());

//		JcsegAnalyzer4X jcseg = new JcsegAnalyzer4X(JcsegTaskConfig.SIMPLE_MODE);
		// JcsegAnalyzer4X jcseg = (JcsegAnalyzer4X)analyzer;
//		JcsegTaskConfig taskConfig = jcseg.getTaskConfig();
//		taskConfig.setAppendCJKPinyin(true);
//		taskConfig.setAppendPartOfSpeech(true);

		// Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_47);
		// PerFieldAnalyzerWrapper pr = new PerFieldAnalyzerWrapper(analyzer);

//		AnalyzerUtils.showToken(pp, new PinYinAnalyzer());
	}

	@Test
	public void testIndex() throws Exception {

		File file = new File(PathUtils.getPath("word.txt").toString());
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);

		List<Document> list = new ArrayList<>();

		String str = "";
		int i = 0;
		while ((str=reader.readLine()) != null) {
			Document doc = new Document();
			Field field = new TextField("title", str, Store.YES);
			if(i%2==0){
				field.setBoost(1.5f);
			}
			i++;
			doc.add(field);
			list.add(doc);
		}

		// AnalyzerUtils.showToken(str, jcseg);
//		IndexUtils.delete("./index");
		IndexUtils.index("./index", list);

		IndexSearcher indexSearcher = IndexUtils.getIndexSearcher();
		Query query = new TermQuery(new Term("title", "梦想"));
		query.setBoost(1.2f);
		Sort sort = new Sort(new SortField("title", Type.STRING, true));
//		TopDocs topDocs = indexSearcher.search(query, null, 10, sort, true, true);
		TopDocs topDocs = indexSearcher.search(query, 10);

		for (ScoreDoc doc : topDocs.scoreDocs) {
			Document d = indexSearcher.doc(doc.doc);

			Explanation exp = indexSearcher.explain(query, doc.doc);

			System.out.println(doc.score + "---" + d.get("title"));
			System.out.println(exp.toString());
		}

//		LuceneUtils.listDoc(indexSearcher, topDocs);;

		reader.close();
	}

	@Test
	public void testDelete() throws Exception {
		IndexUtils.delete("./index");
	}

	@Test
	public void testsearchByTerm() throws Exception {
		SearcherUtils.searchByTerm("title", "中国", 10);
	}

	@Test
	public void testsearchByTermRange() throws Exception {
		SearcherUtils.searchByTermRange("title", "中", "台", 10);
	}

	@Test
	public void testsearchByNumricRange() throws Exception {
		SearcherUtils.searchByNumricRange("name", 1, 5, 10);
	}

	@Test
	public void testsearchByPrefix() throws Exception {
		SearcherUtils.searchByPrefix("title", "中国", 10);
	}

	@Test
	public void testsearchByWildcard() throws Exception {
		SearcherUtils.searchByWildcard("title", "中国", 10);
	}

	@Test
	public void testsearchByBoolean() throws Exception {
		SearcherUtils.searchByBoolean("title", "电视台", 10);
	}
	@Test
	public void testsearchByBoosting() throws Exception {
		SearcherUtils.searchByBoosting("title", "电视台", 10);
	}
	@Test
	public void testsearchByCustomScore() throws Exception {
		SearcherUtils.searchByCustomScore();
	}
	@Test
	public void testsearchByMoreLikeThis() throws Exception {
		SearcherUtils.searchByMoreLikeThis();
	}

	@Test
	public void testsearchByPhrase() throws Exception {
		SearcherUtils.searchByPhrase("title", "maven.txt", 10);
	}

	@Test
	public void testsearchByFuzzy() throws Exception {
		SearcherUtils.searchByFuzzy("title", "小", 10);
	}

	@Test
	public void testsearchBySpan() throws Exception{
		SearcherUtils.searchBySpan();
	}

}
