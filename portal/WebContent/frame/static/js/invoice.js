$(function(){
	siteAdd();
});

var myNumVal = 1;
var dict = {
	"billStatus":{"1":"未寄送","2":"已寄送"},
	"billNatrue":{"1":"电子发票","2":"纸质发票"},
	"billType":{"02":"增值税发票","01":"普通发票"},
	"logisticsName":{"1":"圆通快递","2":"中通快递","3":"申通快递","4":"韵达快递","5":"顺丰快递","6":"邮政快递","20":"其它"},
	"orderType":{"091001":"试刀","091002":"购买"}
};
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
var custCat = {};
var invoice = {
	//开票列表查询
	invoiceList:function(pageNo){
		pageNo = pageNo || 1;
		var data  = invoice.getInputVal();
		var $invoice = $(".invoiceContennt .invoiceCont table tr:first");
		if(data.billStatus == ""){
			data.billStatus = "1";
		}
		data.pageNo = pageNo;
		$.ajax({
			url: "user/getBillPagePay.do",
	        type: "post",
	        data:data,
	        beforeSend:function(){
	        	$invoice.nextAll().empty();
	        	$invoice.after("<tr align='center'><td colspan='7'>查询中，请稍后...</td></tr>")
	        },
	        success: function (data) {
	        	if(data.success){
	        		var rows = data.obj.rows;
	        		var html = [];
	        		if(rows.length > 0){
	        			for(var i=0;i<rows.length;i++){
		        			html.push("<tr data-id='"+rows[i].billNo+"' data-cust='"+rows[i].custNo+"'><td>￥"+rows[i].billMoney+"</td>");
		        			html.push("<td>"+rows[i].billNo+"</td>");
		        			html.push("<td>"+DateFormat(rows[i].billDate)+"</td>");
		        			html.push("<td>"+rows[i].companyName+"</td>");
		        			html.push("<td>"+dict["billNatrue"][rows[i].billNatrue]+"</td>");
		        			html.push("<td>"+dict["billStatus"][rows[i].billStatus]+"</td>");
		        			if(rows[i].billStatus == "1"){
		        				html.push("<td><button class='js_invoice'>寄送</button></td></tr>");
		        			}else{
		        				html.push("<td><button class='xq_invoice'>详情</button></td></tr>");
		        			}
	        			};
	        			$(".pageTotall").show();
	        			$invoice.nextAll().empty();
	        			$invoice.after(html.join(""));
	        			//调用分页
	        			$(".pageTotall .allPage").text(data.obj.total);
	        			$(".pageTotall .perPage").text(data.obj.pageSize);
	    	        	invoice.pageListLayUi(data,"page_list",function(curr){
	    	        		invoice.invoiceList(curr);
	    	        	});
	        		}else{
	        			$(".pageTotall").hide();
	        			$invoice.nextAll().empty();
			        	$invoice.after("<tr align='center'><td colspan='7'>未查询到符合条件的数据！</td></tr>");
	        		}
	        	}else{
	        		$(".pageTotall").hide();
	        		$invoice.nextAll().empty();
		        	$invoice.after("<tr align='center'><td colspan='7'>查询失败！</td></tr>");
	        	}
	        },error:function(e){
	        	
	        }
		});
	},
	//已经寄送详情
	invoiceXQ:function($dom){
		var billNo = $dom.parents("tr").attr("data-id");
		
		$(".invoiceContennt .invoiceListContent").addClass("hidden");
		$(".invoiceContennt .invoiceXq").removeClass("hidden");
		$(".invoiceContennt .callBackList").removeClass("hidden");
		$(".go_invoiceBill_table").hide();
		invoice.invoiceXQAjax(1,billNo);
		$(".myBillVal").val(billNo);
	},
	
	//未寄送详情
	invoiceXQnot:function($dom){
		var billNo = $dom.parents("tr").attr("data-id");
		
		$(".invoiceContennt .invoiceListContent").addClass("hidden");
		$(".invoiceContennt .invoiceXq").removeClass("hidden");
		$(".invoiceContennt .callBackList").removeClass("hidden");
		$(".go_invoiceBill_table").show();
		invoice.invoiceXQAjaxNot(1,billNo);
		$(".myBillVal").val(billNo);
	},
	
	
	invoiceJS:function($dom){
		var custNo = $dom.parents("tr").attr("data-cust");
		var billNo = $dom.parents("tr").attr("data-id");
		$(".invoiceContennt .invoiceListContent").addClass("hidden");
		$(".invoiceContennt .invoiceMangeCount3").removeClass("hidden");
		$(".invoiceContennt .invoiceTopNav").removeClass("hidden");
		invoice.invoiceJSAjax(custNo,billNo);
	},
	invoiceJSAjax:function(custNo,billNo){
		$.ajax({
			url:"user/getByCust.do",
			type:"post",
			data:{custNos:custNo},
			success:function(data){
				invoice.kdDict($("#logisticsName1"));
				$(".invoiceContennt .invoiceMangeCount3 .billAddr").text(data.obj.billReceiveAddress);
				$(".invoiceContennt .invoiceMangeCount3 .custName").text(data.obj.billReceiveName+"： ");
				$(".invoiceContennt .invoiceMangeCount3 .phones").text(data.obj.billReceivePhone);
				$(".invoiceContennt .invoiceMangeCount3 .billTitle").text(data.obj.billTitle);
				$(".invoiceContennt .invoiceMangeCount3 .billNo").text(billNo);
			},error:function(e){
	        	layer.msg("错误，"+e.status);
	        }
		});
	},
	goInvoiceWCAjax:function(){
		var opPwLayer = layer.confirm('确认完成?',{
			btn:['确认','取消']
		},function(index){
				var tBusCompleteBillVo = {};
				var logisticsName = $("#logisticsName1").val();
				var expressNumber = $("#expressNumber1").val();
				var billNo = $(".invoiceContennt .invoiceXq .invoicelistItem .fl_tit .billNo").text();
				tBusCompleteBillVo.logisticsName = logisticsName;
				tBusCompleteBillVo.expressNumber = expressNumber;
				tBusCompleteBillVo.billNo = billNo;
				if(logisticsName == ""||expressNumber==""){
					layer.msg("请填写快递单号或快递名称!")
				}else{
				$.ajax({
					url: "user/saveComplete.do",
			        type: "post",
			        data:tBusCompleteBillVo,
			        beforeSend:function(){
			        	before = layer.msg("保存中,请稍后...", {
			        			 icon: 16
			        			 ,shade: 0.01
			        			 ,time:0
			        		});
			        },
			        success: function (data) {
			        	layer.close(before);
			        	if(data.success){
			        		layer.close(opPwLayer);
			        		$(".invoiceContennt .invoiceListContent").removeClass("hidden");
			        		$(".invoiceContennt .invoiceMangeCount3").addClass("hidden");
			        		$(".invoiceContennt .invoiceTopNav").addClass("hidden");
			        		$(".invoiceContennt .invoiceXq").addClass("hidden");
			        		invoice.invoiceList();
			        	}else{
			        		layer.msg(data.msg);
			        	}
			        },error:function(e){
			        	layer.close(before);
			        	layer.msg("错误，"+e.status);
			        }
				});}
		});
	},
	//已寄送详情显示
	invoiceXQAjax:function(pageNo,billNo){
		var $invoice = $(".invoiceContennt .invoiceXq .pageListBill tr:first");
		var before;
		var pageNo = pageNo || 1;
		$.ajax({
			url: "user/getBillCompleteBill.do",
	        type: "post",
	        data:{"billNo":billNo,"pageNo":pageNo},
	        beforeSend:function(){
	        	$invoice.nextAll().empty();
	        	before = layer.msg("查询中,请稍后...", {
	        			  icon: 16
	        			  ,shade: 0.01
	        			  ,time:0
	        			});
	        	$invoice.after("<tr align='center'><td colspan='4'>查询中，请稍后...</td></tr>")
	        },
	        success: function (data) {
	        	layer.close(before);
	        	var picDict = ['billStatus','billNatrue','billType','logisticsName'];
	        	var row = data.obj.rows;
	        	var map = data.map.completeBill;
	        	$(".billStatus").val(map.billStatus);
	        	if(data.success){
	        		var html=[];
	        		var province = map.billProvince;
	        		var billCity = map.billCity;
	        		var billArea = map.billArea;
	        		var pcaAddress = province+billCity+billArea;
	        		$(".invoiceXq table.listNO tr td[name=pcaAddress]").text(pcaAddress);
	        		$(".invoiceXq .invoicelistItem .billNo").text(map.billNo);
	        		$(".invoiceXq .invoicelistItem .money").text("￥"+map.billMoney);
	        		$(".invoiceXq table.listNO tr td").each(function(){
	        			var key = $(this).attr("name");
	        			if(key){
	        				$(this).text(map[key]);
	        				for(var k=0;k<picDict.length;k++){
	        					if(key == picDict[k]){
	        						$(this).text(dict[key][map[key]]);
	        					}
	        				};
	        			}
	        		});
	        		$(".invoiceXq table tr td[name='billDate']").text(DateFormat(map.billDate));
	        		if(row.length >0){
	        			for(var j=0;j<row.length;j++){
	        				html.push("<tr><td>"+row[j].goodsName+row[j].guigee+"</td>");	
		        			/*	html.push("<td>"+row[j].orderType+"</td>");	*/
		        				html.push("<td class='money'>￥"+row[j].totalAmt+"</td>");	
		        				html.push("<td>"+row[j].totalNum+"</td>");	
	        				
	        			}
	        			$invoice.nextAll().empty();
	        			$invoice.after(html.join(""));
	        			invoice.pageListLayUi(data,"pageNo_list1",function(curr){
	        				invoice.invoiceXQAjax(curr,billNo);
	        			});
	        		}else{
	        			$invoice.nextAll().empty();
			        	$invoice.after("<tr align='center'><td colspan='4'>未查询到符合条件的数据！</td></tr>");
	        		}
	        	}else{
	        		layer.msg("查询失败！");
	        		$invoice.nextAll().empty();
		        	$invoice.after("<tr align='center'><td colspan='4'>查询失败！</td></tr>");
	        	}
	        },
	        error:function(e){
	        	layer.msg("查询失败！");
	        	$invoice.nextAll().empty();
	        	$invoice.after("<tr align='center'><td colspan='4'>查询失败！</td></tr>");
	        }
		});
	},
	//未寄送详情显示
	invoiceXQAjaxNot:function(pageNo,billNo){
		$(".wcPart").hide();
		var $invoice = $(".invoiceContennt .invoiceXq .pageListBill tr:first");
		var before;
		var pageNo = pageNo || 1;
		$.ajax({
			url: "user/getBillCompleteBill.do",
	        type: "post",
	        data:{"billNo":billNo,"pageNo":pageNo},
	      
	        beforeSend:function(){
	        	$invoice.nextAll().empty();
	        	before = layer.msg("查询中,请稍后...", {
	        			  icon: 16
	        			  ,shade: 0.01
	        			  ,time:0
	        			});
	        	$invoice.after("<tr align='center'><td colspan='4'>查询中，请稍后...</td></tr>")
	        },
	        success: function (data) {
	        	invoice.kdDict($("#logisticsName1"));
	        	layer.close(before);
	        	var picDict = ['billStatus','billNatrue','billType'];//,'logisticsName'
	        	var row = data.obj.rows;
	        	var map = data.map.completeBill;
	        	if(data.success){
	        		var html=[];
	        		var province = map.billProvince;
	        		var billCity = map.billCity;
	        		var billArea = map.billArea;
	        		var pcaAddress = province+billCity+billArea;
	        		$(".invoiceXq table.listNO tr td[name=pcaAddress]").text(pcaAddress);
	        		$(".myApplyNoVal").val(map.applyNo);
	        		$(".invoiceXq .invoicelistItem .billNo").text(map.billNo);
	        		$(".invoiceXq .invoicelistItem .money").text("￥"+map.billMoney);
	        		$(".invoiceXq table.listNO tr td").each(function(){
	        			var key = $(this).attr("name");
	        			if(key){
	        				$(this).text(map[key]);
	        				for(var k=0;k<picDict.length;k++){
	        					if(key == picDict[k]){
	        						$(this).text(dict[key][map[key]]);
	        					}
	        				};
	        			}
	        		});
	        		$(".invoiceXq table tr td[name='billDate']").text(DateFormat(map.billDate));
	        		if(row.length >0){
	        			for(var j=0;j<row.length;j++){
	        				html.push("<tr><td>"+row[j].goodsName+row[j].guigee+"</td>");	
	        			/*	html.push("<td>"+row[j].orderType+"</td>");	*/
	        				html.push("<td class='money'>￥"+row[j].totalAmt+"</td>");	
	        				html.push("<td>"+row[j].totalNum+"</td>");	
	        				
	        			}
	        			$invoice.nextAll().empty();
	        			$invoice.after(html.join(""));
	        			invoice.pageListLayUi(data,"pageNo_list1",function(curr){
	        				invoice.invoiceXQAjaxNot(curr,billNo);
	        			});
	        		}else{
	        			$invoice.nextAll().empty();
			        	$invoice.after("<tr align='center'><td colspan='4'>未查询到符合条件的数据！</td></tr>");
	        		}
	        	}else{
	        		layer.msg("查询失败！");
	        		$invoice.nextAll().empty();
		        	$invoice.after("<tr align='center'><td colspan='4'>查询失败！</td></tr>");
	        	}
	        },
	        error:function(e){
	        	layer.msg("查询失败！");
	        	$invoice.nextAll().empty();
	        	$invoice.after("<tr align='center'><td colspan='4'>查询失败！</td></tr>");
	        }
		});
	},
	getInputVal:function(){
		var data = {};
		$(".invoiceMangeSearch").find("input,select").each(function(){
			data[$(this).attr("name")] = $(this).val();
		});
		
		return data;
	},
	getInputVal2:function(){
		var data = {};
		$(".invoiceMiddleTitle .groupInput").find("input,select").each(function(){
			data[$(this).attr("name")] = $(this).val();
		});
		return data;
	},
	invoiceManageListUser:function(pageNo){
		var data = {};
		data.query = $("#query").val();
		var $invoice = $(".invoiceMiddleTitle1.invoiceTeap tr:first");
		var before;
		pageNo = pageNo || 1;
		data.pageNo = pageNo;
		$.ajax({
			url: "user/getOrderCustName.do",
	        type: "post",
	        data:data,
	        beforeSend:function(){
	        	$invoice.nextAll().remove();
	        	before = layer.msg("查询中,请稍后...", {
	        			  icon: 16
	        			  ,shade: 0.01
	        			  ,time:0
	        			});
	        	$invoice.after("<tr align='center'><td colspan='5'>查询中，请稍后...</td></tr>")
	        },
	        success: function (data) {
	        	layer.close(before);
	        	var row = data.obj.rows;
	        	if(data.success){
	        		var html=[];
	        		var check = ""
	        		if(row.length >0){
	        			for(var j=0;j<row.length;j++){
	        				html.push("<tr data-custno='"+row[j].applyNo+"'>");
	        				html.push("<td>"+row[j].custName+"</td>");		
	        				html.push("<td>"+row[j].phone+"</td>");	
	        				html.push("<td>"+row[j].applyNo+"</td></tr>");	
	        			}
	        			$invoice.nextAll().remove();
	        			$invoice.after(html.join(""));
	        			
	        			$(".invoiceMiddleTitle1 .allPage").text(data.obj.total);
	        			$(".invoiceMiddleTitle1 .perPage").text(data.obj.pageSize);
	        			invoice.pageListLayUi(data,"page_list3",function(curr){
	        				invoice.invoiceManageListUser(curr);
	        				$("#checkAll").removeAttr("checked");
	        			});
	        		}else{
	        			$invoice.nextAll().remove();
			        	$invoice.after("<tr align='center'><td colspan='5'>未查询到符合条件的数据！</td></tr>");
	        		}
	        	}else{
	        		layer.msg("查询失败！");
	        		$invoice.nextAll().remove();
		        	$invoice.after("<tr align='center'><td colspan='5'>查询失败！</td></tr>");
	        	}
	        },
	        error:function(e){
	        	layer.msg("查询失败！");
	        	$invoice.nextAll().empty();
	        	$invoice.after("<tr align='center'><td colspan='5'>查询失败！</td></tr>");
	        }
		});
	},
	invoiceManageList:function(pageNo){
		var data  = invoice.getInputVal2();
		
		var $invoice = $(".invoiceMiddleTitle.invoiceTeap tr:first");
		data.custNos = $(".invoiceMiddleTitle.invoiceTeap").attr("data-custno");
		var before;
		pageNo = pageNo || 1;
		data.pageNo = pageNo;
		$.ajax({
			url: "user/getMyOrderNoBill.do",
	        type: "post",
	        data:data,
	        beforeSend:function(){
	        	$invoice.nextAll().remove();
	        	before = layer.msg("查询中,请稍后...", {
	        			  icon: 16
	        			  ,shade: 0.01
	        			  ,time:0
	        			});
	        	$invoice.after("<tr align='center'><td colspan='5'>查询中，请稍后...</td></tr>")
	        },
	        success: function (data) {
	        	layer.close(before);
	        	var row = data.obj.rows;
	        	if(data.success){
	        		debugger;
	        		var html=[];
	        		var check = ""
	        		if(row.length >0){
	        			for(var j=0;j<row.length;j++){
	        				var pd =  custCat[row[j].orderNo] || 0;
	        				if(pd != 0){
	        					check = "checked";
	        				}else{
	        					check = "";
	        				}
	        				
	        				html.push("<tr data-order='"+row[j].orderNo+"' data-custno='"+row[j].custNo+"' data-amt='"+row[j].totalAmt+"' data-custName='"+row[j].custName+"'><td><input type='checkbox' "+check+"></td>");
	        				html.push("<td>"+row[j].orderNo+"</td>");	
	        				if(row[j].billType == "01"){
	        					html.push("<td>普通发票</td>");	
	        				}else if(row[j].billType == "02"){
	        					html.push("<td>增值税发票</td>");	
	        				}else{
	        					html.push("<td>无法识别</td>");	
	        				}
	        				html.push("<td class='money'>￥"+row[j].billMoney+"</td>");	
	        				html.push("<td>"+new Date( row[j].startTime).Format("yy-MM-dd hh:mm")+"</td>");	
	        				html.push("<td>"+row[j].billReceiveAddress+"</td></tr>");	
	        				
	        			}
	        			$invoice.nextAll().remove();
	        			$invoice.after(html.join(""));
	        			
	        			$(".invoiceMangePageList .allPage").text(data.obj.total);
	        			$(".invoiceMangePageList .perPage").text(data.obj.pageSize);
	        			invoice.pageListLayUi(data,"page_list",function(curr){
	        				invoice.invoiceManageList(curr);
	        				$("#checkAll").removeAttr("checked");
	        			});
	        		}else{
	        			$invoice.nextAll().remove();
			        	$invoice.after("<tr align='center'><td colspan='5'>未查询到符合条件的数据！</td></tr>");
	        		}
	        	}else{
	        		layer.msg("查询失败！");
	        		$invoice.nextAll().remove();
		        	$invoice.after("<tr align='center'><td colspan='5'>查询失败！</td></tr>");
	        	}
	        },
	        error:function(e){
	        	layer.msg("查询失败！");
	        	$invoice.nextAll().empty();
	        	$invoice.after("<tr align='center'><td colspan='5'>查询失败！</td></tr>");
	        }
		});
	},
	custCountAmt:function(){
		$(".invoiceMangeList").find("tr:not(:first)").each(function(){
			var _ = $(this);
			if(_.find("td:first input").is(":checked")){
				custCat[$(this).attr("data-order")] = {};
				
				custCat[$(this).attr("data-order")].custNo = $(this).attr("data-custno");
				custCat[$(this).attr("data-order")].goodsMoney = $(this).attr("data-amt");
				custCat[$(this).attr("data-order")].custName = $(this).attr("data-custName");
			}else{
				delete custCat[$(this).attr("data-order")];
			}
		});
		// 总金额
		var allAmt = 0;
		for(i in custCat){
			allAmt += parseFloat(custCat[i].goodsMoney);
		}
		$(".invoiceContTitle .amt_all .money").text("￥"+allAmt.toFixed(2));
	},
	//去开票
	goInvoice:function(pageNo){ /*invoiceMangeCount2 invoiceTeap hidden*/
		var $invoice = $(".invoiceMiddleTitle  .go_invoiceBill_table tr:first");
		var before;
		var data  = custCat;
		var objs = {};
		//选择多个买家
		applyNo = $(".invoiceMiddleTitle.invoiceTeap").attr("data-custno");
		pageNo = pageNo || 1;
		objs.applyNo = applyNo;
		objs.pageNo = pageNo;
		/*if(JSON.stringify(data) == "{}"){
			layer.msg("请选择订单！");
		}else{*/
			//$(".invoiceTopNav .handle_teap").eq(2).addClass("active").siblings(".handle_teap").removeClass("active");
			
			
			objs.pageNo = pageNo;
			var orderNo = custNo ="";
			var allAmt = 0;
			for(i in data){
				orderNo += i+",";
				custNo = data[i].custNo;
				allAmt += parseFloat(custCat[i].goodsMoney);
			}
			objs.orderNo = orderNo;
			objs.custNos = custNo;
			
			$(".go_invoiceBill_title p span.money").text("￥"+allAmt);//总金额
			
			$.ajax({
				url: "user/getMyOrderNoBillDetail.do",
		        type: "post",
		        data:objs,
		        beforeSend:function(){
		        	$invoice.nextAll().remove();
		        	before = layer.msg("查询中,请稍后...", {
		        			  icon: 16
		        			  ,shade: 0.01
		        			  ,time:0
		        			});
		        	$invoice.after("<tr align='center'><td colspan='3'>查询中，请稍后...</td></tr>")
		        },
		        success: function (data) {
		        	layer.close(before);
		        	var row = data.obj.rows;
		        	if(data.success){
		        		var html=[];
		        		var check = "";
		        		$(".go_invoiceBill_title p span.billReceipt").text(data.map.address.billReceipt);//纳税人识别号
		        		$(".go_invoiceBill_title p span.billTitle").text(data.map.address.companyName);//个人/公司(抬头)
		        		$(".go_invoiceBill_title p span.openBank").text(data.map.address.openBand);//开户行
		        		$(".go_invoiceBill_title p span.bandCard").text(data.map.address.bandCard);//开户行账号
		        		var type;
		        		if(data.map.address.billType=="02")
		        		{
		        			type = "增值税发票";
		        		}
		        		else if(data.map.address.billType=="01")
		        		{
		        			type = "普通发票";
		        		}
		        		var nature;
		        		if(data.map.address.billNatrue=="2")
		        		{
		        			nature = "纸质";
		        		}
		        		else if(data.map.address.billNatrue=="1")
		        		{
		        			nature = "电子";
		        		}
		        		$("#typeAddress").val(nature+type);
		        		$("#applyNoHid").val(applyNo);
		        		$(".go_invoiceBill_title p span.billType").text(nature+type);//发票类型
		        		$(".go_invoiceBill_title p span.registerPhone").text(data.map.address.registerPhone);//注册电话
		        		$(".go_invoiceBill_title p span.registerAddress").text(data.map.address.registerAddress);//注册地址
		        		if(row.length >0){
		        			for(var j=0;j<row.length;j++){
			        			html.push("<tr>");
			        			html.push("<td align='left'>"+row[j].goodsName+"</td>");
			        			html.push("<td>"+row[j].totalNum+"</td>");	
			        			html.push("<td class='money'>￥"+row[j].totalAmt+"</td></tr>");
		        			}	
		        			
			        		$invoice.nextAll().remove();
			        		$invoice.after(html.join(""));
			        			
			        		$(".invoiceMangePageList2 .allPage").text(data.obj.total);
			        		$(".invoiceMangePageList2 .perPage").text(data.obj.pageSize);
			        		invoice.pageListLayUi(data,"page_list",function(curr){
			        			invoice.invoiceManageList(curr);
			        			$("#checkAll").removeAttr("checked");
			        		});
		        		}else{
		        			$invoice.nextAll().remove();
				        	$invoice.after("<tr align='center'><td colspan='3'>未查询到符合条件的数据！</td></tr>");
		        		}
		        	}else{
		        		layer.msg("查询失败！");
		        		$invoice.nextAll().remove();
			        	$invoice.after("<tr align='center'><td colspan='3'>查询失败！</td></tr>");
		        	}
		        },
		        error:function(e){
		        	layer.msg("查询失败！");
		        	$invoice.nextAll().empty();
		        	$invoice.after("<tr align='center'><td colspan='3'>查询失败！</td></tr>");
		        }
			});
		/*}*/
	},
	
	
	
	goInvoiceQR:function(){
		var myVal = $("#typeAddress").val();
		var layerContent = "<div class='layer-public-style'><form onsubmit='return false' class='form-inline'><div class='form-group'>"+
			"<label>发票类型：</label>"+
			"<span>"+myVal+"</span></div><div class='form-group'>"+
			"<label>发票编号：</label>"+
			"<input class='form-control mybillNoVal' style='width: 138px; placeholder='请输入发票编号' id='billNo'><button style='margin-left: 9px;background-color: white;color: blue;' onclick='addTick()' type='button'>添加发票</button></div></form></div>";
		var editPwLayer = layer.open({
			type: 1,
			title: '填写发票编号',
			btn: ['确认','取消'],
			btnAlign : 'c',
			skin: 'tn-style', //加上自定义样式
			area: ['320px', '240px'], //宽高
			content: layerContent,
			yes:function(index){
				//var billNo = $(".mybillNoVal").val();
				//alert(billNo);
				var length = $(".mybillNoVal").length;
				var billNoArr = new Array(length);//发票编号
				var totalMoney = $(".go_invoiceBill_title p span.money").text();//总价钱
				var i = 0;
				$($(".mybillNoVal").each(function(){
					billNoArr[i] = $(this).val();
					i++;
				}));
				var applyNo = $("#applyNoHid").val();//发票序号
		
				if(billNoArr == ""){
					layer.msg("请输入发票编号！")
				}else{
					
					var before;
					$.ajax({
						url: "user/saveConditionBill.do",
				        type: "post",
				    	async : false,
				        cache : false,
				        traditional: true,
				  
				        data:{
				        	"applyNo":applyNo,
				        	"billMoney":totalMoney,
				        	 billNoArr:billNoArr
				        },
				        beforeSend:function(){
				        	before = layer.msg("保存中,请稍后...", {
				        			 icon: 16
				        			 ,shade: 0.01
				        			 ,time:0
				        		});
				        },
				        success: function (data) {
				        	layer.close(before);
				        	if(data.success){
				        		layer.close(editPwLayer);
				        		layer.msg("保存成功,跳转中...",{time:2},function(){
				        			location.reload();
				        		});
				        		}else{
				        		layer.msg(data.msg);
				        		}
				        },error:function(e){
				        	layer.close(before);
				        	layer.msg("错误，"+e.status);
				        }
					});
					
				}
			}
		});
	},
	//完成
	goInvoiceWC:function(){
		var opPwLayer = layer.confirm('确认完成?',{
			btn:['确认','取消']
		},function(index){
				var tBusCompleteBillVo = {};
				var logisticsName = $("#logisticsName").val();
				var expressNumber = $("#expressNumber").val();
				var billNo = $(".invoiceMangeCount3 .go_invoiceBill_title .billNo").text();
				tBusCompleteBillVo.logisticsName = logisticsName;
				tBusCompleteBillVo.expressNumber = expressNumber;
				tBusCompleteBillVo.billNo = billNo;
				if(logisticsName == ""||expressNumber==""){
					layer.msg("请填写快递单号或快递名称!")
				}else{
				$.ajax({
					url: "user/saveComplete.do",
			        type: "post",
			        data:tBusCompleteBillVo,
			        beforeSend:function(){
			        	before = layer.msg("保存中,请稍后...", {
			        			 icon: 16
			        			 ,shade: 0.01
			        			 ,time:0
			        		});
			        },
			        success: function (data) {
			        	layer.close(before);
			        	if(data.success){
			        		layer.close(opPwLayer);
			        		$(".invoiceMiddleTitle1").removeClass("hidden").siblings(".invoiceTeap").addClass("hidden");
			    			$(".invoiceTopNav .handle_teap").eq(0).addClass("active").siblings(".handle_teap").removeClass("active");
			    			invoice.invoiceManageListUser();
			    			custCat = {};
			        	}else{
			        		layer.msg(data.msg);
			        	}
			        },error:function(e){
			        	layer.close(before);
			        	layer.msg("错误，"+e.status);
			        }
				});}
		});
	},
	
	kdDict:function($dom){
		var html = [];
		for(i in dict.logisticsName){
			html.push("<option value='"+ i +"'>"+dict.logisticsName[i]+"</option>")
		}
		
		$dom.find("option:first").after(html.join(""));
	},
	pageListLayUi:function(data,$dom,callback){
		$dom = $dom || "page_list";
		var totalPage=Math.ceil(eval(data.obj.total/data.obj.pageSize));
	    //显示分页
	    layui.use(['laypage', 'layer'], function(){
			var laypage = layui.laypage;
			var layer = layui.layer;
			laypage({
				cont: $dom, //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
		    	pages: totalPage, //通过后台拿到的总页数
		     	curr: data.obj.pageNo || 1, //当前页
		      	skin: '#03A1A4',
		  	  	groups:5,
		  	  	prev:"上一页",
		  	  	next:"下一页",
		     	jump: function(obj, first){ //触发分页后的回调
			        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
			        	callback(obj.curr);
			        }
			    }
			});
		}); 
	}
}



//确认开票
function addTick()
{
	myNumVal = myNumVal + 1;
	$(".mybillNoVal").parent().append("<br><label class="+myNumVal+">发票编号：</label>"+
		"<input  class='mybillNoVal form-control "+myNumVal+"' style='width: 138px; placeholder='请输入发票编号'><button style='margin-left: 9px;background-color: white;color: red;' onclick='deleteTick("+myNumVal+")' type='button'  class="+myNumVal+">删除</button>")
}	

function deleteTick(str)
{
	var myId = "."+str;
	$("input").remove(myId);
	$("label").remove(myId);
	$("button").remove(myId);
	$("br").remove();
}

$(".myChange").click(function(){
	$(".clearFix").hide();
	$(".invoiceXq").hide();
	var myValBillStatus  = $(".billStatus").val();
	if(myValBillStatus=="2")
	{
		$(".wcPart").show();
	}	
	$(".invoiceXqbf").show();
	var billNo = $(".myBillVal").val();
	var $invoice = $(".invoiceContennt .invoiceXq .pageListBill tr:first");
	var before;
	var pageNo = pageNo || 1;
	$.ajax({
		url: "user/getBillCompleteBill.do",
        type: "post",
        data:{"billNo":billNo,"pageNo":pageNo},
      
        beforeSend:function(){
        	$invoice.nextAll().empty();
        	before = layer.msg("查询中,请稍后...", {
        			  icon: 16
        			  ,shade: 0.01
        			  ,time:0
        			});
        	$invoice.after("<tr align='center'><td colspan='4'>查询中，请稍后...</td></tr>")
        },
        success: function (data) {
        	invoice.kdDict($("#logisticsName1"));
        	layer.close(before);
        	var picDict = ['billStatus','billNatrue','billType','logisticsName'];//,'logisticsName'
        	var row = data.obj.rows;
        	var map = data.map.completeBill;
        	if(data.success){
        		debugger;
        		var html=[];
        		var province = map.billProvince;
        		var billCity = map.billCity;
        		var billArea = map.billArea;
        		var pcaAddress = province+billCity+billArea;
        		$(".invoiceXqbf table.listNO tr td[name=pcaAddress]").text(pcaAddress);
        		$(".invoiceXqbf table.listNO tr td input[name=billNo]").val(map.billNo);
        		$(".invoiceXqbf table.listNO tr td[name=money]").text("￥"+map.billMoney);
        		$(".invoiceXqbf table.listNO tr td select[name=billType]").val(map.billType);
        		$(".invoiceXqbf table.listNO tr td select[name=billNatrue]").val(map.billNatrue);
        		$(".invoiceXqbf table.listNO tr td[name=pcaAddress]").text(pcaAddress);
        		$(".invoiceXqbf table.listNO tr td select[name=input_province]").val(map.billProvince);
        		$(".invoiceXqbf table.listNO tr td select[name=input_province]").change();
        		$(".invoiceXqbf table.listNO tr td select[name=input_city]").val(map.billCity);
        		$(".invoiceXqbf table.listNO tr td select[name=input_city]").change();
        		$(".invoiceXqbf table.listNO tr td select[name=input_area]").val(map.billArea);
        		
        		if(map.logisticsName!=null)
        		{
        			$(".invoiceXqbf table.listNO tr td select[name=logisticsName]").val(map.logisticsName);
        		}	
        		$(".invoiceXqbf table.listNO tr td select[name=billNatrue]").val();
        		$(".invoiceXqbf table.listNO tr td input").each(function(){
        			var key = $(this).attr("name");
        			if(key){
        				$(this).val(map[key]);
        				for(var k=0;k<picDict.length;k++){
        					if(key == picDict[k]){
        						$(this).val(dict[key][map[key]]);
        					}
        				};
        			}
        		});
        		$(".invoiceXqbf table tr td[name='billDate']").text(DateFormat(map.billDate));
        			$invoice.nextAll().empty();
        			$invoice.after(html.join(""));
        			invoice.pageListLayUi(data,"pageNo_list1",function(curr){
        				invoice.invoiceXQAjaxNot(curr,billNo);
        			});
        			
        		$(".makeSaveUpdate").click(function(){
        			var billType = $(".invoiceXqbf table.listNO tr td select[name=billType] option:selected").val();
        			var billNatrue = $(".invoiceXqbf table.listNO tr td select[name=billNatrue] option:selected").val();
        			var money = $(".invoiceXqbf table.listNO tr td[name=money]").text();
        			var logisticsName = $(".invoiceXqbf table.listNO tr td select[name=logisticsName]").val();
        			var billProvince = $(".invoiceXqbf table.listNO tr td select[name=input_province]").val();
        			var billCity = $(".invoiceXqbf table.listNO tr td select[name=input_city]").val();
        			var billArea = $(".invoiceXqbf table.listNO tr td select[name=input_area]").val();
        			
        			var moneyStr = money.substring(1);
        			$.ajax({
        				url:"user/updateCompleteBill.do",
        				type:"post",
        				data:{
        					"applyNo":map.applyNo,
        					"billNo":$(".invoiceXqbf table.listNO tr td input[name=billNo]").val(),
        					"money":moneyStr,
        					"billReceiveName":$(".invoiceXqbf table.listNO tr td input[name=billReceiveName]").val(),
        					"billReceiveAddress":$(".invoiceXqbf table.listNO tr td input[name=billReceiveAddress]").val(),
        					"billReceiveMail":$(".invoiceXqbf table.listNO tr td input[name=billReceiveMail]").val(),
        					"billReceivePhone":$(".invoiceXqbf table.listNO tr td input[name=billReceivePhone]").val(),
        					"companyName":$(".invoiceXqbf table.listNO tr td input[name=companyName]").val(),
        					"billType":billType,
        					"billNatrue":billNatrue,
        					"logisticsName":logisticsName,
        					"expressNumber":$(".invoiceXqbf table.listNO tr td input[name=expressNumber]").val(),
        					"billProvince":billProvince,
        					"billCity":billCity,
        					"billArea":billArea
        				},
        				success:function(data){
        					location.reload();
        				}
        			});
        		});	
        			
        			
        			
        		}
        	else{
        		layer.msg("查询失败！");
        		$invoice.nextAll().empty();
	        	$invoice.after("<tr align='center'><td colspan='4'>查询失败！</td></tr>");
        	}
        },
        error:function(e){
        	layer.msg("查询失败！");
        	$invoice.nextAll().empty();
        	$invoice.after("<tr align='center'><td colspan='4'>查询失败！</td></tr>");
        }
	});
});


$(".goBackShow").click(function(){
	$(".content_right").load("user/toBillManageMenu.do");
});

function siteAdd() {
	var html = "<option value=''>== 请选择 ==</option>";
	$("#input_city").append(html);
	$("#input_area").append(html);
	$
			.each(
					pdata,
					function(idx, item) {
						if (parseInt(item.level) == 0) {
							html += "<option value='" + item.names + "' exid='" + item.code + "'>"
									+ item.names + "</option>";
						}
					});
	$("#input_province").append(html);
	$("#input_province")
			.change(
					function() {
						if ($(this).val() == "")
							return;
						$("#input_city option").remove();
						$("#input_area option").remove();
						var code = $(this).find("option:selected").attr(
								"exid");
						code = code.substring(0, 2);
						var html = "<option value=''>== 请选择 ==</option>";
						$("#input_area").append(html);
						$
								.each(
										pdata,
										function(idx, item) {
											if (parseInt(item.level) == 1
													&& code == item.code
															.substring(0, 2)) {
												html += "<option value='" + item.names + "' exid='" + item.code + "'>"
														+ item.names
														+ "</option>";
											}
										});
						$("#input_city").append(html);
					});
	$("#input_city")
			.change(
					function() {
						if ($(this).val() == "")
							return;
						$("#input_area option").remove();
						var code = $(this).find("option:selected").attr(
								"exid");
						code = code.substring(0, 4);
						var html = "<option value=''>== 请选择 ==</option>";
						$
								.each(
										pdata,
										function(idx, item) {
											if (parseInt(item.level) == 2
													&& code == item.code
															.substring(0, 4)) {
												html += "<option value='" + item.names + "' exid='" + item.code + "'>"
														+ item.names
														+ "</option>";
											}
										});
						$("#input_area").append(html);
					});
	//绑定
	$("#input_province").val("北京市");
	$("#input_province").change();
	$("#input_city").val("市辖区");
	$("#input_city").change();
	$("#input_area").val("东城区");
}
