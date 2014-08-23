<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
    <base href="<%=basePath%>">
    <title>newMessage.jsp</title>
		<link type="text/css" rel="stylesheet" href="css/company.css"/>
<script type="text/javascript">
function $(op){
	return document.getElementById(op);
}
function valiName(){
	if($('writer').value == null || $('writer').value == ""){
		$('writerDiv').innerHTML = "<font color='red'>��������Ϊ�գ�</font>";
		return false;
	}
	return true;
}
function valiTitle() {
	if($('title').value == null || $('title').value == ""){
		$('titleDiv').innerHTML = "<font color='red'>���ⲻ��Ϊ�գ�</font>";
		return false;
	}
	return true;
}
function valiContent(){
	if($('content').value == null || $('content').value == ""){
		$('contentDiv').innerHTML = "<font color='red'>���ݲ���Ϊ�գ�</font>";
		return false;
	}
	return true;
}
function check(){
	if(valiName() && valiTitle() && valiContent())
		return true;
	return false;
}
</script>
</head>
  
<body>
<div id="revert">
	<jsp:include page="msg-top.jsp"/>
	<div class="top">&nbsp;&nbsp;
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td><img src="images/titlemu_1.gif"></td>
				<td class="msg-top1">
					<a href="messageAction">����</a>
				</td>
				<td><img src="images/titlemu_3.gif"></td>
			</tr>
		</table>
	</div>
	<div class="msg">
		<form action="messageAction!add" method="post" name="myform">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr><td colspan="2" class="title">������</td></tr>
				<tr>
					<td class="right" width="15%">������</td>
					<td>
						<input name="message.writer" type="text" id="writer" size="30" onblur="valiName()">
						<span style="color: red;">*</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<span id="writerDiv"></span>
					</td>
				</tr>
				<tr>
					<td class="right">���⣺</td>
					<td>
						<input name="message.title" type="text" id="title" size="40" onblur="valiTitle()">
						<span style="color: red;">*</span>&nbsp;&nbsp;&nbsp;
						<span id="titleDiv"></span>
					</td>
				</tr>
				<tr>
					<td valign="top" class="right">���ݣ�</td>
					<td>
						<textarea name="message.content" cols="80" rows="5" id="content" onblur="valiContent()"></textarea>
						<span style="color: red;vertical-align:top;">*</span>&nbsp;&nbsp;
						<span id="contentDiv" style="vertical-align:top;"></span>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" name="Submit" value="�ύ" onclick="return check()"></td>
				</tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>
