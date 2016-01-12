package com.webapp.utils.code;

import java.util.Arrays;

import org.junit.Test;

import com.webapp.template.utils.MybatisGenerator;

public class CodeUtilsTest {

	@Test
	public void main() {
//		CodeUtils.viewProp();

//		CodeUtils.buildByTable("build_dev.properties", new String[]{"test_table"});

		MybatisGenerator.buildByTable("build_dev.properties", Arrays.asList("aic_data"));
//		MybatisGenerator.buildByTable("build_dev.properties", "linux");

    }

}
