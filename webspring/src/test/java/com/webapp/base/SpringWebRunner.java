package com.webapp.base;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.webapp.constant.Mapping;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class SpringWebRunner {

	@Autowired
	protected WebApplicationContext wac;
	@Autowired
	protected MockHttpServletRequest req;
	@Autowired
	protected MockHttpServletResponse resp;
	@Autowired
	protected MockServletContext context;

	protected MockMvc mvc;

	@Before
	public void runBefore(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void datasource() {
//	    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		BasicDataSource data = (BasicDataSource)wac.getBean("dataSource");
		System.out.println(data.getUrl());
		BasicDataSource data2 = (BasicDataSource)wac.getBean("dataSource_new");
		System.out.println(data2.getUrl());
	    
    }
	
	@Test
    public void get_req() throws Exception {
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.TEST_BASE+"get_req"))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andReturn();
    }
	
	@Test
    public void json() throws Exception {
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.TEST_BASE+Mapping.JSON))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andReturn();
	    
	    Thread.sleep(10000000);
	    System.out.println(result.getResponse().getContentAsString());
    }
	
	@Test
    public void redirect_mav() throws Exception {
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.TEST_BASE+Mapping.REDIRECT_MAV))
		    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		    .andExpect(MockMvcResultMatchers.redirectedUrl(Mapping.pub_view+"?key=val"))
		    .andReturn();
	    System.out.println(result.getResponse().getContentAsString());
    }
	
	@Test
	public void redirect_mav_rv() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.TEST_BASE+Mapping.REDIRECT_MAV_RV))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl(Mapping.pub_view))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
    public void redirect_rv() throws Exception {
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.TEST_BASE+Mapping.REDIRECT_RV))
		    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
		    .andExpect(MockMvcResultMatchers.redirectedUrl(Mapping.pub_view+"?key=val"))
		    .andReturn();
	    System.out.println(result.getResponse().getContentAsString());
    }
	
	@Test
	public void redirect_str() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.TEST_BASE+Mapping.REDIRECT_STR))
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void forward_str() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.TEST_BASE+Mapping.FORWARD_STR))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl(Mapping.pub_view))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void forward_mav() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.TEST_BASE+Mapping.FORWARD_MAV))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.forwardedUrl(Mapping.pub_view))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}


}
