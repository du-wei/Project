<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>data</title>
</head>
<body>

<form action="/data/upload" method="post" enctype="multipart/form-data">
	file <input type="file" name="file"/> <br/>
	desc <input type="text" name="desc"/>
	<input type="submit" value="sub"/>
</form>

<form action="/data/body" method="post">
	json <input type="text" name="json"/>
	<input type="submit" value="sub"/>
</form>

<form action="/data/convert" method="post">
	student <input type="text" name="student" value="1-no1-hello"/>
	<input type="submit" value="sub"/>
</form>

</body>
</html>