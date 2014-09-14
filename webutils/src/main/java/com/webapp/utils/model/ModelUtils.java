package com.webapp.utils.model;


import java.util.ArrayList;
import java.util.List;

import com.webapp.utils.math.MathUtils;
import com.webapp.utils.random.RndUtils;

public class ModelUtils {

	public static Student getStu() {
	    Student student = new Student();
	    student.setId(MathUtils.nextInt());
	    student.setName(RndUtils.getStr(10));
	    student.setAge(MathUtils.nextInt(10, 20));
	    student.setAddress(RndUtils.rndStr(15));
	    student.setCard(RndUtils.rndStr(18));
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
