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
<form action="admin/Product_update" method="post" name="myform">
  <table width="95%" height="380" border="0" cellpadding="0" cellspacing="0" class="table">
  <colgroup class="yellow"></colgroup>
  <colgroup class="white"></colgroup>
  	<tr>
  	  <td colspan="2" class="leftbgcolor">�����Ʒ��Ϣ</td>
  	</tr>
    <tr>
      <td width="30%">��Ʒ��ţ�</td>
      <td><input name="product.serialNumber" type="text" size="30" value="${product.serialNumber }"/></td>
    </tr>
  	<tr>
      <td>��Ʒ���ƣ�</td>
      <td><input name="product.name" type="text" size="30" value="${product.name }"/></td>
    </tr>
  	<tr>
      <td>��Ʒ�̱꣺</td>
      <td><input name="product.brand" type="text" size="30" value="${product.brand }"/></td>
    </tr>
  	<tr>
      <td>��Ʒ�ͺţ�</td>
      <td><input name="product.model" type="text" size="30" value="${product.model }"/></td>
    </tr>
  	<tr>
      <td>��Ʒ�۸�</td>
      <td><input name="product.price" type="text" size="30" value="${product.price }"/></td>
    </tr>
  	<tr>
      <td>��ƷͼƬ��</td>
      <td><input name="product.picture" type="text" size="30" value="${product.picture }"/></td>
    </tr>
  	<tr>
      <td height="100">��Ʒ���ܣ�</td>
      <td><textarea name="product.description" cols="40" rows="5">${product.description }</textarea></td>
    </tr>
  	<tr>
      <td>&nbsp;</td>
      <td>
		  <input type="submit" value="�ύ"/> &nbsp;&nbsp;  
		  <input type="reset" value="����"/>
	  </td>
    </tr>
  </table>
</form>
</div>
</body>
</html>
