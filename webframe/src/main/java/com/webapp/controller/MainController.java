package com.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.utils.date.DateTools;
import com.webapp.utils.http.HttpUtils;
import com.webapp.utils.http.HttpUtils.Builder;

@Controller
public class MainController {
	
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mav){
		mav.setViewName("/index");
		return mav;
	}
	
	public static Map<String, String> addPara(String key, String val) {
		Map<String, String> param = addPara();
		param.put(key, val);
		return param;
    }
	
	public static Map<String, String> addPara() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("showapi_appid", "235");
		param.put("showapi_sign", "simple_8e38bc2024384ff0bce26d8f5375c959");
		param.put("showapi_timestamp", DateTools.of(new Date()).format());
		return param;
    }
	
	public static void main(String[] args) {
		
		String url = "http://route.showapi.com/6-1";
		Map<String, String> param = addPara("num", "15803343486");
	    String body = HttpUtils.post(url).addParam(param).getBody();
	    
//	    String url = "http://route.showapi.com/20-1";
//	    Map<String, String> param = addPara("ip", "182.92.193.119");
//	    String body = HttpUtils.post(url).addParam(param).getBody();
		
//	    String url = "http://route.showapi.com/24-1";
//	    Map<String, String> param = addPara("name", "meng800.com");
//	    String body = HttpUtils.post(url).addParam(param).getBody();
		
//	    String url = "http://route.showapi.com/22-1";
//	    String body = HttpUtils.post(url).addParam(addPara()).getBody();
	    
	    System.out.println(body);
	    
    }
	
}
