package com.webapp.utils.db;

import com.webapp.utils.model.Student;

public class MybatiseTest {

	public static void main(String[] args) {
		String q000 = MybatisUtils.setModel(Student.class);
		System.out.println(q000);
	}

}
