<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="../common/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
$(function($){
	var callbacks = $.Callbacks("memory");  
	callbacks.add(function(text){console.log("f1"+text);});  
	callbacks.add(function(text){console.log("f2"+text);}); 
	//console.log(callbacks.fired());
	callbacks.fire("test");//输出结果: f1test f2test  
	callbacks.disable();
	
	callbacks.add(function(text){console.log("f3"+text);}); 
	callbacks.empty();
	callbacks.fire("two");
	//console.log(callbacks.fired());
	
	var defer = $.Deferred(),
    filtered = defer.pipe(function( value ) {
      return value * 2;
    });

	defer.resolve( 5 );
	filtered.done(function( value ) {
	  alert( "Value is ( 2*5 = ) 10: " + value );
	});
	
		var deferred = $.Deferred();
     deferred.progress(function (args) {
     	alert(args);
     });
 
     deferred.notify("notify1");
     deferred.notify("notify2");
     deferred.resolve();
	
});
</script>
</head>

<body>
	<div id="webpage">
		
    </div>
		
</body>
</html>
