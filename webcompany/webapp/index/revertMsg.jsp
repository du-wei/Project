<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>revertMessage.jsp</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/> 
	<link type="text/css" rel="stylesheet" href="css/company.css"/>
<script type="text/javascript">
function check(){
	var writer = document.getElementById("writer").value;
	if(writer == null || writer == ""){
		document.getElementById("div").innerHTML = "<font color='red'>姓名不能为空！</font>";
		return false;
	}
	return true;
}
</script>

</head>
<body id="msg">
<div id="msg-div">
	<jsp:include page="msg-top.jsp"/>
	
	<%-- 主题部分 --%>
	<div class="top">&nbsp;&nbsp;
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td><img src="images/titlemu_1.gif"></td>
				<td class="msg-top1"><a href="newMessage.jsp">新留言</a></td>
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
	<table width="100%" border="0" cellpadding="0" cellspacing="0" id="msg-table">
		<tr class="msg-title">
			<td width="70%">卍卍 主题： ${message.title }</td>
			<td class="right">${message.writer } 写于 ${message.writeDate }</td>
		</tr>
		<tr><td colspan="2">${message.content }</td></tr>
		<tr><td height="20" colspan="2">&nbsp;</td></tr>
	</table>
	<table width="750" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="145"><img src="images/message/T_bottomleft.gif"></td>
			<td class="msg-bottom">&nbsp;</td>
			<td width="145"><img src="images/message/T_bottomright.gif"></td>
		</tr>
	</table><br/>
	
	<%-- 回复内容部分 --%>
	<table width="750" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="140"><img src="images/T_left.gif"></td>
			<td class="msg-top2">&nbsp;</td>
			<td width="56"><img src="images/message/T_right.gif"></td>
		</tr>
	</table>
	<s:iterator value="message.revert" var="revert">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" id="msg-table">
		<tr class="msg-title">
			<td width="50%">卍&nbsp;<span style="color:red">回复内容：</span>  </td>
			<td class="right">${revert.writer } 回复于  ${revert.writeDate }</td>
		</tr>
		<tr><td colspan="2">${revert.content }</td></tr>
		<tr><td height="20" colspan="2">&nbsp;</td></tr>
	</table>
	</s:iterator>
	<table width="750" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="145"><img src="images/message/T_bottomleft.gif"></td>
			<td class="msg-bottom">&nbsp;</td>
			<td width="145"><img src="images/message/T_bottomright.gif"></td>
		</tr>
	</table>
	
	
	<br/>
	<div id="revert">
		<form action="revertAction?revert.messageId=${message.id }" method="post" name="myform" class="msg">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr class="title"><td colspan="2">快速回复</td></tr>
				<tr>
					<td class="right" width="15%">姓名：</td>
					<td>
						<input name="revert.writer" type="text" id="writer" size="30" onblur="check()">
						<span style="color: red;">*</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span id="div"></span>
					</td>
				</tr>
				<tr>
					<td valign="top" class="right">内容：</td>
					<td><textarea name="revert.content" cols="80" rows="5" id="content"></textarea></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" name="Submit" value="提交" onclick="return check()"></td>
				</tr>
			</table>
		</form>
	</div><br/>
	
</div>
</body>
</html>
