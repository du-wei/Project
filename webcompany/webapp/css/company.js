function $(op){
	return document.getElementById(op);
}

function page(op){
	if($('page').value > $('hidden').value){
		alert($('page').value + "页不存在，最多 " + $('hidden').value + "页");
		$("enter").href = window.location;
		return false;
	}else{
		$("enter").href = (op+"?currentPage="+$('page').value);
	}
}