;(function($){
	
	//every(fun(item, index, array)) ->boolean
	//some(fun(item, index, array))  ->boolean
	//filter(fun(item, index, array)) ->array
	//map(fun(item, index, array))    ->array
	//forEach(fun(item, index, array)) ->array
	//reduce(fun(prev, cur, index, array))  ->array
	//reduceRight(fun(prev, cur, index, array)) ->array
	$.extend({
		unshift : function(array, item){	//向数组的头部添加一个后者多个元素
			array.unshift(item);
			return array.length;
		},
		push : function(array, item){		//向数组的尾部添加一个后者多个元素
			array.push(item);
			return array.length;
		},
		shift : function(array){			//删除并返回数组的第一个元素
			return array.shift();
		},
		pop : function(array){				//删除并返回数组的最后一个元素
			return array.pop();
		},
		join : function(sep){				//使用指定的分隔符分隔数组成为字符串
			return array.join(sep);
		},
		splice : function(array, index, howmany, ele){			//用于插入、删除或替换数组的元素。
			return array.splice(index,howmany,ele);
		}
		//slice(start, end) 返回字串
		//sort()			排序
		//concat(array1 ...)连接
		//reverse()			反转
		//toString()		转换成字符串
	});
	
	var tools = {
        escape : function(para){
            return escape(para);
        },
        unescape : function(para){
            return unescape(para);
        },
        isFinite : function(para){
            return isFinite(para);
        },
        isNaN : function(para){
            return isNaN(para);
        },
        parseFloat : function(para){
            return parseFloat(para);
        },
        parseInt : function(para){
            return parseInt(para);
        },
        eval : function(para){
            return eval(para);
        }
    };	
    window.tools = new tools();

})(jQuery);