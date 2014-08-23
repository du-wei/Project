<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>left.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css">
<script type="text/javascript">
function $(op){
	return document.getElementById(op);
}
function show(op){
	if($(op).style.display === 'none'){
		$(op).style.display = 'block';
	}else{
		$(op).style.display = 'none';
	}
}
</script>
</head>
<body class="bgcolor">
<table width="110%" class="center">
	<tr>
		<td>系统后台管理面板</td>
	</tr>
	<tr>
		<td><a href="userAction!exitAdmin" target="_parent">[安全退出]</a><a href="index" target="_top">[返回首页]</a></td>
	</tr>
	<tr class="hand">
		<td><a href="javascript:show('system');">＞系统管理</a></td>
	</tr>
	<tr id="system" style="display:none;">
		<td>&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/User_add.jsp" target="main">添加管理员</a><br>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/User_manager" target="main">用户管理</a>
		</td>
	</tr>
	<tr class="hand">
		<td><a href="javascript:show('news');">＞新闻管理</a></td>
	</tr>
	<tr id="news" style="display:none;">
		<td>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/News_add.jsp" target="main">添加新闻</a><br>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/News_manager" target="main">管理新闻</a>
		</td>
	</tr>
	<tr class="hand">
		<td><a href="javascript:show('product');">＞商品管理</a></td>
	</tr>
	<tr id="product" style="display:none;">
		<td>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/Product_add.jsp" target="main">添加商品</a><br>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/Product_manager" target="main">管理商品</a>
		</td>
	</tr>
</table> 	
</body>
</html>
