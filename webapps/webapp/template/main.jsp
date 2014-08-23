<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="basePath.jsp" flush="true"/>
<title><decorator:title default="default title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" />
	<decorator:head />
</head>
<body>
	<div id="container">
		<div class="header"></div>
	    <div class="content">
		    <decorator:body />
		</div>
		
		<div class="footer">hello world</div>
	</div>
</body>
</html>