<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>index</title>
</head>
<body>
spring mvc
${msg }

<form action="testFile" method="post" enctype="multipart/form-data">
	file <input type="file" name="file"/> <br/>
	desc <input type="text" name="desc"/>
	<input type="submit" value="sub"/>
</form>
</body>
</html>