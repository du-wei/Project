<%@ page language="java" pageEncoding="GB18030"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>admin/login.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
<script type="text/javascript">
function check(){
	if(document.getElementById("userName").value == ""){
		alert("账号不能为空！");
		return false;
	}
	if(document.getElementById("password").value == ""){
		alert("密码不能为空！");
		return false;
	}
	return true;
}
</script>
</head>
<body>
<div style="width:798px; margin:auto;">
<jsp:include page="top.jsp"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
   <tr>
    <td colspan="3" class="line" height="30">
      <a href="index">首页</a> &gt;&nbsp;
      <a href="index">企业建站</a>
    </td>
  </tr>
	<tr>
    <td width="200" valign="top"><jsp:include page="left.jsp"/></td>
    <td bgcolor="#e8e8e8" width="2">&nbsp;</td>
    <td class="center">
			<form action="userAction!valiAdmin?user.status=1" method="post" name="myform">
		    <table width="400" height="150" border="0" cellpadding="0" cellspacing="0">
		    	<tr class="center">
					  <td colspan="2" class="title">管理员登陆</td>
					</tr>
					<tr>
					  <td width="40%" class="right">您的账号：</td>
					  <td><input type="text" name="user.userName" id="userName"><span style="color:red;"> *</span></td>
					</tr>  	
					<tr>
					  <td class="right">您的密码：</td>
					  <td><input type="text" name="user.password" id="password"><span style="color:red;"> *</span></td>
					</tr>  	
					<tr class="center">
					  <td colspan="2">
					  	<input name="submit" type="submit" id="submit" value="登陆" onclick="return check()">
					    &nbsp; &nbsp;&nbsp; 
					    <input name="reset" type="reset" id="reset" value="重置">
					  </td>
				  </tr>  	  	
		    </table>
			</form>
    </td>
  </tr>
</table>
<jsp:include page="bottom.jsp"/>
</div>
</body>
</html>
