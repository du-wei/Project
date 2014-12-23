<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="../common/jquery-1.10.2.min.js"></script>

<script type="text/javascript">

//$.Deferred
$(function($){
/** ----------------------------------------------------- **/
	$.ajax("test.html")
		.done(function(){ alert("哈哈，成功了！"); })
		.fail(function(){ alert("出错啦！"); });
/** ----------------------------------------------------- **/
	$.when($.ajax("test1.html"), $.ajax("test2.html"))
		.done(function(){ alert("哈哈，成功了！"); })
		.fail(function(){ alert("出错啦！"); });
/** ----------------------------------------------------- **/
	var dtd = $.Deferred(); // 新建一个Deferred对象
	var wait = function(dtd){
		var tasks = function(){
			alert("执行完毕！");
			dtd.resolve(); // 改变Deferred对象的执行状态
		};
		setTimeout(tasks,5000);
		return dtd.promise(); // 返回promise对象
	};
	var d = wait(dtd); // 新建一个d对象，改为对这个对象进行操作
	$.when(d)
		.done(function(){ alert("哈哈，成功了！"); })
		.fail(function(){ alert("出错啦！"); });
/** ----------------------------------------------------- ok**/
	var wait = function(){
		var dtd = $.Deferred(); //在函数内部，新建一个Deferred对象
		var tasks = function(){
			alert("执行完毕！");
			dtd.resolve(); // 改变Deferred对象的执行状态
		};
		setTimeout(tasks,5000);
		return dtd.promise(); // 返回promise对象
	};
	$.when(wait())
		.done(function(){ alert("哈哈，成功了！"); })
		.fail(function(){ alert("出错啦！"); });
/** ----------------------------------------------------- ok**/
	var wait = function(dtd){
		var tasks = function(){
			alert("执行完毕！");
			dtd.resolve(); // 改变Deferred对象的执行状态
		};
		setTimeout(tasks,5000);
		return dtd.promise(); // 返回promise对象
	};
	$.Deferred(wait)
		.done(function(){ alert("哈哈，成功了！"); })
		.fail(function(){ alert("出错啦！"); });
/** ----------------------------------------------------- **/
	var dtd = $.Deferred(); // 生成Deferred对象
		var wait = function(dtd){
			var tasks = function(){ 
			alert("执行完毕！");
			dtd.resolve(); // 改变Deferred对象的执行状态
		};
		setTimeout(tasks,5000);
	};
	dtd.promise(wait);
	wait.done(function(){ alert("哈哈，成功了！"); })
		.fail(function(){ alert("出错啦！"); });
	wait(dtd);

});
	var ok1 = function(dtd){
		$.post("relationAction!ok").done(function(val){
			alert("val1");
			dtd.resolve();
		});
		return dtd;
	};
	
	var ok2 = function(){
		var dtd = $.Deferred();
		$.post("relationAction!ok", {a:'a'}).done(function(val){
			alert("val2-----------------------------");
		}).done(function(){
			dtd = ok1(dtd);
		});
		return dtd;
	};
	
	var ok3 = function(){
		return $.post("relationAction!ok").done(function(val){
			alert("val3=======================================================");
		});
	};
	$("body").on("click", function(){
		$.when(ok2()).done(ok3);
	});
</script>
</head>

<body>
	<div id="webpage">
		<img src="image/2.jpg" width="499" height="283" alt="Web Page" />
		<div id="retina"></div>
	</div>

</body>
</html>
