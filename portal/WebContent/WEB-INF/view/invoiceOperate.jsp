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
<link rel="stylesheet" type="text/css" href="frame/static/css/backpanel.css"/>
<title>PORTAL发票管理</title>
<style type="text/css">
button {
	width: 120px;
}
</style>
</head>
<body>
	
	<div class="site_head">
		<h4>发票管理</h4>
	</div>
	<div class="incoice_headline">
		
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
	$.ajax({
		url:'user/getAddressByIds.do',
		type:"get",
		success:function(data){
			if(data.success){
				var html=baidu.template('bd_t1',data);
				$(".incoice_headline").html(html);
				
				
			}
		}
	});
	
	//修改
	$(document).on("click","#update>button:eq(0)",function(){
		
			var param = {
				companyName:$("[name=companyName]").val(),
				billReceipt:$("[name=billReceipt]").val(),
				registerAddress:$("[name=registerAddress]").val(),
				registerPhone:$("[name=registerPhone]").val(),
				openBand:$("[name=openBand]").val(),
				bandCard:$("[name=bandCard]").val(),
				billReceiveName:$("[name=billReceiveName]").val(),
				billReceivePhone:$("[name=billReceivePhone]").val(),
				billReceiveMail:$("[name=billReceiveMail]").val(),
				billReceiveAddress:$("[name=billReceiveAddress]").val()
			};
			$.ajax({
				url:'user/updates.do',
				datatype:'json',
				data:param,
				type:"post",
				success:function(data){
					if(data.success){
						layer.msg('修改成功'
							,{icon: 1,time: 1000}
							,function(){
								location.href="user/toInformation.do?update=update"; 
						});
					}
				}
			});
		
	});
	//取消
	$(document).on("click","#update>button:eq(1)",function(){
			//$(".site_head").parent().load("user/toInvoice.do");
			location.href="user/toInformation.do?update=update"; 
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
					<input class="" name="companyName" value="<*=obj.companyName*>">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">纳税人识别码：</span>
				<div class="fl">
					<input class="" name="billReceipt" value="<*=obj.billReceipt*>">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">注册地址：</span>
				<div class="fl">
					<input class="" name="registerAddress" value="<*=obj.registerAddress*>">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">注册电话：</span>
				<div class="fl">
					<input class="" name="registerPhone" value="<*=obj.registerPhone*>">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">开户银行：</span>
				<div class="fl">
					<input class="" name="openBand" value="<*=obj.openBand*>">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">银行账户：</span>
				<div class="fl">
					<input class="" name="bandCard" value="<*=obj.bandCard*>">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item" id="name_div">
				<span class="label">收票人姓名：</span>
				<div class="fl">
					<input class="" name="billReceiveName" value="<*=obj.billReceiveName*>">
					<span class="" id=""></span>
				</div>
			</div>
			<div class="item" id="call_div">
				<span class="label">收票人手机：</span>
				<div class="fl">
					<input class="" name="billReceivePhone" value="<*=obj.billReceivePhone*>">
					<span class="" id=""></span>
				</div>
			</div>
			<div class="item" id="call_div">
				<span class="label">收票人邮箱：</span>
				<div class="fl">
					<input class="" name="billReceiveMail" value="<*=obj.billReceiveMail*>">
					<span class="" id=""></span>
				</div>
			</div>
			<div class="item" id="address_div">
				<span class="label">详细地址：</span>
				<div class="fl">
					<input class="" name="billReceiveAddress" value="<*=obj.billReceiveAddress*>">
					<span class="" id=""></span>
				</div>
			</div>
			<div class="update" id="update">
				<button>保存</button>
				<button>取消</button>
			</div>
		</div>
</script>
<script id="bd_t2" type="text/template">
		<div class="step vat-inv-type0 vat-inv-type1" id="vat-inv-type1">
			<div class="item">
				<span class="label">单位名称：</span>
				<div class="fl">
					<input class="" name="companyName">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">纳税人识别码：</span>
				<div class="fl">
					<input class="" name="billReceipt">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">注册地址：</span>
				<div class="fl">
					<input class="" name="registerAddress">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">注册电话：</span>
				<div class="fl">
					<input class="" name="registerPhone">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">开户银行：</span>
				<div class="fl">
					<input class="" name="openBand">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item">
				<span class="label">银行账户：</span>
				<div class="fl">
					<input class="" name="bandCard">
					<span class='' id=''></span>
				</div>
			</div>
			<div class="item" id="name_div">
				<span class="label">收票人姓名：</span>
				<div class="fl">
					<input class="" name="billReceiveName">
					<span class="" id=""></span>
				</div>
			</div>
			<div class="item" id="call_div">
				<span class="label">收票人手机：</span>
				<div class="fl">
					<input class="" name="billReceivePhone">
					<span class="" id=""></span>
				</div>
			</div>
			<div class="item" id="call_div">
				<span class="label">收票人邮箱：</span>
				<div class="fl">
					<input class="" name="billReceiveMail">
					<span class="" id=""></span>
				</div>
			</div>
			<div class="item" id="address_div">
				<span class="label">详细地址：</span>
				<div class="fl">
					<input class="" name="billReceiveAddress">
					<span class="" id=""></span>
				</div>
			</div>
			<div class="add">
				<button>保存</button>
				<button>取消</button>
			</div>
		</div>
</script>