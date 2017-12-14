<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="frame/static/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/site.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/backpanel.css"/>
<title>PORTAL发票管理</title>
</head>
<body>
	
	<div class="site_head">
		<h4>发票管理</h4>
		<button type="button" class="add_site"><img src="frame/static/img/add.png"/>添加新记录</button>
	</div>
	<div id="incoice_headline">
		
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/layui/layui.js"></script>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/baiduTemplate.js"></script>
<script type="text/javascript">
layui.config({
	 dir: 'frame/static/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
	 ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
	 ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
	 ,base: 'frame/static/layui/' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
});

$(function(){
	
	var index =layer.load(2);
	
	$(".site_head button").click(function(){
		//$(this).parent().parent().load("user/toInvoice.do");
		$(this).parent().parent().load("user/toInvoiceOperate.do");
	});
	//查询
	$.ajax({
		url:'user/getAddressByIds.do',
		type:"get",
		success:function(data){
			if(data.success){
				if(data.obj==null){
					layer.close(index);
				}else{
					$(".add_site").text("编辑");
					var html=baidu.template('bd_t1',data);
					$("#incoice_headline").html(html);
					layer.close(index);
				}
			}
		}
	});
});
baidu.template.LEFT_DELIMITER='<*';
baidu.template.RIGHT_DELIMITER='*>';
</script>
<script id="bd_t1" type="text/template">

		<div class="step vat-inv-type0 vat-inv-type1" id="vat-inv-type1">
			<div class="item">
				<span class="label">单位名称：</span>
				<div class="fl">
					<span class=''><*=obj.companyName*></span>
				</div>
			</div>
			<div class="item">
				<span class="label">纳税人识别码：</span>
				<div class="fl">
					<span class=''><*=obj.billReceipt*></span>
				</div>
			</div>
			<div class="item">
				<span class="label">注册地址：</span>
				<div class="fl">
					<span class=''><*=obj.registerAddress*></span>
				</div>
			</div>
			<div class="item">
				<span class="label">注册电话：</span>
				<div class="fl">
					<span class=''><*=obj.registerPhone*></span>
				</div>
			</div>
			<div class="item">
				<span class="label">开户银行：</span>
				<div class="fl">
					<span class=''><*=obj.openBand*></span>
				</div>
			</div>
			<div class="item">
				<span class="label">银行账户：</span>
				<div class="fl">
					<span class=''><*=obj.bandCard*></span>
				</div>
			</div>
			<div class="item" id="name_div">
				<span class="label">收票人姓名：</span>
				<div class="fl">
					<span class=""><*=obj.billReceiveName*></span>
				</div>
			</div>
			<div class="item" id="call_div">
				<span class="label">收票人手机：</span>
				<div class="fl">
					<span class=""><*=obj.billReceivePhone*></span>
				</div>
			</div>
			<div class="item" id="call_div">
				<span class="label">收票人邮箱：</span>
				<div class="fl">
					<span class=""><*=obj.billReceiveMail*></span>
				</div>
			</div>
			<div class="item" id="address_div">
				<span class="label">详细地址：</span>
				<div class="fl">
					<span class=""><*=obj.billReceiveAddress*></span>
				</div>
			</div>
		</div>
</script>