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
<title>发票列表</title>
</head>
<body style="background-color: #e2e2e2;">

		<!--内容-->
	<input type="hidden"  class="myBillVal">
	<input type="hidden"  class="myApplyNoVal">
	<input type="hidden"  class="billStatus">
	<div class="invoiceContennt">
		<div class="clearFix invoiceTop">
			<h4>发票管理</h4>
			<button class="callBackList hidden">返回列表</button>
		</div>
		<!-- 开票列表 -->
		<div class="invoiceListContent">
			<div class="invoiceMangeSearch">
				<div class="groupInput">
					<label>发生时间</label>
					<input type="text" data-settime="true"  placeholder="开始时间" class="form-type" id="startTime" name="startTime">
					<span>~</span>
					<input type="text" data-settime="true"  placeholder="结束时间" class="form-type" id="endTime" name="endTime">
				</div>
				<div class="groupInput">
					<label>发票编号</label>
					<input type="text"  placeholder="发票编号" class="form-type" id="orderNo" name="orderNo">
				</div>
				<div class="groupInput">
					<label>发票状态</label>
					<select id="billStatus" name="billStatus">
						<option value="1">未寄送</option>
						<option value="2">已寄送</option>
					</select>
				</div>
				<div class="search" id="invoice_search"></div>
			</div>
			
			<div class="invoiceCont">
				<table class="table">
					<tr>
						<th>发票金额</th>
						<th>发票编号</th>
						<th>开票时间</th>
						<th>名称</th>
						<th>发票性质</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</table>
			</div>
			<div class="pageTotall">
				<div class="totalPage">总共 <span class="allPage">-</span> 条；每页显示 <span class="perPage">-</span> 条!</div>
				<div id="page_list"></div>
			</div>
		</div>
		<div class="invoiceTopNav hidden">
			<div class="handle_teap teatpde3 active ">发票地址、快递单号</div>
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
			<!-- <div class="go_invoiceBill_table bgfff">
				<form onsubmit="return false" class="form-inline">
					<div class="form-group">
						<label>快递名称：</label>
						<select class="form-control" id="logisticsName1">
							<option value="">选择快递</option>
						</select>
					</div>
					<div class="form-group">
						<label>快递单号：</label>
						<input class="form-control" id="expressNumber1" placeholder="请输入快递单号" />
					</div>
					<button id="goInvoiceWC1" style="padding:0 30px;margin:0!important;height:32px;float:right">完成</button>
				</form>
			</div> -->
		</div>
		<!-- 开票详情 -->
		<div class="invoiceXq hidden">
			<!-- <div class="xqInvoiceTop">
				<span class="title">开发票明细：</span>
			</div> -->
			<div class="go_invoiceBill_table bgfff"  style="display: none;height: 56px;">
				<form onsubmit="return false" class="form-inline">
					<div class="form-group" style="margin-top: 12px;margin-left: 2px;">
						<label style="margin-top: 6px;">快递名称：</label>
						<select class="form-control" id="logisticsName1">
							<option value="">选择快递</option>
						</select>
					</div>
					<div class="form-group" style="margin-top: 12px;">
						<label style="margin-top: 54x;padding-top: 7px;">快递单号：</label>
						<input class="form-control" id="expressNumber1" placeholder="请输入快递单号"  style="margin-top: 5px;"/>
					</div>
					
					<button id="goInvoiceWC1" style="margin-top: 15px;padding:0 30px;/* margin:0!important; */height: 31px;float:right;margin-right: 17px;">完成</button>
				</form>
				
			</div>
			<div class="invoicelistItem">
				<div class="fl_tit">发票编号：<span class="billNo">--</span>	</div>
				<div class="fr_money">发票金额：<span class="money">￥--</span></div>
				<a class="myChange" style="margin-left: 48px;">修改信息</a>
			</div>
			<table class="table listNO">
				<tr>
					<td align="right">发票性质：</td>
					<td name="billNatrue">纸质发票</td>
					<td align="right">发票状态：</td>
					<td class="a2" name="billStatus">--</td>
				</tr>
				<tr>
					<td align="right">发票类型：</td>
					<td name="billType">增值税普通发票</td>
					<td align="right">名称：</td>
					<td name="companyName">--</td>
				</tr>
				<tr>
					<td align="right">申请时间：</td>
					<td name="billDate">--</td>
					<td align="right">发票编号：</td>
					<td name="billNo">--</td>
				</tr>
				<tr>
					<td align="right">收件人：</td>
					<td name="billReceiveName">--</td>
					<td align="right">发票地址：</td>
					<td name="billReceiveAddress">--</td>
				</tr>
				<tr>
					<td align="right">邮编：</td>
					<td name="billReceiveMail">--</td>
					<td align="right">联系电话：</td>
					<td name="billReceivePhone">--</td>
				</tr>
				<tr class="wcPart">
					<td align="right">物流公司：</td>
					<td name="logisticsName">610000</td>
					<td align="right">快递编号：</td>
					<td name="expressNumber">18988923332732</td>
				</tr>
				<tr>
					<td align="right">地址：</td>
					<td name="pcaAddress">--</td>
				</tr>
			</table>
			
		<!-- 	<div class="invoicelistItem" style="border:none;margin-bottom:10px;">
				<span>关联订单/账单</span>
			</div> -->
			<table class="table pageListBill">
				<tr>
					<td style="padding-left: 39px;">商品信息</td>
					<!-- <td>类型</td> -->
					<td>总额</td>
					<td>数量</td>
				</tr>
				<tr>
					<td>D41265790217721</td>
					<td>购买</td>
					<td class="money">￥156.00</td>
					<td>2017-02-28</td>
				</tr>
				<tr>
					<td>D41265790217721</td>
					<td>购买</td>
					<td class="money">￥156.00</td>
					<td>2017-02-28</td>
				</tr>
				<tr>
					<td>D41265790217721</td>
					<td>购买</td>
					<td class="money">￥156.00</td>
					<td>2017-02-28</td>
				</tr>
			</table>
		
			
			<div id="pageNo_list1" class="bgfff"></div>
		
		</div>
		
			<!-- 修改部分 -->
			<div class="invoiceXqbf" style="display: none">
		
		
			<table class="table listNO">
				<tr>
					<td align="right">发票编号：</td>
					<td><input name="billNo"></td>
					<td align="right">发票金额：</td>
					<td name="money"></td>
				</tr>
				<tr>
					<td align="right">发票性质：</td>
					<td><select name='billNatrue'>
  					<option value="2">纸质发票</option>
  					<option value="1">电子发票</option>
					</select></td>
					<td align="right">发票状态：</td>
					<td><input name="billStatus"  readonly="readonly"></td>
				</tr>
				<tr>
					<td align="right">发票类型：</td>
					<td><select name='billType'>
  					<option value="01">普通发票</option>
  					<option value="02">增值税发票</option>
					</select></td>
					<td align="right">名称：</td>
					<td><input name="companyName"></td>
				</tr>
				<tr>
					<td align="right">收件人：</td>
					<td><input name="billReceiveName"></td>
					<td align="right">发票地址：</td>
					<td><input name="billReceiveAddress"></td>
				</tr>
				<tr>
					<td align="right">邮编：</td>
					<td><input name="billReceiveMail"></td>
					<td align="right">联系电话：</td>
					<td><input name="billReceivePhone"></td>
				</tr>
				<tr class="wcPart" style="display: none;">
					<td align="right">物流公司：</td>
					<td>
					<!-- <input name="logisticsName"> -->
					<select name="logisticsName" class="form-control" id="logisticsName1">
						<option value="1">圆通快递</option>
						<option value="2">中通快递</option>
						<option value="3">申通快递</option>
						<option value="4">韵达快递</option>
						<option value="5">顺丰快递</option>
						<option value="6">邮政快递</option>
						<option value="20">其它</option>
					</select>
					</td>
					<td align="right">快递编号：</td>
					<td><input name="expressNumber"></td>
				</tr>
				<tr>
				<td align="right">地址：</td>
				<td>
				省:
				<select name="input_province" id="input_province" class="myNewSelectShow">
				</select>
				</td>
				<td>
				市:
				<select name="input_city" id="input_city" class="myNewSelectShow">
				</select>
				</td>
				<td>
				区:
				<select name="input_area" id="input_area" class="myNewSelectShow">
				</select>
				</td>
				</tr>
				<tr>
				<td align="right">详细地址：</td>
				<td><input name="billReceiveAddress"></td>
				</tr>
				
			</table>
					
			<div>
				<button class="goBackShow" style="float: right;">返回</button>
				<button class="makeSaveUpdate" style="float: right;">确认修改</button>
			</div>		
				
		<!-- 	<div class="invoicelistItem" style="border:none;margin-bottom:10px;">
				<span>关联订单/账单</span>
			</div> -->		
			<div id="pageNo_list1" class="bgfff"></div>
		
		</div>
		
		
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/layui/layui.js"></script>
<script src="frame/static/js/bootstrap.min.js"></script>
<script src="frame/static/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript" src="frame/static/js/pdata.js"></script>
<script type="text/javascript" src="frame/static/js/jQuery-jcDate.js"></script>
<script type="text/javascript" src="frame/static/js/invoice.js?x=15"></script>
<script type="text/javascript">
$(function(){
	layui.config({
		 dir: 'frame/static/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
		 ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
		 ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
		 ,base: 'frame/static/layui/' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
	});
	invoice.invoiceList();
	
	$("#invoice_search").click(function(){
		invoice.invoiceList();
	});
	
	$(".invoiceContennt .invoiceListContent").on("click","table .xq_invoice",function(){
		invoice.invoiceXQ($(this));
	});
	$(".invoiceContennt .invoiceListContent").on("click","table .js_invoice",function(){
		invoice.invoiceXQnot($(this));
	});
	
	$(".invoiceContennt .callBackList").click(function(){
		$(this).addClass("hidden");
		$(".invoiceContennt .invoiceListContent").removeClass("hidden");
		$(".invoiceContennt .invoiceXq").addClass("hidden");
	});
	$("#goInvoiceWC1").click(function(){
		invoice.goInvoiceWCAjax();
	})
	
});

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
</script>
