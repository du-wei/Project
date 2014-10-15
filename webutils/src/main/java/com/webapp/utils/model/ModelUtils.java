package com.webapp.utils.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.webapp.utils.random.RandomUtils;

public class ModelUtils {

	public static JavaType getJavaType() {
		JavaType type = new JavaType();
		type.setBbyte(new Byte("byte"));
		type.setBchar('a');
		type.setBdouble(22.70000);
		type.setBfloat(11.0f);
		type.setBint(10);
		type.setBlong(123);
		type.setBshort((short)2);
		type.setBbool(true);
		type.setStr("str");
		return type;
	}

	public static Student getStu() {
	    Student student = new Student();
	    student.setId(RandomUtils.nextInt());
	    student.setStuNo(RandomUtils.str(5));
	    student.setName(RandomUtils.str(5));
	    student.setCard(RandomUtils.str(18));
	    student.setSex(true);
	    student.setBirthday(new Date());
	    student.setAge((short)1);
	    student.setAddress(RandomUtils.str(15));
	    student.setNation("中国");
	    student.setPolitic("");
	    student.setSpecialty("compute");
	    student.setMoney(11.0f);
	    student.setWeight(22.01);
	    student.setHeight(22.00);
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
