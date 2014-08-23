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
<script type="text/javascript" src="common/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
$(function(){
	//var defer = $.Deferred(),
	/*
	var defer = $.when($.ajax("jsonAction!temp?name=11").done(function(val){alert(val);}));
    filtered = defer.pipe(function( value ) {
    	var xx = value;
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
     
     */
    
});
</script>
<style type="text/css"></style>
</head>
<body>
<a href="jsp/request.jsp">request properties</a> <br/>
<a href="jspsmart/upload.jsp">upload test</a> <br/>
<a href="jqueryui/datepicker.jsp">datepicker.jsp</a> <br/>
<a href="jqueryui/drag.jsp">drag.jsp</a> <br/>
<a href="jqueryplugins/corner/corner.htm">corner.jsp</a> <br/>
<a href="jqueryplugins/diag/test.html">diag.jsp</a> <br/>
<a href="jqueryplugins/formvalidate/formvalidate.html">formvalidate.html</a> <br/>
<a href="jqueryplugins/funswitcher/index.html">funswitcher.html</a> <br/>
<a href="jqueryplugins/hiAlert/demo.html">hiAlert.html</a> <br/>
<a href="jqueryplugins/jquery-lightbox/index.htm">jquery-lightbox.html</a> <br/>
<a href="jqueryplugins/jwysiwyg/jquery-edit.html">jquery-edit.html</a> <br/>
<a href="jqueryplugins/lavalamp/demo.html">lavalamp.html</a> <br/>
<a href="jqueryplugins/maskdiv/index.html">maskdiv.html</a> <br/>
<a href="jqueryplugins/pagination/index.jsp">pagination.jsp</a> <br/>
<a href="jqueryplugins/windowdiv/ss.html">windowdiv.html</a> <br/>
<a href="jqueryplugins/zoominfo/index.html">zoominfo.html</a> <br/>
<a href="jqueryeasyui/easyui.html">easyui.html</a> <br/>


</body>
</html>