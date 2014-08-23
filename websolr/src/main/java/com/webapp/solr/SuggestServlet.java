package com.webapp.solr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

public class SuggestServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		MySolr mySolr = new MySolr();
		String sug = req.getParameter("sug");
		if (sug == null || sug.equals("")) {
			resp.getWriter().print("[]");
			return;
		}
		JSONArray array = mySolr.suggest(sug);
		resp.getWriter().print(array);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
