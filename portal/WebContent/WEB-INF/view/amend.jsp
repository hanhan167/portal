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
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/amend.css" />
<title>PORTAL修改密码</title>
</head>
<body>

	<h4 style="font-weight: 400;">修改密码</h4>
	<form action="" id="amend_password" onsubmit="return false;">
		<div class="old_password">
			<label>旧密码：</label>
			<input type="password" name="old_password" />
			<span></span>
		</div>
		<div class="new_password">
			<label>新密码：</label>
			<input type="password" name="pasword1" />
			<span></span>
		</div>
		<div class="new_password">
			<label>确认新密码：</label>
			<input type="password" name="pasword2" />
			<span></span>
		</div>
		<input type="submit" value="确认" class="fillet"/>
	</form>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="frame/static/js/messages_zh.js"></script>
<script type="text/javascript" src="frame/static/js/additional-methods.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	$("#amend_password").validate({
		rules:{
			old_password:{
				required:true,
			},
			pasword1:{
				required:true,
				correctPass:true,
			},
			pasword2:{
				required:true,
				PasWord:true,
			}
		},//rules-end
		messages:{
			old_password:{
				required: "请输入旧密码！",
			},
			pasword1:{
				required: "请输入新密码！",
				correctPass:"密码由6-20位数字+字母组合组成",
			},
			pasword2:{
				required: "请再次输入新密码！",
			}
		},
		//错误信息位置设置方法  
	 	 errorPlacement: function (error, element) {
	 		error.appendTo(element.siblings("span"));
         },
		 submitHandler: function(form){
			var oldPwd=$("[name='old_password']").val();
			var newPwd=$("[name='pasword1']").val();
			$.ajax({
				url:"user/updatePwd.do",
				data:{"oldPwd":oldPwd,"newPwd":newPwd},
				type:"post",
            	success : function(data){
 					if(data.success==false){
 						layer.open({
							 title: '错误信息'
							 ,content:data.msg
						}); 
 					}else{
 						layer.open({
							 title: '消息提示'
							 ,content:"修改成功"
							 ,btn: '确认'
		  					 ,yes: function(index, layero){
		  						window.location.reload();
		  					 }
						});  
 					}
            	}
			});
		}
	});
});
</script>
