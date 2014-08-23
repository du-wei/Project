<%@ page language="java" pageEncoding="GBK" contentType="text/html; charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <base href="<%=basePath%>">
  <title>showProductList.jsp</title>
  <link type="text/css" rel="stylesheet" href="css/company.css"/>
</head>
<body class="bgcolor">
<div id="admin-table">
  <table width="95%" border="0" cellpadding="0" cellspacing="0" class="center">
  	<tr class="leftbgcolor">
  	  <td height="30" colspan="7" class="left">�����Ʒ��Ϣ</td>
  	</tr>
    <tr class="yellow">
      <td height="30">��Ʒ���</td>
      <td>��Ʒ����</td>
      <td>��Ʒ�̱�</td>
      <td>��Ʒ�ͺ�</td>
      <td>��Ʒ�۸�</td>
      <td>�޸�</td>
      <td>ɾ��</td>
    </tr>
    <s:iterator value="listProduct" var="p">
	    <tr class="white">
	      <td height="30">${p.serialNumber }</td>
	      <td>${p.name }</td>
	      <td>${p.brand }</td>
	      <td>${p.model }</td>
	      <td>${p.price }</td>
	      <td><a href="admin/Product_managerUpdate?id=${p.id }">�޸�</a></td>
	      <td><a href="admin/Product_delete?product.id=${p.id }">ɾ��</a></td>
	    </tr>
    </s:iterator>
  </table>
</div>
</body>
</html>
