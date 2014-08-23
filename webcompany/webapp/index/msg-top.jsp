<%@ page language="java" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>msg-top.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body>
<div align="center">
  	<img src="images/welcome.jpg" alt="MessageBoard Logo" width="750" height="135">
	<marquee direction="left" scrolldelay="100">ปถำญฤ๚สนำร ม๔ัิฑพ </marquee>
</div>
</body>
</html>
