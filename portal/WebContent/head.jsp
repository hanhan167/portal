<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--[if lte IE 8]>
<script>
	alert("浏览器版本过低，请及时升级你的浏览器");
	//window.location.href='http://cdn.dmeng.net/upgrade-your-browser.html?referrer='+location.href;
</script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/padding_and_marign.css" />
<title>PORTAL_head</title>
</head>
<body>
	
	<div class="content" id="head_content">
		<ul>
			<li><a href="index.jsp">首页</a></li>
			<li><a href="intro.jsp">公司简介</a></li>
			<li><a href="support.jsp">联系支持</a></li>
			<li><a href="recruit.jsp">招聘</a></li>
		</ul>
		<ul id="login">
			<!-- <li><a href="user/toRegisterPerson.do">注册</a></li> -->
			<li><a href="register_personage.jsp" target="_blank">注册</a></li>
			<li><a href="javascript:view.alert();" >登录</a></li>
		</ul>
		<ul id="loginOut">
			<li><a target="_blank" href="sys/toTech.do">技术平台</a></li>
			<li><a target="_blank" href="sys/toTransAction.do">交易平台</a></li>
<!-- 			<li><a target="_blank" href="sys/toCutter.do">管理平台</a></li> -->
			<li><a href="user/toInformation.do" id="loginName0"></a>
				<!--个人信息概述-->
				<div class="personage_overview">
					<div id="first">
						<a href="user/toInformation.do" class="portrait">
							<img src="frame/static/head_portrait/IMG_0338.jpg"/>
						</a>
						<h3 id="loginName1"></h3>
						<img id="grade_img" src="frame/static/img/grade.png"/>
						<a href="sys/loginOut.do" class="exit">退出</a>
					</div>
					<div id="second">
						<span>订单</span>
						<span id="second_num">0</span>
					</div>
					<!-- <div id="thirdly">
						<span>申请试验刀</span>
						<span>成功</span>
					</div> -->
				</div>
			</li>
		</ul>
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript">
var gradeimg = {
		"grade001":"frame/static/img/grade1.png",
		"grade002":"frame/static/img/grade2.png",
		"grade003":"frame/static/img/grade3.png",
		"grade004":"frame/static/img/grade4.png"
		};
$(function() { 	
	//判断是否登录
	$.ajax({
        url: "sys/isLogin.do",
        type: "post",
        success: function (data) {
            if(data.success==true){//true登录
            	var gradeNo = data.obj.gradeNo;
            	$("#loginName0").text(splitStr(data.obj.userAlias,4)).attr("title",data.obj.userAlias);
            	$("#loginName1").text(splitStr(data.obj.userAlias,6)).attr("title",data.obj.userAlias);
            	$("#grade_img").attr("src", gradeimg[gradeNo]);
            	$("#second_num").text(data.msg);
            	$("#login").hide();
            	$("#loginOut").show();
            }else{
            	$("#login").show();
            	$("#loginOut").hide();
            }
        }
    });
	function overview_left(num){
		var left1 = $("#loginOut li:eq(3)").offset().left-num;
		$(".personage_overview").css("left",left1);
	}
	$("#loginOut li:eq(3)").mouseenter(function(){
		overview_left(200);
	});
});
var view = {
	alert:function(){
		layer.open({
			type: 2,
			title: false,
			resize:false,
			scrollbar:false,//不允许浏览器出现滚动条
			area:['520px','462px'],
			content:['sys/toUserPage.do','no']
		});
	}
}
</script>