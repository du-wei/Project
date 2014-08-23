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
<form action="admin/News_add" method="post" name="myform">
  <table width="95%" border="0" cellpadding="0" cellspacing="0">
  	<tr>
  	  <td height="35" colspan="2" class="leftbgcolor">添加新闻信息</td>
  	</tr>
    <tr>
      <td height="35" class="yellow">新闻标题：</td>
      <td class="white"><input name="news.title" type="text" size="30"/></td>
    </tr>
  	<tr>
      <td height="100" class="yellow">新闻内容：</td>
      <td class="white"><textarea name="news.content" cols="40" rows="5"></textarea></td>
    </tr>
  	<tr>
      <td height="35" class="yellow">&nbsp;</td>
      <td colspan="2" class="white"><input type="submit" value="提交"/> 
      &nbsp;&nbsp;&nbsp;  
      <input type="reset" value="重置"/></td>
    </tr>
  </table>
</form>
</div>
</body>
</html>
