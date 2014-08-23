/**
* EasyDrag 1.5 - Drag & Drop jQuery Plug-in
* 
* http://fromvega.com/scripts
*/

(function($){

	var isMouseDown    = false;
	var curElem = null;
	var dropCallbacks = {};
	var dragCallbacks = {};
	
	//on off handler
	var dragStatus = {};	
	// bubbling status
	var bubblings = {};
	var lastMouseX = 0;
	var lastMouseY = 0;
	var lastElemTop = 0;
	var lastElemLeft = 0;
	
	// if user is holding any handle or not
	var holdingHandler = false;

	$(document).mousemove(function(e){
		if(isMouseDown && dragStatus[curElem.id] != 'false'){
			$.updatePosition(e);
			if(dragCallbacks[curElem.id] != undefined){
				dragCallbacks[curElem.id](e, curElem);
			}
			return false;
		}
	});

	$(document).mouseup(function(e){
		if(isMouseDown && dragStatus[curElem.id] != 'false'){
			isMouseDown = false;
			if(dropCallbacks[curElem.id] != undefined){
				dropCallbacks[curElem.id](e, curElem);
			}
			return false;
		}
	});

	$.extend({
		// returns the mouse (cursor) current position
		getMousePosition : function(e){
			var posx = 0;
			var posy = 0;
	
			if (!e) var e = window.event;
	
			if (e.pageX || e.pageY) {
				posx = e.pageX;
				posy = e.pageY;
			}
			else if (e.clientX || e.clientY) {
				posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
				posy = e.clientY + document.body.scrollTop  + document.documentElement.scrollTop;
			}
	
			return { 'x': posx, 'y': posy };
		},
		// updates the position of the current element being dragged
		updatePosition : function(e) {
			var pos = $.getMousePosition(e);
	
			var spanX = (pos.x - lastMouseX);
			var spanY = (pos.y - lastMouseY);
	
			$(curElem).css("top",  (lastElemTop + spanY));
			$(curElem).css("left", (lastElemLeft + spanX));
		}
	});

	$.extend($.fn, {
		// register the function to be called while an element is being dragged
		ondrag : function(callback){
			return this.each(function(){
				dragCallbacks[this.id] = callback;
			});
		},
		// register the function to be called when an element is dropped
		ondrop : function(callback){
			return this.each(function(){
				dropCallbacks[this.id] = callback;
			});
		},
		// disable the dragging feature for the element
		dragOff : function(){
			return this.each(function(){
				dragStatus[this.id] = 'off';
			});
		},
		// enable the dragging feature for the element
		dragOn : function(){
			return this.each(function(){
				dragStatus[this.id] = 'on';
			});
		},
		// set a child element as a handler
		setHandler : function(handler){
			return this.each(function(){
				var draggable = this;
				
				// enable event bubbling so the user can reach the handle
				bubblings[this.id] = true;
				
				// reset cursor style
				$(draggable).css("cursor", "");
				
				// set current drag status
				dragStatus[draggable.id] = "handler";
	
				var $handler = $(handler);
				$handler.css("cursor", "move");	
				
				$handler.mousedown(function(e){
					holdingHandler = true;
					$(draggable).trigger('mousedown', e);
				});
				
				$handler.mouseup(function(e){
					holdingHandler = false;
				});
			});
		},
		// set an element as draggable - allowBubbling enables/disables event bubbling
		easydrag : function(allowBubbling){
			return this.each(function(){
				if(undefined == this.id || !this.id.length) this.id = "easydrag"+(new Date().getTime());
				
				// save event bubbling status
				bubblings[this.id] = allowBubbling ? true : false;
	
				// set dragStatus 
				dragStatus[this.id] = "on";
				$(this).css("cursor", "move");
				$(this).mousedown(function(e){
					// just when "on" or "handler"
					if((dragStatus[this.id] == "off") || (dragStatus[this.id] == "handler" && !holdingHandler))
						return bubblings[this.id];
	
					// set it as absolute positioned
					$(this).css("position", "absolute");
					$(this).css("z-index", parseInt( new Date().getTime()/1000 ));
	
					isMouseDown    = true;
					curElem = this;
	
					var pos    = $.getMousePosition(e);
					lastMouseX = pos.x;
					lastMouseY = pos.y;
					lastElemTop  = this.offsetTop;
					lastElemLeft = this.offsetLeft;
					$.updatePosition(e);
					return bubblings[this.id];
				});
			});
		}
		
	});

})(jQuery);