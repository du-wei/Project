package com.webapp.utils.http;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webapp.utils.string.Utils.Charsets;

public class HttpUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	private static CloseableHttpClient client;
	private static PoolingHttpClientConnectionManager cm;
	private static Properties prop = new Properties();

	static {
		InputStream in = HttpUtils.class.getResourceAsStream("/http.properties");
		try {
	        if(in != null) prop.load(in);
        } catch (IOException e) {
	        e.printStackTrace();
        }
		
		if(Boolean.valueOf(prop.getProperty("httpPool"))){
			logger.info("启用Http连接池");
			Integer maxTotal = Integer.valueOf(prop.getProperty("maxTotal", "500"));
			Integer reqTimeout = Integer.valueOf(prop.getProperty("requestTimeout", "30000"));
			Integer conTimeout = Integer.valueOf(prop.getProperty("connectTimeout", "30000"));
			Integer socketTimeout = Integer.valueOf(prop.getProperty("socketTimeout", "30000"));
			
			cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(maxTotal);
			cm.setDefaultMaxPerRoute(cm.getMaxTotal());
			RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(reqTimeout).setConnectTimeout(conTimeout)
					.setSocketTimeout(socketTimeout).build();
			
			client = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).build();
		}else {
			logger.info("未启用Http连接池");
		}
	}
	
	private static CloseableHttpClient getClient(){
		if(client == null){
			return HttpClients.createDefault();
		}
		cm.closeExpiredConnections();
		cm.closeIdleConnections(30, TimeUnit.MINUTES);
		return client;
	}
	
	public static BuilderGet get(String url) {
		return new BuilderGet(url);
	}

	public static BuilderPost post(String url) {
		return new BuilderPost(url);
	}

	private static List<NameValuePair> getParams(Map<String, String> param) {
		if (param == null) return null;

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Iterator<String> iterator = param.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = param.get(key);
			params.add(new BasicNameValuePair(key, value));
		}
		return params;
	}

	public static abstract class Builder {
		
		CloseableHttpResponse resp;
		CookieStore cookieStore;
		String url;
		Map<String, String> param = new HashMap<String, String>();
		Map<String, String> header = new HashMap<String, String>();

		public Builder(String url) {
			this.url = url;
		}

		public Builder addParam(String key, String val) {
			this.param.put(key, val);
			return this;
		}

		public Builder addParam(Map<String, String> param) {
			this.param.putAll(param);
			return this;
		}

		public Builder addHeader(String key, String val) {
			this.header.put(key, val);
			return this;
		}

		public Builder addHeader(Map<String, String> param) {
			this.header.putAll(param);
			return this;
		}

		public abstract Builder send();
		
		public CloseableHttpResponse response() {
	        return resp;
        }
		
		public List<Cookie> getCookies() {
			return cookieStore.getCookies();
        }
		
		public Cookie getCookie(String name){
			List<Cookie> cookies = getCookies();
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(name)){
					return cookie;
				}
			}
			return null;
		}
		
		public Header[] getHeaders(String name) {
	        return resp.getHeaders(name);
        }
		
		public Header getHeader(String name) {
	        Header[] headers = resp.getHeaders(name);
	        for(Header header : headers){
	        	String key = header.getName();
	        	if(key.equals(name)){
	        		return header;
	        	}
	        }
	        return null;
        }

		public String getBody() {
			if(resp == null) return null;
			HttpEntity entity = resp.getEntity();
			try {
				return EntityUtils.toString(entity, Charsets.uft8);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
			return null;
		}
		
		public void close(){
			if(resp != null && client == null){
				try {
		            resp.close();
	            } catch (IOException e) {
		            e.printStackTrace();
	            }
			}
		}

 		protected void setHeader(HttpUriRequest request) {
			Iterator<String> iterator = header.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				request.addHeader(key, header.get(key));
			}
		}
 		
 		protected HttpContext getContext(){
 			cookieStore = new BasicCookieStore();
 			HttpContext context = new BasicHttpContext();
    		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
    		return context;
 		}
	}

	public static class BuilderPost extends Builder {

		public BuilderPost(String url) {
			super(url);
		}

        public Builder send() {
			HttpPost post = new HttpPost(url);
			setHeader(post);
			try {
				post.setEntity(new UrlEncodedFormEntity(getParams(param), Charsets.uft8));
				resp = getClient().execute(post, getContext());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return this;
        }
	}

	public static class BuilderGet extends Builder {
		public BuilderGet(String url) {
			super(url);
		}

        public Builder send() {
        	try {
				URIBuilder uri = new URIBuilder(url);
				uri.addParameters(getParams(param));
				HttpGet get = new HttpGet(uri.build());
				setHeader(get);
				resp = getClient().execute(get, getContext());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
	        return this;
        }
	}

}
