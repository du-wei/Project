$(function(){
    var inx = ['bounce', 'flash', 'pulse', 'rubberBand', 'shake', 'swing'/*, 'tada', 'wobble'*/];
    
    //28 = 5 + 9 + 4 + 5 + 2 + 5
    var ins = ['bounceIn','bounceInDown','bounceInLeft','bounceInRight','bounceInUp',
               'fadeIn','fadeInDown','fadeInDownBig','fadeInLeft','fadeInLeftBig','fadeInRight','fadeInRightBig','fadeInUp','fadeInUpBig',
               /*'flip',*/'flipInX','flipInY','lightSpeedIn',
               'rotateIn','rotateInDownLeft','rotateInDownRight','rotateInUpLeft','rotateInUpRight',
               'rollIn',
               'zoomIn','zoomInDown','zoomInLeft','zoomInRight','zoomInUp'];
    
    //28 = 5 + 9 + 3 + 4 + 1 + 5
    var out = ['bounceOut','bounceOutDown','bounceOutLeft','bounceOutRight','bounceOutUp',
               'fadeOut','fadeOutDown','fadeOutDownBig','fadeOutLeft','fadeOutLeftBig','fadeOutRight','fadeOutRightBig','fadeOutUp','fadeOutUpBig',
               'flipOutX','flipOutY','lightSpeedOut',
               'rotateOut','rotateOutDownLeft','rotateOutUpLeft','rotateOutUpRight',
               'hinge','rollOut',
               'zoomOut','zoomOutDown','zoomOutLeft','zoomOutRight','zoomOutUp'];
	
	var menuItems = $('#menu li');
	var active = "active";
	menuItems.on("click", function(event) {
		menuItems.removeClass(active);
		
		var $this = $(this);
		var tab = $this.attr("class");
		var main = tab + "-body";
		
		var $tabbody = $("#tab-body");
		$tabbody.children("div").hide();
		//$tabbody.css({"background": $this.data("bg-color")});
		$this.addClass(active);
		
		var anim = ins[Math.floor(Math.random()*28)];
		
		$("#" + main).show().removeClass().addClass(anim + ' animated')
			.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
			$(this).attr("class", main);
	    });
		
		event.preventDefault();
	});
	menuItems.first().trigger("click");
});
  
