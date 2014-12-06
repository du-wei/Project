package com.webapp.mvc;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.webapp.constant.Mapping;
import com.webapp.utils.model.ModelUtils;
import com.webapp.utils.model.Student;

/**
 *	spring mvc return result controller
 */
@Controller
@RequestMapping(Mapping.BASE)
public class ResultController {

	//所有方法之前执行，并将结果放到模型中
	@ModelAttribute(Mapping.ATTR)
	public String befor() {
		return "modelAttr";
	}
	
	@RequestMapping(Mapping.OUT)
	public void printWriter(HttpServletResponse resp) throws Exception {
		PrintWriter pw = resp.getWriter();
		pw.write("hello");
		pw.flush();
	}
	
	@RequestMapping(Mapping.STR)
	@ResponseBody
	public String str(HttpServletResponse resp) throws Exception {
		return "string";
	}
	
	@RequestMapping(Mapping.OBJ)
	@ResponseBody
	public Student obj() {
		return ModelUtils.getStu();
	}
	
	@RequestMapping(Mapping.JSON)
	@ResponseBody
	public JSONObject json() {
		return JSON.parseObject(JSON.toJSONString(ModelUtils.getStu()));
	}

	@RequestMapping(Mapping.LIST)
	@ResponseBody
	public List<Student> list() {
		return ModelUtils.getStuList(5);
	}

	@RequestMapping(Mapping.MAP)
	@ResponseBody
	public Map<String, Student> map() {
		Map<String, Student> map = new HashMap<String, Student>();
		map.put("key", ModelUtils.getStu());
		return map;
	}

}
