package com.webapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webapp.tools.ApiOpenUtils;
import com.webapp.tools.ApiStoreUtils;
import com.webapp.tools.MyOpenUtils;
import com.webapp.tools.ShowApiUtils;
import com.webapp.utils.date.DateTools;
import com.webapp.utils.poi.ExcelUtils;

@Controller
@RequestMapping(value={"", "/utils", "/tools"})
public class UtilsController {
	
	//view
	private static final String V = "";
	//query
	private static final String Q = "/q";
	
	private static final String VIEW_PREFIX = "/utils";
	
	@RequestMapping(V + "/{type}")
	public ModelAndView type(ModelAndView mav, @PathVariable("type")String type) {
		/** color
		 * 	json
		 * 	pdf
		 * 	weixin
		 * 	sister
		 * 	trans
		 * 	id
		 * 	ip
		 * 	tel
		 * 	domain
		 *  excel
		 */
		
		mav.setViewName(VIEW_PREFIX + "/" + type);
		if (type.equals("ip") || type.equals("domain") || type.equals("tel") || type.equals("id")){
			mav.setViewName(VIEW_PREFIX + "/util");
			mav.addObject("type", type);
		}else if (type.equals("sou") || type.equals("search")) {
			mav.setViewName(VIEW_PREFIX + "/search");
		}
		return mav;
	}
	
	@RequestMapping(V + "/date")
	public ModelAndView date(ModelAndView mav){
		mav.setViewName(VIEW_PREFIX + "/date");
		String[] patterns = DateTools.getPatterns();
		mav.addObject("pattern", JSON.parseArray(JSON.toJSONString(patterns)));
		mav.addObject("now", DateTools.now().format());
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(Q + "/excel")
	public String excel(HttpServletRequest req, @RequestParam("file")MultipartFile file){
		String pattern = req.getParameter("pattern");
		String spilt = req.getParameter("spilt");
		boolean isEnter = Boolean.parseBoolean(req.getParameter("enter"));
		boolean show = Boolean.parseBoolean(req.getParameter("show"));
		
		InputStream inputStream = null;
		try {
			inputStream = file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String[]> data = null;
				
		if(show){
			data = ExcelUtils.readExcelByPath(inputStream, -1, 0);
			if(data.size() >= 1){
				String[] heads = data.get(0);
				for(int i=0; i<heads.length; i++){
					heads[i] = heads[i] + "($" + (i+1) + ")";
				}
			}
			return JSON.toJSONString(data);
		}else {
			data = ExcelUtils.readExcelByPath(inputStream, 0, 0);
			
			Map<Integer, String> cols = new HashMap<Integer, String>();
			Set<Integer> keySet = cols.keySet();
			if(data.size() >= 1){
				String[] row = data.get(0);
				for(int i=0; i<row.length; i++){
					String ptn = "$" + (i + 1);
					if(pattern.contains(ptn)) cols.put(i, ptn);
				}
			}
			
			StringBuffer result = new StringBuffer();
			for(String[] row : data){
				String col = pattern;
				Iterator<Integer> iterator = keySet.iterator();
				for(;iterator.hasNext();){
					int next = iterator.next();
					col = col.replace(cols.get(next), row[next]);
				}
				result.append(col + spilt);
				if(isEnter) result.append("\n");
			}
			
			return StringUtils.removeEnd(result.toString(), spilt + (isEnter ? "\n" : ""));
		}
	}
	
	@ResponseBody
	@RequestMapping(Q + "/tel")
	public String queryPhone(HttpServletRequest req){
		String phone = req.getParameter("tel");
		JSONObject result = ShowApiUtils.queryPhone(phone);
		return result.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(Q + "/ip")
	public String queryIP(HttpServletRequest req){
		String ip = req.getParameter("ip");
		JSONObject result = ShowApiUtils.queryIP(ip);
		return result.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(Q + "/id")
	public String queryID(HttpServletRequest req){
		String id = req.getParameter("id");
//		JSONObject result = ApiStoreUtils.queryCard(id);
		JSONObject result = ShowApiUtils.queryCard(id);
		return result.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(Q + "/domain")
	public String queryDomain(HttpServletRequest req){
		String domain = req.getParameter("domain");
		String[] item = domain.split(".");
		String suffix = item.length <= 1 ? "com" : item[1];
		JSONObject result = ApiStoreUtils.queryDomain(domain, suffix);
		return result.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(Q + "/wx")
	public String queryWX(HttpServletRequest req){
		String num = req.getParameter("num");
		String isRand = req.getParameter("isRand");
		boolean rand = true;
		if(StringUtils.isNotEmpty(isRand)){
			rand = Boolean.parseBoolean(isRand);
		}
		JSONArray result = ShowApiUtils.queryWX(num, rand);
		System.out.println(JSON.toJSONString(result, true));
		return result.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(Q + "/guess")
	public String queryGuess(HttpServletRequest req){
		JSONObject result = ShowApiUtils.queryGuess();
		return result.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(Q + "/sister")
	public String querySister(HttpServletRequest req){
		String page = req.getParameter("page");
		String type = req.getParameter("type");
		String title = req.getParameter("title");
		JSONObject result = ShowApiUtils.querySister(page, type, title);
		System.out.println(JSON.toJSONString(result, true));
		return result.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(Q + "/history")
	public String queryHistory(HttpServletRequest req){
		JSONObject result = ShowApiUtils.queryHistory();
		System.out.println(JSON.toJSONString(result, true));
		return result.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(Q + "/trans")
	public String queryTrans(HttpServletRequest req){
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		String q = req.getParameter("q");
		JSONObject result = new JSONObject();
		if(q.contains(" ")){
			result = ApiOpenUtils.queryTrans(from, to, q);
		}else {
			result = ApiOpenUtils.queryDicts(from, to, q);
		}
		System.out.println(JSON.toJSONString(result, true));
		return JSON.toJSONString(result, true);
//		return result.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(Q + "/chinese")
	public String queryChinese(HttpServletRequest req){
		String data = req.getParameter("data");
		String type = req.getParameter("type");
		return MyOpenUtils.queryChinese(data, type);
	}
	
	@ResponseBody
	@RequestMapping(Q + "/date")
	public String queryDate(HttpServletRequest req){
		String date = req.getParameter("date");
		String pattern = req.getParameter("pattern");
		if(!NumberUtils.isNumber(pattern)){
			pattern = "-1";
		}
		
		JSONArray result = MyOpenUtils.queryDate(date, Integer.parseInt(pattern));
		return result.toJSONString();
	}
}
