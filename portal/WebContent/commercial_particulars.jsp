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
<link rel="stylesheet" type="text/css" href="frame/static/css/index.css" />
<title>商品销售</title>
<style type="text/css">
.content>h1{
	color: rgb(2,161,164);
	text-align: center;
	margin: 30px 0 80px 0;
}
.content_one{display: table;}
.content_one>div,
.content_one>img{
	float: left;
}
.supply_log{
	height: 200px;
	width: 200px;
}
.introduce{
	margin-left: 30px;
	width: 400px;
}
.introduce li{
	list-style: none;
}
.introduce h1{
	margin-bottom: 25px;
	color: rgb(2,161,164);
}
.license>img{
	width: 200px;
	height: 150px;
	display: inline-block;
}
.agency>h2{color: rgb(2,161,164);}
.agency>ul{
	margin: 0 0 10px 25px;
}
.agency li{margin-top: 10px;}
.description{margin: 50px 0;}
.centre p,
.centre li{
	font: 16px "宋体";
	line-height: 200%;
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
			<h1>这里能找到你想要的!</h1>
			<div class="content_one">
				<img class="supply_log" src=""/>
				<div class="introduce">
					<h1>供应商名</h1>
					<ul>
						<li>销售：</li>
						<li>技术服务：</li>
						<li>某某银行：</li>
						<li>某某银行：</li>
						<li>地址：</li>
					</ul>
				</div>
				<div class="agency license">
					<h3>供应商营业执照：</h3>
					<img src="">
				</div>
			</div>
			<div class="description">
				<h3>供应商描述：</h3>
				<p style="margin: 10px 0 0 0;">供应商描述</p>
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
$(function(){
	var url = location.search; //获取url中"?"符后的字串
	var val;
	if (url.indexOf("?") != -1) {    //判断是否有参数
		var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
		strs = str.split("=");   //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
		val = strs[1];
	}
	$.ajax({
		url:"sys/getUserSupplyDetail.do",
		type:"post",
		data:{custNo:val},
		success: function (data) {
            if(data.success){
            	$(".supply_log").attr("src","http://"+location.host+"/"+data.obj.libDirectory1+"/"+data.obj.libName1+""+data.obj.libExtName1+"?libId="+data.obj.libId1);
            	$(".introduce>h1").text(data.obj.custName);
            	$(".license>img").attr("src","http://"+location.host+"/"+data.obj.libDirectory+"/"+data.obj.libName+""+data.obj.libExtName+"?libId="+data.obj.libId);
            	$(".introduce li:eq(0)").text("销售："+data.obj.supplyMobile);
            	$(".introduce li:eq(1)").text("技术服务："+data.obj.supplyServerMobile);
            	$(".introduce li:eq(2)").text(data.obj.baseAcctOrg+"："+data.obj.baseAcctNo);
            	$(".introduce li:eq(3)").text("开户户名："+data.obj.baseAcctName);
            	$(".introduce li:eq(4)").text("地址："+data.obj.bizAddress);
            	$(".description p").text(data.obj.supplyRemark);
            }else{
            	layer.open({
					 title: '错误信息',
					 content:data.msg
				});
            }
		}
	});
});
function GetRequest() {
	var url = location.search; //获取url中"?"符后的字串
	if (url.indexOf("?") != -1) {    //判断是否有参数
		var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
		strs = str.split("=");   //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
		console.log(strs);
		for(var i in strs){
			console.log(strs[i]);
		}
	}
	
}
</script>