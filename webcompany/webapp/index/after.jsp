<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>after.jsp</title>
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
    <td>
    <table width="80%" align="center">
   	  <tr>
   	    <td class="title">态度决定一切，沟通解决一切</td>
   	  </tr>
   	  <tr>
   	  	<td class="intro">
	   	  	凡在本公司购买的商品，保证七天内无条件退、换商品；如有质量问题保证在一个月内
	   	  	换货，三个月内免费维修
					保修期内的维修服务： 此类商品所承诺的免费服务期内,您所购货物出现故障时,可拨打
					售后服务免费热线电话进行报修.我们将会在下一工作日或您指定的日期(法定节假日除
					外)提供免费服务.如损坏零件不属于保修范围之内,仅收取零件的成本费用,并进行更换.
					6.2保修期外的维修服务: 对您购买的,但已处于保修期外的商品,我们将按保修期外维修
					服务标准向您提供服务,对于保修期外的维修,您需要承担的费用,包括维修所更换的零配
					件成本,维修费用;如果您需要上门服务,不定期需承担工程师现场维修的上门服务费. 。
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
