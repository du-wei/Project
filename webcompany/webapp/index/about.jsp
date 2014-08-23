<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>about.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body>
<div style="width:798px;">
<jsp:include page="top.jsp"></jsp:include>
<table>
 <tr>
    <td colspan="3" class="line" height="30">
      <a href="index">首页</a> &gt; 
      <a href="newsAction">站内新闻</a>
    </td>
  </tr>
  <tr>
    <td width="200" valign="top"><jsp:include page="left.jsp"/></td>
    <td bgcolor="#e8e8e8"></td>
    <td class="center">
    <table width="80%">
   	  <tr>
   	    <td class="title">诚信、勤奋、激情、团结、互助</td>
   	  </tr>
   	  <tr>
   	  	<td class="intro">
   	  	信息咨询,使众多用户能够利用网络资源为自身带来最大的利益。 公司自成立那天起，
   	  	就秉承"诚信、创新、激情、勤奋、团结、互助"的经营理念,以其优秀的现代管理意识、
   	  	快速的服务体系，充分利用现有资源，为用户提供了最好的商品与支持
   	  	</td>
   	  </tr>
    </table>
    </td>
  </tr>
</table>
<jsp:include page="bottom.jsp"></jsp:include>
</div>
</body>
</html>
