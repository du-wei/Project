package com.webapp.action;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerAction extends ActionSupport {

	public void index() throws Exception {
		Configuration cfg = new Configuration();
		cfg.setClassForTemplateLoading(this.getClass(), "/ftl");
		Template temp = cfg.getTemplate("test.ftl");

		Map<String, Object> rootMap = new HashMap<>();
		rootMap.put("name", "king");

		Writer out = ServletActionContext.getResponse().getWriter();
		temp.process(rootMap, out);

	}

	public static void main(String[] args) throws Exception {
		FreemarkerAction fAction = new FreemarkerAction();
		fAction.index();
	}

}
