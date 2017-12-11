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
<link rel="stylesheet" type="text/css" href="frame/static/css/recruit.css" />
<title>PORTAL招聘</title>
</head>
<body>

	<!--顶部-->
	<div class="head">
		<jsp:include page="head.jsp" flush="true"/>
	</div>
	<!--内容-->
	<div class="centre">
		<div class="content" style="width: 800px;">
			<h1 class="margin-left-20">期待你的加入！</h1>
			<h4 class="margin-top-10">WELCOME</h4>
			<p>如果你对下一职位感兴趣</p>
			<span>可将你的简历发送至邮箱</span>
			<div class="relation">
				<!-- <img src="frame/static/img/job_e-mail.png"/>
				<span>daoju@youxiang.com</span> -->
				<img src="frame/static/img/e-mail.png"/>
			</div>
			<span>通过电话联系我们</span>
			<div class="relation">
				<!-- <img src="frame/static/img/job_phone.png"/>
				<span>110</span> -->
				<img src="frame/static/img/phone.png"/>
			</div>
			<article>
				<h1>岗位名称1</h1>
				<span>职责：</span>
				<ol>
					<li>负责猎聘网大数据产品和服务的核心模型与算法的创新、研究和实现</li>
					<li>应用机器学习、NLP、统计预测等前沿技术分析和挖掘猎聘网海量数据（用户、销售等）</li>
					<li>参与大数据产品和服务的产品设计和技术架构</li>
				</ol>
				<span>要求：</span>
				<ol>
					<li>计算机、数学或统计等相关专业本科及以上学历嘛年以上数据科学相关工作经验</li>
					<li>扎实的机器学习，数理统计理论和技术基础，有相关研究或工程经验</li>
					<li>扎实的编程基础，熟悉C++/Java/Python/R编程语言，有海量数据处理经验者优先</li>
					<li>优秀的逻辑思维和独立思考能力，对数据有敏锐的直觉</li>
					<li>优秀的学习能力，分析和解决问题的能力</li>
					<li>对技术和工作充满热情，热爱挑战</li>
					<li>具备良好的沟通能力和团队合作精神</li>
				</ol>
			</article>
			<!-- <iframe src="recruit.txt" scrolling="no" style="border: none"></iframe> -->
		</div>
	</div>
	<!--底部-->
	<div class="base">
		 <jsp:include page="base.jsp" flush="true"/>
	</div>
	
</body>
</html>
<script type="text/javascript">
<!--

//-->
</script>