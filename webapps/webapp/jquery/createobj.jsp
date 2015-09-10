<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jquery test</title>
<script type="text/javascript" src="js/jquery162-custom1816.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	Function.prototype.proxy = function(context){
		return $.proxy(this, context);
	};
	
	var $loopOne = {
		init:function(){
			this.initDom();
			this.show();
			this.initEvent();
		},
		initDom:function(){
			this.color = new Array("#ffffff","#eeeeee","#00aaee","#222222","#999999");
			this.temp = "欢迎来到Javascript的世界，这里的生活丰富多彩";
			this.con = 1;
			this.$loopVal = $("#loopOne");
		},
		initEvent:function(){
			//setTimeout("", 200);
		},
		show:function(){
			alert("world");
			if(this.con <= this.temp.length){
				this.$loopVal.val(this.temp.substring(0, this.con)) ;
				this.$loopVal[0].style.color = this.color[this.con%5];
				this.con ++;
			}else{
				this.con = 1;
			}
		}
	}
	$loopOne.init();
	
});


var Class = {
	create:function(classObj){
		var ags = $.makeArray(arguments);
		args.shift();
		var tempObj = function(params){
			this.init.apply(this, params);
			this.initDom.apply(this, params);
			this.initEvent.apply(this, params);
			this.pageLoad.apply(this, params);
		};
		
		classObj.init = classObj.init || $.noop;
		classObj.initDom = classObj.initDom || $.noop;
		classObj.initEvent = classObj.initEvent || $.noop;
		classObj.pageLoad = classObj.pageLoad || $.noop;
		tempObj.prototype = classObj;
		var result = new tempObj(args);
		return result;
	}
}


</script>
<script language="JavaScript">
<!-- Hide
function showtime(){
    var now = new Date();
    var year = now.getYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var hours = now.getHours();
    var minutes = now.getMinutes();
    var seconds = now.getSeconds();
    var day = now.getDay();
	now.getDay = new Array("星期天","星期一","星期二","星期三","星期四", "星期五","星期六");
    var timeValue = "";
    timeValue += year + "年";
    timeValue += ((month < 10) ? "0" : "") + month + "月";
    timeValue += date + "日  ";
    timeValue += (Day[day]) + "  ";
    timeValue += ((hours <= 12) ? hours : hours - 12);
    timeValue += ((minutes < 10) ? ":0" : ":") + minutes;
    timeValue += ((seconds < 10) ? ":0" : ":") + seconds;
    timeValue += (hours < 12) ? "上午" : "下午";
    document.jsfrm.face.value = timeValue;
    timerID = setTimeout("showtime()", 1000);
    timerRunning = true
}

function startclock(){
    stopclock();
    showtime()
}
  //-->

</script>

</head>
<body>
<input type="text" id="loopOne" size="35" />
<input type="text" id="loopTwo" size="35" />
</body>
</html>