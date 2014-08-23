<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>messageBoard.jsp</title>
	<link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>

<body id="msg">
<div id="msg-div">
	<jsp:include page="msg-top.jsp"/>
	<div class="top">&nbsp;&nbsp;
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td><img src="images/titlemu_1.gif"></td>
				<td class="msg-top1"><a href="index/newMsg.jsp">新留言</a></td>
				<td><img src="images/titlemu_3.gif"></td>
			</tr>
		</table>
	</div>
	<table width="750" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="140"><img src="images/T_left.gif"></td>
			<td class="msg-top2">&nbsp;</td>
			<td width="56"><img src="images/message/T_right.gif"></td>
		</tr>
	</table>
	<div id="msg-table">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr class="msg-title">
				<td width="60">回复</td>
				<td>标题</td>
				<td width="200">作者</td>
				<td width="150">时间</td>
			</tr>
			<s:iterator value="listMsg" var="msg">
			<tr>
				<td>${msg.count }</td>
				<td><a href="messageAction!getById?message.id=${msg.id }">${msg.title }</a></td>
				<td>${msg.writer }</td>
				<td>${msg.writeDate }</td>
			</tr>
			</s:iterator>
		</table>
	</div>
	<table width="750" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="145"><img src="images/message/T_bottomleft.gif"></td>
			<td class="msg-bottom">&nbsp;</td>
			<td width="145"><img src="images/message/T_bottomright.gif"></td>
		</tr>
	</table>
</div>
</body>
</html>
