package com.webapp.solr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

public class StudentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MySolr mySolr = new MySolr();

		String key = req.getParameter("key");
		if (key != null) {
			JSONArray array = mySolr.query(key);
			resp.getWriter().print(array);
		} else {
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String age = req.getParameter("age");
			String desc = req.getParameter("desc");

			mySolr.add(id, name, age, desc);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	private static final long serialVersionUID = 1L;

}
