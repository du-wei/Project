package com.webapp.webservice.service.cxf;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

public class CxfServletTest extends CXFNonSpringServlet {

	private static final long serialVersionUID = 1930791254280865620L;

	@Override
	public void loadBus(ServletConfig servletConfig) {
		super.loadBus(servletConfig);

		Bus bus = this.getBus();
		BusFactory.setDefaultBus(bus);
		// 获取在web.xml中配置的要发布的所有的Web服务实现类并发布Web服务
		Enumeration<String> enumeration = getInitParameterNames();
		while (enumeration.hasMoreElements()) {
			String key = enumeration.nextElement();
			String value = getInitParameter(key);
			try {
				Class clazz = Class.forName(value);
				Endpoint.publish(key, clazz.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
