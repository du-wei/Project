<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>after.jsp</title>
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
    <td>
    <table width="80%" align="center">
   	  <tr>
   	    <td class="title">̬�Ⱦ���һ�У���ͨ���һ��</td>
   	  </tr>
   	  <tr>
   	  	<td class="intro">
	   	  	���ڱ���˾�������Ʒ����֤�������������ˡ�����Ʒ�������������Ᵽ֤��һ������
	   	  	�����������������ά��
					�������ڵ�ά�޷��� ������Ʒ����ŵ����ѷ�������,������������ֹ���ʱ,�ɲ���
					�ۺ����������ߵ绰���б���.���ǽ�������һ�����ջ���ָ��������(�����ڼ��ճ�
					��)�ṩ��ѷ���.������������ڱ��޷�Χ֮��,����ȡ����ĳɱ�����,�����и���.
					6.2���������ά�޷���: ���������,���Ѵ��ڱ����������Ʒ,���ǽ�����������ά��
					�����׼�����ṩ����,���ڱ��������ά��,����Ҫ�е��ķ���,����ά��������������
					���ɱ�,ά�޷���;�������Ҫ���ŷ���,��������е�����ʦ�ֳ�ά�޵����ŷ����. ��
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
