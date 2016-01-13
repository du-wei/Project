package com.webapp.utils.code;

import java.util.Arrays;

import org.junit.Test;

import com.webapp.template.utils.MybatisBuilder;

public class CodeUtilsTest {

	@Test
	public void main() {
//		CodeUtils.viewProp();

//		CodeUtils.buildByTable("build_dev.properties", new String[]{"test_table"});

		MybatisBuilder.buildByTable("build_dev.properties", Arrays.asList("aic_data"));
//		MybatisGenerator.buildByTable("build_dev.properties", "linux");

    }

}
