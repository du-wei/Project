package com.webapp.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class ServletUtils {

	/*-----------------------------response------------------------------------*/
	public static void out(Object object) {
		out(object, "text/plain", "UTF-8");
	}

	public static void out(Object object, String contentType) {
		out(object, contentType, "UTF-8");
	}

	public static void out(Object object, String encoding, String contentType) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(contentType);
		response.setCharacterEncoding(encoding);
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			System.out.println("【工具类打印出错！】");
			e.printStackTrace();
		}
		pw.print(object);
		pw.flush();
		pw.close();
	}

	/*-----------------------------request-------------------------------------*/
	public static void testRequest(HttpServletRequest request) {
		p("Protocol: " + request.getProtocol() + "<br>");
		p("Scheme: " + request.getScheme() + "<br>");
		p("Server Name: " + request.getServerName() + "<br>");
		p("Server Port: " + request.getServerPort() + "<br>");
		p("Remote Addr: " + request.getRemoteAddr() + "<br>");
		p("Remote Host: " + request.getRemoteHost() + "<br>");
		p("Character Encoding: " + request.getCharacterEncoding() + "<br>");
		p("Content Length: " + request.getContentLength() + "<br>");
		p("Content Type: " + request.getContentType() + "<br>");
		p("Auth Type: " + request.getAuthType() + "<br>");
		p("HTTP Method: " + request.getMethod() + "<br>");
		p("Path Info: " + request.getPathInfo() + "<br>");
		p("Path Trans: " + request.getPathTranslated() + "<br>");
		p("Query String: " + request.getQueryString() + "<br>");
		p("Remote User: " + request.getRemoteUser() + "<br>");
		p("Session Id: " + request.getRequestedSessionId() + "<br>");
		p("Request URI: " + request.getRequestURI() + "<br>");
		p("Servlet Path: " + request.getServletPath() + "<br>");
		p("Accept: " + request.getHeader("Accept") + "<br>");
		p("Host: " + request.getHeader("Host") + "<br>");
		p("Referer : " + request.getHeader("Referer") + "<br>");
		p("Accept-Language : " + request.getHeader("Accept-Language") + "<br>");
		p("Accept-Encoding : " + request.getHeader("Accept-Encoding") + "<br>");
		p("User-Agent : " + request.getHeader("User-Agent") + "<br>");
		p("Connection : " + request.getHeader("Connection") + "<br>");
		p("Cookie : " + request.getHeader("Cookie") + "<br>");
	}

	public static void p(String str) {
		System.out.println(str);
	}

}
