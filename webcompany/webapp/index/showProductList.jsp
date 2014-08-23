<%@ page language="java" pageEncoding="UTF-8"%>
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
  <script type="text/javascript" src="css/company.js"></script>
</head>
<body>
<div id="product">
<jsp:include page="top.jsp"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" class="line" height="30">
    	<a href="index">首页</a> &gt; &nbsp;
    	<a href="productAction">商品</a>
    </td>
  </tr>
  <tr>
    <td width="200" valign="top"><jsp:include page="left.jsp"/></td>
    <td bgcolor="#e8e8e8">&nbsp;</td>
    <td class="center">
		<s:iterator value="listProduct" var="pro">
	  <div id="product-list">
   	  <div class="pTable">
	  	  <a href="productAction!getById?id=${pro.id}">
	  	  	<img src="images/${pro.picture}" height="80" width="100">
	  	  </a>
   			<table border="0" cellpadding="0" cellspacing="0">
   			  <tr>
   					<td width="20%" class="bg">产品名称</td>
   					<td width="30%">${pro.name}</td>
   					<td width="20%" class="bg">产品品牌</td>
   					<td>${pro.brand}</td>
   			  </tr>
   			  <tr>
   					<td class="bg">产品型号</td>
   					<td>${pro.model}</td>
   					<td class="bg">产品价格</td>
   					<td>${pro.price }</td>
   			  </tr>
   	    </table>
    	</div>
	  </div>
	 	</s:iterator>
	 	<div id="pageDiv">
	  	第<input type="text" size="2" id="page">页 
  		<input type="hidden" value="${page.totalPage }" id="hidden">
	  	<a href="" onclick="page('productAction')" id="enter">go</a>
  		&nbsp;&nbsp;&nbsp;&nbsp;
		<s:if test="page.hasPrePage">
			<a href="productAction">首页&nbsp;</a>
			<a href="productAction?currentPage=${currentPage-1}">上一页&nbsp;</a>
	  </s:if>
	  <s:else>
			<em>首页&nbsp;</em><em>上一页&nbsp;</em>
	  </s:else>
	  <s:if test="page.hasNextPage">
			<a href="productAction?currentPage=${currentPage+1}">下一页&nbsp;</a>
			<a href="productAction?currentPage=${page.totalPage}">末页<br></a>
	  </s:if>
	  <s:else>
			<em>下一页&nbsp;</em><em>尾页</em>
	  </s:else>
	  </div>
    </td>
  </tr>
</table>
<jsp:include page="bottom.jsp"/>
</div>
</body>
</html>
