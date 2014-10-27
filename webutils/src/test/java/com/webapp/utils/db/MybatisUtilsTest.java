package com.webapp.utils.db;

import org.junit.Test;

import com.webapp.utils.model.Score;

public class MybatisUtilsTest {

	@Test
    public void testName() throws Exception {
		System.out.println(MybatisUtils.sql_insert_cols(Score.class));
	    System.out.println(MybatisUtils.sql_insert_vals(Score.class));

	    System.out.println(MybatisUtils.sql_select_cols(Score.class));
	    System.out.println(MybatisUtils.const_cols(Score.class));
    }

}
