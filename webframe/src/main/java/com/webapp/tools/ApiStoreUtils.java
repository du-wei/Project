package com.webapp.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.webapp.utils.http.HttpUtils;
import com.webapp.utils.http.HttpUtils.Builder;

public class ApiStoreUtils {
	
	private final static String APIKEY = "2e1bf33985fb2edf0ec94319b98d6b49";
	
	private final static String ERR_NUM = "errNum";
	private final static String ERR_MSG = "errMsg";
	private final static String RET_MSG = "retMsg";
	private final static String RET_DATA = "retData";
	
	private static Builder build(String url){
		Builder build = HttpUtils.get(url).addHeader("apikey", APIKEY);
		return build;
	}
	
//	private static String buildBody(String url){
//		String body = build(url).getBody();
//		return body;
//	}
	private static String buildBody(String url, String key, String val){
		String body = build(url).addParam(key, val).send().getBody();
		return body;
	}
//	private static String buildBody(String url, Map<String, String> param){
//		String body = build(url).addParam(param).getBody();
//		return body;
//	}
	
	private static JSONObject getResult(String body) {
	    JSONObject resp = JSON.parseObject(body);
		
		JSONObject result = new JSONObject();
		if(resp.getIntValue(ERR_NUM) == 0){
			result = resp.getJSONObject(RET_DATA);
		}else {
			if(result.containsKey(ERR_MSG)){
				result.put(ERR_MSG, resp.getString(ERR_MSG));
			}else {
				result.put(ERR_MSG, resp.getString(RET_MSG));
			}
		}
	    return result;
    }
	
	
	public static JSONObject queryIP(String ip) {
		String url = "http://apis.baidu.com/apistore/iplookupservice/iplookup";
	    String body = buildBody(url, "ip", ip);
	    return getResult(body);
    }
	public static JSONObject queryCard(String id) {
		String url = "http://apis.baidu.com/apistore/idservice/id";
		String body = buildBody(url, "id", id);
		return getResult(body);
	}
	public static JSONObject queryTel(String tel) {
		String url = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone";
		String body = buildBody(url, "tel", tel);
		return getResult(body);
	}
	public static JSONObject queryDomain(String domain, String suffix) {
		String url = "http://www.yumingco.com/api";
		String body = HttpUtils.get(url).addParam("domain", domain).addParam("suffix", suffix).send().getBody();
		return JSON.parseObject(body);
		
	}
	
}
