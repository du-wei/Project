package com.webapp.action;

import java.util.Properties;

import org.junit.Test;

import com.webapp.base.RunnerWeb;
import com.webapp.utils.WebPropsResolver;
import com.webapp.utils.WebPropsUtils;

public class WebPropTest extends RunnerWeb {

	@Test
	public void getProps() throws Exception {
		Properties property = WebPropsUtils.getProp("propertySouorceConfigurer");
		System.out.println(property);
    }
	
	@Test
	public void getProp() throws Exception {
		String property = WebPropsUtils.get("driver");
		System.out.println(property);
    }
	
	@Test
	public void viewProp() throws Exception {
		WebPropsUtils.viewProp();
    }
}
