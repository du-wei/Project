package com.webapp.utils.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpTools {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpTools.class);
	
	private static CloseableHttpClient client;
	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
		client = HttpClients.custom().setConnectionManager(cm).build();
	}
	
	public static BuilderGet get(String url) {
		return new BuilderGet(url);
    }
	public static BuilderPost post(String url) {
		return new BuilderPost(url);
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

	private static HttpEntity postParams(Map<String, String> param) {
		List<NameValuePair> params = getParams(param);
		try {
	        return param == null ? null : new UrlEncodedFormEntity(params);
        } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
        }
		return null;
	}
	
	public static abstract class Builder {
		String url;
		Map<String, String> param = new HashMap<>();
		Map<String, String> header = new HashMap<>();
		
		public Builder(String url) {
	        this.url = url;
        }
		
		public Builder addParam(String key, String val) {
			param.put(key, val);
			return this;
        }
		public Builder addParam(Map<String, String> param) {
			param.putAll(param);
			return this;
        }
		public Builder addHeader(String key, String val) {
			header.put(key, val);
			return this;
        }
		public Builder addHeader(Map<String, String> param) {
			header.putAll(param);
			return this;
        }
		public abstract CloseableHttpResponse getResponse();
		
		public String getBody(){
			HttpEntity entity = getResponse().getEntity();
			try {
	            return EntityUtils.toString(entity, "utf-8");
            } catch (IOException e) {
            	logger.error(null, e);
            }
			return null;
		};
		
		protected void setHeader(HttpUriRequest request) {
			Iterator<String> iterator = header.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				request.addHeader(key, header.get(key));
			}
        }
	}
	
	public static class BuilderPost extends Builder{
		public BuilderPost(String url) {
	        super(url);
        }
		public CloseableHttpResponse getResponse() {
	        HttpPost post = new HttpPost(url);
	        post.setEntity(postParams(param));
	        setHeader(post);
	        
	        try {
	            return client.execute(post);
            } catch (Exception e) {
            	logger.error(null, e);
            }
	        return null;
        }
	}
	
	public static class BuilderGet extends Builder {
		public BuilderGet(String url) {
	        super(url);
        }
		public CloseableHttpResponse getResponse() {
	        try {
	        	URIBuilder uri = new URIBuilder(url);
				uri.addParameters(getParams(param));
				HttpGet get = new HttpGet(uri.build());
				setHeader(get);
	            return client.execute(get);
            } catch (Exception e) {
            	logger.error(null, e);
            }
	        return null;
        }
	}
	
}