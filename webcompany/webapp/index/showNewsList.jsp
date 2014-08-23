<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>showNewList.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
  <script type="text/javascript" src="css/company.js"></script>
</head>
<body>
<div id="news">
<%=basePath%>
<jsp:include page="top.jsp"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="3" class="line" height="30">
      <a href="index">首页</a> &gt;&nbsp;
      <a href="newsAction"> 站内新闻</a>
    </td>
  </tr>
  <tr>
    <td width="200" valign="top"><jsp:include page="left.jsp"/></td>
    <td bgcolor="#e8e8e8" width="2">&nbsp;</td>
    <td class="center">
		<div id="news-list">
	      <table border="0" cellpadding="0" cellspacing="0">
		  	<colgroup span="3" align="center"></colgroup>
	      	<tr>
	      	  <td valign="middle" colspan="3"><span class="title">新闻列表</span></td>
	      	</tr>
	      	<s:iterator value="listNews" var="list">
	      	  <tr>
	      	  	<td>${list.id}</td>
	      	  	<td><a href="newsAction!getById?id=${list.id}">${list.title}</a></td>
	      	  	<td>${list.writeDate}</td>
	      	  </tr>
	      	</s:iterator>
	      </table>    
	     </div>
	     <div id="pageDiv">
	     	<span style="color:red">共${page.totalPage}页</span>&nbsp;
		   <span style="color:red">第<input type="text" size="2" id="page">页</span>
		     <input type="hidden" value="${page.totalPage }" id="hidden">
	  		<a href="" onclick="page('newsAction')" id="enter">go</a>
	  		&nbsp;&nbsp;&nbsp;&nbsp;
     	  <s:if test="page.hasPrePage">
     	  	<a href="newsAction">首页</a>
     	  	<a href="newsAction?currentPage=${currentPage-1}">上一页</a>
     	  </s:if>
     	  <s:else>
     	  	<em>首页</em><em>上一页</em>
     	  </s:else>
     	  <s:if test="page.hasNextPage">
     	  	<a href="newsAction?currentPage=${currentPage+1}">下一页</a>
   				<a href="newsAction?currentPage=${page.totalPage}">末页</a>
   			</s:if>
     	  <s:else>
     	  	<em>下一页</em><em>尾页</em>
     	  </s:else>&nbsp;
     	  <span style="color:red">当前是第${currentPage}页</span>
     	 </div>
    </td>
  </tr>
</table><br>
<jsp:include page="bottom.jsp"/>
</div>
</body>
</html>
