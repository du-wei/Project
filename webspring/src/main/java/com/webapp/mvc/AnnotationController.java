/**
 * @Title: TestView.java
 * @Package com.webapp.view
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-5 下午4:59:34
 * @version V1.0
 */
package com.webapp.mvc;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

/**
 * @ClassName: TestView.java
 * @Package com.webapp.view
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-5 下午4:59:34
 * @version V1.0
 */

@Controller
@RequestMapping("/para")
public class AnnotationController {

	// @Autowired
	private HttpServletRequest request;

	@RequestMapping("/inde")
	public String execute() {

		return "index";
	}

	@RequestMapping("/print")
	public void print(HttpServletResponse resp) throws Exception {
		PrintWriter pw = resp.getWriter();
		pw.write("hello world");
		pw.flush();
		pw.close();
	}

	@RequestMapping("/webutil")
	public void webutil(HttpServletRequest req) {
		String id = WebUtils.findParameterValue(req, "id");
	}

	// params 1 "value" 2 "!value" 3 "param!=value" 4 {"param=value", "param2"}
	@RequestMapping(value = "/map1", method = RequestMethod.POST, params = "userId", headers = "content-type=text/*")
	public String execute1() {
		return "index";
	}

	@RequestMapping("/map2")
	public String execute2(
			@RequestParam(value = "para", required = true) int para,
			@CookieValue(value = "JESSIONID", required = false) String sessionId,
			@RequestHeader("Accept-Language") String acceptLanguage) {
		return "index";
	}

	@RequestMapping("/map3")
	public String execute3(WebRequest req, NativeWebRequest request) {
		String id = req.getParameter("id");
		return "index";
	}

	@RequestMapping("/map4")
	public String execute4(InputStream is, OutputStream os) {
		return "index";
	}

	@RequestMapping("/map5")
	public void execute5(Reader reader, Writer writer) throws Exception {
		writer.write("hello");
		writer.flush();
	}

	@RequestMapping("/map6")
	public void execute6(@RequestBody String reqBody) throws Exception {
		System.out.println("enter");
		System.out.println(reqBody); // id=id&user=name
	}

	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("user", "name");
		form.add("id", "id");
		restTemplate.postForLocation(
				"http://localhost:8080/webspring/para/map6", form);

	}

}
