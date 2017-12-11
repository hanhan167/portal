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
<link rel="stylesheet" type="text/css" href="frame/static/css/intro.css" />
<style type="text/css">
form input{padding-left: 5px;}
</style>
<title>PORTAL基本信息</title>
</head>
<body>

	<h4>基本信息</h4>
	<form action="" method="post" onsubmit="return false;" id="basicEdit">
		<label>用户名：</label>
		<input type="text" name="userName" disabled="disabled"/>
		<span></span>
		<label>昵称：</label>
		<input type="text" name="userAlias"/>
		<span></span>
		<label>手机号：</label>
		<input type="tel" name="userPhone"/>
		<label id="certNoLa" style="display:none">身份证号：</label>
		<input type="text" name="certNo" style="display:none" disabled="disabled"/>
		<label id="regNoLa" style="display:none">企业注册号：</label>
		<input type="text" name="regNo" style="display:none" disabled="disabled"/>
		<span></span>
		<label>邮箱：</label>
		<input type="email" name="userEmail"/>
		<span></span>
		<label>推荐者：</label>
		<input type="text" name="superUserNo"/>
		<input type="hidden" name="userNo"/>
		<input type="submit" value="保存"/>
	</form>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript" src="frame/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="frame/static/js/messages_zh.js"></script>
<script type="text/javascript" src="frame/static/js/additional-methods.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script type="text/javascript" src="frame/static/layui/layui.js"></script>
<script type="text/javascript">
$(function (){
	var oldphone;
	$.ajax({
		url:"user/getUserBaic.do",
		success:function(data){
			if(data.success){
				if(data.obj.userType=="00010001"){
					$("#certNoLa").show();
					$("[name='certNo']").show();
					$("[name='userNo']").val(data.obj.userNo);
					$("[name='userName']").val(data.obj.userName);
					$("[name='userAlias']").val(data.obj.userAlias);
					$("[name='userPhone']").val(data.obj.userPhone);
					$("[name='certNo']").val(data.obj.certNo);
					$("[name='userEmail']").val(data.obj.userEmail);
					$("[name='superUserNo']").val(data.obj.superUserNo);
					oldphone = data.obj.userPhone;
				}else{
					$("#regNoLa").show();
					$("[name='regNo']").show();
					$("[name='userNo']").val(data.obj.userNo);
					$("[name='userName']").val(data.obj.userName);
					$("[name='userAlias']").val(data.obj.userAlias);
					$("[name='userPhone']").val(data.obj.userPhone);
					$("[name='regNo']").val(data.obj.regNo);
					$("[name='userEmail']").val(data.obj.userEmail);
					$("[name='superUserNo']").val(data.obj.superUserNo);
					oldphone = data.obj.userPhone;
				}
			}else{
				
			}
		}
	})
	
	//验证
	$("#basicEdit").validate({
		rules:{
			userAlias:{
				required:true,
				isUserName:true,
				noChinese:true,
			},userPhone:{
				required:true,
				isMobile:true,
			},userEmail:{
				required:true,
				email:true,
			}
		},//rules-end
		messages:{
			userAlias:{
				required:"请输入昵称",
				isUserName:"昵称字母开头，5-14位数字加字母",
				noChinese:"昵称不能存在中文",
			},userPhone:{
				required:"请输入电话号码",
				isMobile:"请输入正确的电话号码",
			},userEmail:{
				required:"请输入邮箱",
				email:"请输入正确的邮箱",
			}
		},
		//错误信息位置设置方法  
	 	 errorPlacement: function (error, element) {
	 		//error.appendTo(element.siblings("span"));
	 		//layer.tips(,element, {tipsMore: true});
	 		/* layer.open({
	 			 type: 4,
	 			 content: [$(error).text(),element],
	 			 shade: 0,
	 			 tipsMore: true,
	 			 tips: [2, '#c00'],
	 			 time: 5000,
	 			 closeBtn: 0
	 		});  */
         },
         submitHandler: function(form){
 			var userAlias=$("[name='userAlias']").val();
 			var userPhone=$("[name='userPhone']").val();
 			var userEmail=$("[name='userEmail']").val();
 			var userNo=$("[name='userNo']").val();
 			var superUserNo=$("[name='superUserNo']").val();
 			$.ajax({
 				url:"user/updateUserBasic.do",
 				data:{
 					"userAlias":userAlias,
 					"userPhone":userPhone,
 					"userEmail":userEmail,
 					"userNo":userNo,
 					"superUserNo":superUserNo,
 					"oldPhone":oldphone
 				},
 				type:"post",
 				beforeSend: function(){
					$("input[type=submit]").attr("disabled","disabled");
				},
             	success : function(data){         		
  					if(!data.success){
  						layer.open({
 							title: '错误信息'
 							,content:data.msg
 							,yes:function(){
 								location.reload();
 							}
 						}); 
  					}else{
  						layer.alert("修改成功");
  						location.reload();
  					}
             	}
 			});
 		}
	});
});
</script>