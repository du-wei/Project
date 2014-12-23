<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="js/themes/redmond/jquery-ui-1.8.16.custom.css" media="screen"/>
<script type="text/javascript" src="../common/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.16.custom.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//slideDown,fadeIn,blind,bounce,clip,drop,fold,slide
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
	var options = {
		dateFormat:'yy-mm-dd',
		firstDay:0,
		changeYear:true,
		changeMonth:true,
		buttonImage:'js/images/calendar.gif',
		showOn:'both',
		buttonText:"date",
		//closeText:"close",
		//currentText:"ok",
		//gotoCurrent:true,
		//nextText:"next",
		//shortYearCutoff:30,
		//showButtonPanel:true,
		showAnim:'drop'
	}
	$("#fromDate, #toDate").datepicker(options);

	$("#ui-datepicker-div").css({fontSize:'12px'});
	
	$("#accordion").css("width",200).accordion({
		icons:{ 'header': 'ui-icon-plus', 'headerSelected': 'ui-icon-minus' },
		collapsible:false,
		event:'mouseover',
		animated:'slideDown'
	});
	
	$("#each").click(function(){
		$.each([0,1,2], function(index, value){
	  		alert("Item #" + index + ": " + value);
		});
		$.each({name:"John", lang:"JS"}, function(key, value){
			alert("name:" + key + ", value:" + value);
		});
	})

});
</script>
<style type="text/css">
select.ui-datepicker-month, select.ui-datepicker-year {
	width:40%;
}
</style>
</head>
<body>
<h3>datepicker</h3>
<div id="datepicker" class="div">
	开始时间 <input type="text" id="fromDate"><br/>
	结束时间 <input type="text" id="toDate">
</div>

<div id="each">each</div>

<div id="accordion">
	<h3>accordion1</h3>
	<div>hello world</div>
	<h3>accordion2</h3>
	<div>hello world</div>
	<h3>accordion3</h3>
	<div>hello world</div>
</div>

</body>
</html>