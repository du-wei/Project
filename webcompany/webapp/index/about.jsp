<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>about.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body>
<div style="width:798px;">
<jsp:include page="top.jsp"></jsp:include>
<table>
 <tr>
    <td colspan="3" class="line" height="30">
      <a href="index">��ҳ</a> &gt; 
      <a href="newsAction">վ������</a>
    </td>
  </tr>
  <tr>
    <td width="200" valign="top"><jsp:include page="left.jsp"/></td>
    <td bgcolor="#e8e8e8"></td>
    <td class="center">
    <table width="80%">
   	  <tr>
   	    <td class="title">���š��ڷܡ����顢�Žᡢ����</td>
   	  </tr>
   	  <tr>
   	  	<td class="intro">
   	  	��Ϣ��ѯ,ʹ�ڶ��û��ܹ�����������ԴΪ��������������档 ��˾�Գ���������
   	  	�ͱ���"���š����¡����顢�ڷܡ��Žᡢ����"�ľ�Ӫ����,����������ִ�������ʶ��
   	  	���ٵķ�����ϵ���������������Դ��Ϊ�û��ṩ����õ���Ʒ��֧��
   	  	</td>
   	  </tr>
    </table>
    </td>
  </tr>
</table>
<jsp:include page="bottom.jsp"></jsp:include>
</div>
</body>
</html>
