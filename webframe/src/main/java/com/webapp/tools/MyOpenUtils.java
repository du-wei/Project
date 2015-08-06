package com.webapp.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webapp.utils.date.DateTools;
import com.webapp.utils.jpinyin.ChineseHelper;
import com.webapp.utils.jpinyin.PinyinHelper;

public class MyOpenUtils {
	public static void main(String[] args) {
    }
	public static String queryChinese(String data, String type) {
		// 0  汉语->拼音 
		// 1 简体->繁体
		// 2 繁体->简体
		String result = data;
		if(type.equals("0")){
			result = PinyinHelper.convertToPinyinString(data, " ");
		}else if (type.equals("1")) {
			result = ChineseHelper.convertToTraditionalChinese(data);
		}else if (type.equals("2")){
			result = ChineseHelper.convertToSimplifiedChinese(data);
		}
		return result;
	}
	
	public static JSONArray queryDate(String date, int type){
		JSONArray result = new JSONArray();
		String[] patterns = DateTools.getPatterns();
		if(type > 0){
			patterns = new String[]{DateTools.getPatterns()[--type]};
		}
		DateTools dt = DateTools.of(date);
		for(String pattern : patterns){
			JSONObject item = new JSONObject();
			item.put("val", dt.format(pattern));
			item.put("pattern", pattern);
			result.add(item);
		}
		return result;
	}
	
}
