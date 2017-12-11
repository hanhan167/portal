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
<link rel="stylesheet" type="text/css" href="frame/static/css/support.css" />
<title>PORTAL联系支持</title>
<style type="text/css">
#grabble{
	margin: 20px 0;
	/* float: right; */
	text-align: right;
}
#grabble input{
	height: 26px;
	line-height: 26px;
}
#grabble a{
	background:url(frame/static/img/search.png) no-repeat;
	background-size: 100% 100%;
	display: inline-block;
	height: 22px;
	width:22px;
	margin: 2px;
	position: relative;
	left:2px;
	top: 8px;
}
</style>
</head>
<body>

	<!--顶部-->
	<div class="head">
		<jsp:include page="head.jsp" flush="true"/>
	</div>
	<!--内容-->
	<div class="centre">
		<div class="content">
		
			<h1>这里能找到你想要的</h1>
			<h4>FOLLOW ME!</h4>
			<div id="grabble">
				<input><a></a>
			</div>
		<div id="cont"></div>
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
<script type="text/javascript" src="frame/static/js/baiduTemplate.js"></script>
<script type="text/javascript">
$(function(){
	$("#grabble a").click(function(){
		$.ajax({
			url:"sys/userSupplyInfoQuery.do",
			type:"post",
			data:{custName:$("#grabble input").val()},
			success: function (data) {
				var html=baidu.template('bd_t1',data);
				$(".centre>.content>#cont").html("");
				$(".centre>.content>#cont").append(html);
				load();
			}
		});
	});
	baidu.template.LEFT_DELIMITER='<*';
	baidu.template.RIGHT_DELIMITER='*>';
	$.ajax({
		url:"sys/userSupplyInfoQuery.do",
		type:"post",
		success: function (data) {
        	if(data.success==true){
            	var html=baidu.template('bd_t1',data);
				$(".centre>.content>#cont").html("");
				$(".centre>.content>#cont").append(html);
				load();
            }else{
            	layer.open({
					title: '错误信息',
					content:data.msg
				});
            }
		}
	});
	
});
function load(){
	var shu = $('.centre .content #cont').children('.module').length;
	for(var i = 0;i < shu;i++){
		if(i%3!=0){
			$(".module:eq("+i+")").css("margin-left","39px");
		}
	}
}
</script>
<script id="bd_t1" type="text/template">
<*for(var i in obj){*>
<*var list = obj[i]*>
<div class="module">
	<div class="image">
		<a href="commercial_particulars.jsp?key=<*=list.custNo*>">详情…></a>
		<img src="http://<*=location.host*>/<*=list.libDirectory1*><*=list.libName1*><*=list.libExtName1*>">
	</div>
	<span><*=list.custName*></span>
	<p><*=list.supplyRemark*></p>
</div>
<*}*>
</script>