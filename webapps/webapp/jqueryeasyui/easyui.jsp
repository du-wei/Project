<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>easyui layout</title>

<link rel="stylesheet" type="text/css" href="/jqueryeasyui/themes/default/easyui.css" />
<script type="text/javascript" src="../common/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/jqueryeasyui/js/jquery.easyui.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#layout").layout('collapse', 'north');
	$("#panel").panel({
		title:'hello world',
		iconCls:'icon-help',
		//content:'my panel',
		bodyCls:'panel',
		collapsible:true,
		maximizable:true,
		minimizable:true,
		closable:true,
		doSize:true,
		height:'100',
		loadingMessage:"hello world.......",
		tools:[{
			iconCls:'icon-add',
			handler:function(){alert("hello world");}
		},{
			iconCls:'icon-help',
			handler:function(){alert("help!");}
		}]

	});
	$("#tabs").tabs({
		height:'200',
		fit:true,
		border:false,
		onSelect:function(title){
			var pp = $("#tabs").tabs("getSelected");
		}
	}).tabs("select", "tab8");
	$("#accordion").accordion();
	$("#calendar-text").click(function(){
		$("#calendar").calendar();
	});

	$("#showImage").click(function(){
		$("<img />").insertAfter("#showImage").appendTo("<div id='showBigImage' />");

		$("#showBigImage").show().dialog({
			title:'',
			width:'300',
			height:'300',
			modal:true,
			closable:false,
			buttons:[{
				text:'close',
				handler:function(){
					$("#showBigImage").dialog("close");
				}
			}]
		});
	});
	$("#dialog-button").click(function(){
		$("#dialog").dialog({
			title:'dialog',
			width:'400',
			height:'300',
			iconCls:'icon-ok',
			minimizable:true,
			maximizable:true,
			resizable:true,
			collapsible:true,
			modal:true,
			shadow:true,
			toolbar:[{
				text:'ok',
				iconCls:'icon-ok',
				handler:function(){
					alert("hello dialog");
				}
			}],
			buttons:[{
				text:'save',
				iconCls:'icon-ok',
				handler:function(){
					alert("save");
				}
			},{
				text:'close',
				iconCls:'icon-cancel',
				handler:function(){
					$("#dialog").dialog("close");
				}
			}]
		});
	});
	$("#window-button").click(function(){
		$("#window").window({
			title:'window',
			width:'400',
			height:'300',
			iconCls:'icon-ok'
			//minimizable:true,
			//maximizable:true,
			//closable:true,
			//zindex:9000,
			//resizable:true,
			//collapsible:true,
			//modal:true,
			//shadow:true,
			//draggable:true,
			//inline:false,
		});
	});
	$("#validatebox").validatebox({
		required:true,
		validType:'email',	//email url length[0,100] remote["http://....action","paramName"]
		missingMessage:'This field is required',
		invalidMessage:'This field is invalid'
	});
	$("#numberbox").numberbox({
		required:true,
		min:0,
		max:100,
		precision:2,
		missingMessage:'This field is number',
		invalidMessage:'This field is number'
	});
    $.extend($.fn.validatebox.defaults.rules, {
        minLength: {
            validator: function(value, param){
                return value.length >= param[0];
            },
            message: 'Please enter at least {0} characters.'
        }
    });

	$("#draggable").draggable({
		handle:"#title"
		//revert:false
		//deltaX:0
		//deltaY:0
		//disabled:false
		//edge:500
	});

	$('.drag').draggable({
		proxy:'clone',
//		proxy:function(source){
//			var n = $('<div class="proxy"></div>');
//			n.html($(source).html()).appendTo('body');
//			return n;
//		}
		revert:true,
		cursor:'auto',
		onStartDrag:function(){
			$(this).draggable('options').cursor='not-allowed';
			$(this).draggable('proxy').addClass('dp');
		},
		onStopDrag:function(){
			$(this).draggable('options').cursor='auto';
		}
	});
	$('#target').droppable({
		accept:'#d1,#d3',
		onDragEnter:function(e,source){
			$(source).draggable('options').cursor='auto';
			$(source).draggable('proxy').css('border','1px solid red');
			$(this).addClass('over');
		},
		onDragLeave:function(e,source){
			$(source).draggable('options').cursor='not-allowed';
			$(source).draggable('proxy').css('border','1px solid #ccc');
			$(this).removeClass('over');
		},
		onDrop:function(e,source){
			$(this).append(source);
			$(this).removeClass('over');
		}
	});

	$("#combobox").combobox({
		url:"jqueryeasyui/js/combobox_data.json",
		valueField:"id",
		textField:"text",
		//multiple:"false",
		panelHeight:"auto"
		//panelWidth:"auto"
		//mode:"local"
		//method:"post"
		//filter:function(q, row){}
	});
	$("#com_box1").click(function(){
		$('#combobox').combobox('setValue','bitem3');
	})
	$("#com_box2").click(function(){
		var val = $('#combobox').combobox('getValue');
		alert(val);
	})
	$("#com_box3").click(function(){
		$('#combobox').combobox('disable');
	})
	$("#com_box4").click(function(){
		$('#combobox').combobox('enable');
	})

	$("#combo").combo({
		required:true,
		editable:false
	});
	$("#sp").appendTo($("#combo").combo("panel"));
	$('#sp input').click(function(){
		var v = $(this).val();
		var s = $(this).next('span').text();
		$('#combo').combo('setValue', v).combo('setText', s).combo('hidePanel');
	});

	$("#datagrid").datagrid({
		url:"js/datagrid_data.json",
		title:"my datagrid",
		iconCls:"icon-save",
		width:900,
		fitColumns:true,
		pagination:true,
		//pageNumber:2,
		pageSize:15,
		height:400,
		nowrap:false,
		striped:true,
		collapsible:true,
		sortName:"code",
		sortOrder:"desc",
		remoteSort:false,
		idFiled:"code",
		rownumbers:true,
		showFooter:true,
		frozenColumns:[[
			{field:'ck',checkbox:true},
	        {title:'code',field:'code',width:80,sortable:true}
		]],
		columns:[[
			{title:'one',colspan:3},
			{title:'two',field:'opt',width:100,align:"center",rowspan:2,formatter:function(value,rowData,rowIndex){
				return '<span style="color:red;">hello world</span>';
			}}
		],[
			{field:'name',title:'Name',width:120},
			{field:'addr',title:'Address',width:220,rowspan:2,sortable:true,sorter:function(a,b){
				return (a>b?1:-1);
			}},
			{field:'col4',title:'Col41',width:150,rowspan:2}
		]],
//		rowStyler:function(rowIndex,rowData){
//			return rowIndex%2==0?"background:#ffffff;":"background:#eeffee;";
//		},
		onSelect:function(rowIndex,rowData){
			alert(rowIndex + "----"+rowData);
		},
		toolbar:[{
			id:'btnadd',
			text:'Add',
			iconCls:'icon-add',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('add')
			}
		},{
			id:'btncut',
			text:'Cut',
			iconCls:'icon-cut',
			handler:function(){
				$('#btnsave').linkbutton('enable');
				alert('cut');
			}
		},'-',{
			id:'btnsave',
			text:'Save',
			disabled:true,
			iconCls:'icon-save',
			handler:function(){
				$('#btnsave').linkbutton('disable');
				alert('save');
			}
		}]
	});
	$("#button1").linkbutton();
	$("#button2").linkbutton({
		disabled:false,
		plain:false,
		text:"hello",
		iconCls:"icon-help"
	});
	$("#button3").linkbutton();
	$("#btn-menu").click(function(){
		$("#menu").menu("show",{
			left:100,
			top:50
		});
	});
	$("#menubutton").menubutton({
		menu:"#menu",
		plain:false,
		duration:50
	});
	$("#show").click(function(){
		$.messager.show({
			showType:"show",
			showSpeed:500,
			width:250,
			height:100,
			msg:"hello world",
			title:"show",
			timeout:0
		})
	}).linkbutton();
	$("#slide").click(function(){
		$.messager.show({
			title:"slide",
			showType:"slide",
			msg:"hello world",
			timeout:5000
		})
	}).linkbutton();
	$("#fade").click(function(){
		$.messager.show({
			title:"fade",
			showType:"fade",
			msg:"hello world",
			showSpeed:500,
			timeout:5000
		})
	}).linkbutton();

	$("#progress").click(function(){
		var win = $.messager.progress({
			title:"progrss",
			msg:"load...."
			//text:"hello progress",
			//interval:300
		});
		setTimeout(function(){
			$.messager.progress("close");
		},5000);
	}).linkbutton();
	$("#alert").click(function(){
		//error info question warning
		//title msg icon fn
		$.messager.alert("alert","hello alert","error",function(){
			alert("hello");
		});
	}).linkbutton();
	$("#confirm").click(function(){
		$.messager.confirm("comfirm", "hello comfirm", function(b){
			alert(b);
		});
	}).linkbutton();
	$("#prompt").linkbutton().click(function(){
		$.messager.prompt("prompt", "hello prompt",function(val){
			alert(val);
		});
	});
	$("#resizable").resizable({
		minWidth:10,
		minHeight:10,
		maxWidth:300,
		maxHeight:300,
		edge:10
	});
	$("#splitbutton").splitbutton({
		menu:"#mm"
	});
	$("#datebox").datebox({
		required:true,
		panelWidth:180,
		panelHeight:200,
		currentText:"今天",
		closeText:"关闭",
		okText:"确定",
    	//parser: function(date){ return new Date(Date.parse(date.replace(/-/g,"/")));},
		formatter: function(date){ return date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();}
	});
	$("#propertygrid").propertygrid({
		width:700,
		height:400,
		url:"js/propertygrid_data.json",
		showGroup:true,
		scrollbarSize:10,
		groupField:"group",
		//groupFormatter:function(){},
		showHeader:true
	});
	$("#property-change").click(function(){
		var rows = $("#propertygrid").propertygrid("getChanges");
		for(var i=0; i<rows.length; i++){
			alert(rows[i].name + ":" + rows[i].value);
		}
	});
	$("#progressbar").progressbar({
		width:400
		//value:0
		//text:"{value}%"
	});
	$("#progressbar-btn").click(function(){
		var value = $('#progressbar').progressbar('getValue');
		if (value < 100){
			value += Math.floor(Math.random() * 10);
			$('#progressbar').progressbar('setValue', value);
			setTimeout(arguments.callee, 200);
		}
	});
	$("#pagination").pagination({
		total:20,
		pageSize:15,
		pageNumber:1,
		pageList:[10,15,20,25,30],
		loading:false,
		buttons:[{
			iconCls:'icon-add',
			handler:function(){
				alert('add');
			}
		},{
			iconCls:'icon-cut',
			handler:function(){
				alert('cut');
			}
		}],
		showPageList:true,
		showRefresh:true,
		beforePageText:"page",
		afterPageText:" of {pages}",
		displayMsg:"displaying {from} to {to} of {total} items",
		onSelectPage:function(pageNumber, pageSize){
			$(this).pagination('loading');
			alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
			$(this).pagination('loaded');
		}
	});

	$('#searchbox').searchbox({
	     width:350,
	     searcher:function(value,name){
	         alert(value + "," + name)
	     },
	     menu:'#searchbox-mm',
		 value:"hello world",
	     prompt:'Please Input Value'
	 });

});

</script>
<style type="text/css">
#layout{
	height:600px;
}
#panel{
	background-color:#ffffff;
}
.datagrid-row-over{
	background:red;
}
.drag{
	width:100px;
	height:50px;
	padding:10px;
	margin:5px;
	border:1px solid #ccc;
	background:#AACCFF;
}
.dp{
	opacity:0.5;
	filter:alpha(opacity=50);
}
.over{
	background:#FBEC88;
}
</style>
</head>
<body>


<div id="layout" class="easyui-layout">
	<!-- title=null region="" border=true split=false icon=null href=null -->
	<!-- resize panel collapse expand-->
	<div region="east" icon="icon-help" title="East" style="width:200px;"></div>

    <div region="west" title="West" split="true" style="width:200px;">
		<!-- width height fit=false border=true animate=true iconCls -->
		<div id="accordion" fit="true">
			<div title="accordion1" selected="true" iconCls="icon-ok">hello world selected</div>
			<div title="accordion2">hello world</div>
		</div>
	</div>

	<div region="north" title="North Title" style="height:100px;"></div>

    <div region="center" title="center title" style="padding:0px;background:#eee;">
		<!-- width=auto height=auto plain=false fit=false border=true tools=null scrollDuration=400 scrollcrement=100 -->
		<div id="tabs">
			<!-- title="" content="" href=null cache=true iconCls=null width=auto height=auto closable=false selected=false -->
			<div title="tab1" iconCls="icon-add" closable="true">
				<div id="calendar"></div>
				<input type="text" id="calendar-text"/>
			</div>
			<div title="tab2" style="text-align:center;">
				<a href="javascript:void(0)" id="dialog-button" class="easyui-linkbutton">dialog</a>
				<div id="dialog">hello world</div>
				<a href="javascript:void(0)" id="window-button" class="easyui-linkbutton">window</a>
				<div id="window">hello world</div>
				<img src="../images/shoe1_small.jpg" id="showImage" />

			</div>
			<div title="tab3">
				<div id="draggable" style="border:1px solid red;width:100px;">
					<div id="title" style="background:#ccc;">title</div>
					hello world<br/>hello world<br/>hello world<br/>hello world
				</div>

				<div id="source" style="border:1px solid #ccc;width:300px;height:400px;float:left;margin:5px;">
					drag me!
					<div id="d1" class="drag">Drag 1</div>
					<div id="d2" class="drag">Drag 2</div>
					<div id="d3" class="drag">Drag 3</div>
				</div>
				<div id="target" style="border:1px solid #ccc;width:300px;height:400px;float:left;margin:5px;">
					drop here!
				</div>

			</div>
			<div title="tab4">
				<select id="combobox" name="dept">
				</select>
				<a href="#" id="com_box1" class="easyui-linkbutton">setValue</a>
				<a href="#" id="com_box2" class="easyui-linkbutton">getValue</a>
				<a href="#" id="com_box3" class="easyui-linkbutton">disable</a>
				<a href="#" id="com_box4" class="easyui-linkbutton">enable</a>

				<select id="combo" name="combo"></select>
				<div id="sp">
					<div style="color:#99BBE8;background:#fafafa;padding:5px;">Select a language</div>
					<input type="radio" name="lang" value="01" /><span>Java</span><br/>
					<input type="radio" name="lang" value="02" /><span>C#</span><br/>
					<input type="radio" name="lang" value="03" /><span>Ruby</span><br/>
				</div>
			</div>
			<div title="tab5" align="center" style="padding:10px;">
				<div align="left"><table id="datagrid"></table>
					<a href="" id="button1" disabled="true" plain="true" text="ok" iconCls="icon-ok"></a>
					<a href="" id="button2">he</a>
					<a href="" id="button3">hehe</a>
				</div>
			</div>
			<div title="tab6">
				<button id="btn-menu">menu</button>
				<a href="#" id="menubutton" iconCls="icon-edit">show</a>

                <a href="javascript:void(0)" id="splitbutton" iconCls="icon-ok" onclick="javascript:alert('ok')">Ok</a>
                <div id="mm" style="width:100px;">
                    <div iconCls="icon-ok">Ok</div>
                    <div iconCls="icon-cancel">Cancel</div>
                </div>

				<br/>
				<br/>
				<br/>
				<a href="#" id="show" class="easyui-linkbutton">show</a>
				<a href="#" id="slide">slide</a>
				<a href="#" id="fade">fade</a>
				<a href="#" id="progress">progress</a>
				<a href="#" id="alert">alert</a>
				<a href="#" id="confirm">confirm</a>
				<a href="#" id="prompt">prompt</a>
				<br/>
				<br/>
				<input id="datebox" type="text"/>
				<br/>
				<br/>
				<div id="resizable" style="width:100px;height:100px;border:1px solid #ccc;">
					hello world
				</div>

				<div id="menu" class="easyui-menu" style="width:120px;">
					<div onclick="javascript:alert('new')">New</div>
					<div>
						<span>Open</span>
						<div style="width:150px;">
							<div><b>Word</b></div>
							<div>
								<span>Window Demos</span>
								<div style="width:120px;">
									<div href="window.html">Window</div>
									<div href="dialog.html">Dialog</div>
									<div><a href="http://www.jeasyui.com" target="_blank">EasyUI</a></div>
								</div>
							</div>
						</div>
					</div>
					<div iconCls="icon-save">Save</div>
					<div class="menu-sep"></div>
					<div>Exit</div>
				</div>
			</div>

			<div title="tab7">
				<div id="propertygrid"></div>
				<a href="#" id="property-change" class="easyui-linkbutton">getchanges</a>
			</div>

			<div title="tab8">
				<div id="progressbar"></div>
				<a href="#" id="progressbar-btn" class="easyui-linkbutton">test</a>

				<div id="pagination" style="background:#eeffff;border:1px solid #ccc;"></div>

				<input id="searchbox"></input>
				<div id="searchbox-mm" style="width:120px">
				    <div name="all" iconCls="icon-ok">All News</div>
				    <div name="sports">Sports News</div>
				</div>

				<ul id="tree" class="easyui-tree" animate="true" dnd="true">
					<li>
						<span>Folder</span>
						<ul>
							<li state="closed">
								<span>Sub Folder 1</span>
								<ul>
									<li><span><a href="#">File 11</a></span></li>
									<li><span>File 12</span></li>
									<li><span>File 13</span></li>
								</ul>
							</li>
							<li><span>File 2</span></li>
							<li><span>File 3</span></li>
							<li>File 4</li>
							<li>File 5</li>
						</ul>
					</li>
					<li><span>File21</span></li>
				</ul>


			</div>
		</div>
	</div>
</div>

<div id="panel">
	<input id="validatebox" type="text"/>
	<input type="text" class="easyui-validatebox" validType="minLength[5]" required="true"/>
	<input id="numberbox" type="text"/>
</div>

</body>
</html>