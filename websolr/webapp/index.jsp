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

<link rel="stylesheet" type="text/css" href="common/jquery-ui.css">
<script type="text/javascript" src="common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="common/jquery-ui.custom.js"></script>

<script type="text/javascript">
$(function(){
	
	$("#add").on("click", function(){
		var addPara = {
			id : $("#id").val(),	
			name : $("#name").val(),	
			age : $("#age").val(),	
			desc : $("#desc").val()	
		};
		$.post("solrOk", addPara).done(function(val){
			$("#show").text(val);
		});
	});
	
	$("#search").on("click", function(){
		
		var date = {
			key : $("#key").val()
		};
		$.post("solrOk", date).done(function(val){
			var result = eval('(' + val + ')');
			
			var $result = $();
			
			 $.each(result, function(i, v) { 
				 var $temp = $("#temp").clone(true).show();
				 $temp.append(i + " = " + v.id + " - " + v.name + " - " + v.age + " - " + v.desc + " <br/>");
				 $.merge($result, $temp);
			 });
			 $("#show").empty().append($result);
			
		});
	});
	
	$( "#key" ).autocomplete({
	    source: function( request, response ) {
	    	var data = {
        		sug : $("#key").val()
        	};
	    	$.post("mysug", data).done(function(val){
	    		var result = eval('(' + val + ')');
	    		response(result);
	   		});
	    }
	});
});
</script>
<style type="text/css">

body {}
div {margin: 0px auto;border: 1px solid red; width: 500px; text-align: left; margin-top: 20px;}

</style>
</head>
<body>
	<div>
		id 	 : <input type="text" id="id" /><br/><br/>
		name : <input type="text" id="name" /><br/><br/>
		age  : <input type="text" id="age" /><br/><br/>
		desc : <input type="text" id="desc" /><br/><br/>
		<input type="submit" id="add" value="add">
	</div>
	<div>
		<input type="text" id="key" value="">
		<input type="submit" id="search" value="search">
		<div id="temp" style="display: none; line-hight: 25px;"></div>
		<div id="show"></div>
	</div>
</body>
</html>