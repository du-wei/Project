<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>index.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
<script type="text/javascript">
function validate(){
	if(document.myform.message.value==""){
		alert("���ܷ��Ϳ���Ϣ��");
		return false;
	}else{
		document.myform.msg.value = document.myform.message.value;
		document.myform.message.value = "";
		return true;
	}
}
</script>  

</head>
 
<body style="border:0px;padding:3px 3px;margin:30px 15px;">
<form action="chat/message.jsp" method="post" name="myform" onsubmit="return validate()" target="msg">
	<input type="hidden" name="msg" />
	<input type="text" name="message" size="55" class="write"/>
	<input type="submit" value="�� ��" class="send"/>
</form>
</body>
</html>
