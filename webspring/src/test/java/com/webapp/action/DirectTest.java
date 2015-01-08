package com.webapp.action;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.webapp.base.RunnerWeb;
import com.webapp.constant.Mapping;

public class DirectTest extends RunnerWeb {
	
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
