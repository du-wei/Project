package com.webapp.tools;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.webapp.utils.http.HttpUtils;

public class ApiOpenUtils {
	
private final static String CLIENT_ID = "NGXFCnLfXXFa0gkPgfup1Ie7";
	
	private static Map<String, String> addPara() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("client_id", CLIENT_ID);
		return param;
	}
	
	private static String buildBody(String url, Map<String, String> param){
		String body = HttpUtils.get(url).addParam(param).send().getBody();
		return body;
	}

//  自动识别	auto
//	中文 		zh 		英语 		en
//	日语 		jp 		韩语 		kor
//	西班牙语 	spa 	法语 		fra
//	泰语 		th 		阿拉伯语 	ara
//	俄罗斯语 	ru 		葡萄牙语 	pt
//	粤语 		yue 	文言文 		wyw
//	白话文 		zh 		自动检测 	auto
//	德语 		de 		意大利语 	it
//	荷兰语 		nl 		希腊语 		el 
	
	public static JSONObject queryTrans(String from, String to, String q) {
		String url = "http://openapi.baidu.com/public/2.0/bmt/translate";
		Map<String, String> param = addPara();
		param.put("from", from);
		param.put("to", to);
		param.put("q", q);
		String body = buildBody(url, param);
		JSONObject result = JSON.parseObject(body);
		return result;
	}
	
	public static JSONObject queryDicts(String from, String to, String q) {
		String url = "http://openapi.baidu.com/public/2.0/translate/dict/simple";
		Map<String, String> param = addPara();
		param.put("from", from);
		param.put("to", to);
		param.put("q", q);
		String body = buildBody(url, param);
		JSONObject result = JSON.parseObject(body);
		return result;
	}
	
}
