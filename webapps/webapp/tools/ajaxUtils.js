;(function($){
    
	$.extend({
		ajaxData : function(url, settings){  //同步获取数据并返回
			var defaults = {
				data:{},
				dataType : "text",
				type : "post",
				timeout : 20000,
				cache : false,
				dataFilter : function(){},
				callback : function(){}
			};
			var opts = $.extend(defaults, settings);
			
			return $.ajax({
				url : url,
				data : opts.data ,
				dataFilter : function(val, type){
					opts.dataFilter(val, type);
				},
				dataType : opts.dataType, //xml html script json jsonp text
				async : false,
				cache : opts.cache,
				crossDomain : false,
				type : opts.type,
				timeout : opts.timeout
			}).done(function(status, statusTest, xhr){
				opts.callback(xhr);
			}).responseText;
		},
		getData : function(url, data, dataFilter){
			return $.ajaxData(url, {data:data, dataFilter:dataFilter});
		}
	});
    
})(jQuery);
