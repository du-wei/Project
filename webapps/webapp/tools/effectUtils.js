;(function($){
    $.fn.extend({
    	loop : function(interval){
    		var val = this.val();
    		val = val.substr(val.length-1, 1) + val.substr(0, val.length-1);
    		this.val(val);
    		typeof(loopSet) !== "undefined" && clearTimeout(loopSet);
    		loopSet = setTimeout("$('"+this.selector+"').loop(" + interval +")", interval ? interval : 200);
    	},
    	fadeInOut : function(str, interval){
    		var color = new Array("#ffffff","#eeeeee","#00aaee","#222222","#999999");
    		typeof(con) === "undefined" && (window.con = 1);
    		if(con <= str.length){
    			this.val(str.substr(0, con));
    			this.css("color", color[con%5]);
    			con ++;
    		}else{
    			con = 1;
    		}
    		typeof(fadeSet) !== "undefined" && clearTimeout(fadeSet);
    		fadeSet = setTimeout("$('"+this.selector+"').fadeInOut('"+str+ "'," + interval +")", interval ? interval : 200);
    	},
    	rolling : function(interval){
    		var tempId = 'demo';
    		var warpId = 'warp_div';
    		var temp = '#'+tempId;
    		var warp = '#'+warpId;
    		var $this = this;
    		$this.wrap("<div id='"+warpId+"'></div>");
    		$(warp).append("<div id='"+tempId+"'></div>").css({
    			overflow : "hidden",
    			height:100,
    			width:200
    		});
    		$(temp).html(this.html());
    		var show = function(){
    			if($(temp)[0].offsetTop-$(warp)[0].scrollTop<=0) $(warp)[0].scrollTop-=$this[0].offsetHeight;
    			else $(warp)[0].scrollTop++;
    		};
    		var MyMar=setInterval(show,interval ? interval : 30);
    		$(warp).hover(
    			function(){clearInterval(MyMar);},
    			function(){MyMar=setInterval(show,30);}
    		);
    		
    	}
    });
})(jQuery);


