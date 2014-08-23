<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>login</title>

<script type="text/javascript" src="common/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#link").click(function(){
		return false;
	});

});
</script>
</head>
<body>
<page:applyDecorator name="panel" page="panel.jsp"/>
<hr/>
login xxxxx

${param.key }


<table>
	<tr>
		<td>1</td>
		<td>2</td>
		<td>3</td>
	</tr>
	<tr>
		<td>a</td>
		<td>b</td>
		<td>c</td>
	</tr>
</table>

<a href="login.jsp" id="link" onclick="alert('xxxss');">链接</a>

<form action="login.jsp">
	<input type="text" id="name" name="name" value="" />
	<input type="text" id="pwd" name="pwd" value="" />
	<input type="submit" value="submit" />
</form>

</body>
</html>