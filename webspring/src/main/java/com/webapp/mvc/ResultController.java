package com.webapp.mvc;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.ServletContextResource;

import com.webapp.utils.ReqConst;
import com.webapp.utils.model.ModelUtils;
import com.webapp.utils.model.Student;

/**
 *	spring mvc return result controller
 */
@Controller
@RequestMapping(ReqConst.RESULT_BASE)
public class ResultController {

	@RequestMapping(ReqConst.PRINTWRITER)
	public void printWriter(HttpServletResponse resp) throws Exception {
		PrintWriter pw = resp.getWriter();
		pw.write("hello");
		pw.flush();
	}

	@RequestMapping(ReqConst.SOURCEPATH)
	public void sourcePath(HttpServletRequest req, OutputStream os)
			throws Exception {
		Resource r1 = new ClassPathResource("/");
		Resource r2 = new FileSystemResource("/");
		Resource r3 = new ServletContextResource(req.getServletContext(), "/webspring");
		Resource r4 = new UrlResource("http://www.baidu.com/img/bdlogo.gif");

		System.out.println(r1.getURI().getPath().toString()); // webspring/WEB-INF/classes/
		System.out.println(r2.getURI().getPath().toString()); // /C:/
		System.out.println(r3); 							// /localhost/webspring/
		System.out.println(r4.getURI().getPath().toString());
	}

	@RequestMapping(ReqConst.LIST)
	public List<Student> list() {
		return ModelUtils.getStuList(5);
	}

	@ResponseBody
	@RequestMapping(ReqConst.MAP)
	public Map<String, Student> map() {
		Map<String, Student> map = new HashMap<String, Student>();
		map.put("key", ModelUtils.getStu());
		return map;
	}

	@RequestMapping(ReqConst.ATTR)
	public @ModelAttribute Student attr() {
		return ModelUtils.getStu();
	}

	@RequestMapping(ReqConst.BODY)
	public JSONObject body() {
		JSONObject object = JSONObject.fromObject(ModelUtils.getStu());
		return object;
	}

	@Test
	public void test() throws Exception {
		RestTemplate restTemplate = new RestTemplate();

		byte[] resp = restTemplate.postForObject(
				"http://localhost:8080/webspring/result/image2", null,
				byte[].class);
		Resource r2 = new FileSystemResource("F:/ok.jpg");
		FileCopyUtils.copy(resp, r2.getFile());
	}
	
	@ResponseBody
	@RequestMapping("/image2")
	// 输出的二进制流
	public byte[] print2(HttpServletRequest req) throws Exception {
		Resource res = new ServletContextResource(req.getServletContext(),
				"images/monky.jpg"); // 读取类路径下的图片文件
		byte[] images = FileCopyUtils.copyToByteArray(res.getInputStream());
		return images;
	}

}
