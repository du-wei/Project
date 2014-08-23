package com.webapp.utils.model;


import java.util.ArrayList;
import java.util.List;

import com.webapp.utils.math.MathUtils;
import com.webapp.utils.random.RandomUtils;

public class ModelUtils {
	
	public static Student getStu() {
	    Student student = new Student();
	    student.setId(MathUtils.nextInt());
	    student.setName(RandomUtils.getStr(10));
	    student.setAge(MathUtils.nextInt(10, 20));
	    student.setAddress(RandomUtils.randomStr(15));
	    student.setCard(RandomUtils.randomStr(18));
	    return student;
    }
	
	public static List<Student> getStuList(int size){
		List<Student> list = new ArrayList<>();
		for(int i=0; i<size; i++){
			list.add(getStu());
		}
		return list;
	}
	
}
