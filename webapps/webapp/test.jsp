<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>index</title>
<script type="text/javascript" src="common/jquery-1.10.2.min.js"></script>
<!--
<script type="text/javascript" src="common/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="common/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="common/jquery.bpopup-0.9.3.js"></script>
 -->
<script type="text/javascript" src="common/jquery.bpopup-0.9.0.js"></script>
<!--
<script type="text/javascript" src="tools/testUtils.js"></script>
<script type="text/javascript" src="tools/utils.js"></script>
<script type="text/javascript" src="tools/effectUtils.js"></script>
<script type="text/javascript" src="tools/ajaxUtils.js"></script>
 -->
<script type="text/javascript" src="tools/jqueryUtils.js"></script>
<script type="text/javascript" src="tools/pageUtils.js"></script>
<script type="text/javascript">
$(function(){
	//$.widen("#id");
	//$("#id").loop(200);
	//$("#id").fadeInOut($("#id").val(), 200);
	//alert($.viewOffset("#id"));
	var ii = 0;
	$("#page").page({}).done(function(pager){				//加载数据

		var ok = {curPage : 2,pageSize : 10,totalNum : 100};
		!ii && (pager = ok);
		ii++;
		$.post("").done(function(){
			$("#page").setPager(pager);
		});
	});

	var oo = 0;
	$("#page").page({}).done(function(pager){				//加载数据

		var ok = {curPage : 1,pageSize : 10,totalNum : 100};
		!oo && (pager = ok);
		oo++;
		$.post("").done(function(){
			$("#page").setPager(pager);
		});
	});

	$("#search").click(function(){
		$.post("").done(function(){
			$("#page").setPager({curPage : 2,pageSize : 10,totalNum : 100});
		});
	});

});


</script>
<style type="text/css">
img {width:200px; height:100px;}
</style>
</head>
<body>
<!--
	<div id="demo1">
		<img src="./javascript/image/1.jpg" onclick="window.open(this.src);"/>
		<img src="./javascript/image/2.jpg" onclick="window.open(this.src);"/>
	</div>
<input type="text" id="id" value=">>>>>123456789">

<div id="pop" style="height:200px;width:200px;background:red; border:1px solid black;">
<div id="ok" style="cursor: pointer;">xxxxx</div>
hello world</div>
 -->

<div id="page">
	<a href="javascript:void(0);" id="firstPage" >first</a>----
	<a href="javascript:void(0);" id="lastPage" >last</a>----
	<a href="javascript:void(0);" id="prevPage" >prev</a>----
	<a href="javascript:void(0);" id="nextPage">next</a>----
	curPage-><span id="curPage">0</span>----
	totalPage-><span id="totalPage">0</span>----
	totalNum-><span id="totalNum">0</span>----

	<select id="alterSize">
		<option value="2">2</option>
		<option value="5">5</option>
		<option value="10">10</option>
	</select>
	<input type="text" id="goVal" value="" />----
	<a href="javascript:void(0);" id="go">go</a>----
	<a href="javascript:void(0);" id="search">search</a>----
</div>
<br>
<br>
<br>


</body>
</html>