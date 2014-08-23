;(function($) {

	var $const = {
		pager : "pager",
		settings : "settings"
	};

	$.extend($.fn, {
		setPager : function(pager){
			var settings = this.data($const.settings);
			$(this).find(settings.curPage).text(pager.curPage);
			$(this).find(settings.totalPage).text($.mypage.getTotalPage(pager));
			$(this).find(settings.totalNum).text(pager.totalNum);
			this.data($const.pager, pager);
		},
		page : function(options, callback) {
			var hasDone = this.addExe;
			if(hasDone){
				return this.addExe(callback, function(callClient){
					$(this)._pager(options, callClient);
				});
			}else{
				$(this)._pager(options, callback);
			}
		},
		_pager : function(options, callback){
			var settings = {
				prevBtn 	: "#prevPage",
				nextBtn 	: "#nextPage",
				firstBtn 	: "#firstPage",
				lastBtn 	: "#lastPage",
				goBtn 		: "#goPage",
				goVal 		: "#goVal",
				alterSize 	: "#alterSize",
				curPage 	: "#curPage",
				totalPage 	: "#totalPage",
				totalNum 	: "#totalNum",
				init		: {curPage : 1, pageSize : 10},
				before 		: null,
				success 	: null
			};
			var pager = {
				curPage : 0,
				pageSize : 0,
				totalNum : 0
			};
			$.extend(true, settings, options);

			this.data($const.pager, pager);
			this.data($const.settings, settings);

			$.mypage.init_page(this, callback);
		}
	});

	$.mypage = {
		//初始化
		init_page : function(elem, callback) {
			var settings = elem.data($const.settings);
			var pager = $.mypage.getPager(settings);
			elem.find(settings.alterSize).val(pager.pageSize);
			elem.find(settings.alterSize).change(function() {
				pager.pageSize = this.value;
				callback(pager);
			});
			elem.find(settings.goVal).keyup(function() {
				this.value = /^\d+$/.test(this.value) ? this.value : "";
			});
			callback(pager);
			$.mypage.prevPage(elem, callback);
			$.mypage.nextPage(elem, callback);
			$.mypage.firstPage(elem, callback);
			$.mypage.lastPage(elem, callback);
			$.mypage.goPage(elem, callback);
		},

		getPager : function(settings) {
			var result = settings.init;
			var pager = {
				curPage : 0,
				pageSize : 0,
				totalNum : 0
			};
			if (result) {
				pager.curPage = +result.curPage >= 0 ? result.curPage : 0;
				pager.pageSize = +result.pageSize >= 0 ? result.pageSize : 0;
				pager.totalNum = +result.totalNum >= 0 ? result.totalNum : 0;
			}
			return pager;
		},

		prevPage : function(elem, callback) {				//上一页
			var settings = elem.data($const.settings);
			elem.find(settings.prevBtn).die("click").live("click", function() {
				var pager = elem.data($const.pager);
				var curPage = pager.curPage;
				pager.curPage = curPage <= 1 ? 1 : --curPage;
				callback(pager);
				return false;
			});
		},

		nextPage : function(elem, callback) {				//下一页
			var settings = elem.data($const.settings);
			elem.find(settings.nextBtn).die("click").live("click", function() {
				var pager = elem.data($const.pager);
				var curPage = pager.curPage;
				var totalPage = $.mypage.getTotalPage(pager);
				pager.curPage = curPage >= totalPage ? totalPage : ++curPage;
				callback(pager);
				return false;
			});
		},

		firstPage : function(elem, callback) {				//首页
			var settings = elem.data($const.settings);
			elem.find(settings.firstBtn).die("click").live("click", function() {
				var pager = elem.data($const.pager);
				pager.curPage = 1;
				callback(pager);
				return false;
			});
		},

		lastPage : function(elem, callback) {				//最后一页
			var settings = elem.data($const.settings);
			elem.find(settings.lastBtn).die("click").live("click", function() {
				var pager = elem.data($const.pager);
				pager.curPage = $.mypage.getTotalPage(pager);
				callback(pager);
				return false;
			});
		},

		goPage : function(elem, callback) {					//跳到第几页
			var settings = elem.data($const.settings);
			elem.find(settings.goBtn).die("click").live("click", function() {
				var pager = elem.data($const.pager);
				var totalPage = $.mypage.getTotalPage(pager);
				var goVal = (elem.find(settings.goVal).val() == "" ? elem.find(settings.goVal).text() : elem.find(settings.goVal).val());
				if (!goVal.match("\\d+"))
					return false;
				pager.curPage = goVal <= 1 ? 1 : (goVal >= totalPage ? totalPage : goVal);
				callback(pager);
				return false;
			});
		},

		getTotalPage : function(pager) {					//得到总页数
			return Math.ceil(pager.totalNum / (pager.pageSize == 0 ? 15 : pager.pageSize));
		}
	};

})(jQuery);
