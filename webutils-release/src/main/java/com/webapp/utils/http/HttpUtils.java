package com.webapp.utils.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public final class HttpUtils {

	private HttpPost post;
	private HttpGet get;
	private HttpUtils(HttpPost post){
		this.post = post;
	}
	private HttpUtils(HttpGet get){
		this.get = get;
	}

	public static HttpUtils get(String url){
		return new HttpUtils(new HttpGet(url));
	}
	public static HttpUtils post(String url){
		return new HttpUtils(new HttpPost(url));
	}

	public HttpUtils addHeader(String key, String val){
		if(get != null){
			get.addHeader(key, val);
		}else {
			post.addHeader(key, val);
		}
		return this;
	}

	public HttpUtils addHeader(Map<String, String> header){
		Iterator<String> iterator = header.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if(get != null){
				get.addHeader(key, header.get(key));
			}else {
				post.addHeader(key, header.get(key));
			}
		}
		return this;
	}

	public HttpUtils addParam(String key, String val){
		if(get != null){
			try {
	            get.setURI(new URIBuilder(get.getURI()).addParameter(key, val).build());
            } catch (URISyntaxException e) {
	            e.printStackTrace();
            }
		}else {
			Map<String, String> param = new HashMap<String, String>();
			param.put(key, val);
			post.setEntity(getParams(getParams(param)));
		}
		return this;
	}

	public HttpUtils addParam(Map<String, String> param){
		if(get != null){
			try {
	            get.setURI(new URIBuilder(get.getURI()).addParameters(getParams(param)).build());
            } catch (URISyntaxException e) {
	            e.printStackTrace();
            }
		}else {
			post.setEntity(getParams(getParams(param)));
		}
		return this;
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

	public HttpResponse getResp() throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpResponse resp = client.execute(get != null ? get : post);
		return resp;
	}

	public String getStr() throws Exception {
		StringBuffer result = new StringBuffer();
		InputStream is = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpResponse resp = client.execute(get != null ? get : post);
		if (resp.getStatusLine().getStatusCode() == 200) {
			is = resp.getEntity().getContent();
		}
		if(is != null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
		}
		client.close();
		return result.toString();
	}

}
