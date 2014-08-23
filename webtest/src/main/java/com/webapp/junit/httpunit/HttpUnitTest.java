package com.webapp.junit.httpunit;

import java.util.Arrays;

import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.TableCell;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;
import com.meterware.httpunit.cookies.Cookie;
import com.meterware.httpunit.cookies.CookieJar;
import com.meterware.httpunit.cookies.CookieProperties;
import com.meterware.httpunit.scripting.ScriptingHandler;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

public class HttpUnitTest {

	@Test
	public void testScript() throws Exception {

		// HttpUnitOptions.setScriptingEnabled(false);
		// WebConversation web = new WebConversation();
		// WebRequest request = new
		// GetMethodWebRequest("http://localhost:8080/webstruts/login.jsp");
		// WebResponse response = web.getResponse(request);
		// System.out.println(response.getText());
		// WebLink webLink = response.getLinkWithID("link");
		// WebResponse resp = webLink.click();

	}

	// @Test
	public void testCookie() {
		CookieProperties.reset();
		CookieJar jar = new CookieJar(HttpUnitUtils.getCookieSource(
				"http://localhost:8080/webstruts",
				new String[] { "Reason=, path=/ ,age=12, name= george" }));
		jar.putCookie("king", "king");
		jar.putSingleUseCookie("king", "king", "localhost:8080/webstruts",
				"test/me");
		System.out.println(jar.getCookieValue("Reason"));
		System.out.println(jar.getCookieValue("name"));

		Cookie cookie = jar.getCookie("age");
		System.out.println(cookie.getExpiredTime());
	}

	public void testServlet() throws Exception {
		// 创建Servlet的运行环境
		ServletRunner sr = new ServletRunner();

		// 向环境中注册Servlet
		// sr.registerServlet("JsonAction", JsonAction.class.getName());

		// 创建访问Servlet的客户端
		ServletUnitClient sc = sr.newClient();

		WebRequest request = new GetMethodWebRequest(
				"http://localhost/InternalServlet");
		request.setParameter("pwd", "pwd");

		// 获得该请求的上下文环境
		InvocationContext ic = sc.newInvocation(request);

		// 调用Servlet的非服务方法
		// InternalServlet is = (InternalServlet)ic.getServlet();
		// is.myMethod();

		// 直接通过上下文获得request对象
		System.out.println("request中获取的内容："
				+ ic.getRequest().getParameter("pwd"));

		// 直接通过上下文获得response对象,并且向客户端输出信息
		ic.getResponse().getWriter().write("haha");

		// 直接通过上下文获得session对象，控制session对象
		// 给session赋值
		ic.getRequest().getSession().setAttribute("username", "timeson");
		// 获取session的值
		System.out.println("session中的值："
				+ ic.getRequest().getSession().getAttribute("username"));

		// 使用客户端获取返回信息，并且打印出来
		WebResponse response = ic.getServletResponse();
		System.out.println(response.getText());
	}

	// @Test
	public void testLink() throws Exception {
		WebConversation web = new WebConversation();
		WebRequest request = new GetMethodWebRequest(
				"http://localhost:8080/webstruts/login.jsp");
		WebResponse response = web.getResponse(request);

		// WebLink webLink = response.getLinkWith("链接");

		WebLink webLink = response.getLinkWithID("link");
		ScriptingHandler handler = webLink.getScriptingHandler();
		WebResponse linkResp = webLink.click();
		System.out.println(linkResp.getText());

	}

	// @Test
	public void test() throws Exception {
		WebConversation web = new WebConversation();

		WebRequest request = new GetMethodWebRequest(
				"http://localhost:8080/webstruts/login.jsp");
		request.setParameter("key", "value");

		WebResponse response = web.getResponse(request);

		// 得到最外层表格
		WebTable webTable = response.getTables()[0];

		String[][] data = webTable.asText();
		System.out.println(Arrays.toString(data));

		int colCount = webTable.getColumnCount();
		int rowCount = webTable.getRowCount();
		TableCell cell = webTable.getTableCell(1, 2);
		String text = cell.getText();
		System.out.println(colCount);
		System.out.println(rowCount);
		System.out.println(text);

		// 链接
		WebLink webLink = response.getLinkWith("链接");
		webLink.click();
		System.out.println(web.getCurrentPage());

		WebRequest clickReq = webLink.getRequest();

		// 表单
		WebForm webForm = response.getForms()[0];
		String[] formPara = webForm.getParameterNames();
		// webForm.getParameterValue("name");

		webForm.setParameter("name", "value");
		webForm.setParameter("pwd", "pwd");
		webForm.removeParameter("name");

		WebResponse webresp = webForm.submit();
		// checkbox 选中的时候为 on
		// System.out.println(response.getText());

		// web.getResponse("http://localhost:8080/webstruts/login.jsp");
	}

}
