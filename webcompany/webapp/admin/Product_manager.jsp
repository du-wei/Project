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
  <table width="95%" border="0" cellpadding="0" cellspacing="0" class="center">
  	<tr class="leftbgcolor">
  	  <td height="30" colspan="7" class="left">添加商品信息</td>
  	</tr>
    <tr class="yellow">
      <td height="30">商品编号</td>
      <td>商品名称</td>
      <td>商品商标</td>
      <td>商品型号</td>
      <td>商品价格</td>
      <td>修改</td>
      <td>删除</td>
    </tr>
    <s:iterator value="listProduct" var="p">
	    <tr class="white">
	      <td height="30">${p.serialNumber }</td>
	      <td>${p.name }</td>
	      <td>${p.brand }</td>
	      <td>${p.model }</td>
	      <td>${p.price }</td>
	      <td><a href="admin/Product_managerUpdate?id=${p.id }">修改</a></td>
	      <td><a href="admin/Product_delete?product.id=${p.id }">删除</a></td>
	    </tr>
    </s:iterator>
  </table>
</div>
</body>
</html>
