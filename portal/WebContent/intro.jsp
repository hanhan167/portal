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
<link rel="stylesheet" type="text/css" href="frame/static/css/intro.css" />
<title>PORTAL公司简介</title>
</head>
<body>

	<!--顶部-->
	<div class="head">
		<jsp:include page="head.jsp" flush="true"/>
	</div>
	<!--内容-->
	<div class="centre">
		<div class="content">
			<h1>真诚向您展示自我！</h1>
			<h4>ABOUT ME</h4>
			<img src="frame/static/head_portrait/IMG_0338.jpg"/>
			<div class="article">
				<div class="pre">
					<pre></pre>
				</div>
				<h2><img src="frame/static/img/-company_arrow.png"/>CONTACT ME DIRECTLY</h2>
				<span>联系我</span>
				<p><img src="frame/static/img/-company_phone.png"/>110</p>
				<p><img src="frame/static/img/-company_e-mail.png"/>daoju@136.com</p>
			</div>
			
		</div>
	</div>
	<!--底部-->
	<div class="base">
		 <jsp:include page="base.jsp" flush="true"/>
	</div>
	
</body>
</html>
<script type="text/javascript">
$(function(){
	$(".article .pre pre").load("intro.txt");
});
</script>