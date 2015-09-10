<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="basePath.jsp" flush="true"/>
<title><sitemesh:write property='title'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" />
	<sitemesh:write property='head'/>
</head>
<body>
	<div id="container">
		<div class="header"></div>
	    <div class="content">
		    <sitemesh:write property='body'/>
		</div>
		
		<div class="footer">hello world</div>
	</div>
</body>
</html>