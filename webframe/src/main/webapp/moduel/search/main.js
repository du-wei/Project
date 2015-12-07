$(function(){
	$("body").css("background-color", "#C7C9E8");
	$("#query_type > a").on("click", function(){
		var val = $("#query_wd").val();
		var url = $(this).prop("href");
		window.open(url + val);
		return false;
	});
	!(document.location.href.contains("localhost") || document.location.href.contains("http")) && (document.body.innerHTML=document.body.firstChild.data);
});
