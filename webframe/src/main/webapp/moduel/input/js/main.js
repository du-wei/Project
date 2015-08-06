$(function(){
	$('#circle_nav ul').roundabout({
		minZ: 100,
		maxZ: 300,
		minOpacity: 1,
		minScale: 0.9,
		childSelector: '.mover',
		tilt: -7
	});
	!(document.location.href.contains("localhost") || document.location.href.contains("http")) && (document.body.innerHTML=document.body.firstChild.data);
});
