$(function(){
	
	$(document).on("keydown", function(e){
		if(e.keyCode === 13){
			$("a[id^='query']").trigger("click");
		}
	});
	$("#queryTel").click(function(){
    	$.get("/utils/q/tel", {tel : $("#tel").val()}).done(function(val){
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
    	$.get("/utils/q/ip", {ip : $("#ip").val()}).done(function(val){
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
    	$.get("/utils/q/id", {id : $("#ID").val()}).done(function(val){
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
