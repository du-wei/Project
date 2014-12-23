<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../common/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
//1 开放公共参数
//2 开放部分功能
//3 保留插件隐私
//4 非破坏性操作
//5 添加事件日志
;(function($){
	//1 封装对象方法的插件 $("").color();
	$.fn.pluginName = function(options) {//扩展单个对象方法
		var defaults = {
			bg:"red",
			font:"12"
		};
		var opts = $.extend(defaults, options);
	};
	$.fn.extend({ //扩展多个对象方法
		color:function(value){
			return value === undefined ? this.css("color") : this.css("color", value);
		},
		border:function(){}
	});
	
	//2 封装全局函数的插件$.trim();
	$.foo = function(options) {    
		alert('This is a test. This is only a test.');
	};
	$.extend({
		ltrim:function(text){
			return (text || "").replace(/^\s+/g, "");
		}
	});
	//采用命名空间的函数仍然是全局函数
	$.myPlugin = {           
		foo:function() {           
			//alert('This is a test. This is only a test.');
		},           
		bar:function(param) {           
			//alert('This function takes a parameter, which is "' + param + '".');
		}          
	};   
	//采用命名空间的函数仍然是全局函数，调用时采用的方法：   
	$.myPlugin.foo();          
	$.myPlugin.bar('baz');  
	
/*
	//暴露插件的默认设置
	$.fn.hilight = function(options) {
		var opts = $.extend({}, $.fn.hilight.defaults, options);
	};
	$.fn.hilight.defaults = {     
		foreground: 'red',
		background: 'yellow'
	}; 
	$.fn.hilight.defaults.foreground = 'blue'; 
	$('#myDiv').hilight();
	//--------------
	
	//保持私有函数的私有性
	$.fn.hilight = function(options) {
		debug(this);     
	};
	function debug($obj) {
		if (window.console && window.console.log)
			window.console.log('hilight selection count: ' + $obj.size());
	};
	//---------
*/
	
	
	
	
	
})(jQuery);

</script>
</head>
<body>
hello
<div>world</div>
</body>
</html>