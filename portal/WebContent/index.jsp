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
<!--[if lte IE 8]>
<script>
	alert("浏览器版本过低，请及时升级你的浏览器");
	//window.location.href='http://cdn.dmeng.net/upgrade-your-browser.html?referrer='+location.href;
</script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/index.css" />
<title>PORTAL首页</title>
</head>
<body>
	
	<!--顶部-->
	<div class="head">
		<jsp:include page="head.jsp" flush="true"/>
	</div>
	<!--内容-->
	<div class="centre">
		<div class="content">
			<div class="publicity">
				<img alt="" src="frame/static/img/banner.png">
			</div>
			<div class="character">
				<h1>我们正在走进未来制造业</h1>
				<span>我们携手同行</span>
			</div>
			<div class="module">
				<div class="module1">
					<div class="print">
						<img alt="" src="frame/static/img/home_project.png">
					</div>
					<span>方案推荐</span>
				</div>
				<div class="module2">
					<div class="print">
						<img alt="" src="frame/static/img/home_product.png">
					</div>
					<span>产品中心</span>
				</div>
				<div class="module3">
					<div class="print">
						<img alt="" src="frame/static/img/home_knowledge.png">
					</div>
					<span>知识库</span>
				</div>
			</div>
		</div>
	</div>
	<!--底部-->
	<div class="base">
		 <jsp:include page="base.jsp" flush="true"/>
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript">

</script>