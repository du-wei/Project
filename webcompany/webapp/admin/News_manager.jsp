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
  		<td height="30" colspan="3" class="leftbgcolor">���Ź���</td>
  	</tr>
  	<tr class="yellow" align="center">
  		<td height="30">���ű���</td>
  		<td>���ŷ���ʱ��</td>
        <td>ɾ��</td>
  	</tr>
  	<s:iterator value="listNews" var="n">
  	<tr class="white" align="center">
        <td height="30">${n.title }</td>  
        <td>${n.writeDate }</td>  
        <td><a href="admin/News_delete?news.id=${n.id }" target="main">ɾ��</a></td>  
    </tr>
    </s:iterator>
</table>
</div>
</body>
</html>
