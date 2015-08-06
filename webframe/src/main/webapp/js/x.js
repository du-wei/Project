$(function(){
	$.post("/token", function(val){
		if(val == true) $("body").data("w", "i");
	});
});