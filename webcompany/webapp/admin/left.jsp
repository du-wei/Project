<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>left.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css">
<script type="text/javascript">
function $(op){
	return document.getElementById(op);
}
function show(op){
	if($(op).style.display === 'none'){
		$(op).style.display = 'block';
	}else{
		$(op).style.display = 'none';
	}
}
</script>
</head>
<body class="bgcolor">
<table width="110%" class="center">
	<tr>
		<td>ϵͳ��̨�������</td>
	</tr>
	<tr>
		<td><a href="userAction!exitAdmin" target="_parent">[��ȫ�˳�]</a><a href="index" target="_top">[������ҳ]</a></td>
	</tr>
	<tr class="hand">
		<td><a href="javascript:show('system');">��ϵͳ����</a></td>
	</tr>
	<tr id="system" style="display:none;">
		<td>&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/User_add.jsp" target="main">��ӹ���Ա</a><br>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/User_manager" target="main">�û�����</a>
		</td>
	</tr>
	<tr class="hand">
		<td><a href="javascript:show('news');">�����Ź���</a></td>
	</tr>
	<tr id="news" style="display:none;">
		<td>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/News_add.jsp" target="main">�������</a><br>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/News_manager" target="main">��������</a>
		</td>
	</tr>
	<tr class="hand">
		<td><a href="javascript:show('product');">����Ʒ����</a></td>
	</tr>
	<tr id="product" style="display:none;">
		<td>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/Product_add.jsp" target="main">�����Ʒ</a><br>&nbsp;&nbsp;
			<img src="images/icon_arrow_r.gif">
			<a href="admin/Product_manager" target="main">������Ʒ</a>
		</td>
	</tr>
</table> 	
</body>
</html>
