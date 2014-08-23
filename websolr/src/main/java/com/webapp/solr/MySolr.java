package com.webapp.solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.LBHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Suggestion;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SpellingParams;
import org.apache.solr.core.CoreContainer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MySolr {

	private String url = "http://localhost:8080/websolr";
	private HttpSolrServer queryServer = null;
	private ConcurrentUpdateSolrServer updateSolrServer = null;
	private LBHttpSolrServer lbSolrServer = null;

	public MySolr() {
		// queryServer = new HttpSolrServer(url);
		updateSolrServer = new ConcurrentUpdateSolrServer(url, 2500, 20);
		try {
			lbSolrServer = new LBHttpSolrServer(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void testEmbeddedSolrServer() {
		CoreContainer container = new CoreContainer.Initializer().initialize();
		SolrServer server = new EmbeddedSolrServer(container, "");

	}

	public void add(String id, String name, String age, String desc) {
		SolrInputDocument doc1 = new SolrInputDocument();
		doc1.addField("id", id);
		doc1.addField("msg_name", name);
		doc1.addField("msg_age", age);
		doc1.addField("msg_desc", desc);
		doc1.addField("text", "ok");
		try {
			updateSolrServer.add(doc1);
			updateSolrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		MySolr mySolr = new MySolr();
		mySolr.suggest("msg_name:na");
	}

	public void delete() {
		try {
			updateSolrServer.deleteByQuery("*:*");
			updateSolrServer.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public JSONArray suggest(String key) {

		// ModifiableSolrParams p = new ModifiableSolrParams();
		// p.add(SpellingParams.SPELLCHECK_Q, key);
		// p.add(SpellingParams.SPELLCHECK_BUILD, "true");

		SolrQuery query = new SolrQuery();
		query.setParam("qt", "/suggest");
		query.setParam(SpellingParams.SPELLCHECK_Q, key);

		QueryResponse response = null;
		try {
			queryServer = new HttpSolrServer(url);
			response = queryServer.query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}
		JSONArray array = new JSONArray();
		List<Suggestion> list = response.getSpellCheckResponse()
				.getSuggestions();
		for (int i = 0; i < list.size(); i++) {
			List<String> sug = list.get(i).getAlternatives();
			for (String str : sug) {
				array.add(str);
				System.out.println(array);
			}
		}

		System.out.println(" suggest ");

		// SolrDocumentList docs = response.getResults();
		// for (SolrDocument doc : docs) {
		// System.out.print("--" + doc.getFieldValue("msg_name"));
		// System.out.println();
		// }
		return array;
	}

	public JSONArray query(String key) {
		// server.setConnectionTimeout(3000);
		// server.setDefaultMaxConnectionsPerHost(500);

		SolrQuery query = new SolrQuery(key);
		query.addSort("id", SolrQuery.ORDER.asc).setStart(0).setRows(10);
		// .setHighlight(true)
		// .setParam("hl.fl", "msg_name")
		// .setHighlightSimplePre("<span style='color:red;'>")
		// .setHighlightSimplePost("</span>");

		//
		QueryResponse response = null;
		try {
			queryServer = new HttpSolrServer(url);
			response = queryServer.query(query);
		} catch (SolrServerException e) {
			e.printStackTrace();
		}

		// getHighlight(response);

		SolrDocumentList docs = response.getResults();
		System.out.println("文档个数：" + docs.getNumFound());
		System.out.println("查询时间：" + response.getQTime());
		Map<String, Map<String, List<String>>> map = response.getHighlighting();
		JSONArray array = new JSONArray();
		for (SolrDocument doc : docs) {
			JSONObject object = new JSONObject();
			object.put("id", doc.getFieldValue("id"));
			// object.put("name",
			// map.get(doc.getFieldValue("id")).get("msg_name"));
			object.put("age", doc.getFieldValue("msg_name"));
			object.put("age", doc.getFieldValue("msg_age"));
			object.put("desc", doc.getFieldValue("msg_desc"));

			array.add(object);
			System.out.print("--" + doc.getFieldValue("id"));
			// System.out.print("--" +
			// map.get(doc.getFieldValue("id")).get("msg_name"));
			System.out.print("--" + doc.getFieldValue("msg_name"));
			System.out.print("--" + doc.getFieldValue("msg_age"));
			System.out.print("--" + doc.getFieldValue("msg_desc"));
			System.out.println();
		}
		return array;

	}

	private void getHighlight(QueryResponse response) {
		Map<String, Map<String, List<String>>> map = response.getHighlighting();

		Iterator<String> ids = map.keySet().iterator();
		while (ids.hasNext()) {
			String id = ids.next();
			System.out.print(id);
			Map<String, List<String>> doc = map.get(id);
			Iterator<String> fieldName = doc.keySet().iterator();
			while (fieldName.hasNext()) {
				String field = fieldName.next();
				List<String> value = doc.get(field);
				System.out.print(field);
				for (int i = 0; i < value.size(); i++) {
					System.out.println(value.get(i));
				}
			}
		}
	}

}
