package com.webapp.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.env.StandardEnvironment;

import com.webapp.modules.ContextPropsUtils;

public class SpringBase extends RunnerWeb {

//	@Autowired
//	private StandardEnvironment standardEnv;
//	private MockEnvironment mockEnv;

	@Autowired
	StandardEnvironment env;

	@Test
	public void springBase() throws Exception {
		//具体应用待查
		Map<String, Object> map = new HashMap<>();
		map.put("test", "test_val");

		PropertySource<Map<String, Object>> ps = new MapPropertySource("map", map);
		System.out.println(ps.getProperty("test"));

		MutablePropertySources mps = new MutablePropertySources();
		mps.addFirst(ps);
		PropertyResolver pr = new PropertySourcesPropertyResolver(mps);
		System.out.println(pr.getProperty("test"));

//		MockEnvironment mockEnv = new MockEnvironment();
//		StandardServletEnvironment standardEnv = new StandardServletEnvironment();
		StandardEnvironment standardEnv = new StandardEnvironment();

		MutablePropertySources propertySources = env.getPropertySources();
		Iterator<PropertySource<?>> iterator = propertySources.iterator();
		for(;iterator.hasNext();){
			PropertySource<?> next = iterator.next();
			System.out.println(next.getName());
		}

		System.out.println(env.getPropertySources());

		ContextPropsUtils.viewProp();

	}

}
