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
		alert("�˺Ų���Ϊ�գ�");
		return false;
	}
	if(document.getElementById("password").value == ""){
		alert("���벻��Ϊ�գ�");
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
      <a href="index">��ҳ</a> &gt;&nbsp;
      <a href="index">��ҵ��վ</a>
    </td>
  </tr>
	<tr>
    <td width="200" valign="top"><jsp:include page="left.jsp"/></td>
    <td bgcolor="#e8e8e8" width="2">&nbsp;</td>
    <td class="center">
			<form action="userAction!valiAdmin?user.status=1" method="post" name="myform">
		    <table width="400" height="150" border="0" cellpadding="0" cellspacing="0">
		    	<tr class="center">
					  <td colspan="2" class="title">����Ա��½</td>
					</tr>
					<tr>
					  <td width="40%" class="right">�����˺ţ�</td>
					  <td><input type="text" name="user.userName" id="userName"><span style="color:red;"> *</span></td>
					</tr>  	
					<tr>
					  <td class="right">�������룺</td>
					  <td><input type="text" name="user.password" id="password"><span style="color:red;"> *</span></td>
					</tr>  	
					<tr class="center">
					  <td colspan="2">
					  	<input name="submit" type="submit" id="submit" value="��½" onclick="return check()">
					    &nbsp; &nbsp;&nbsp; 
					    <input name="reset" type="reset" id="reset" value="����">
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
