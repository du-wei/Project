package com.webapp.action;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.webapp.base.SpringWebRunner;
import com.webapp.mvc.ResultController;
import com.webapp.utils.ReqConst;

/**
 * 	测试普通控制器 
 *	spring mvc ResultController test
 */
public class ResultControllerTest extends SpringWebRunner {
	
	////http://jinnianshilongnian.iteye.com/blog/2004660

	@Test
	public void printWriter() throws Exception{
		
		MvcResult result = mvc.perform(get(ReqConst.RESULT_BASE + ReqConst.PRINTWRITER))
			.andExpect(handler().handlerType(ResultController.class))
			.andExpect(forwardedUrl(null))
//			.andExpect(model().hasNoErrors())
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void sourcePath() throws Exception{
		MvcResult result = mvc.perform(get(ReqConst.RESULT_BASE + ReqConst.SOURCEPATH))
			.andExpect(forwardedUrl(null))
			.andExpect(status().isOk())
			.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void list() throws Exception{
		MvcResult result = mvc.perform(get(ReqConst.RESULT_BASE + ReqConst.LIST))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("studentList"))
			.andDo(print())
			.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void map() throws Exception{
		MvcResult result = mvc.perform(get(ReqConst.RESULT_BASE + ReqConst.MAP))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void attr() throws Exception{
		MvcResult result = mvc.perform(get(ReqConst.RESULT_BASE + ReqConst.ATTR))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void body() throws Exception{
		MvcResult result = mvc.perform(get(ReqConst.RESULT_BASE + ReqConst.BODY))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
}
