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
<link rel="stylesheet" type="text/css" href="frame/static/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css" href="frame/static/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="frame/static/css/invoice.css">
<title>发票管理</title>
</head>
<body style="background-color: #e2e2e2;">

	<!--内容-->
	<div class="invoiceContenntTop">
		<div class="clearFix invoiceTop">
			<h4>开发票</h4>
		</div>
		
		<div class="invoiceTopNav">
			<div class="handle_teap teatpde1 active">1、选择用户<i>&nbsp;</i></div>
			<!-- <div class="handle_teap teatpde1">2、选择订单开票<i>&nbsp;</i></div> -->
			<div class="handle_teap teatpde1">2、开票信息、开票编号<i>&nbsp;</i></div>
			<div class="handle_teap teatpde2">3、发票地址、快递单号</div>
		</div>
		
		<div class="invoiceMiddleTitle1 invoiceTeap">
			<div class="kaiInvoice bgfff">
				<div class="groupInput">
					<label style="width: 140px!important">用户名称/手机号码</label>
					<input style="width:140px" type="text" placeholder="用户名称/手机号码" class="form-type" id="query" name="query">
				</div>
				<div class="search" id="invoice_search1"></div>
			</div>
			<table class="table invoiceMangeList">
				<tr>
					<td>用户名称</td>
					<td>手机号码</td>
					<td>发票序号</td>
				</tr>
			</table>
			<div class="invoiceMangePageList">
				<p>总共 <span class="allPage">--</span> 条，每页显示 <span class="perPage">-</span> 条！</p>
				<div id="page_list3"></div>
			</div>
		</div>
		
	<!-- 	<div class="invoiceMiddleTitle invoiceTeap hidden">
			<div class="kaiInvoice">
				<div class="groupInput">
					<label>订单成功时间</label>
					<input type="text" data-settime="true" placeholder="开始时间" class="form-type" id="startTime" name="startTime">
					<span>~</span>
					<input type="text" data-settime="true" placeholder="结束时间" class="form-type" id="endTime" name="endTime">
				</div>
				<div class="groupInput">
					<label>可开票金额</label>
					<input type="text" placeholder="开始金额" class="form-type" id="startAmt" name="startAmt">
					<span>~</span>
					<input type="text" placeholder="结束金额" class="form-type" id="endAmt" name="endAmt">
				</div>
				<div class="groupInput">
					<label>订单编号</label>
					<input style="width:140px" type="text" placeholder="订单编号/客户编号" class="form-type" id="query" name="query">
				</div>
				<div class="search" id="invoice_search"></div>
				<div class="groupInput">
					<select style="width:140px;height: 28px;" id="chooseFpType" class="form-type" id="query" name="query">
  					<option value ="2" selected="selected">纸质发票</option>
  					<option value ="1">电子发票</option>
					</select>
				</div>
				
			</div>
			<div class="invoiceContTitle">
				<div class="amt_all">
					<p>已选总金额：<span class="money">￥0.00</span></p>
				</div>
			</div>
			<table class="table invoiceMangeList">
				<tr>
					<td><input type="checkbox" id="checkAll"></td>
					<td>订单编号</td>
					<td>发票类型</td>
					<td>总额</td>
					<td>订单日期</td>
					<td>收票地址</td>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
			</table>
			<div class="invoiceMangePageList">
				<p>总共 <span class="allPage">--</span> 条，每页显示 <span class="perPage">-</span> 条！</p>
				<div id="page_list"></div>
				<button class="goInvoice" id="goInvoice">去开票</button>
			</div>
		</div> -->
		<!-- invoiceMangeCount2 invoiceTeap hidden -->
		<div class="invoiceMiddleTitle invoiceTeap hidden">
			<div class="go_invoiceBill_title bgfff">
				<div class="line-title">
					<p class="fll">税务号：<span class="bandCard">--</span></p>
					<p class="flr">开票金额合计：<span class="money">￥--</span></p>
				</div>
				<div class="line-title">
					<p class="fll">发票抬头：<span class="billTitle">--</span></p>
					<p class="fll">开票类型：<span>企业</span></p>
					<p class="fll">发票类型：<span>增值税普通发票</span></p>
					<p class="fll"><span class="companyName hidden"></span></p>
				</div>
			</div>
			<div class="go_invoiceBill_table bgfff">
				<table class="table table-border text-right">
					<tr>
						<th class="align">产品名称</th>
						<th>数量</th>
						<th>总额</th>
					</tr>
				</table>
			</div>
			<div class="invoiceMangePageList2">
				<p>总共 <span class="allPage">--</span> 条，每页显示 <span class="perPage">-</span> 条！</p>
				<div id="page_list"></div>
				<button class="goInvoice" id="goInvoiceQR">确定</button>
			</div>
		</div>
		
		<div class="invoiceMangeCount3 invoiceTeap hidden">
			<div class="go_invoiceBill_title bgfff">
				<div class="line-title">
					<p class="fll">发票编号：<span class="billNo">--</span></p>
					<p class="flr">发票抬头：<span class="billTitle">--</span></p>
				</div>
				<div class="line-title">
					<p class="fll">地址：<span class="billAddr">--</span></p>
					<p class="fll"><span class="custName">--</span><span class="phones">--</span></p>
				</div>
			</div>
			<div class="go_invoiceBill_table bgfff">
				<form onsubmit="return false" class="form-inline">
					<div class="form-group">
						<label>快递名称：</label>
						<select class="form-control" id="logisticsName">
							<option value="">选择快递</option>
						</select>
					</div>
					<div class="form-group">
						<label>快递单号：</label>
						<input class="form-control" id="expressNumber" placeholder="请输入快递单号" />
					</div>
					<button id="goInvoiceWC">完成</button>
				</form>
			</div>
		</div>
		
		
		
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/layui/layui.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script src="frame/static/js/bootstrap.min.js"></script>
<script src="frame/static/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript" src="frame/static/js/invoice.js?x=15"></script>
<script type="text/javascript">
$(function(){
	layui.config({
		 dir: 'frame/static/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
		 ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
		 ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
		 ,base: 'frame/static/layui/' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
	});
	
	//第一步
	invoice.invoiceManageListUser();
	
	$(".invoiceMiddleTitle1.invoiceTeap").on("click","table tr:not(:first)",function(){
		var custNo = $(this).attr("data-custno");
		if(custNo != ""){
			$(".invoiceMiddleTitle").attr("data-custno",custNo).removeClass("hidden").siblings(".invoiceTeap").addClass("hidden");
			$(".invoiceTopNav .handle_teap").eq(1).addClass("active").siblings(".handle_teap").removeClass("active");
			invoice.goInvoice();
			custCat = {};
		}
	});
	
	$("#invoice_search1").click(function(){
		
		invoice.invoiceManageListUser();
	});
	
	//第二步
	$(".invoiceMangeList").on("click","tr:not(:first)",function(){
		var input = $(this).find("input[type='checkbox']");
			input.click();
			
		invoice.custCountAmt();
	});
	$(".invoiceMangeList").on("click","input[type='checkbox']",function(event){
		event.stopPropagation();
		invoice.custCountAmt();
	});
	
	$("#checkAll").click(function(){
		if($(this).is(":checked")){
			$(this).parents("table").find("tr:not(:first) input[type='checkbox']").prop("checked",true);
		}else{
			$(this).parents("table").find("tr:not(:first) input[type='checkbox']").removeAttr("checked")
		}
		
		invoice.custCountAmt();
	});
	
	$("#invoice_search").click(function(){
		invoice.invoiceManageList();
	});
	
	//去开票
	$("#goInvoice").click(function(){
		invoice.goInvoice();
	});
	
	//第三步
	$("#goInvoiceQR").click(function(){
		invoice.goInvoiceQR();
	});
	$("#goInvoiceWC").click(function(){
		invoice.goInvoiceWC();
	})
});

	$("#chooseFpType").change(function(){
		var chooseVal =  $('#chooseFpType option:selected').val();
		invoice.invoiceManageList();
	});


</script>