package com.webapp.tools.test;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webapp.tools.ShowApiUtils;

public class ShowApiUtilsTest {
	
	@Test
    public void queryPhone() throws Exception {
	    JSONObject query = ShowApiUtils.queryPhone("13621186235");
	    System.out.println(JSON.toJSONString(query, true));
    }
	@Test
    public void queryIP() throws Exception {
		JSONObject query = ShowApiUtils.queryIP("182.92.193.119");
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryCard() throws Exception {
		JSONObject query = ShowApiUtils.queryCard("13082519880524101X");
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryWX() throws Exception {
		JSONArray query = ShowApiUtils.queryWX("2", true);
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryGuess() throws Exception {
		JSONObject query = ShowApiUtils.queryGuess();
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryTGirl() throws Exception {
		JSONObject query = ShowApiUtils.queryTGirl("1");
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void querySister() throws Exception {
		JSONObject query = ShowApiUtils.querySister("10", "", "1");
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryWeather() throws Exception {
		JSONObject query = ShowApiUtils.queryWeather("182.92.193.119");
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryOil() throws Exception {
		JSONObject query = ShowApiUtils.queryOil("北京");
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryHotWord() throws Exception {
		JSONObject query = ShowApiUtils.queryHotWord("1", "15", "count", "7");
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryHotInfo() throws Exception {
		JSONObject query = ShowApiUtils.queryHotInfo("10657");
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryNewsJoy() throws Exception {
		JSONObject query = ShowApiUtils.queryNewsJoy("5");
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryJoke() throws Exception {
		JSONObject query = ShowApiUtils.queryJoke();
	    System.out.println(JSON.toJSONString(query, true));
	}
	@Test
    public void queryHistory() throws Exception {
		JSONObject query = ShowApiUtils.queryHistory();
	    System.out.println(JSON.toJSONString(query, true));
	}
}
