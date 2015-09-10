<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>javascript</title>
<script type="text/javascript" src="../common/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	
	$("#home").click(function(event){
		this.setHome = $(this).get(0);
		this.setHome.style.behavior = "url(#default#homepage)";
		this.setHome.sethomepage($(this).attr("href"));
		event.preventDefault();
	});
	$("#favorite").click(function(event){
		window.external.AddFavorite(location.href, document.title);
		event.preventDefault();
	});
	$("#prev").click(function(){ history.back();});
	$("#next").click(function(){ history.forward();});
})

</script>

</head>
<body>
<div>
	<a href="#" id="home">设为主页</a>
	<a href="#" id="favorite">添加收藏</a>
	<a href="#" id="prev">上一页</a>
	<a href="#" id="next">下一页</a>
	
	<br><br><br>
	<a href="#" onclick='this.style.behavior="url(#default#homepage)";this.setHomePage("http://www.hao123.com")';>设为首页</a>
	<a href="#" onclick="window.external.AddFavorite(location.href, document.title);">添加收藏</a>
	<a href="#" onClick="document.location.reload()">刷新</a>
	<a href="#" id="prev" onclick="javascript:history.back();">上一页</a>
	<a href="#" id="next" onclick="javascript:history.forward();">下一页</a>
	
</div>
<div id="myArray"></div>
<div id="myArray2"></div>
<div id="myArray3"></div>

</body>
</html>