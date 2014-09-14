package com.webapp.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

public class ResultAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private JSONObject jsonObject;

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public String result() {
		String type = ServletActionContext.getRequest().getParameter("type");
		return type;
	}

	public void result1() throws Exception { // 返回json
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		JSONObject json1 = new JSONObject();
		json1.put("name", "king");
		pw.print(json1.toString());
		pw.flush();
		pw.close();
	}

	public void result3() throws Exception { // 返回json 适合同步
		HttpServletRequest req = ServletActionContext.getRequest();
		JSONObject json1 = new JSONObject();
		json1.put("name", "king");
		req.setAttribute("json", json1);
	}

	public String result2() { // 返回json
		jsonObject = new JSONObject();
		jsonObject.put("name", "king");
		jsonObject.put("id", "king");
		jsonObject.put("age", "king");
		return "json";
	}

}
