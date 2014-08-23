<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
function $(op){
	return document.getElementById(op);
}
function loop(){
	$('do2').innerHTML = $('do1').innerHTML;
	if($('imgLoop').scrollTop > $('do2').offsetHeight)
		$('imgLoop').scrollTop = 0;
	else
		$('imgLoop').scrollTop++;
}
var myTime = setInterval(loop,20);
function stop(){
	clearInterval(myTime);
}
function start(){
	myTime = setInterval(loop,20);
}
function error(){
	return true;
}
window.onerror = error;
</script>
</head>
<body>

<div id="index-main">
<jsp:include page="index/top.jsp"></jsp:include>
<table border="0" cellpadding="0" cellspacing="0">
  <tr>
  	<td>
   	  <div id="index-news">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
   		<tr class="news-bg">
   		  <td valign="top"><img src="images/d_r7_c1.jpg"></td>
   		  <td class="right">
   		    <a href="newsAction"><img src="images/d_r7_c14.jpg" border="0"></a>
   		  </td>
   		</tr>
           <s:iterator value="listNews" var="list">
             <tr>
               <td colspan="2" class="news-content">
                 <a href="newsAction!getById?id=${list.id}">${list.title}</a>
                 &nbsp;&nbsp;&nbsp;&nbsp;
                 ${list.writeDate}
               </td>
             </tr>
           </s:iterator>
   		<tr>
   		  <td colspan="2">
   		  	<a href="newsAction"><img src="images/d_r11_c15.jpg" border="0" align="right"></a>
   		  </td>
   		</tr>
    	</table>
      </div>
      <div id="index-release">
    	<table border="0" cellpadding="0" cellspacing="0">
    		<tr>
    			<td><a href="productAction!getById?id=2"><img src="images/d_r11_5_r1_c1.jpg" border="0"></a></td>
    		</tr>
    		<tr>
    			<td><a href="productAction!getById?id=4"><img src="images/d_r11_5_r3_c1.jpg" border="0"></a></td>
    		</tr>
    		<tr>
    			<td><a href="productAction!getById?id=3"><img src="images/d_r11_5_r4_c1.jpg" border="0"></a></td>
    		</tr>
    		<tr>
    			<td><a href="productAction!getById?id=5"><img src="images/d_r11_5_r5_c1.jpg" width="268" height="64" border="0"></a></td>
    		</tr>
    	</table>
      </div>
   	</td>
  	<td rowspan="3" align="center" valign="top">
      <img src="images/d_r11_6.jpg" width="175" height="42">
      <div id="imgLoop" onMouseOver="stop()" onMouseOut="start()" class="loop">
		<div id="do1">
		  <s:iterator value="listProduct" var="pro">
		    <img src="images/${pro.picture}">
		  </s:iterator>
		</div>
		<div id="do2">
		</div>
      </div>
    </td>
  </tr>
  <tr>
  	<td>
  	  <div id="index-service">
  		<table width="623" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr height="27" class="service-bg">
				<td colspan="3"><img src="images/d_r13_c1.jpg"></td>
				<td class="right"><a href="#"><img src="images/d_r13_c21.jpg" border="0"></a></td>
			</tr>
			<tr class="center">
				<td><a href="productAction">
					<img src="images/d_r11_10_r1_c1.jpg" width="143" height="112"></a>
				</td>
				<td><a href="productAction">
					<img src="images/d_r11_10_r1_c2.jpg" width="143" height="112"></a>
				</td>
				<td><a href="productAction">
					<img src="images/d_r11_10_r1_c8.jpg" width="143" height="112"></a>
				</td>
				<td><a href="productAction">
					<img src="images/d_r11_10_r1_c16.jpg" width="143" height="112"></a>
				</td>
			</tr>
			<tr class="center">
				<td><a href="productAction">
					<img src="images/d_r11_10_r1_c22.jpg" width="143" height="112"></a>
				</td>
				<td><a href="productAction">
					<img src="images/d_r11_10_r1_c81.jpg" width="143" height="112"></a>
				</td>
				<td><a href="productAction">
					<img src="images/d_r11_10_r1_c116.jpg" width="143" height="112"></a>
				</td>
				<td><a href="productAction">
					<img src="images/d_r11_10_r1_c221.jpg" width="143" height="112"></a>
				</td>
			</tr>
  		</table>
  	  </div>
  	</td>
  </tr>
  <tr>
    <td>
      <div id="index-phone">
    	<table border="0" cellpadding="0" cellspacing="0" width="100%">
    		<tr class="phone-bg">
    			<td colspan="2"><img src="images/n06.jpg" width="185"></td>
    		</tr>	
    		<tr>
    			<td width="15%"><img src="images/d_r11_12_r1_c1.jpg"></td>
    			<td width="85%">业务专线： 010-88888888</td>
    		</tr>
    		<tr>
    			<td><img src="images/d_r11_12_r1_c1.jpg"></td>
    			<td>售后服务： 010-66666666</td>
    		</tr>
    		<tr>
    			<td><img src="images/d_r11_12_r1_c1.jpg"></td>
    			<td>传   &nbsp;&nbsp;&nbsp;&nbsp; 真： 010-88888888</td>
    		</tr>	
    	</table>
      </div>
      <div id="index-links">
    	<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" align="center">
			<tr valign="top" class="links-bg">
				<td height="27" colspan="2"><img src="images/d_r11_11_r1_c21.jpg"></td>
				<td class="right">
					<a href="#"><img src="images/d_r13_c21.jpg" border="0"></a>    			</td>	
			</tr>
			<tr class="center">
				<td><img src="images/f1.gif"></td>
				<td><img src="images/f2.gif"></td>
				<td><img src="images/f3.gif"></td>
			</tr>
			<tr class="center">
				<td><img src="images/d_r11_13_r1_c1.jpg"></td>
				<td><img src="images/d_r11_13_r1_c7.jpg"></td>
				<td><img src="images/d_r11_13_r6_c13.jpg"></td>
			</tr>
    	</table>
      </div>
    </td>
  </tr>
</table>
<jsp:include page="index/bottom.jsp"></jsp:include>
</div>
</body>
</html>
