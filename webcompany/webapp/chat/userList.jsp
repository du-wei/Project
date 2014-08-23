<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
  <base href="<%=basePath%>">
  <title>index.jsp</title>

</head>
 
<body style="border:0px;">
<table>
<s:iterator value="#application.users" var="u">
<tr>
	<td>${u.userName }</td>
</tr>
</s:iterator>
</table>

</body>
</html>
