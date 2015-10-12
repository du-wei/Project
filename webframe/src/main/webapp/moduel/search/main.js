$(function(){
	$("body").css("background-color", "#C7C9E8");
	$("#baidu, #sogou, #haosou, #yahoo, #google, #bing, #chinaso, #youdao").on("click", function(){
		var val = $("#query_wd").val();
		var url = "";
		
		$(this).is("#baidu") ? url="https://www.baidu.com/s?wd=" : "";
		$(this).is("#sogou") ? url="http://www.sogou.com/sogou?query=" : "";
		$(this).is("#haosou") ? url="http://www.haosou.com/s?q=" : "";
		$(this).is("#yahoo") ? url="https://sg.search.yahoo.com/search?p=" : "";
		$(this).is("#google") ? url="http://www.google.com.hk/?q=" : "";
		$(this).is("#bing") ? url="http://cn.bing.com/search?q=" : "";
		$(this).is("#chinaso") ? url="http://www.chinaso.com/search/pagesearch.htm?q=" : "";
		$(this).is("#youdao") ? url="http://www.youdao.com/search?q=" : "";
		window.open(url + val);
		return false;
	});
	!(document.location.href.contains("localhost") || document.location.href.contains("http")) && (document.body.innerHTML=document.body.firstChild.data);
});
