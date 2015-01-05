package com.webapp.mvc;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
@RequestMapping(Mapping.RESULT)
public class ResultController {

	@RequestMapping(Mapping.OUT)
	public void printWriter(HttpServletResponse resp) throws Exception {
		PrintWriter pw = resp.getWriter();
		pw.write("hello");
		pw.flush();
	}
	
	@ResponseBody
	@RequestMapping(Mapping.STR)
	public String str() throws Exception {
		return "string";
	}
	
	@ResponseBody
	@RequestMapping(Mapping.OBJ)
	public Student obj() {
		return ModelUtils.getStu();
	}
	
	@ResponseBody
	@RequestMapping(Mapping.JSON)
	public JSONObject json() {
		return JSON.parseObject(JSON.toJSONString(ModelUtils.getStu()));
	}

	@ResponseBody
	@RequestMapping(Mapping.LIST)
	public List<Student> list() {
		return ModelUtils.getStuList(5);
	}

	@ResponseBody
	@RequestMapping(Mapping.MAP)
	public Map<String, Student> map() {
		Map<String, Student> map = new HashMap<String, Student>();
		map.put("key", ModelUtils.getStu());
		return map;
	}
	
	@RequestMapping("entity")
	public ResponseEntity<String> output() {
		ResponseEntity<String> resp = new ResponseEntity<String>("hello", HttpStatus.OK);
	    return resp;
    }

}
