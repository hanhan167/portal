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
<link rel="stylesheet" type="text/css" href="frame/static/css/fans.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<title>PORTAL产品销售排行</title>
<style type="text/css">
.site_head>div>label{
	width: 100px;
}
.site_head>div>input{
	display: inline-block;
	padding:0 5px;
	margin:0 5px;
	width: 170px;
	border-radius:4px;
}
</style>
</head>
<body>
	<div class="site_head">
		<img src="frame/static/img/search.png">
		<input placeholder="输入名称、规格搜索">
		<div id="time">
			<label>时间区间:</label>
			<input id="test1" type='text' data-settime='true' placeholder='开始时间'/>~
			<input type='text' data-settime='true' id="test2" placeholder='结束时间'/>
		</div>
		<button id="number">数量正反排序</button>
		<button id="money">金额正反排序</button>
	</div>
	<table id="tabel">
		<thead>
			<tr>
				<th>销售产品名称</th>
				<th>销售规格</th>
				<th>销售数量</th>
				<th>总销售金额</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>	
	<div id="demo"></div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/layui/layui.js"></script>
<script src="frame/static/js/bootstrap.min.js"></script>
<script src="frame/static/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript">
layui.config({
	 dir: 'frame/static/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
	 ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
	 ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
	 ,base: 'frame/static/layui/' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
});
$(function(){
	var curr=1;
	var query;
	var startTime;
	var endTime;
	var sort1 = "";
	var sort2 = "";
	//运行
	demo(curr);
	//销售数量
	$(".site_head #number").click(function(){
		sort2 = "";
		if(sort1){
			sort1=0;
		}else{
			sort1=1;
		}
		sort2 ="";
		curr=1;
		demo(curr,query,startTime,endTime,sort1,sort2);
	});
	//jin'e
	$(".site_head #money").click(function(){
		sort1 = "";
		if(sort2){
			sort2=0;
		}else{
			sort2=1;
		}
		sort1="";
		curr=1;
		demo(curr,query,startTime,endTime,sort1,sort2);
	});
	console.log($(".site_head #time input:eq(1)"))
	//shijian
	//$(".site_head #time input:eq(1)").change(function(){
	$(".site_head img").click(function(){
		query = $(this).next().val();
		startTime = $("#time input:eq(0)").val();
		endTime = $("#time input:eq(1)").val();
		curr=1;
		demo(curr,query,startTime,endTime,sort1,sort2);
	});
	
});
function demo(curr,query,startTime,endTime,sort1,sort2){
	$.ajax({
		url:"user/productRank.do",
		data:{
			pageNo:curr||1,
			query:query,
			startTime:startTime,
			endTime:endTime,
			Sort1:sort1,
			Sort2:sort2,
		},
		type:"post",
		success:function(data){
			var html ='';
	        $(data.obj.rows).each(function (n, Row) {
				html += '<tr><td>'+Row.goodsName+'</td>';
				html += '<td>'+Row.guigee+'</td>';
				html += '<td>'+Row.totalNum+'</td>';
			  	html += '<td>'+Row.totalAmt+'</td></tr>';
	        });
	   		$("#tabel tbody").html(html);
	        var totalPage=Math.ceil(eval(data.obj.total/data.obj.pageSize));
		    //显示分页
		    layui.use(['laypage', 'layer'], function(){
				var laypage = layui.laypage;
				var layer = layui.layer;
				laypage({
					cont: 'demo', //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
			    	pages: totalPage, //通过后台拿到的总页数
			     	curr: data.obj.pageNo || 1, //当前页
			      	skin: '#03A1A4',
			  	  	groups:5,
			  	  	prev:"<",
			  	  	next:">",
			     	jump: function(obj, first){ //触发分页后的回调
				        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
				        	demo(obj.curr);
				        }
				    }
				});
			}); 
		}
	});
}
</script>