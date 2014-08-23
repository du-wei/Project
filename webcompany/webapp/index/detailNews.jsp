<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>detailNews.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body>
<div id="news">
<jsp:include page="top.jsp"/>
<table width="100%">
  <tr>
    <td colspan="3" class="line" height="30">
      <a href="newsAction">首页</a> &gt; 
      <a href="newsAction">站内新闻</a>
    </td>
  </tr>
  <tr>
    <td width="200" valign="top"><jsp:include page="left.jsp"/></td>
    <td bgcolor="#e8e8e8"></td>
    <td class="center" valign="top">
    <div id="news-list">
      <table border="0" cellpadding="0" cellspacing="0">
    	  <tr>
    	  	<td class="center"><span class="title">${news.title}</span></td>
    	  </tr>
    	  <tr>
    	  	<td width="500" class="content">${news.content}</td>
    	  </tr>
    	  <tr>
    	  	<td class="right">写于 ${news.writeDate}</td>
    	  </tr>
      </table>
    </div>
    </td>
  </tr>
</table>
<jsp:include page="bottom.jsp"/>
</div>
</body>
</html>
