/**
 * @Title: JsonAction.java
 * @Package com.webapp.action
 * @Description: TODO 描述
 * @author king chenglong@coweibo.cn
 * @date 2013-1-24 下午4:11:22
 * @version V1.0
 */
package com.webapp.action;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName: JsonAction.java
 * @Package com.webapp.action
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2013-1-24 下午4:11:22
 * @version V1.0
 */
public class JsonAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	public String loadJson() {
		List<Integer> lists = Arrays.asList(2, 1, 5, 4);

		// 集合比较
		// Collections.sort(lists, (a, b) -> a.compareTo(b));
		System.out.println(lists);
		// JsonEntity = new JsonEntity();
		// json.setId("1");
		// json.setName("king");
		// json.setAge("18");
		ServletActionContext.getRequest().setAttribute("name", "king");
		return SUCCESS;
	}

	public void temp() throws Exception {
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		JSONArray array = new JSONArray();
		for (int i = 0; i < 10; i++) {
			JSONObject object = new JSONObject();
			object.put("name", "kings");
			array.add(object);
		}
		pw.print(array);
		pw.flush();
		pw.close();

	}

}
