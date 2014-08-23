<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>index</title>
<script type="text/javascript" src="common/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
$(function(){
	var defer = $.when($.post("JsonAction!result2", function(val){
		alert(val);
	}));
    filtered = defer.pipe(function( value ) {
    	alert("---"+value);
      return value * 2;
    });

	//defer.resolve( 5 );
	filtered.done(function( value ) {
	  alert( "Value is ( 2*5 = ) 10: " + value );
	});
	
	var deferred = $.Deferred();
     deferred.progress(function (args) {
     	//alert(args);
     });
 
     deferred.notify("notify1");
     deferred.notify("notify2");
     deferred.resolve();
});
</script>
</head>
<body>
struts
<input type="button" value="slfjsdl" id="ok"/>
</body>
</html>