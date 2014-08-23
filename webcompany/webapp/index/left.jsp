<%@ page language="java" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>left.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body>
<div id="index-left">
	<div align="right" class="left-margin">
	<table width="188" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td><a href="newsAction"><img src="images/cg1_r1_c2.jpg" alt="最新新闻" border="0"></a></td>
		</tr>
		<tr>
			<td><a href="productAction"><img src="images/cg1_r2_c2.jpg" alt="最新商品" border="0"></a></td>
		</tr>
		<tr>
			<td><a href="after.jsp"><img src="images/cg1_r4_c2.jpg" alt="售后服务" border="0"></a></td>
		</tr>
		<tr>
			<td><a href="about.jsp"><img src="images/cg1_r6_c2.jpg" alt="关于我们" border="0"></a></td>
		</tr>
  </table>
  </div>
	<table width="188" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="2"><img src="images/cg1_r10_c1.jpg"></td>
		</tr>
		<tr>
			<td><img src="images/cg5_1.jpg"></td>
			<td width="160" onmouseover="this.className='pOver'" onmouseout="this.className='pOut'">业务专线：8888888</td>
		</tr>
		<tr>
			<td colspan="2"><hr/></td>
		</tr>
		<tr>
			<td><img src="images/cg5_1.jpg"></td>
			<td onmouseover="this.className='pOver'" onmouseout="this.className='pOut'">售后服务：8888888</td>
		</tr>
		<tr>
			<td colspan="2"><hr/></td>
		</tr>
		<tr>
			<td><img src="images/cg5_1.jpg"></td>
			<td onmouseover="this.className='pOver'" onmouseout="this.className='pOut'">传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真：8888888</td>
		</tr>
		<tr>
			<td colspan="2"><hr/></td>
		</tr>
  </table>
</div>

</body>
</html>
