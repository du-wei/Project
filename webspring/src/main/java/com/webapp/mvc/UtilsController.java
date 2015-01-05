package com.webapp.mvc;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.ServletContextResource;

import com.webapp.constant.Mapping;

@Controller
@RequestMapping(Mapping.ACTION)
public class UtilsController {

	@RequestMapping(Mapping.PATH)
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

	@Test
	public void test() throws Exception {
//		WebUtils.
//		RequestContextUtils
//		WebAsyncUtils
//		WebApplicationContextUtils.
		RestTemplate restTemplate = new RestTemplate();

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
