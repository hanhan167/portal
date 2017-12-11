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
<link rel="stylesheet" type="text/css" href="frame/static/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="frame/static/css/fans.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />

<title>PORTAL我的销售员</title>
</head>
<body>
	<div class="site_head">
		<img src="frame/static/img/search.png">
		<input placeholder="输入名称、电话搜索">
	</div>
	<table id="tabel">
		<thead>
			<tr>
				<th>业务员名称</th>
				<th>业务员电话</th>
				<th>上次登陆时间</th>
				<th>销售金额</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>	
	<div id="demo"></div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/layui/layui.js"></script>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
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
	demo(curr);
	$(".site_head img").click(function(){
		var query = $(".site_head input").val();
		$.ajax({
			url:"user/selectSale.do",
			type:"post",
			data:{
				query:query,
			},
			success:function(data){
				var html ='';
		        $(data.obj.rows).each(function (n, Row) {
					html += '<tr><td>'+Row.name+'</td>';
					html += '<td>'+(Row.phoneNo==null?'无':Row.phoneNo)+'</td>';
					html += '<td>'+DateFormat(Row.logintime,"yy-MM-dd")+'</td>';
				  	html += '<td>'+(Row.totalConsume==null?0:Row.totalConsume)+'</td></tr>';
		        });
		   		$("#tabel tbody").html(html);
			}
		});
	});
});
function demo(curr){
	$.ajax({
		url:"user/selectSale.do",
		type:"post",
		data:{pageNo:curr||1},
		success:function(data){
			var html ='';
	        $(data.obj.rows).each(function (n, Row) {
				html += '<tr><td>'+Row.name+'</td>';
				html += '<td>'+(Row.phoneNo==null?'无':Row.phoneNo)+'</td>';
				html += '<td>'+DateFormat(Row.logintime,"yy-MM-dd")+'</td>';
			  	html += '<td>'+(Row.totalConsume==null?0:Row.totalConsume)+'</td></tr>';
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