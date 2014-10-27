package com.webapp.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class HttpUtils_old {

	public static String toStr(InputStream in) throws Exception {

		BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gbk"));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}

	public static InputStream get(String uri) throws Exception {
		HttpGet get = new HttpGet(uri);
		return execute(get);
	}

	public static InputStream get(String uri, List<NameValuePair> param) throws Exception {
		String url = new URIBuilder(uri).setParameters(param).build().toString();
		return get(url);
	}

	public static InputStream get(String uri, Map<String, String> param) throws Exception {
		return get(uri, getParams(param));
	}

	public static InputStream post(String uri) throws Exception {
		HttpPost post = new HttpPost(uri);
		return execute(post);
	}

	public static InputStream post(String uri, Map<String, String> param) throws Exception {
		return post(uri, getParams(param));
	}

	public static InputStream post(String uri, List<NameValuePair> param) throws Exception {
		return post(uri, getParams(param));
	}

	private static InputStream post(String uri, HttpEntity entity) throws Exception {
		HttpPost post = new HttpPost(uri);
		post.setEntity(entity);
		return execute(post);
	}

	private static List<NameValuePair> getParams(Map<String, String> param) {
		if (param == null) return null;

		List<NameValuePair> params = new ArrayList<>();
		Iterator<String> iterator = param.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			params.add(new BasicNameValuePair(key, param.get(key).toString()));
		}
		return params;
	}

	private static HttpEntity getParams(List<NameValuePair> param) {
		try {
	        return param == null ? null : new UrlEncodedFormEntity(param);
        } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	        return null;
        }
	}

	private static InputStream execute(HttpUriRequest req) throws Exception {
		InputStream result = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpResponse resp = client.execute(req);
		if (resp.getStatusLine().getStatusCode() == 200) {
			result = resp.getEntity().getContent();
		}
		client.close();
		return result;
	}

}
