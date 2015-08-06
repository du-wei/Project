package com.webapp.utils.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.webapp.utils.http.HttpUtils.Builder;

public class HttpUtilsTest {

//	@Test
	public static void main(String[] args) {
		String url = "http://www.qichacha.com/";
		Map<String, String> param = new HashMap<String, String>();
		param.put("ip", "www.baidu.com");
		
		Builder build = HttpUtils.get(url).addParam(param).send();
		System.out.println(build.getCookie("SERVERID"));
		
//		System.out.println(HttpUtils.get(url).addParam("ip", "www.baidu.com").send().getBody());
//		System.out.println(HttpUtils.post(url).addParam(param).send().getBody());
//		System.out.println(HttpUtils.post(url).addParam("ip", "www.baidu.com").send().getBody());
    }
    public void testName() throws Exception {
    }

}
