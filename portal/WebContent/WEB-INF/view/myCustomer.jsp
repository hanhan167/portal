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
<!-- <link rel="stylesheet" type="text/css" href="frame/static/layui/css/layui.css" media="all" /> -->
<link rel="stylesheet" type="text/css" href="frame/static/css/fans.css" />
<title>PORTAL粉丝查询</title>
<style>
	table{
	    width: 735px;
	    height: 30px;
	}
	table th{
		background-color: #ccc;
		text-align:left;
	}
	table th,table td{
		height:40px;
		padding:5px;
	}
	table td{
		background:#fff;
	}
	table tr:hover>td{
		background-color: #ccc;
	}
	.margin-tr{
		height:20px;
	}
</style>
</head>
<body>
		<div class="site_head">
			<h4>我的客户</h4>
			<button>金额排序</button>
			<button>数量排序</button>
			<img src="frame/static/img/search.png">
			<input placeholder="输入客户名称搜索">
		</div>
		<table id="tabel">

		</table>	
		<div id="demo"></div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/layui/layui.js"></script>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
layui.config({
	 dir: 'frame/static/layui/' //layui.js 所在路径（注意，如果是script单独引入layui.js，无需设定该参数。），一般情况下可以无视
	 ,version: false //一般用于更新模块缓存，默认不开启。设为true即让浏览器不缓存。也可以设为一个固定的值，如：201610
	 ,debug: false //用于开启调试模式，默认false，如果设为true，则JS模块的节点会保留在页面
	 ,base: 'frame/static/layui/' //设定扩展的Layui模块的所在目录，一般用于外部模块扩展
});

$(function(){
	var curr=1;
	//运行
	demo(curr,"");
	$(".site_head>img").click(function(){
		demo(curr,$(this).next().val());
	});
	var shi = true;
	//金额排序
	$(".site_head>button:eq(0)").click(function(){
		if (shi){
			sort(curr,"1","");
			shi = false;
		}else{
			sort(curr,"0","");
			shi = true;
		}
		
	});
	var shi1 = true;
	//数量排序
	$(".site_head>button:eq(1)").click(function(){
		if (shi1){
			sort(curr,"","1");
			shi1 = false;
		}else{
			sort(curr,"","0");
			shi1 = true;
		}
		
	});
});
function demo(curr,name){
	$.ajax({
		url:'user/getMyCustomerPage1.do',
		data:{pageNo:curr||1,name:name},
		success:function(data){
			var html ='<tr><th>客户姓名</th><th>电话号码</th><th>地址</th><th>总购买量</th><th>总购买金额</th></tr><tr class="margin-tr"></tr>';
	        $(data.obj.rows).each(function (n, Row) {
				html += '<tr><td>'+Row.name+'</td><td>'+Row.phone+'</td><td>'+(Row.addr==null?'':Row.addr)+'</td>'+
			  	'<td>'+Row.totalNum+'</td><td>￥'+(Row.totalAmt==null?0:Row.totalAmt)+'</td></tr><tr class="margin-tr"></tr>';
	        }); 
	   		$("#tabel").html(html);
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
				          demo(obj.curr,name);
				        }
				      }
				});
			}); 
		}
	});
}
function sort(curr,Sort1,Sort2){
	$.ajax({
		url:'user/getMyCustomerPage1.do',
		data:{pageNo:curr||1,Sort1:Sort1,Sort2:Sort2},
		success:function(data){
			var html ='<tr><th>客户姓名</th><th>电话号码</th><th>地址</th><th>总购买量</th><th>总购买金额</th></tr><tr class="margin-tr"></tr>';
	        $(data.obj.rows).each(function (n, Row) {
				html += '<tr><td>'+Row.name+'</td><td>'+Row.phone+'</td><td>'+(Row.addr==null?'':Row.addr)+'</td>'+
			  	'<td>'+Row.totalNum+'</td><td>￥'+(Row.totalAmt==null?0:Row.totalAmt)+'</td></tr><tr class="margin-tr"></tr>';
	        }); 
	   		$("#tabel").html(html);
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
				        if(!first){ //点击跳页触发函数自身，并传递当前页
				          sort(curr,Sort1,Sort2)
				        }
				      }
				});
			}); 
		}
	});
}
</script>