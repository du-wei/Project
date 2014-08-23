<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>showProductList.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body class="bgcolor">
<div id="admin-table">
  <table width="95%" border="0" cellpadding="0" cellspacing="0" class="table">
  	<tr>
  		<td height="30" colspan="3" class="leftbgcolor">新闻管理</td>
  	</tr>
  	<tr class="yellow" align="center">
  		<td height="30">新闻标题</td>
  		<td>新闻发布时间</td>
        <td>删除</td>
  	</tr>
  	<s:iterator value="listNews" var="n">
  	<tr class="white" align="center">
        <td height="30">${n.title }</td>  
        <td>${n.writeDate }</td>  
        <td><a href="admin/News_delete?news.id=${n.id }" target="main">删除</a></td>  
    </tr>
    </s:iterator>
</table>
</div>
</body>
</html>
