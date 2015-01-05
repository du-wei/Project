package com.webapp.action;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.webapp.base.RunnerWeb;
import com.webapp.constant.Mapping;

/**
 * 	测试普通控制器
 *	spring mvc ResultController test
 */
public class ResultTest extends RunnerWeb {

	////http://jinnianshilongnian.iteye.com/blog/2004660

	@Test
	public void printWriter() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.RESULT + "/out"))
			    .andExpect(MockMvcResultMatchers.status().isOk())
			    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void str() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.RESULT + "/str"))
			    .andExpect(MockMvcResultMatchers.status().isOk())
			    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void obj() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.RESULT + "/obj"))
			    .andExpect(MockMvcResultMatchers.status().isOk())
			    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void json() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.RESULT + "/json"))
			    .andExpect(MockMvcResultMatchers.status().isOk())
			    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	public void list() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.RESULT + "/list"))
			    .andExpect(MockMvcResultMatchers.status().isOk())
			    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

	@Test
	public void map() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.RESULT + "/map"))
			    .andExpect(MockMvcResultMatchers.status().isOk())
			    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void entity() throws Exception{
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.RESULT + "/entity"))
			    .andExpect(MockMvcResultMatchers.status().isOk())
			    .andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

}
