package com.webapp.action;

import org.junit.Test;

import com.webapp.base.RunnerWeb;
import com.webapp.modules.ContextPropsUtils;

public class ContextPropTest extends RunnerWeb {
	
	@Test
	public void name() throws Exception {
		String property = ContextPropsUtils.get().getProperty("evictionPolicyClassName");
		System.out.println(property);
    }
}
