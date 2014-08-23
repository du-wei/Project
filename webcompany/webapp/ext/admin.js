Ext.onReady(function(){
    var left = new Ext.Panel({
    		title:'π‹¿Ì‘±',
        region:'west',
        margins:'5 0 5 5',
        width: 210,
        collapsible:true,
				split:true,
        layout:'accordion',
        html:'<iframe width=100% height=100% src="admin/left.jsp" />'
    });
    
    var right = new Ext.Panel({
    		region:'center',
    		margins:'5 5 5 0',
    		collapsible:true,
    		split:true,
    		layout:'fit',
    		spilt:true,
    		html:'<iframe width=100% height=100% name="main" src=admin/right.jsp />'
    })

    var vp = new Ext.Viewport({
				layout:"border",
				split:true,
				items:[
					left,right
				]
		});
})
