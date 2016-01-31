package com.webapp.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webapp.dao.User;
import com.webapp.utils.model.ModelUtils;
import com.webapp.utils.model.Student;

@Controller
public class SimpleTest {

	@ResponseBody
	@RequestMapping("/getStu")
	public Student get(User user){
		return ModelUtils.getStu();
	}

}
