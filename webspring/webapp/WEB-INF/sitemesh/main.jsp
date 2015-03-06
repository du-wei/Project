<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title'/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<sitemesh:write property='head'/>
</head>
<body>
	<div id="container">
		<div class="header">ffffff</div>
	    <div class="content">
		    <sitemesh:write property='body'/>
		</div>
		
		<div class="footer">hello world</div>
	</div>
</body>
</html>