<%@ page language="java" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
  <base href="<%=basePath%>">
  <title>top.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body>
<div id="top-logo">
  <table width="798" height="207" border="0" cellpadding="0" cellspacing="0">
  	<tr><td class="top-side" colspan="3"></td></tr>
  	<tr>
		<td height="40" colspan="2">
			<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0" width="798" height="161" title="品红Logo">
		      <param name="movie" value="css/2.swf"><param name="quality" value="high">
		      <embed src="css/2.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="798" height="161"></embed>
		    </object>
      	</td>
  	</tr>
  	<tr><td colspan="2" class="top-side"></td>
  	</tr>
  	<tr height="40" class="top-navigation" valign="top">
  		<td width="762"><img src="images/d_r3_c1.jpg" width="60" height="40">
	  		<a href="index"><img src="images/d_r3_c5.jpg" alt="首页" width="64" height="40"></a>
	  		<a href="productAction"><img src="images/d_r3_c7.jpg" alt="商品展示" width="94" height="40"></a>
	  		<a href="newsAction"><img src="images/d_r3_c8.jpg" alt="站内新闻" width="94" height="40"></a>
	  		<a href="chat/index.jsp"><img src="images/d_r3_c9.jpg" alt="在线聊天" width="94" height="40"></a>
	  		<a href="messageAction" target="_blank"><img src="images/d_r3_c10.jpg" alt="留言薄" width="94" height="40"></a>
	  		<a href="admin/index.jsp"><img src="images/d_r3_c13.jpg" alt="后台管理" width="94" height="40"></a></td>
  		<td width="36"><img src="images/d_r3_c25.jpg" width="36" height="40"></td>
  	</tr>
  </table>
</div>
</body>
</html>
