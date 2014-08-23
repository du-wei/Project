<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'zoom.jsp' starting page</title>
	<link rel="stylesheet" type="text/css" href="js/jqzoom.css">
	<script type="text/javascript" src="../../common/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.jqzoom.js"></script>
	

<script type="text/javascript">
$(document).ready(function(){
    $(".jqzoom").jqueryzoom();
});
</script>
</head>
			
<body>

<br/>
<br/>
<div class="jqzoom">
	<img src="images/shoe1_small.jpg" jqimg="images/shoe1_big.jpg" />
</div>

</body>
</html>
