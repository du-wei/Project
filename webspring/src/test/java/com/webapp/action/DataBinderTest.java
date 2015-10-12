package com.webapp.action;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.webapp.base.RunnerWeb;
import com.webapp.constant.Mapping;
import com.webapp.utils.model.Student;

public class DataBinderTest extends RunnerWeb {

	@Test
    public void dataBinder() throws Exception {
		Student data = new Student();
		data.setId(100);
		data.setName("data");
		data.setStuNo("stuNo");
		data.setSex(false);
		data.setCard("card");
		JSONObject json = JSON.parseObject(JSON.toJSONString(data));
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(Mapping.DATA + "/body")
				.content(json.toJSONString()).contentType(MediaType.APPLICATION_JSON))
		    .andExpect(MockMvcResultMatchers.status().isOk())
//			.andDo(MockMvcResultHandlers.print())
		    .andReturn();
	    
	    System.out.println(result.getResponse().getContentAsString());
    }
	
}
