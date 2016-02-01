package com.webapp.action;

import java.util.Properties;

import org.junit.Test;

import com.webapp.base.RunnerWeb;
import com.webapp.utils.spring.CtxPropsUtils;

public class WebPropTest extends RunnerWeb {

	@Test
	public void getProps() throws Exception {
		Properties property = CtxPropsUtils.getProp("propertySouorceConfigurer");
		System.out.println(property);
    }

	@Test
	public void getProp() throws Exception {
		String property = CtxPropsUtils.get("driver");
		System.out.println(property);
    }

	@Test
	public void viewProp() throws Exception {
		CtxPropsUtils.showProp();
    }
}
