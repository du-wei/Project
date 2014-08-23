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
<form action="admin/Product_update" method="post" name="myform">
  <table width="95%" height="380" border="0" cellpadding="0" cellspacing="0" class="table">
  <colgroup class="yellow"></colgroup>
  <colgroup class="white"></colgroup>
  	<tr>
  	  <td colspan="2" class="leftbgcolor">添加商品信息</td>
  	</tr>
    <tr>
      <td width="30%">商品编号：</td>
      <td><input name="product.serialNumber" type="text" size="30" value="${product.serialNumber }"/></td>
    </tr>
  	<tr>
      <td>商品名称：</td>
      <td><input name="product.name" type="text" size="30" value="${product.name }"/></td>
    </tr>
  	<tr>
      <td>商品商标：</td>
      <td><input name="product.brand" type="text" size="30" value="${product.brand }"/></td>
    </tr>
  	<tr>
      <td>商品型号：</td>
      <td><input name="product.model" type="text" size="30" value="${product.model }"/></td>
    </tr>
  	<tr>
      <td>商品价格：</td>
      <td><input name="product.price" type="text" size="30" value="${product.price }"/></td>
    </tr>
  	<tr>
      <td>商品图片：</td>
      <td><input name="product.picture" type="text" size="30" value="${product.picture }"/></td>
    </tr>
  	<tr>
      <td height="100">商品介绍：</td>
      <td><textarea name="product.description" cols="40" rows="5">${product.description }</textarea></td>
    </tr>
  	<tr>
      <td>&nbsp;</td>
      <td>
		  <input type="submit" value="提交"/> &nbsp;&nbsp;  
		  <input type="reset" value="重置"/>
	  </td>
    </tr>
  </table>
</form>
</div>
</body>
</html>
