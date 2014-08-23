<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- 从被装饰页面获取title标签内容,并设置默认值-->
<title><decorator:title default="default title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

OK,there is a decorator begin!<hr />

<!-- 从被装饰页面获取head标签内容 -->
<decorator:head />

</head>
<body>
    <!-- 被修饰页的body内容将在这里插入  -->
    <decorator:body />

<hr />Yse,there is a decorator end !

</body>
</html>