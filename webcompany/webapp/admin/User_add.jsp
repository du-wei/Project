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
<form action="admin/User_add?user.status=1" method="post" name="myform">
  <table width="95%" height="180" border="0" cellpadding="0" cellspacing="0">
  	<tr>
  		<td colspan="2" class="leftbgcolor">����Ա �� ���</td>
  	</tr>
  	<tr>
  		<td width="38%" class="yellow">�� �� Ա �� �ƣ�</td>
  		<td width="62%" class="white"><input type="text" name="user.userName" id="username"/></td>
  	</tr>
  	<tr>
  		<td class="yellow">�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�룺</td>
  		<td class="white"><input type="text" name="user.password" id="password"/></td>
  	</tr>
  	<tr>
  		<td class="yellow">ȷ &nbsp;�� &nbsp;�� &nbsp;�룺</td>
  		<td class="white"><input type="text" name="repwd" id="repwd"/></td>
  	</tr>
  	<tr>
  		<td height="35" class="yellow">&nbsp;</td>
  		<td height="35" class="white"><input type="submit" value="�ύ"/> 
  		&nbsp;&nbsp;&nbsp;  
  		<input type="reset" value="����"/></td>
  	</tr>
  </table>
</form>
</div>
</body>
</html>
