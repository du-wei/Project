<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
  <base href="<%=basePath%>">
  <title>index.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
  <script type="text/javascript" src="css/chat.js"></script>
</head>
<body>
<div>
  <form action="userAction!add?user.status=0" name="myform" method="post">
  <table id="chat-login" border="0" cellpadding="0" cellspacing="0">
  <colgroup align="right"></colgroup>
  <colgroup align="left"></colgroup>
  <colgroup align="left"></colgroup>
  	<tr class="simple">
  		<td colspan="3" align="center"><span class="title">会员注册</span></td>
  	</tr>
  	<tr class="double">
  		<td width="30%">用户名：</td>
  		<td>
  			<input type="text" name="user.userName" id="userName" size="25" onblur="valiChatUser()">
  			<font color="red">*</font>
  		</td>
  		<td width="25%"><div id="userDiv">&nbsp;</div></td>
  	</tr>
  	<tr class="double">
  		<td>密码：</td>
  		<td>
  			<input type="password" name="user.password" id="password" size="25" onblur="valiChatPwd()">
  			<font color="red">*</font>
  		</td>
  		<td width="25%"><div id="pwdDiv">&nbsp;</div></td>
  	</tr>
  	<tr class="double">
  		<td>确认密码：</td>
  		<td>
  			<input type="password" name="repwd" id="repwd" size="25" onblur="valiChatRePwd()">
  			<font color="red">*</font>
  		</td>
  		<td width="25%"><div id="repwdDiv">&nbsp;</div></td>
  	</tr>
  	<tr class="simple">
  		<td colspan="3" align="center">
  			<input type="submit" value="提交" onclick="return valiRegister();">
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<input type="reset" value="重置">
  		</td>
  	</tr>
  </table>
  </form>
</div>
</body>
</html>
