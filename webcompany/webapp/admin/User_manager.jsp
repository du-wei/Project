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
  <table width="95%" border="0" cellpadding="0" cellspacing="0">
  	<tr>
  		<td colspan="3" class="leftbgcolor" height="30">用户管理</td>
  	</tr>
  	<tr class="yellow" align="center">
  		<td height="30">用户名</td>
  		<td width="40%">身份代码</td>
        <td width="30%">删除</td>
  	</tr>
  	<s:iterator value="listUser" var="u">
  	<tr class="white" align="center">
        <td height="30">${u.userName }</td>  
        <td>${u.status }</td>  
        <td><a href="admin/User_delete?user.id=${u.id }" target="main">删除</a></td> 
    </tr>
  	</s:iterator>
  </table>
</div>
</body>
</html>