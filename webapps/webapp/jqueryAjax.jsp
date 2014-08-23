<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>index</title>
<script type="text/javascript" src="common/jquery-1.8.2.js"></script>
<script type="text/javascript" src="tools/jqueryUtils.js"></script>
<script type="text/javascript">
$(function(){
	$.ajaxPrefilter(function( options, originalOptions, jqXHR ) {//每次
		$("#msg").append("<br>ajaxPrefilter");
	});
	
	$("#msg").ajaxStart(function(event){
		$(this).append("<br>ajaxStart");
	});
	
	$("#msg").ajaxSend(function(event, request, settings){//每次
		$(this).append("<br>ajaxSend");
	});
	
	$("#msg").ajaxComplete(function(event, request, settings){//每次
		$(this).append("<br>ajaxComplete");
	});
	
	$("#msg").ajaxSuccess(function(event, request, settings){//每次
		$(this).append("<br>ajaxSuccess");
	});
	$("#msg").ajaxError(function(event, request, settings, error){//每次
		$(this).append("<br>ajaxError" + error);
	});
	
	$("#msg").ajaxStop(function(event){
		$(this).append("<br>ajaxStop");
	});

	/* $.post("jsonAction!temp", {}, null, "json").done(function(val){
		$("#oks").tmpl(val).done(function(tpl, item){
			tpl.text(item.name);
		});
	}); */
	
});
</script>
<style type="text/css"></style>
</head>
<body>

<div id="msg"></div>
<div id="oks">

</div>
</body>
</html>