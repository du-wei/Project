<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>detailProduct.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body>
<div id="product">
<jsp:include page="top.jsp"></jsp:include>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" class="line" height="30">
    	<a href="newsAction">首页</a> &gt; 
    	<a href="productAction">商品</a>
    </td>
  </tr>
  <tr>
    <td width="200" valign="top"><jsp:include page="left.jsp"/></td>
    <td bgcolor="#e8e8e8"></td>
    <td class="center" valign="top">
    <div id="pDetail">
      <div class="pTable">
    		<img src="images/${product.picture}" width="120" height="100">
	      <table border="0" cellpadding="0" cellspacing="0">
	      	<s:iterator value="product" var="product">
	      	  <tr>
	      	  	<td width="20%">产品编号</td>
	      	  	<td width="30%">${product.serialNumber}</td>
	      	  	<td width="20%">产品名称</td>
	      	  	<td>${product.name}</td>
	      	  </tr>
	      	  <tr>
	      	  	<td>产品品牌</td>
	      	  	<td>${product.brand}</td>
	      	  	<td>产品型号</td>
	      	  	<td>${product.model}</td>
	      	  </tr>
	      	  <tr>
	      	  	<td>产品价格</td>
	      	  	<td>${product.price}</td>
	      	  	<td>产品介绍</td>
	      	  	<td>${product.description}</td>
	      	  </tr>
	      	</s:iterator>
	      </table>
	    </div>
    </div> 
    </td>
  </tr>
</table>
<jsp:include page="bottom.jsp"/>
</div>
</body>
</html>
