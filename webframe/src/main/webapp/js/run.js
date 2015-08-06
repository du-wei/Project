;(function($){
	
    /*var domAnimator = new DomAnimator();
    var frame1 = ['       .-"-.       ',
                  '     _/.-.-.\\_     ',
                  '    ( ( o o ) )    ',
                  '     |/  "  \\|     ',
                  "      \\'/^\\'/      ",
                  '      /`\\ /`\\      ',
                  '     /  /|\\  \\     ',      
                  '    ( (/ T \\) )    ',
                  '     \\__/^\\__/     '];

    var frame2 = ['       .-"-.       ',
                  '     _/_-.-_\\_     ',
                  '    / __> <__ \\    ',
                  '   / //  "  \\\\ \\   ',
                  "  / / \\'---'/ \\ \\  ",
                  '  \\ \\_/`"""`\\_/ /  ',
                  '   \\           /   ',      
                  '    \\         /    ',
                  '     |   .   |     ']

    var frame3 = ['       .-"-.       ',
                  '     _/_-.-_\\_     ',
                  '    /|( o o )|\\    ',
                  '   | //  "  \\\\ |   ',
                  "  / / \\'---'/ \\ \\  ",
                  '  \\ \\_/`"""`\\_/ /  ',
                  '   \\           /   ',      
                  '    \\         /    ',
                  '     |   .   |     ']

    domAnimator.addFrame(frame1);
    domAnimator.addFrame(frame2);
    domAnimator.addFrame(frame3);
    domAnimator.animate(1000);*/
	
	$(document).bind("contextmenu", function(e){
		  e.preventDefault();  
		  return false;
	});
	$.extend({
		run : function(css, type){
			var $head = $("head");
			var $link = $head.find("link");
			var size = $link.size();
			
			if(size <= 1){
				for(var i=0; i<css.length; i++){
					$link.eq(0).before("<link type='text/css' href='" + css[i] + "' rel='stylesheet'/>");
				}
			}else{
				if(type == true){//up
					for(var i=0; i<css.length; i++){
						$link.eq(1).before("<link type='text/css' href='" + css[i] + "' rel='stylesheet'/>");
					}
				}else{
					for(var i=css.length; i>0; i--){
						$link.eq(size-1).after("<link type='text/css' href='" + css[i-1] + "' rel='stylesheet'/>");
					}
				}
			}
		}
	});
})(jQuery);
