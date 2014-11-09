package com.webapp.utils.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HttpUtilsTest {

	@Test
    public void testName() throws Exception {
		String url = "http://ip.blueera.net/api?";
		Map<String, String> param = new HashMap<String, String>();
		param.put("ip", "www.baidu.com");

		System.out.println(HttpUtils.get(url).addParam(param).getStr());
		System.out.println(HttpUtils.get(url).addParam("ip", "www.baidu.com").getStr());
		System.out.println(HttpUtils.post(url).addParam(param).getStr());
		System.out.println(HttpUtils.post(url).addParam("ip", "www.baidu.com").getStr());
    }

}