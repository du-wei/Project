package com.webapp.tools.test;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.webapp.tools.ApiOpenUtils;

public class ApiOpenUtilsTest {
	
	@Test
	public void queryTrans() throws Exception {
		JSONObject result = ApiOpenUtils.queryTrans("en", "zh", "hello world");
		System.out.println(result);
	}
	
	@Test
	public void queryDicts() throws Exception {
		JSONObject result = ApiOpenUtils.queryDicts("zh", "jp", "大家");
		System.out.println(result);
	}
	
}
