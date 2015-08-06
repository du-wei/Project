package com.webapp.tools;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webapp.utils.date.DateTools;
import com.webapp.utils.http.HttpUtils;

public class ShowApiUtils {
	
	private final static String SHOWAPI_CODE = "showapi_res_code";
	private final static String SHOWAPI_BODY = "showapi_res_body";
	private final static String SHOWAPI_ERROR = "showapi_res_error";
	
	private static Map<String, String> addPara() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("showapi_appid", "235");
		param.put("showapi_sign", "simple_8e38bc2024384ff0bce26d8f5375c959");
		param.put("showapi_timestamp", DateTools.of(new Date()).format());
		return param;
	}
	
	private static Map<String, String> addPara(String key, String val) {
		Map<String, String> param = addPara();
		param.put(key, val);
		return param;
    }
	
	private static JSONObject getResult(String url, Map<String, String> param) {
		String body = HttpUtils.post(url).addParam(param).send().getBody();
	    JSONObject resp = JSON.parseObject(body);
		
		JSONObject result = new JSONObject();
		if(resp.getIntValue(SHOWAPI_CODE) == 0){
			result = resp.getJSONObject(SHOWAPI_BODY);
		}else {
			result.put(SHOWAPI_ERROR, resp.getString(SHOWAPI_ERROR));
		}
	    return result;
    }
	
	public static JSONObject queryPhone(String phone){
//		String num = "num";		//号段
//		String prov = "prov";	//省
//		String city = "city";	//市
//		String name = "name";	//运营商 
//		String type = "type";	//1为移动 2为电信 3为联通 
//		String areaCode = "areaCode";
//		String postCode = "postCode";	//邮政编码
//		String provCode = "provCode";	//省别编码
//		String ret_code = "ret_code";
		
		String url = "http://route.showapi.com/6-1";
		Map<String, String> param = addPara("num", phone);
	    JSONObject result = getResult(url, param);
	    return result;
	}
	
	public static JSONObject queryIP(String ip){
//		String country = "country";	//国家
//		String area = "area";		//片区->西南 
//		String region = "region";	//省级
//		String city = "city";		//市级 
//		String county = "county";	//县级
//		String isp = "isp";			//运营商 
		
		String url = "http://route.showapi.com/20-1";
		Map<String, String> param = addPara("ip", ip);
		JSONObject result = getResult(url, param);
		return result;
	}
	public static JSONObject queryCard(String id){
		String url = "http://route.showapi.com/25-3";
		Map<String, String> param = addPara("id", id);
		JSONObject result = getResult(url, param);
		return result;
	}
	
	public static JSONArray queryWX(String num, boolean isRand){
//		String picUrl = "picUrl";
//		String description = "description";
//		String title = "title";
//		String url = "url";
		
		String url = "http://route.showapi.com/181-1";
		Map<String, String> param = addPara("num", num);
		param.put("rand", isRand ? "1" : "0");
		
		JSONObject wxs = getResult(url, param);
		JSONArray result = new JSONArray();
		Iterator<String> keys = wxs.keySet().iterator();
		for( ;keys.hasNext(); ){
			String key = keys.next();
			if(key.matches("\\d+")){
				result.add(wxs.getJSONObject(key));
			}
		}
		return result;
	}
	
	public static JSONObject queryGuess(){
		//id Title Answer
		
		String url = "http://route.showapi.com/151-2";
		Map<String, String> param = addPara("id", "-1");
		JSONObject result = getResult(url, param);
		return result;
	}
	
	public static JSONObject queryTGirl(String page){
		//maxResult 	每页最大数量
		//allNum 		所有数量
		//allPages		所有页
		//currentPage	当前页 
		//contentlist	淘女郎条目列表 
			//avatarUrl	头像图片地址 
			//cardUrl	封面图片地址
			//city		所在城市
			//height	身高
			//weight	体重
			//imgList[]	模特图片地址列表 
			//realName	名字
			//totalFanNum	粉丝数
			//userId	模特id
			//link		淘女郎url地址 
			
		
		String url = "http://route.showapi.com/126-2";
		Map<String, String> param = addPara("order", "time_up");	//排序
		param.put("page", page);	//页数
		param.put("type", "");		//风格
		param.put("count", "1");		//风格
		
		JSONObject result = getResult(url, param);
		JSONObject bean = result.getJSONObject("pagebean");
		
		return bean;
	}
	
	public static JSONObject querySister(String page, String type, String title){
		String url = "http://route.showapi.com/255-1";
		Map<String, String> param = addPara("type", type);//type=10 图片	type=29 段子	type=31 声音	type=41 视频
		param.put("title", title);
		param.put("page", page);
		
		JSONObject result = getResult(url, param);
		JSONObject bean = result.getJSONObject("pagebean");
		
		return bean;
	}
	
	@Deprecated//地区新闻
	public static JSONObject queryNewsId(){
		String url = "http://route.showapi.com/170-48";
		Map<String, String> param = addPara();
		JSONObject result = getResult(url, param);
		return result;
	}
	@Deprecated
	public static JSONObject queryNews(String page){
		String url = "http://route.showapi.com/170-47";
		Map<String, String> param = addPara();
		param.put("page", page);
//		param.put("areaId", );
//		param.put("areaName", );
//		param.put("title", );
		JSONObject result = getResult(url, param);
		return result;
	}
	
	public static JSONObject queryWeather(String ip){
		String url = "http://route.showapi.com/9-4";
		Map<String, String> param = addPara("ip", ip);
		JSONObject result = getResult(url, param);
		return result;
	}
	
	public static JSONObject queryOil(String prov){
		String url = "http://route.showapi.com/138-46";
		Map<String, String> param = addPara("prov-s", prov);
		JSONObject result = getResult(url, param);
		return result;
	}
	
	public static JSONObject queryHotWord(String page, String limit, String type, String days){
		String url = "http://route.showapi.com/98-110";
		Map<String, String> param = addPara("page", page);	//1
		param.put("limit", limit);	//20
		param.put("type", type);	//id：最新时间，count：访问数
		param.put("id", days);
		JSONObject result = getResult(url, param);
		return result;
	}
	public static JSONObject queryHotInfo(String id){
		String url = "http://route.showapi.com/98-37";
		Map<String, String> param = addPara("id", id);
		JSONObject result = getResult(url, param);
		return result;
	}
	
	public static JSONObject queryNewsJoy(String num){
		String url = "http://route.showapi.com/198-1";
		Map<String, String> param = addPara("num", num);
		JSONObject result = getResult(url, param);
		return result;
	}
	
	//来福岛笑话
	public static JSONObject queryJoke(){
		String url = "http://route.showapi.com/107-33";
		Map<String, String> param = addPara();
		JSONObject result = getResult(url, param);
		return result;
	}
	
	public static JSONObject queryHistory(){
//		String url = "http://route.showapi.com/148-1";
		String url = "http://route.showapi.com/119-42";
		Map<String, String> param = addPara();
		JSONObject result = getResult(url, param);
		return result;
	}
}
