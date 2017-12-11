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
<link rel="stylesheet" type="text/css" href="frame/static/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/information.css" />
<title>PORTAL个人信息</title>
</head>
<body>
	
	<!--顶部-->
	<div class="head">
		<jsp:include page="../../head.jsp" flush="true"/>
	</div>
	<!--内容-->
	<div class="centre">
		<div class="content">
			<!--左-->
			<div class="content_left">
				<div class="message">
					<div class="head_portrait">
						<img src="frame/static/head_portrait/IMG_0338.jpg"/>
					</div>
					<h3 id="loginName"></h3>
					<img id="userVip" src="frame/static/img/grade.png"/>
				</div>
				<img src="frame/static/img/line.png"/>
				<span>用户信息</span>
				<ul>
					<li class="pitch">基本信息</li>
					<li>地址管理</li>
					<li>粉丝详情</li>
					<li>修改密码</li>
					<li style="display:none;">我的客户</li>
					<li style="display:none;">开发票</li>
					<li style="display:none;">发票列表</li>
					<li>发票地址管理</li>
					<li style="display:none;">我的销售员</li>
					<li style="display:none;">用户消费排行</li>
					<li style="display:none;">产品销售排行</li>
				</ul>
			</div>
			<!--右-->
			<div class="content_right">
				
			</div>
		</div>
	</div>
	<!--底部-->
	<div class="base">
		 <jsp:include page="../../base.jsp" flush="true"/>
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript">
$(function(){
	var gradeimg = {
			"grade001":"frame/static/img/grade1.png",
			"grade002":"frame/static/img/grade2.png",
			"grade003":"frame/static/img/grade3.png",
			"grade004":"frame/static/img/grade4.png"
			};
	$.ajax({
        url: "sys/isLogin.do",
        type: "post",
        success: function (data) {
            if(data.success==true){
            	$("#loginName").text(splitStr(data.obj.userAlias,6)).attr("title",data.obj.userAlias);
            	$("#userVip").attr("src", gradeimg[data.obj.gradeNo]);
            	if (data.obj.userType=="00010003"){
            		$(".content_left>ul>li:eq(4)").show();
            		$(".content_left>ul>li:eq(5)").show();
            		$(".content_left>ul>li:eq(6)").show();
            		$(".content_left>ul>li:eq(8)").show();
            		$(".content_left>ul>li:eq(9)").show();
            		$(".content_left>ul>li:eq(10)").show();
            	}
            }else{
            	layer.open({
					title: '错误信息'
					,content:data.msg
					,yes:function(){
						location.reload();
					}
				}); 
            }
        }
    });
	
	
	$(".content_right").load("user/toBasic.do");
	$(".content_left>ul>li").click(function(){
		$(".content_left>ul>li").removeClass("pitch");
		$(this).addClass("pitch");
	});
	$(".content_left>ul>li:eq(0)").click(function(){
		$(".content_right").load("user/toBasic.do");
	});
	$(".content_left>ul>li:eq(1)").click(function(){
		$(".content_right").load("user/toSite.do");
	});
	$(".content_left>ul>li:eq(2)").click(function(){
		$(".content_right").load("user/toFans.do");
	});
	$(".content_left>ul>li:eq(3)").click(function(){
		$(".content_right").load("user/toAmend.do");
	});
	$(".content_left>ul>li:eq(4)").click(function(){
		$(".content_right").load("user/tomyCustomer.do");
	});
	
	$(".content_left>ul>li:eq(5)").click(function(){
		$(".content_right").load("user/toBillManage.do");
	});
	$(".content_left>ul>li:eq(6)").click(function(){
		$(".content_right").load("user/toBillManageMenu.do");
	});
	
	$(".content_left>ul>li:eq(7)").click(function(){
		$(".content_right").load("user/toInvoice.do");
	});
	$(".content_left>ul>li:eq(8)").click(function(){
		$(".content_right").load("user/toSelectSale.do");
	});
	$(".content_left>ul>li:eq(9)").click(function(){
		$(".content_right").load("user/toConsumeRank.do");
	});
	$(".content_left>ul>li:eq(10)").click(function(){
		$(".content_right").load("user/toProductRank.do");
	});
});
</script>