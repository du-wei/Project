<%@ page language="java" pageEncoding="GB18030" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
  <base href="<%=basePath%>">
  <title>chat/index.jsp</title>
  <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css">
	<script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="css/chat.js"></script>
	<link type="text/css" rel="stylesheet" href="css/company.css">
</head>
<body>
<s:if test="session.size > 0">
	<script type="text/javascript" src="ext/chat.js"></script>
</s:if>

<form action="userAction" name="myform" method="post" onsubmit="return valiChat()">
  <table id="chat-login"  border="0" cellpadding="0" cellspacing="0">
    <colgroup align="right"></colgroup>
    <colgroup align="left"></colgroup>
    <colgroup align="left"></colgroup>
  	<tr class="simple">
  		<td colspan="3" align="center"><span class="title">会员登陆</span></td>
  	</tr>
  	<tr class="double">
  		<td width="35%">您的账号：</td>
  		<td class="pad">
  			<input type="text" name="user.userName" id="userName" onblur="valiChatUser()">
  			<font color="red">*</font>
  		</td>
      	<td width="25%"><div id="userDiv">&nbsp;</div></td>
  	</tr>
  	<tr class="double">
  		<td>您的密码：</td>
  		<td class="pad">
  			<input type="password" name="user.password" id="password" onblur="valiChatPwd()">
  			<font color="red">*</font>
  		</td>
      	<td><div id="pwdDiv">&nbsp;</div></td>
  	</tr>
  	<tr class="simple">
  		<td>&nbsp;</td>
  		<td>
  			<input type="submit" value="登陆" /> &nbsp;&nbsp;&nbsp;&nbsp;
  			<input type="reset" value="重置"/>
  		</td>
  		<td class="pad" align="right"><a href="chat/register.jsp">我要注册</a></td>
  	</tr>
  </table>
</form>
</body>
</html>
