package com.webapp.utils.db;

import com.webapp.utils.model.Student;

public class MybatiseTest {

	public static void main(String[] args) {
		MybatisUtils.propSet(Student.class);
		MybatisUtils.propConst(Student.class);
		MybatisUtils.insertCols(Student.class);
		MybatisUtils.insertVals(Student.class);
		MybatisUtils.searchCols(Student.class);
		MybatisUtils.updateCols(Student.class);

	}

}
