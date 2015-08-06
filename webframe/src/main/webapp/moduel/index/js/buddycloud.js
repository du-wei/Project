;(function($){
	$.extend($.fn, {
		buddycloud : function(){						
			var innerElement = $(this);
		
			var transformString = Modernizr.prefixed('transform');
			var inputDown = "mousedown", inputMove = "mousemove", inputUp = "mouseup";
			if (window.Touch) {
				inputDown = "touchstart";
				inputMove = "touchmove";
				inputUp = "touchend";
			}
			 
			var mouse = { startX: 0, startY: 0 };
			
			function normalizedX(event){
				return window.Touch ? event.originalEvent.touches[0].pageX : event.pageX;
			}	
			function normalizedY(event){
				return window.Touch ? event.originalEvent.touches[0].pageY : event.pageY;
			}
			function roundToMultiple(number, multiple){
				var value = number/multiple
				,   integer = Math.floor(value)
				,   rest = value - integer;
			  	return rest > 0.5 ? (integer+1)*multiple : integer*multiple;
			}
			function move(event){
				event.preventDefault();
				var offsetX = normalizedX(event) - mouse.startX;
				var offsetY = normalizedY(event) - mouse.startY;
				if(event.shiftKey){
					offsetX = roundToMultiple(offsetX, 15);
				  	offsetY = roundToMultiple(offsetY, 15);
				}
				innerElement.css(transformString, 'rotateY('+offsetX+'deg) rotateX('+-offsetY+'deg)');
			}
			 
			$(document).bind(inputDown, function(event){
				event.preventDefault();
			  	if(event.button === 2) return true; // right-click
			  	mouse.startX = normalizedX(event);
			  	mouse.startY = normalizedY(event);
			  	$(document).bind(inputMove, move).one(inputUp, function(){ 
			  		$(document).unbind(inputMove); 
			  	});
			});
		}
	});
})(jQuery);
	
 
