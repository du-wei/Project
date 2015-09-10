package com.webapp.utils.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.webapp.utils.random.RandomUtils;

public class ModelUtils {

	public static JsonType getJsonType() {
		JsonType type = new JsonType();
		type.setJkDouble(22.70000);
		type.setJkInt(10);
		type.setJkLong(123);
		type.setJkBool(true);
		type.setStr("str");
		type.setJkDate(new Date());
		type.setList(null);
		type.setMap(null);
		type.setSet(null);
		return type;
	}

	public static JavaType getJavaType() {
		JavaType type = new JavaType();
		type.setJkByte(new Byte("1"));
		type.setJkChar('a');
		type.setJkDouble(22.70000);
		type.setJkFloat(11.0f);
		type.setJkInt(10);
		type.setJkLong(123);
		type.setJkShort((short)2);
		type.setJkBool(true);
		type.setStr("str");

		type.setList(null);
		type.setMap(null);
		type.setSet(null);
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
