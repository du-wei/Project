Ext.onReady(function(){
	Ext.state.Manager.setProvider(new Ext.state.CookieProvider());
	
		var msg = new Ext.Panel({
			title:'navigation',
			spilt:true,
			collapsible:true,
			region:'center',
			margins:'1 0 1 1',
			html:'<iframe width=100% height=100% name="msg" src=chat/message.jsp />'
		});
		var online = new Ext.Panel({
			title:'online',
			width:150,
			spilt:true,
			collapsible:true,
			region:'east',
			margins:'1 0 1 1',
			html:'<iframe width=100% height=100% src=chat/userList.jsp />'
		});
		var sendMsg = new Ext.Panel({
			title:'message',
			spilt:true,
			height:150,
			collapsible:true,
			region:'south',
			margins:'1 0 1 1',
			html:'<iframe width=100% height=100% src=chat/sendMessage.jsp />'
		});
		var login = new Ext.Window({
			title:'layout window',
			width:500,
			height:450,
			closable:true,
			plain:true,
			layout:'border',
			items:[msg, online, sendMsg]
		});
		login.show();
	
});