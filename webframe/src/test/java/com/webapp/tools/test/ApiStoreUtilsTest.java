package com.webapp.tools.test;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.webapp.tools.ApiStoreUtils;

public class ApiStoreUtilsTest {
	
	@Test
    public void queryIP() throws Exception {
		JSONObject body = ApiStoreUtils.queryIP("182.92.193.119");
	    System.out.println(JSON.toJSONString(body, true));
    }
	@Test
    public void queryCard() throws Exception {
		JSONObject body = ApiStoreUtils.queryCard("532329198104305142");
	    System.out.println(JSON.toJSONString(body, true));
    }
	@Test
    public void queryTel() throws Exception {
		JSONObject body = ApiStoreUtils.queryTel("13621186235");
	    System.out.println(JSON.toJSONString(body, true));
    }
	@Test
    public void queryDomain() throws Exception {
		JSONObject body = ApiStoreUtils.queryDomain("seeidea", "com");
	    System.out.println(JSON.toJSONString(body, true));
    }
	
}
