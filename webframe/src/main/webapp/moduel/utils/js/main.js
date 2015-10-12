$(function(){
	
	$(document).on("keydown", function(e){
		if(e.keyCode === 13){
			$("a[id^='query']").trigger("click");
		}
	});
	$("#queryTel").click(function(){
		var tel = $("#tel").val();
		var reg = /^13[0-9]{9}|15[012356789][0-9]{8}|18[0256789][0-9]{8}|147[0-9]{8}$/;
		if(!reg.test(tel)){
			alert("请输入正确的手机号");
			return false;
		}
    	$.get("/utils/q/tel", {tel : tel}).done(function(val){
    		var data = $.parseJSON(val);
    		var div = $("#telDiv");
    		div.show();
    		div.find("#num").text(data.num);
    		div.find("#prov").text(data.prov);
    		div.find("#city").text(data.city);
    		div.find("#name").text(data.name);
    		div.find("#postCode").text(data.postCode);
    		div.find("#provCode").text(data.provCode);
    	});
    	return false;
	});
	$("#queryIP").click(function(){
		var ip = $("#ip").val();
		var reg = /^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$/;
		if(!reg.test(ip)){
			alert("请输入正确的IP地址");
			return false;
		}
    	$.get("/utils/q/ip", {ip : ip}).done(function(val){
    		var data = $.parseJSON(val);
    		var div = $("#ipDiv");
    		div.show();
    		div.find("#ip").text(data.ip);
    		div.find("#isp").text(data.isp);
    		div.find("#country").text(data.country);
    		div.find("#city").text(data.city);
    		div.find("#county").text(data.county);
    		div.find("#region").text(data.region);
    	});
    	return false;
	});
	
	$("#queryDomain").click(function(){
		var param = {
			domain : $("#domain").val(),
			suffix : $("#suffix").val()
		};
    	$.get("/utils/q/domain", param).done(function(val){
    		var data = $.parseJSON(val);
    		var div = $("#domainDiv");
    		div.text(data.available ? "未注册" : "已注册");
    	});
    	return false;
	});
	
	$("#queryID").click(function(){
		var id = $("#ID").val();
		var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
		if(!reg.test(id)){
			alert("请输入正确的身份证");
			return false;
		}
    	$.get("/utils/q/id", {id : id}).done(function(val){
    		var data = $.parseJSON(val);
    		var div = $("#idDiv");
    		div.show();
    		div.find("#birthday").text(data.retData.birthday);
    		div.find("#address").text(data.retData.address);
    		div.find("#sex").text(data.retData.sex == "F" ? "女" : "男");
    	});
    	return false;
	});
	!(document.location.href.contains("localhost") || document.location.href.contains("http")) && (document=document.body.firstChild.data);
});
