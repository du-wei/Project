/**
 * @Title: TestXmlView.java
 * @Package com.webapp.view
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-6 下午2:56:05
 * @version V1.0
 */
package com.webapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.constant.Mapping;

/**
 * @ClassName: TestXmlView.java
 * @Package com.webapp.view
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-6 下午2:56:05
 * @version V1.0
 */
@Controller
@RequestMapping(Mapping.REST)
public class RestController {

	@ResponseBody
	@RequestMapping("/{name}")
	public String rest(@PathVariable String name) {
		System.out.println(name);
		return name;
	}

	@ResponseBody
	@RequestMapping("/{name}/{id}")
	public String rest(@PathVariable String name, @PathVariable String id) {
		return name + '-' + id;
	}
	
	
	//Ant风格
	//? 	一个字符
	//*		多个字符
	//**	多层路径
	@ResponseBody
	@RequestMapping("/ant/name?")
	public String ant_1() {
		return "ant/name?";
	}
	
	@ResponseBody
	@RequestMapping("/ant/pwd*")
	public String ant_n() {
		return "ant/pwd*";
	}
	
	@ResponseBody
	@RequestMapping("/ant/**/2n")
	public String ant_2n() {
		return "ant/**/2n";
	}

}
