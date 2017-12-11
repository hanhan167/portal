<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
	String toUrl =  (String)request.getSession().getAttribute("tourl"); 
	if(toUrl == null || toUrl == ""){
		toUrl = "index.jsp";
	}
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
					<li class="selected">
						<em>3</em>
						<span>填写个人信息</span>
					</li>
				</ul>
			</div>
			<form method="post" id="personage3" onsubmit="return false">
				<div class="line">
					<label>真实姓名:</label>
					<input type="text" name="userName"/>
					<b>*</b>
					<span></span>
				</div>
				<div class="line">
					<label>性别:</label>
					<div class="text" name="userSex">
						<input type="radio" name="userSex"  checked value="0"/><label>男</label>
						<input type="radio" name="userSex" value="1"/><label>女</label>
					</div>
					<b></b>
					<span></span>
				</div>
				<div class="line">
					<label>证件类型:</label>
					<select name="certType">
						<option value="00020001">二代身份证</option>
						<option value="00020002">临时身份证</option>
						<option value="00020003">军官证</option>
						<option value="00020004">学生证</option>
						<option value="00020005">护照</option>
						<option value="00020006">港澳台同行证</option>
					</select>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label>证件号码:</label>
					<input type="text" name="certNo"/>
					<b>*</b>
					<span></span>
				</div>
				<div class="line">
					<label>职业:</label>
					<input type="text" name="userWork"/>
					<b>*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label>学历:</label>
					<select name="education">
						<option value="0009001">初中及以下</option>
						<option value="0009002">高中（职高、中专）</option>
						<option value="0009003">大专（高职）</option>
						<option value="0009004">本科</option>
						<option value="0009005">硕士研究生</option>
						<option value="0009006">博士及以上</option>
					</select>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label>月收入:</label>
					<select name="monthIncome">
						<option value="0">0~2000</option>
						<option value="1">3000~5000</option>
						<option value="2">5000~8000</option>
						<option value="3">8000~15000</option>
						<option value="4">15000~20000</option>
						<option value="5">20000以上</option>
					</select>
					
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label>婚姻状况:</label>
					<select name="maritalStatus">
						<option value="0010001">未婚</option>
						<option value="0010002">已婚</option>
					</select>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label></label>
					<input type="submit" class="fillet" value="立即注册" />
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
	$("#personage3").validate({
		rules:{
			userName:{
				required:true,
				isChinese:true,
			},
		/* 	monthIncome:{
				isNumber:true,
			}, */
			certNo:{
				required:true,
				provinceCard:true
			},
			userWork:{
				required:true,
			},
		},//rules-end
		messages:{
			userName:{
				required: "请输入姓名！",
				isChinese:"请输入汉字,且为两位以上",
			},
		/* 	monthIncome:{
				isNumber:"必须全为数字",
			}, */
			certNo:{
				required: "请输入你的证件号码！",
				provinceCard:"身份证格式错误"
			},
			userWork:{
				required: "请输入你的职业！",
			},
		},
		//错误信息位置设置方法  
	 	 errorPlacement: function (error, element) {
	 		error.appendTo(element.siblings("span"));
         },
         submitHandler: function(form){
 			var userName=$("[name='userName']").val();
 			var userSex=$("input[name='userSex']:checked").val();
 			var certType=$("[name='certType']").val();
 			var certNo=$("[name='certNo']").val();
 			var userWork=$("[name='userWork']").val();
 			var education=$("[name='education']").val();
 			var monthIncome=$("[name='monthIncome']").val();
 			var maritalStatus=$("[name='maritalStatus']").val();
 			$.ajax({
 				url:"sys/userRegisterThird.do",
 				data:{"userName":userName,"userSex":userSex,"certType":certType,"certNo":certNo,"userWork":userWork,"education":education,"monthIncome":monthIncome,"maritalStatus":maritalStatus},
 				type:"post",
 				beforeSend: function(){
					$(".fillet").attr("disabled","disabled");
				},
             	success : function(data){         		
  					if(data.success==false){
  						layer.open({
 							title: '错误信息'
 							,content:data.msg
 						}); 
  					}else{
  						layer.open({
							 title: '消息提示'
							 ,content:data.msg
							 ,btn: '确定'
		  					 ,yes: function(index, layero){
		  					   location.href='<%=toUrl %>';
		  					 }
						}); 
  					}
             	}
 			});
 		}
	});
});
</script>