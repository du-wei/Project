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
<link rel="stylesheet" type="text/css" href="css/project-validate.css"/>
<script type="text/javascript" src="common/jquery-1.10.2.js"></script>
<script type="text/javascript" src="common/jquery.validate-1.11.1.js"></script>
<script type="text/javascript" src="tools/jqueryUtils.js"></script>
<script type="text/javascript">
$(function(){

	var ok = [1, 2, 3, 4, 1];
	//ok.removeByVal(1, true);
	//alert(ok.toString());
	//ok.viewProp();
	$.extend($.expr[":"], {
		le : function(elem, i, match){
			return i < match[3] - 0 || i == match[3] - 0;
		}
	});

	$("#inserts").click(function(){
		$.post("jsonAction!temp", {}, null, "json").done(function(val){
			$("#oks").tmpl(val).done(function(tpl, item){
				tpl.text(item.name);
			});
		});
	});

	$("#insert").click(function(){
		$("#oks").autoTmpl("jsonAction!temp", {}).done( function(tpl, item){
			tpl.text(item.name);
		});
	});

	$.validator.setDefaults({
		debug : true
	});
	
	$.validator.addMethod("regex", function(value, element, params) {
		var exp = new RegExp(params); 
	    return this.optional(element) || exp.test(value);
	}, "regex error");

	var validObj = {
		rules : {
			name : {
				required : true,
				minlength : 3,
				maxlength : 5
			},
			names : {
				required : true,
				regex : "^\\d+$"
			}
		},
		messages : {
			name: {
				required : "required",
				minlength : " 2 2",
				maxlength : " 2 5"
			},
			names : {
				required : "required",
				regex : "xx"
			}	
		}
	};
	
	var validDefault = {
		//onfocusout : false,		//焦点离开时验证（单选/复选按钮除外）
		//onkeyup : false,			//当键盘按键弹起时验证
		//onclick: false, 			//鼠标点击验证针对单选和复选按钮。
		//focusInvalid: false, 		//当验证无效时，焦点跳到第一个无效的表单元素
		//focusCleanup: true,		//当验证无效时，焦点跳到第一个无效的表单元素,并且隐藏提示,与上面互斥
		rules: {},
		messages: {},
		//ignore : "#xx",
		submitHandler: function(form) {
			//alert("表单通过验证，提交表单");
		},
		invalidHandler: function(form, validator) {
			//alert("未通过验证的表单提交");
		},
		errorClass : "error",
		validClass : "valid",
		
		//wrapper : "div",
		//errorLabelContainer : "#div",
		errorElement : "label",
		errorPlacement : function(error, element){
			var $errorDiv = $("#" + element.prop("name") + "Tip");
			if($errorDiv.size()){
				$errorDiv.empty().append(error);
			}else{
				error.insertAfter(element);
			}
		},
		errorContainer : "#label",
		showErrors: function(errorMap, errorList) {
			//$("#label").html(this.numberOfInvalids() + " errors");
			//this.defaultShowErrors();
		},
		success: function(label) {
	        //label.addClass("valid").text("Ok!");
	    },
	    highlight: function(element, errorClass) {
	        $(element).fadeOut(function() {
	            $(element).fadeIn();
	        });
	    }	
	};
	
	var valid = $.extend({}, validDefault, validObj);
	
	var form = $("#hello").validate(valid);
	
	//alert($("#hello").valid());
	//alert(form.form());
	//alert($.viewJson($("#name").rules()));
});
</script>
<style type="text/css"></style>
</head>
<body>
<div id="div"></div>
<div id="label"></div>
<form action="" method="get" id="hello" name="">
<input type="text" name="name" id="name" />
<span id="nameTip"></span>

<br>
<input type="text" name="names" id="names"/>
<br>
<input type="submit" id="sub" value="sub"/>
</form>

</body>
</html>