<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>admin/index.jsp</title>
  <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body>
<s:if test="#session.admin.status == 1">
	<script type="text/javascript" src="ext/admin.js"></script>
</s:if>
<s:else>
<jsp:forward page="/index/login.jsp"></jsp:forward>
</s:else>
</body>
</html>
