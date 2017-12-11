<%@page import="com.hansy.portal.filter.ValidateCodeServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% String rand = ValidateCodeServlet.createCharacter();%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="frame/static/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/forget.css"/>
<style type="text/css">
.form-forget input{padding-left: 5px;}
</style>
<title>PORTAL忘记密码</title>
</head>
<body>
	
	<!--顶部-->
	<div class="head">
		<jsp:include page="../../head.jsp" flush="true"/>
	</div>
	<!--内容-->
	<div class="centre">
		<div class="content">
		<form>
				<div class="form-forget">				
					<h4>找回帐号密码</h4>
						<div class="phone">
							<label>真实姓名:</label>
							<input id="custName" name="custName" type="text" />
						</div>
						<div class="phone">
							<label>手机号:</label>
							<input id="phone" name="phone" type="tel" />
						</div>
						<div class="proving">
							<label>验证码:</label>
							<input id="vno" name="vno" type="text" />
							<input id="h_random" value="<%=rand %>" type="hidden" />
							<img src='<%=request.getContextPath()%>/randomImage?code=<%=rand%>' onclick="changeImg(this);" />
						</div>
						<p style="text-align:center;margin:15px 0;">点击验证码换一张</p>
					<input type="button" value="找回" onclick="getPwd();" class="fillet"/>					
				</div>
			</form>
		</div>
	</div>
	<!--顶部--> 
	<div class="base">
		<jsp:include page="../../base.jsp" flush="true"/>
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script src="frame/static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript">
function getPwd(){
	var custName = $("#custName").val();
	var phone = $("#phone").val();
	var vno = $("#vno").val();
	var h_random = $("#h_random").val();
	if(!/^[\u0391-\uFFE5]+$/.test(custName)){
		layer.msg("姓名不能为空且只能输入汉字！",{icon: 2});
		return;
	}
	if(!/^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(phone)){
		layer.msg("手机号码格式不正确！",{icon: 2});
		return;
	}
	if(vno == ''&&vno==null){
		layer.msg("验证码不能为空！",{icon: 2});
		return;
	}
	if(h_random.toUpperCase() != vno.toUpperCase()){
		layer.msg("验证码错误！",{icon: 2});
		return;
	}
	$.ajax({
	    url: "user/getPwd.do",
	    type: "post",
	    data:{"custName":custName,"phone":phone,"vno":vno},
	    success: function (data) {
    	   var tip='';
	       if(data.success){
	    	   tip = "尊敬的【"+custName+"】：您的密码已重置成功！并以短信的方式发送到您的绑定手机！请妥善保存！";
	       }else{
	    	   tip = data.msg;  
	       }
	       layer.open({
    		   title: '提示',
    		   content: tip
   		 	});  
	    }	    
	});
}

function changeImg(img){
	var rand = Math.round(Math.random()*10000);
	$('#h_random').val(rand);
	img.src = "<%=request.getContextPath()%>/randomImage?code=" + rand;
}

</script>
