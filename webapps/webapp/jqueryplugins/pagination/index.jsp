<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="./css/pagination.css"/>

<script type="text/javascript" src="./script/jquery-1.2.3.pack.js"></script>
<script type="text/javascript" src="./script/jquery.cookie-min.js"></script>
<script type="text/javascript" src="./script/pagination.js"></script>

<script type="text/javascript">
$(function(){
	
	var param = {
			//"money":encodeURI($("#money").val())
	};
	var initData = [
			"1 多国华人华侨举行反“藏独”游行 视频 专题 <br/>",
			"2 组图：西藏警方悬赏通缉打砸抢烧疑犯 <br/>",
			"3 我国改革开放30年成为博鳌论坛热议焦点 专题 <br/>",
			"4 奥运圣火抵达阿曼马斯喀特 华人护圣火 视频 <br/>",
			"5 请参与全球华人反分裂护圣火签名活动 <br/>",
			"6 刘兆玄将任台“行政院长” 江丙坤任海基会董事长<br/>",
			"7 国家粮食局长撰文解读粮油安全 广东成第一缺粮省<br/>",
			"8 湖北当阳女市长撞死男童续 交警部门被指不作为 <br/>",
			"9 香港金像奖揭晓 《投名状》揽八项大奖 视频集 <br/>",
			"10 江西鄱阳高速路员工报警被民警击毙(组图) <br/>",
			"11 清华大学教授秦晖建议深圳率先兴建贫民区 <br/>",
			"12 视频：实拍南京女大学生裹床单在食堂打饭 <br/>",
			"13 博客：培养1个飞行员多少钱 黄健翔：足球记者敢说真话 <br/>",
			"14 17时成龙李连杰聊《功夫之王》 MLTR乐队聊奥运歌曲 <br/>",
			"15 乐库全新改版 全方位视听体验 宇多田光 许飞 张惠妹 <br/>"
		];
	$("#pagetest").pagination({
		totalRecord:15,
		proxyUrl:'data.jsp',
		groupSize:3,
		barPosition:'bottom',
		//ajaxParam:param,
		dataStore:initData,
		perPage:5
	});
	
});
</script>

</head>
<body>

<input type="text" id="money" value="测试参数"/>
<div style="width:750px;height:200px;">
	
	<div id="pagetest" style="border:1px solid #DADADA;height:200px;"></div>
	
</div>

</body>
</html>