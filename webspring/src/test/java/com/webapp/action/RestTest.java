package com.webapp.action;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.webapp.base.RunnerWeb;
import com.webapp.constant.Mapping;

public class RestTest extends RunnerWeb {
	
	@Test
    public void rest_name() throws Exception {
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.REST + "/name/1"))
		    .andExpect(MockMvcResultMatchers.status().isOk())
//		    .andDo(MockMvcResultHandlers.print())
		    .andReturn();
	    
	    System.out.println(result.getResponse().getContentAsString());
    }
	
	@Test
    public void rest() throws Exception {
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.REST + "/name"))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andReturn();
	    
	    System.out.println(result.getResponse().getContentAsString());
    }
	
	@Test
    public void ant_1() throws Exception {
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.REST + "/ant/namep"))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andReturn();
	    
	    System.out.println(result.getResponse().getContentAsString());
    }
	
	@Test
    public void ant_n() throws Exception {
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.REST + "/ant/pwdok"))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andReturn();
	    
	    System.out.println(result.getResponse().getContentAsString());
    }
	
	@Test
    public void ant_2n() throws Exception {
	    MvcResult result = mvc.perform(MockMvcRequestBuilders.get(Mapping.REST + "/ant/name/jjj/2n"))
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andReturn();
	    
	    System.out.println(result.getResponse().getContentAsString());
    }
	
}
