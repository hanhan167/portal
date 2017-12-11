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
<meta http-equiv="X-UA-Compatible" content="edge" />
<!--[if lte IE 8]>
<script>
	alert("浏览器版本过低，请及时升级你的浏览器");
	//window.location.href='http://cdn.dmeng.net/upgrade-your-browser.html?referrer='+location.href;
</script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/register.css" />
<style type="text/css">
form input{padding-left: 5px;}
</style>
<title>PORTAL企业注册</title>
</head>
<body>
	
	<!--顶部-->
	<div class="head">
		<div class="content">
			<a href="index.jsp"><img alt="" src="frame/static/img/logo.png"></a>
			<img alt="" src="frame/static/img/help.png">
		</div>
	</div>
	<!--内容-->
	<div class="centre">
		<div class="content">
			<div class="tab">
				<h3>企业注册</h3>
				<ul>
					<li class="selected">
						<em>1</em>
						<span>设置用户名</span>
					</li>
					<li class="">
						<em>2</em>
						<span>填写帐号信息</span>
					</li>
					<li class="">
						<em>3</em>
						<span>填写企业信息</span>
					</li>
				</ul>
			</div>
			<form method="post">
				<div class="line">
					<label>联系人手机号</label>
					<input type="tel" name="userPhone" id="userPhone"/>
					<b class="">*</b>
					<span class="phone_error"></span>
				</div>
				<div class="line yanzheng">
					<label>验证码</label>
					<input type="text" id="yzm"/>
					<button type="button" id="getYzm">获取验证码</button>
					<b class="">*</b>
					<span class="yzm_error"></span>
				</div>
				<div class="phone_verify">
					
				</div>
				<div class="line"  style="margin:0">
					<label><input type="checkbox" id="check" name="agree" style="float:right;margin-top: 8px" /></label>
					<label>我已阅读并同意<a href="javascript:agreement();">《xxx用户协议》</a></label>
				</div>
				<div class="line"  style="margin:0">
					<!-- <label></label> -->
					<button type="button" class="fillet" disabled="disabled">下一步</button>
					<!-- <b class=""></b>
					<span class=""></span> -->
				</div>
			</form>
			<a href="register_personage.jsp" class="switchover">切换为个人用户注册</a>
		</div>
	</div>
	<!--底部-->
	<div class="base">
		 <jsp:include page="base.jsp" flush="true"/>
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script type="text/javascript">
function agreement(){
	layer.open({
	  title: '企业协议'
	  ,content: '企业协议11111111111111'
	  ,area:['520px','462px']
	});     
}
$(function(){
	
	$("#getYzm").click(function(){
		var _ = $(this);
		var userPhone=$("#userPhone").val();
		if(userPhone!=""||userPhone!=null){
			$(".phone_error").text("");
			if(isPhoneNo(userPhone)==true){
				$(".phone_error").text("");
				$.ajax({
					url:"sys/smsApi.do",
					data:{"userPhone":userPhone},
					type:"post",
					beforeSend:function(){
						_.text("发送中...");
						_.attr("disabled","disabled");
					},
					success:function(data){
						if(data.success==false){
							layer.open({
								 title: '错误信息'
								 ,content:data.msg
							});
							_.text("获取验证码");
							_.removeAttr("disabled");
						}else{
							reduce();
							$(".phone_verify").html("验证码获取成功，10分钟内有效")
						}
					},error:function(){
						_.text("获取验证码");
						_.removeAttr("disabled");
					}
				});
			}else{
				$(".phone_error").text("请输入正确的手机号码");
			}
		}else{
			$(".phone_error").text("请输入手机号码");
		}
	});
	
	$(".fillet").click(function(){
		var yzm=$("#yzm").val();
		var userPhone=$("#userPhone").val();
		if(yzm==null||yzm==""){
			$(".yzm_error").text("请输入验证码");
		}else if(userPhone==""||userPhone==null){
			$(".phone_error").text("请输入手机号码");
		}else{
			$.ajax({
				url:"sys/smsValidate.do",
				data:{"valCode":yzm,"userPhone":userPhone},
				type:"post",
				success:function(data){
					if(data.success==false){
						layer.open({
							 title: '错误信息'
							 ,content:data.msg
						}); 
					}else{
						location.href="register_company2.jsp";
					}
				}
			});
		}
	});
});

// 验证手机号
function isPhoneNo(phone) { 
 var pattern = /^1[34578]\d{9}$/; 
 return pattern.test(phone); 
}

//发送验证码到手机的等待效果
function reduce(){
	$("#getYzm").attr("disabled","disabled");
	var time= 60;
	var residue = setInterval(function(){
		if(time <= 0){
			/*$("#getYzm").val("获取验证码");*/
			$("#getYzm").text("获取验证码");
			window.clearInterval(residue);
			$("#getYzm").removeAttr("disabled");
			return;
		}
		/*$("#getYzm").val(time+"秒重新获取");*/
		$("#getYzm").text(time+"秒重新获取");
		time--;
	},1000)
}
</script>
