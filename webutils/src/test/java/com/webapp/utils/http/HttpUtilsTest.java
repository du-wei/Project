package com.webapp.utils.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HttpUtilsTest {

	@Test
    public void testName() throws Exception {
		String url = "http://www.badiu.com";
		Map<String, String> param = new HashMap<String, String>();
		param.put("ip", "www.baidu.com");

		System.out.println(HttpUtils.get(url).addParam(param).getBody());
		System.out.println(HttpUtils.get(url).addParam("ip", "www.baidu.com").getBody());
		System.out.println(HttpUtils.post(url).addParam(param).getBody());
		System.out.println(HttpUtils.post(url).addParam("ip", "www.baidu.com").getBody());
    }

}
