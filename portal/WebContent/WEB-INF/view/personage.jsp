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
<title>PORTAL个人用户注册</title>
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/register.css" />
<style type="text/css">
div.row{
	margin: 18px 0 12px 200px;
}
</style>
</head>
<body>

	<!--顶部-->
	<div class="head">
		<jsp:include page="../../head.jsp" flush="true"/>
	</div>
	<!--中间-->
	<div class="centre">
		<div class="content">
			<div class="tab">
				<ul>
					<li class="personage selected">
						<a href="user/toRegisterPerson.do">个人用户</a>
					</li>
					<li class="company">
						<a href="user/toRegisterCompany.do">企业用户</a>
					</li>
				</ul>
			</div>
			<form action="" method="post">
			<h3></h3>
				<div class="line">
					<label class="label">昵称:</label>
					<input type="text" class="text" name="userAlias"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">密码:</label>
					<input type="password" class="text" name="userPwd"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">确认密码:</label>
					<input type="password" class="text" name="userPwd"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">性别:</label>
					<div class="text">
						<input type="radio" name="userSex" /><label>男</label>
						<input type="radio" name="userSex" /><label>女</label>
					</div>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">手机号:</label>
					<input type="tel" class="text userPhone" name="userPhone"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">邮箱:</label>
					<input type="email" class="text" name="userEmail"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">真实姓名:</label>
					<input type="text" class="text" name="userName"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">证件类型:</label>
					<select name="certType">
						<option value="111">二代身份证</option>
						<option>临时身份证</option>
						<option>军官证</option>
						<option>学生证</option>
						<option>护照</option>
					</select>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">证件号码:</label>
					<input type="text" class="text" name="certNo"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">职业:</label>
					<input type="text" class="text" name="userWork"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">学历:</label>
					<select name="education">
						<option>初中及以下</option>
						<option>高中（职高、中专）</option>
						<option>大专（高职）</option>
						<option>本科</option>
						<option>硕士研究生</option>
						<option>博士及以上</option>
					</select>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">月收入:</label>
					<input type="number" class="text monthIncome" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">婚姻状况:</label>
					<select>
						<option>已婚</option>
						<option>未婚</option>
					</select>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">推荐者:</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="row">
					<input type="checkbox" id="check" name="agree" />
					<label>我已阅读并同意<a href="#">《xxx用户协议》</a></label>
				</div>
				<div class="line">
					<label class="label"></label>
					<button type="submit" class="fillet" disabled="disabled">立即注册</button>
				</div>
			</form>
		</div>
	</div>
	<!--底部-->
	<div class="base">
		 <jsp:include page="../../base.jsp" flush="true"/>
	</div>

</body>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript">
	/* $(function(){
		$(".fillet").click(function(){
			var userPhone=$(".userPhone").val();
			alert(userPhone);
			var monthIncome=$(".monthIncome").val();
			$.ajax({
	            url: "user/userRegister.do",
	            type: "post",
	            dataType: "json",
	            data: {
	            	userPhone: userPhone,
	            	monthIncome:monthIncome
	            },
	            success: function (data) {
	                alert(data.msg);
	            }
	        });
		})
	}) */
</script>
</html>