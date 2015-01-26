package com.webapp.solr;

import org.junit.Test;

public class SolrTest {

	@Test
	public void testQuery() {
		MySolr mySolr = new MySolr();
		mySolr.query("*:*");
	}

	@Test
	public void testQuery1() {
		MySolr mySolr = new MySolr();
		mySolr.suggest("na");
	}

	@Test
	public void testAdd() {
		MySolr mySolr = new MySolr();
		mySolr.add("1", "name1", "11", "hello1");
		mySolr.add("2", "name2", "22", "hello2");
		mySolr.add("3", "name3", "33", "hello3");
		mySolr.add("4", "name4", "44", "hello4");
		mySolr.add("5", "name5", "55", "hello5");
		mySolr.add("6", "name6", "66", "hello6");
		mySolr.add("7", "name7", "77", "hello7");
		mySolr.add("8", "name8", "88", "hello8");
	}

	@Test
	public void testDelete() {
		MySolr mySolr = new MySolr();
		mySolr.delete();
	}

	public static void main(String[] args) {

	}

}
