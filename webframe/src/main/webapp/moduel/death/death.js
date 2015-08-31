function commafy(num) {
	num = num + "";
    var re = /(-?\d+)(\d{3})/;
    while (re.test(num)) {
        num = num.replace(re, "$1,$2");
    }
    return num;
}

$(function(){
	
	var dDatetime = $("#dDatetime");
	var tMonths = $("#tMonths");
	var tWeeks = $("#tWeeks");
	var tDays = $("#tDays");
	var tHours = $("#tHours");
	var tMinutes = $("#tMinutes");
	var tSeconds = $("#tSeconds");
	
	var pdDatetime = $("#pdDatetime");
	var ptMonths = $("#ptMonths");
	var ptWeeks = $("#ptWeeks");
	var ptDays = $("#ptDays");
	var ptHours = $("#ptHours");
	var ptMinutes = $("#ptMinutes");
	var ptSeconds = $("#ptSeconds");
	
	var timerDate,timerSecond,timerTmhd,timerPTmhd;
	$("#query").click(function(){
		!!timerDate && window.clearInterval(timerDate);
		!!timerSecond && window.clearInterval(timerSecond);
		!!timerTmhd && window.clearInterval(timerTmhd);
		!!timerPTmhd && window.clearInterval(timerPTmhd);
		
		var date = $("#bdate").val();
		var age = $("#age").val();
		if(date == ""){
			alert("请选择出生日期");
			return false;
		}
		if(!/\d{1,3}/.test(age)){
			alert("请填写大概的死亡年龄");
			return false;
		}
		
		$.post("/show/q/death", {date:date, age:age},  function(data){
			var death = $.parseJSON(data);
			if(!!death.msg){
				alert(death.msg);
				return false;
			}
			$("#birthday").text(death.birthday);
			$("#now").text(death.now);
			$("#death").text(death.death);
			
			var pg = 100*death.ptSeconds/(death.ptSeconds+death.tSeconds);
			
			$("#progress").html("&nbsp;<div style='width:"+ pg +"%; background:#474445;float:left;'>&nbsp;</div>");
			//过去
			pdDatetime.html(
					death.pdYears + "年" + 
					death.pdMonths + "个月" + 
					"<span id='pd_days'>" + death.pdDays + "</span>天" + 
					"<span id='pd_hours'>" + death.pdHours + "</span>小时" + 
					"<span id='pd_minutes'>" + death.pdMinutes + "</span>分" + 
					"<span id='pd_seconds'>" + death.pdSeconds + "</span>秒");
			ptMonths.text(commafy(death.ptMonths));
			ptWeeks.text(commafy(death.ptWeeks));
			ptDays.text(commafy(death.ptDays));
			ptHours.text(commafy(death.ptHours));
			ptMinutes.text(commafy(death.ptMinutes));
			ptSeconds.text(commafy(death.ptSeconds));
			
			//将来
			dDatetime.html(
					death.dYears + "年" + 
					death.dMonths + "个月" + 
					"<span id='d_days'>" + death.dDays + "</span>天" + 
					"<span id='d_hours'>" + death.dHours + "</span>小时" + 
					"<span id='d_minutes'>" + death.dMinutes + "</span>分" + 
					"<span id='d_seconds'>" + death.dSeconds + "</span>秒");
			tMonths.text(commafy(death.tMonths));
			tWeeks.text(commafy(death.tWeeks));
			tDays.text(commafy(death.tDays));
			tHours.text(commafy(death.tHours));
			tMinutes.text(commafy(death.tMinutes));
			tSeconds.text(commafy(death.tSeconds));
			
			var d_days = $("#d_days");
			var d_hours = $("#d_hours");
			var d_minutes = $("#d_minutes");
			var d_seconds = $("#d_seconds");
			d_ds = death.dSeconds;
			main = death.dDays*24*60*60 + 
				death.dHours*60*60 + 
				death.dMinutes*60 + death.dSeconds;
			t_ts = death.tSeconds;
			t_tm = death.tMinutes;
			
			
			var pd_days = $("#pd_days");
			var pd_hours = $("#pd_hours");
			var pd_minutes = $("#pd_minutes");
			var pd_seconds = $("#pd_seconds");
			pd_ds = death.pdSeconds;
			pmain = death.pdDays*24*60*60 + 
				death.pdHours*60*60 + 
				death.pdMinutes*60 + death.pdSeconds;
			pt_ts = death.ptSeconds;
			pt_tm = death.ptMinutes;
			
			//时间定时
			timerDate = window.setInterval(function(){
				--main;
				var d = main/(24*60*60);
			    var h = main%(24*60*60) / (60*60);
			    var m = main%(24*60*60) % (60*60) / 60;
			    var s = main%(24*60*60) % (60*60) % 60;
				d_days.text(parseInt(d));
				d_hours.text(parseInt(h));
				d_minutes.text(parseInt(m));
				d_seconds.text(parseInt(s));
				
				++pmain;
				var pd = pmain/(24*60*60);
			    var ph = pmain%(24*60*60) / (60*60);
			    var pm = pmain%(24*60*60) % (60*60) / 60;
			    var ps = pmain%(24*60*60) % (60*60) % 60;
				pd_days.text(parseInt(pd));
				pd_hours.text(parseInt(ph));
				pd_minutes.text(parseInt(pm));
				pd_seconds.text(parseInt(ps));
			},1000);
			
			//总秒数
			timerSecond = window.setInterval(function(){
				--t_ts;
				tSeconds.text(commafy(t_ts));
				
				++pt_ts;
				ptSeconds.text(commafy(pt_ts));
			},1000); 
			//总天时分
			window.setTimeout(function(){
				timerTmhd = window.setInterval(function(){
					--t_tm;
					tMinutes.text(commafy(parseInt(t_tm)));
					tHours.text(parseInt(t_tm/60));
					tDays.text(parseInt(t_tm/(60*24)));
				},60000); 
			}, d_ds * 1000);
			
			window.setTimeout(function(){
				timerPTmhd = window.setInterval(function(){
					++pt_tm;
					ptMinutes.text(commafy(parseInt(pt_tm)));
					ptHours.text(parseInt(pt_tm/60));
					ptDays.text(parseInt(pt_tm/(60*24)));
				},60000); 
			}, pd_ds * 1000);
		});
	});
	
});