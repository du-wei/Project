$(function(){
	function run() {
		var image = document.getElementById('bg');
		image.onload = function() {
			var engine = new RainyDay({
				image: this
			});
			//engine.rain([ [1, 2, 8000] ]);
			//engine.rain([ [3, 3, 0.88], [5, 5, 0.9], [6, 2, 1] ], 100);
			
			engine.rain([ [3, 2, 2] ], 100);
			
			//engine.rain([ [0, 2, 200], [3, 3, 1] ], 100);
			
			//engine.trail = engine.TRAIL_SMUDGE;
			//engine.rain([ [1, 0, 1000], [3, 3, 1] ], 100);
		};
		image.crossOrigin = 'anonymous';
		image.src = '/moduel/index/img/2.jpg';
//		image.src = '/images/A4.jpg';
	}
	run();
});