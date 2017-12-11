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
<title>PORTAL_head</title>
</head>
<body>
	
	<div class="content">
		<div class="first">
			<a>客服中心</a> |
			<a href="recruit.jsp">招聘英才</a> |
			<a href="support.jsp">联系我们</a> 
		</div>
		<div class="second">
			京ICP备xxx号
			京公网安备xxx号
		</div>
		<div class="thirdly">
			Copyright©2017  xxx 版权所有
		</div>
	</div>
	
</body>
</html>