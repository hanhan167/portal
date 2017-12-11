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
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/register.css" />
<style type="text/css">
form input{padding-left: 5px;}
</style>
<title>PORTAL个人注册</title>
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
				<h3>用户注册</h3>
				<ul>
					<li class="selected">
						<em>1</em>
						<span>设置用户名</span>
					</li>
					<li class="selected">
						<em>2</em>
						<span>填写帐号信息</span>
					</li>
					<li class="">
						<em>3</em>
						<span>填写个人信息</span>
					</li>
				</ul>
			</div>
			<form method="post" id="personage2" onsubmit="return false;">
				<div class="line">
					<label class="label">昵称:</label>
					<input type="text" name="userAlias" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">密码:</label>
					<input type="password" name="pasword1"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">确认密码:</label>
					<input type="password" name="pasword2"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				
				<div class="line">
					<label class="label">邮箱:</label>
					<input type="email" name="userEmail"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label>推荐者:</label>
					<input type="text" name="superUserNo"/>
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label></label>
					<input type="submit" class="fillet" value="下一步"/>
					<b class=""></b>
					<span class=""></span>
				</div>
			</form>
		</div>
	</div>
	<!--底部-->
	<div class="base">
		 <jsp:include page="base.jsp" flush="true"/>
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script type="text/javascript" src="frame/static/js/messages_zh.js"></script>
<script type="text/javascript" src="frame/static/js/additional-methods.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript">
$(function(){
	$("#personage2").validate({
		rules:{
			userAlias:{
				required:true,
				isUserName:true,
				noChinese:true,
			},
			pasword1:{
				required:true,
				correctPass:true,
			},
			pasword2:{
				required:true,
				PasWord:true,
			},
			userEmail:{
				required:true,
				email:true,
			},
			superUserNo:{
				isMobile:true,
			}
		},//rules-end
		messages:{
			userAlias:{
				required: "请输入昵称！",
				isUserName:"昵称字母开头，5-14位数字加字母",
				noChinese:"昵称不能存在中文",
			},
			pasword1:{
				required: "请输入密码！",
				correctPass:"密码由6-20位数字+字母组合组成",	
			},
			pasword2:{
				required: "请再次输入密码！",
			},
			userEmail:{
				required:"请输入邮箱",
				email:"请输入正确的邮箱地址！",
			},
			superUserNo:{
				isMobile:"请输入正确的电话号码！",
			}
		},
		//错误信息位置设置方法  
	 	 errorPlacement: function (error, element) {
	 		error.appendTo(element.siblings("span"));
         },
		submitHandler: function(form){
			var userAlias=$("[name='userAlias']").val();
			var userPwd=$("[name='pasword1']").val();
			var userEmail=$("[name='userEmail']").val();
			var superUserNo=$("[name='superUserNo']").val();
			$.ajax({
				url:"sys/registerSecond.do",
				data:{"userAlias":userAlias,"userPwd":userPwd,"userEmail":userEmail,"superUserNo":superUserNo},
				type:"post",
            	success : function(data){         		
 					if(data.success==false){
 						layer.open({
							 title: '错误信息'
							 ,content:data.msg
						}); 
 					}else{
 						location.href="register_personage3.jsp";
 					}
            	}
			});
		}
	});
});
</script>
