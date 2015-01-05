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
public class RunnerWeb {

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
	
}
