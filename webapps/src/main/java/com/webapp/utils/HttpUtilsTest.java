package com.webapp.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.webapp.utils.http.HttpUtils;

public class HttpUtilsTest {
	public static void main(String[] args) throws IOException {
		try {
			List<NameValuePair> list = new ArrayList<>();
			list.add(new BasicNameValuePair("ip", "www.baidu.com"));

			// list.add(new BasicNameValuePair("type", "mobile"));
			// list.add(new BasicNameValuePair("q", "13621186235"));
			// readResponse(HttpUtils.get("http://www.youdao.com/smartresult-xml/search.s",
			// list));
			String temp = HttpUtils.toStr(HttpUtils.get(
					"http://ip.blueera.net/api?", list));
			System.out.println(temp);
			// post("http://www.youdao.com/smartresult-xml/search.s?type=mobile&q=13621186235");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
