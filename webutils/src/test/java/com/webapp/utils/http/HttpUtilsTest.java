package com.webapp.utils.http;

import java.util.HashMap;
import java.util.Map;

import com.webapp.utils.http.HttpUtils.Builder;

public class HttpUtilsTest {

//	@Test
	public static void main(String[] args) {
		String url = "http://www.qichacha.com/";
		Map<String, String> param = new HashMap<String, String>();
		param.put("ip", "www.baidu.com");
		Builder build = HttpUtils.get(url).addParam(param).send();
		System.out.println(build.getCookie("SERVERID"));
		
    }

}
