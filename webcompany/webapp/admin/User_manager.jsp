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
  		<td colspan="3" class="leftbgcolor" height="30">�û�����</td>
  	</tr>
  	<tr class="yellow" align="center">
  		<td height="30">�û���</td>
  		<td width="40%">��ݴ���</td>
        <td width="30%">ɾ��</td>
  	</tr>
  	<s:iterator value="listUser" var="u">
  	<tr class="white" align="center">
        <td height="30">${u.userName }</td>  
        <td>${u.status }</td>  
        <td><a href="admin/User_delete?user.id=${u.id }" target="main">ɾ��</a></td> 
    </tr>
  	</s:iterator>
  </table>
</div>
</body>
</html>