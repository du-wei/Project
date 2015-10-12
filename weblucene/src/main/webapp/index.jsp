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
<script type="text/javascript" src="common/jquery-1.9.1.min.js"></script>
<script language=javascript>
$(function(){
	$("#search").on("click", function(){
		var date = {
			key : $("#key").val()
		}
		$.post("search", date).done(function(val){
			$("#show").text(val);
		});
	});
});
</script>

</head>
<body>

<input type="text" id="key" value="">
<input type="submit" id="search">
<div id="show"></div>
</body>
</html>