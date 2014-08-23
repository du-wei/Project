function $(op){
	return document.getElementById(op);
}
function valiChat(){
	if(valiChatUser() && valiChatPwd()){
		return true;
	}
	return false;
}
function valiRegister(){
	if(valiChatUser() && valiChatPwd() && valiChatRePwd()){
		return true;
	}
	return false;
}
function valiChatUser(){
	if($('userName').value === null || $('userName').value === ""){
		$('userDiv').innerHTML = "<font color='red'>账户不能为空！</font>";
		return false;
	}else{
		$('userDiv').innerHTML = "";
		return true;
	}
}
function valiChatPwd(){
	if($('password').value === null || $('password').value === ""){
		$('pwdDiv').innerHTML = "<font color='red'>密码不能为空！</font>";
		return false;
	}else{
		$('pwdDiv').innerHTML = "";
		return true;
	}
}
function valiChatRePwd(){
	if($('repwd').value === null || $('repwd').value === ""){
		$('repwdDiv').innerHTML = "<font color='red'>确认密码不能为空！</font>";
		return false;
	}else if($('password').value != $('repwd').value){
		$('repwdDiv').innerHTML = "<font color='red'>两次密码不一致！</font>";
		return false;
	}else{
		$('repwdDiv').innerHTMl = "";
		return true;
	}
}

