<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<%@page import="com.accp.entity.User" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	request.setCharacterEncoding("gbk");
	List msgList = (List) application.getAttribute("msgList");
	String msg = request.getParameter("msg");
	User user = (User) session.getAttribute("user");

	if (msgList == null || msgList.size() == 0) {
		msgList = new ArrayList();
		application.setAttribute("msgList",msgList);
	}
	if(msg != null && !"".equals(msg)){
		msgList.add("<font color='blue'>"+user.getUserName()+"&nbsp;<b>หตฃบ</b></font><br />"+ msg);
	}
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>index.jsp</title>
  <meta http-equiv="refresh" content="3">
</head>

<body style="border:0px;">
<table>
<s:iterator value="#application.msgList" var="m">
	<tr>
		<td valign="top"><img src="images/iboy.gif" ></td><td>${m }</td>
	</tr>
</s:iterator>
<s:append var="">
	
</s:append>
</table>
</body>
</html>
