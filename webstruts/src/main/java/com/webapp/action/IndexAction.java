/**   
 * @Title: IndexAction.java 
 * @Package com.webapp.action 
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn 
 * @date 2013-2-25 下午4:30:16 
 * @version V1.0   
 */
package com.webapp.action;

import java.io.IOException;
import java.io.PrintWriter;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName: IndexAction.java
 * @Package com.webapp.action
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-2-25 下午4:30:16
 * @version V1.0
 */
public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String execute() {
		return SUCCESS;
	}

	public String test() throws Exception {
		PrintWriter pw = ServletActionContext.getResponse().getWriter();
		JSONObject object = new JSONObject();
		object.put("name", "king");
		pw.print(object.toString());
		pw.flush();
		return null;
	}

}
