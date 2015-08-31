package com.webapp.utils.code;

import org.junit.Test;

import com.webapp.template.utils.CodeUtils;

public class CodeUtilsTest {
	
	@Test
	public void main() {
//		CodeUtils.viewProp();
		
//		CodeUtils.buildByTable("build_dev.properties", new String[]{"test_table"});
		
		CodeUtils.buildByTable("build_dev.properties", "com", "user");
    }
	
}
