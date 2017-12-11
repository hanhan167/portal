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
<title>PORTAL员工登录</title>
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/login.css" />
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".login").click(function(){
			var loginName=$('#loginName').val();
			var loginPwd= $('#loginPwd').val();
			if(loginName==""){
				alert("登录名不能为空");
				return;
			}
			if(loginPwd==""){
				alert("密码不能为空");
				return;
			}
			$.ajax({
                url: "sys/login.do",
                type: "post",
                dataType: "json",
                data: {
                    loginName: loginName,
                    loginPwd: loginPwd,
                },
                beforeSend: function(){
                	$(".login").text("登录中，请稍后...");
                },
                success: function (data) {
                    if(data.success=="true"){
                    	parent.location.href="index.jsp";
                    }else{
                    	$(".login").text("登录");
                    	alert(data.msg);
                    }
                }
            });
		})
	});
</script>
</head>
<body>
	<div id="login_box">
		<div class="logo">
			
		</div>
		<div class="tab">
			<ul>
				<li class="personage">
					<a href="sys/toUserPage.do">会员登录</a>
				</li>
				<li class="company selected">
					<a target="_blank" href="sys/toStaffPage.do">员工登录</a>
				</li>
			</ul>
		</div>
		<form method="post" onsubmit="return false">
			<div class="row">
				<input type="text" id="loginName" placeholder="昵称或手机号" />
			</div>
			<div class="row">
				<input type="password" id="loginPwd" placeholder="密码" />
			</div>
			<div class="row">
				<a href="sys/forget.do" target="_parent">忘记密码?</a>
				<a href="register_personage.jsp" target="_parent">申请帐号&gt;</a>
			</div>
			<button type="submit" class="login">登录</button>
		</form>
	</div>
</body>
</html>