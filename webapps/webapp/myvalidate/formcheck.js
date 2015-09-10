/*
** [插件名称] myvalidate
** [邮    箱] googlecosplay@163.com
** [更新日期] 2013-05-30
**
** 全局配置通常以settings结尾
** 方法内部配置通常以options
**
** 
**
**/

;(function($){
	
	var elem_settings = "elem_settings";
	
	$.extend({					//主要是下面用到的工具函数
		getById : function(id){
            return $("#"+id).first();
        },
		getData : function(id){
			return $.getById(id).data(elem_settings);
		},
		//获取指定字符串的长度
		getLength : function(id){
			var $elem = $("#"+id);
			var elem = $elem.get(0);
			var sName = elem.name;
			var sType = elem.type;
			var len = 0;
			switch(sType){
				case "text":
				case "hidden":
				case "password":
				case "textarea":
				case "file":
					var val = $elem.val();
					//var setting = elem.settings[0];
					//如果有显示提示内容的，要忽略掉
					//if(elem.isInputControl && elem.value == setting.onShowText){val=""}
					//var initConfig = $("body").data(elem.validatorGroup);
					//if (initConfig.wideWord){
					//	for (var i = 0; i < val.length; i++) {
					//		len = len + ((val.charCodeAt(i) >= 0x4e00 && val.charCodeAt(i) <= 0x9fa5) ? 2 : 1); 
					//	}
					//}else{
						len = val.length;
					//}
					break;
				case "checkbox":
				case "radio": 
					len = $("input[type='"+sType+"'][name='"+sName+"']:checked").length;
					break;
				case "select-one":
					len = elem.options ? elem.options.selectedIndex : -1;
					break;
				case "select-multiple":
					len = $("select[name="+sName+"] option:selected").length;
					break;
			}
			return len;
		}
	});

	var init_sets = {		//全局的初始化配置，用户自定义配置也会扩展到这里
	
	};

    var default_options = {
        formId : "",            //表单ID
        submitOnce : false,     //页面是否提交一次，不会停留
        onError : $.noop,       //提交失败的回调函数度
        validCount : 0,         //含ajaxValidator的控件个数
        validObjects : [],      //参加校验的控件集合
        errorFocus:true,		//第一个错误的控件获得焦点
        wideWord:true,			//一个汉字当做2个长
        onSuccess: function() {return true;},
        validType : "initConfig"
    }; 
    
    var validType = {
        formValid	: "formValid",
        inputValid  : "inputValid",
        compValid   : "compValid",
        regexValid  : "regexValid",
        funValid    : "funValid",
        ajaxValid   : "ajaxValid",
        pwdValid    : "pwdValid"
    };
   	
   	var valType = {
        size        : "size",
        number      : "number",
        string      : "string",
        date        : "date",
        datetime    : "datetime"
    };
    
    var form_sets = {				//此form设置尤为重要 已经过改变
        tipId : "",
        tipCss : {},
        onShow : "请输入内容show",
        onFocus : "请输入内容focus",
		onEmpty : "输入内容为空",
        onCorrect : "输入正确",
        empty : false,
        defaultVal : null,
        validType : validType.formValid,
        
		triggerEvent : "blur",
		ajax : false,
        tipSelector : null,
		forceValid : false,
		onShowText : "",
		onShowTextColor:{mouseOnColor:"#000000",mouseOutColor:"#999999"},
        onShowFixText:"",
		fixTipId : null,
		leftTrim : false,
		rightTrim : false,
		index : 0
    };
    
    var input_sets = {
        isValid : false,
		validType : validType.inputValid,
        type : valType.size,
		onError : "输入错误",
        min : 0,
        max : 99999,
        onErrorMax : "",
        onErrorMin : "",
		empty:{
			leftEmpty:true,
			rightEmpty:true,
			lrEmptyError : "左右不能为空",
			emptyError : "不能为空",
			leftEmptyError:null,
			rightEmptyError:null
		}
    };
    
    var pwd_sets = {
		isValid : true,
		continueChar:false,          
		continueError:"密码字符为连续字符不被允许",
		sameChar:false,          
		sameError:"密码字符都相同不被允许",
		compareId : "",
		compareSameError:"密码于用户名相同不被允许",
		validType: validType.pwdValid
	};
	
	var compare_sets = {
		isValid 	: false,
		desId 		: "",
		operateor 	: "=",
		onError		: "输入错误",
		validType	: validType.compValid
	};
	
	var regex_sets = {
        isValid 	: false,
        regExp 		: "",
        param 		: "",					//i g m
        onError 	: "不匹配正则表达式",
        dataType 	: "string",		//string enum	枚举名在formValidatorReg.js里设置
        compareType : "||",			//当regExp为数组的时候，当前这个参数就表示，数组里的正则表达式的相互关系。
        validType 	: validType.regexValid
    };

	var ajax_sets = {
		isValid 	: false,
		lastValid 	: "",
		onceValid 	: false,
		//buttons 	: null,
		onError		: "服务器校验没有通过",
		onWait		: "正在等待服务器返回数据",
		validType	: validType.ajaxValid
	};
	
	var ajaxForm_sets = {
		type 		: "GET",
		url 		: window.location.href,
		dataType 	: "text",
		timeout 	: 100000,
		data 		: {},
		async 		: true,
		cache 		: false,
		beforeSend 	: function(){return true;},
		success 	: function(){return true;},
		complete 	: $.noop,
		processData : true,
		error 		: $.noop
	};

	var fun_sets = {
		isValid 	: true,
		fun 		: function(){this.isValid = true;},
		validType	:validType.funValid,
		onError		:"输入错误"
	};

	$.extend(true, ajax_sets, ajaxForm_sets);

    $.validate = {
        init : function(options){					//主要是初始化配置，目前由客户端调用， 再改进时需注意没有表单的情况
            init_sets = $.extend(true, {}, default_options, options || {});
            
            if(init_sets.formId != ""){
			    $("#"+init_sets.formId).submit(function(){
			        //if(settings.ajaxForm!=null){
			        //    $.formValidator.ajaxForm(settings.validatorGroup,settings.ajaxForm);
				    //    return false;
					//}else{
						var ok = $.validate.bindSubmit(init_sets);
						return ok; //$.validate.bindSubmit(init_sets);
	                //}
	            });
			}
        },
        
        bindSubmit : function(init_sets){
        	return $.validate.pageValid(init_sets);
        },
        
        pageValid : function(init_sets){
        	var flag = true;
        	$.each(init_sets.validObjects, function(i, v){
        		
        		var result = $.validate.singleValid(v.get(0).id);
        		if(!result.isValid){
        			flag = false;
        			$.validate.showMsg(result);
        			
        			if(init_sets.errorFocus){
        				v.focus();
        			}
        			return false;
        		}
        	});
        	
        	if(flag){
	        	if(init_sets.onSuccess()){return true};
	        	return true;
        	}else{
        		return false;
        	}
        },
        
		sustainType : function(elem, validType){		//各种校验方式支持的控件类型
			var sTag = elem.tagName.toLowerCase();
			var stype = elem.type.toLowerCase();
			switch(validType){
				case "formValid":
					return true;
				case "inputValid":
					return (sTag == "input" || sTag == "textarea" || sTag == "select");
				case "compValid":
					return ((sTag == "input" || sTag == "textarea") ? (stype != "checkbox" && stype != "radio") : false);
				case "ajaxValid":
					return (stype == "text" || stype == "textarea" || stype == "file" || stype == "password" || stype == "select-one");
				case "regexValid":
					return ((sTag == "input" || sTag == "textarea") ? (stype != "checkbox" && stype != "radio") : false);
				case "funValid":
					return true;
				case "pwdValid":
					return stype == "password";
			}
		},
		
		addValid : function($elem){					//将要验证的控件添加到全局配置的验证组里	#主要在formValid中调用
			var validIndex = $.inArray($elem, init_sets.validObjects); 
			if (validIndex == -1) {
				init_sets.validObjects.push($elem);
			} else {
				init_sets.validObjects[validIndex] = $elem;
			}
		},
        
        appendValid : function(elem, options){			//追加验证类型数据，数据绑定在元素上	#在各个验证中调用
            var $elem = $(elem);  
            var settings = $elem.data(elem_settings);

			if(!$.validate.sustainType($elem.get(0), options.validType)){
				return -1;
			};

            if(options.validType == validType.formValid || settings == undefined){
                settings = [];
            }
            var len = settings.push(options);
            settings[len - 1].index = len - 1;		//给元素配置加个index索引
            $elem.data(elem_settings, settings);
        },

        inputValid : function(result){				//对控件的输入进行验证
            var setting = result.setting;
            var $elem = $.getById( result.id );
            
            var val = $elem.val();
            var sType = $elem.get(0).type;

			var empty = setting.empty;
			var len = $.getLength(result.id);
			var lrEmptyError = false; 

            switch(sType) {
                case "text":
				case "hidden":
				case "password":
				case "textarea":
				case "file":
					
				case "checkbox":
				case "select-one":
				case "select-multiple":
				case "radio":
					
					if (setting.type == valType.size) {				//判断控件的类型是否为 size
						empty = setting.empty;
						if(val.replace(/^[ \s]+$/, '') == ""){
							lrEmptyError = true;
							result.errormsg= empty.emptyError;
						}else{
							if(!empty.leftEmpty){
								lrEmptyError = (val.replace(/^[ \s]+/, '').length != val.length);	//val左边有空格时为true
							}
							if(!lrEmptyError && !empty.rightEmpty){									//只有左边没有空格时才判断右边
								lrEmptyError = (val.replace(/[ \s]+$/, '').length != val.length);	//val右边有空格时为true
							}
							if(lrEmptyError && empty.lrEmptyError){
								result.errormsg= empty.lrEmptyError;
							}
						}
					}
					
					var flag = false;								//主要标识各个控件的值是否符合标准
					
					(sType=="select-one" || sType=="select-multiple") && (setting.type = "size");
					
					var type = setting.type;
					
					if (type == "size") {							//获得输入的字符长度，并进行校验
						lrEmptyError || (flag = true);
						flag && (val = len);
					}else if (type =="date" || type =="datetime"){
						flag = isDate(val);
						if(flag){
							val = new Date(val);
							setting.min=new Date(setting.min);
							setting.max=new Date(setting.max);
						};
					}else{
						stype = (typeof setting.min);
						if(stype =="number"){
							val = (new Number(val)).valueOf();
							isNaN(val) || (flag = true);
						}
						stype == "string" && (flag = true);
					}
					setting.isValid = false;
					
					if(flag){
						if(val < setting.min){
							result.errormsg = (val < setting.min && setting.onErrorMin) ? setting.onErrorMin : setting.onError;
						}else if(val > setting.max){
							result.errormsg = (val > setting.min && setting.onErrorMax) ? setting.onErrorMax : setting.onError;
						}else{
							setting.isValid = true;
						}
					}
					break;
            }
        },
        
        compValid : function(result){
			var setting = result.setting;
			var $elem = $.getById(result.id);
		    var $dest = $.getById(setting.desId);
			
			elemVal = $elem.val();
			destVal = $dest.val();
			
		    switch(setting.operateor){
		        case "=":
		            setting.isValid = (elemVal == destVal);
		            break;
		        case "!=":
		            setting.isValid = (elemVal != destVal);
		            break;
		        case ">":
		            setting.isValid = (elemVal > destVal);
		            break;
		        case ">=":
		            setting.isValid = (elemVal >= destVal);
		            break;
		        case "<": 
		            setting.isValid = (elemVal < destVal);
		            break;
		        case "<=":
		            setting.isValid = (elemVal <= destVal);
		            break;
				default :
					setting.isValid = false;
					break; 
		    };
		    
		    if(!setting.isValid){
				result.errormsg = setting.onError;
			}
        },
        
        regexValid : function(result){
        	
        	var setting = result.setting;
			var $elem = $.getById(result.id);
			var elem = $elem.get(0);
			var sTag = $elem.get(0).tagName;
			
			var regexArray = ($.type(setting.regExp) == "string") ? [setting.regExp] : setting.regExp;
			setting.isValid = false;
			var isValid;
			$.each(regexArray, function() {
				
				var regex = this;
			    (setting.dataType == "enum") && (regex = eval("regexEnum." + regex));	
			    
			    if(regex == undefined || regex == "") {
			        return false;
			    }
			    
			    var reg = setting.param ? new RegExp(regex, setting.param) : new RegExp(regex);
			    isValid = reg.test($(elem).val());
			    if(setting.compareType == "||" && isValid){
			        setting.isValid = true;
			        return false;
			    }
			    if(setting.compareType == "&&" && !isValid) {
			        return false
			    }
            });
            if(!setting.isValid) setting.isValid = isValid;
            
            if(!setting.isValid){
				result.errormsg = setting.onError;
			}
        },
        
        ajaxValid : function(result){
        	var opts = result.setting;
			var $elem = $.getById(result.id);
			var elem = $elem.get(0);
			var name = elem.name;
			var value = elem.value;
			
			var data = {
				name : value
			};
			
			$.extend(opts.data, data);
			
			//var dtd = $.Deferred();
        	$.ajax({
				url : opts.url,
				data : opts.data ,
				dataType : opts.dataType, //xml html script json jsonp text
				async : false,
				cache : opts.cache,
				crossDomain : false,
				type : opts.type,
				timeout : opts.timeout,
				beforeSend : opts.beforSend,
				success : opts.success,
				complete : opts.complete,
				processData : opts.processData,
				error : opts.error
			}).done(function(status, statusText, xhr){
				$("body").append(status);
				if(status == "true"){
					opts.onceValid = true;
					opts.isValid = true;
				}else{
					opts.onceValid = false;
					opts.isValid = false;
					result.errormsg = opts.onError;
				}
			}).fail(function(status, statusText, xhr){
				opts.onceValid = false;
				opts.isValid = false;
				result.errormsg = opts.onError;
			});
        },
        
        funValid : function(result){
        	var id = result.id;
			var setting = result.setting;
		    var $elem = $.getById(result.id);
			var flag = setting.fun($elem.val(),$elem.get(0));
			if(flag != undefined) {
				if($.type(flag) === "string"){
					setting.isValid = false;
					result.errormsg = flag;
				}else{
					setting.isValid = flag;
					if(!flag){
						result.errormsg = setting.onError;
					}
				}
			}else{
			    setting.isValid = true;
			}
        },
        
        beforeValid : function(validObjects){
        	var objs = [];
        	if($.type(validObjects) == "array"){
        		objs = validObjects;
        	}else if($.type(validObjects) == "object"){
        		objs.push(validObjects);
        	}
        	$.each(objs, function(i, v){
        		var $elem = v;
        		var id = $elem.attr("id");
        		var settings = $.getData(id);
        		
        		if(settings){
	                if(!!settings[0].defaultVal && $elem.val() == settings[0].defaultVal){
	                	$elem.val("");
	                }
	                if(!!settings[0].onShowText && $elem.val() == settings[0].onShowText){
	                	$elem.val("");
	                }
        		}
        	});
        },
        
        singleValid : function(id){					//验证单个是否验证通过,正确返回settings[0],错误返回对应的settings[i]
        	
			var $elem = $.getById(id);
			var settings = $.getData(id);
            var setslen = settings.length;
            var result = {
				//id : id,
				//isValid : true,
				//setting : settings[i],
			};
            result.id = id;
            $.validate.beforeValid($elem);
            for(var i=0; i<setslen; i++){
                //验证的时候要判断 值是否为  	1->默认值 2->提示 3 空
                //当验证为formValid验证要判断  是否能为空
                if(i == 0){//只有formValid
                    if(settings[i].empty && $elem.val() == ""){
                        result.isValid = true;
                        result.setting = settings[i];
                        break;
                    }
                    continue;
                }
                result.setting = settings[i];
                
                var vType = settings[i].validType;;
                
                switch(vType) {
                    case validType.inputValid:
                        $.validate.inputValid(result); 
                        break;
                    case validType.compValid:
                        $.validate.compValid(result);
                        break;
                    case validType.regexValid:
                        $.validate.regexValid(result);
                        break;
                    case validType.funValid:
                        $.validate.funValid(result);
                        break;
                    case validType.ajaxValid:
                    	$.validate.ajaxValid(result);
                        break;
                    case validType.pwdValid:
                        break;
                }
                if(!settings[i].isValid) {
                    result.isValid = false;
                    result.setting = settings[i];
                    break;
                }else{
                    result.isValid = true;
                    result.setting = settings[0];
                }
            }
            return result;            
        },
        
        showMsg : function(result){					//根据不同的结果展示不同的提示信息
			var id = result.id;
			var showText = result.setting.onShowText;
			var tipId = result.setting.tipId;
			var $elem = $.getById(id);
			var from_set = $.getData(id)[0];
			var $show = $.getById(from_set.tipId);
			
			var msg = "";
			if(result.isValid){
				if(result.setting.empty && $elem.val() == ""){
					msg = result.setting.onEmpty;
				}else{
					msg = result.setting.onCorrect;
				}
				$show.text(msg);
			}else{
				msg = result.errormsg;
				$show.text(msg);
			}
			return msg;
		},
        
        resetTipMsg : function(){					//显示固定层内提示的信息
			
		},
		
		resetShowMsg : function(triggerType, $elem, opts, isInputControl){//显示文本域中的提示信息
			
			if(isInputControl){
				var showText = opts.onShowText;
				if(triggerType == "focus"){
					if(showText == $elem.val()){
                        $elem.val("");
                        $elem.css("color", opts.onShowTextColor.mouseOnColor);
                    }
				}else if(triggerType == "blur"){
                    if(!$elem.val()){
                        $elem.val(showText);
                        $elem.css("color", opts.onShowTextColor.mouseOutColor);
                    }
				}else{
	                if(!!showText){
	                    $elem.val(showText);
	                    $elem.css("color", opts.onShowTextColor.mouseOutColor);
	                };
				}
                
            }
		},
        
        
    };
    
    //每个校验控件必须初始化的
    $.fn.formValid = function(options){
        var opts = $.extend(true, {}, form_sets, options || {});
        init_sets.validCount++;
        return this.each(function(){
            
            var $this = $(this);//this
            var id = this.id;
            var stype = this.type;
            var tagName = this.tagName.toLowerCase();
            
            //每个控件都要保存这个配置信息、为了取数方便，冗余一份控件总体配置到控件上
            opts.tipId = opts.tipId ? opts.tipId : id+"Tip";
            opts.fixTipId = opts.fixTipId ? opts.fixTipId : id+"FixTip";
            
            $.validate.addValid($this);                         	//将要验证的控件放到全局的验证容器中
            $.validate.appendValid(this, opts);                  	//追加验证类型  
            
            opts.defaultVal && $this.val(opts.defaultVal);      //输入框中的默认值
            
            var fixTip = $("#"+opts.fixTipId);                  //固定提示内容 此值和验证提示信息不同
            var showFixText = opts.onShowFixText;
            if(fixTip.length==1 && !!showFixText){
                $this.hover(
                    function () {
                        fixTip.html(showFixText);
                    },
                    function () {
                        fixTip.html(showFixText);
                    }
                );
                fixTip.html(showFixText);
            }
            
            var $tip = $.getById(opts.tipId);
            $tip.text(opts.onShow);
            $tip.css(opts.tipCss);
            
            var isInputControl = (stype == "password" || stype == "text" || stype == "textarea");
            
            if(tagName == "input" || tagName=="textarea"){
            	var showText = opts.onShowText;
                
                $.validate.resetShowMsg("", $this, opts, isInputControl);
                
                $this.on("focus", function(){

                    $tip.text(opts.onFocus);
                    $.validate.resetShowMsg("focus", $this, opts, isInputControl);
                }).on(opts.triggerEvent, function(){
                	var result = $.validate.singleValid(id); 
					$.validate.showMsg(result);
                    $.validate.resetShowMsg("blur", $this, opts, isInputControl);
                });
            }else if(tagName == "select"){
				$this.on("focus", function(){
					$tip.text(opts.onFocus);
				}).on("blur", function(){
					$this.trigger("change");
				}).on("change", function(){
					var result = $.validate.singleValid(id); 
					$.validate.showMsg(result);
				});
            }
        });
    };

    $.fn.inputValid = function(options){
        var opts = $.extend(true, {}, input_sets, options || {});
        return this.each(function(){
            $.validate.appendValid(this, opts);
        });
    };

	$.fn.regexValid = function(options){
		var opts = $.extend(true, {}, regex_sets, options || {});
		return this.each(function(){
			$.validate.appendValid(this, opts);
		});
	};
	
	$.fn.compValid = function(options){
		var opts = $.extend(true, {}, compare_sets, options || {});
		return this.each(function(){
			$.validate.appendValid(this, opts);
		});
	};

	$.fn.ajaxValid = function(options){
		var opts = $.extend(true, {}, ajax_sets, options || {});
		return this.each(function(){
			$.validate.appendValid(this, opts);
		});
	};

	$.fn.funValid = function(options){
		var opts = $.extend(true, {}, fun_sets, options || {});
		return this.each(function(){
			$.validate.appendValid(this, opts);
		});
	};

})(jQuery);
