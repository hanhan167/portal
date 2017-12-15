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
<link rel="stylesheet" type="text/css" href="frame/static/css/fans.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<title>PORTAL粉丝查询</title>
<style type="text/css">
.site_head>h4{
	margin: 0;
}
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
		<h4>我的粉丝</h4>
		<br>
		<img src="frame/static/img/search.png">
		<input placeholder="搜索粉丝">
		<div id="time">
			<label>时间区间:</label>
			<input id="test1" type='text' data-settime='true' placeholder='开始时间'/>~
			<input type='text' data-settime='true' id="test2" placeholder='结束时间'/>
		</div>
		<button>按销量排序</button>
		<button>按金额排序</button>
	</div>
	<table id="tabel">

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
	var Sort1;
	var Sort2;
	var startTime;
	var endTime;
	$(".site_head img").click(function(){
		startTime = $(".site_head #time input:eq(0)").val();
		endTime = $(".site_head #time input:eq(1)").val();
		query = $(".site_head>input").val();
		curr=1;
		demo(curr,query,Sort1,Sort2,startTime,endTime);
	});
	//运行
	demo(curr,"","","","","");
	var shi = true;
	$(".site_head>button:eq(0)").click(function(){
		if(shi){
			demo(curr,query,"","1",startTime,endTime);
			shi = false;
			Sort2 =1;
		}else{
			demo(curr,query,"","0",startTime,endTime);
			shi = true;
			Sort2 =0;
		}
		Sort1 = "";
	});
	var shi1 = true;
	$(".site_head>button:eq(1)").click(function(){
		if(shi1){
			demo(curr,query,"1","",startTime,endTime);
			shi1 = false;
			Sort1 =1;
		}else{
			demo(curr,query,"2","",startTime,endTime);
			shi1 = true;
			Sort1=2;
		}
		Sort2="";
	});
});
function demo(curr,query,Sort1,Sort2,startTime,endTime){
	$.ajax({
		url:'user/getFansPage.do',
		data:{
			pageNo:curr||1,
			query:query,
			Sort1:Sort1,
			Sort2:Sort2,
			startTime:startTime,
			endTime:endTime,
		},
		beforeSend:function(){
			$("#tabel").html("正在加载中...");
		},
		success:function(data){
			var html ='<tr><th>粉丝名</th><th>等级</th><th>邮箱</th><th>电话</th><th>总消费</th><th>总购买量</th><th>上次登陆时间</th><th>累计奖励</th></tr><tr class="margin-tr"></tr>';
	        $(data.obj.rows).each(function (n, Row) {
				html += '<tr><td>'+Row.name+'</td><td>'+(Row.grade==null?'暂无等级':Row.grade)+'</td><td><img src="frame/static/img/-company_e-mail.png" style="vertical-align: middle;margin-right:5px;" /><span style="vertical-align: middle;">'+Row.email+'</span></td>'+
			  	  '<td><img src="frame/static/img/contact_phone.png" style="vertical-align: middle; margin-right:5px;" /><span style="vertical-align: middle;">'+Row.phoneNo+'</span></td><td>￥'+(Row.totalConsume==null?0:Row.totalConsume)+'</td>'+
			  	  '<td>'+(Row.tota==null?0:Row.tota)+'</td><td>'+DateFormat(Row.logintime,"yy-MM-dd")+'</td><td>'+Row.reward+'</td></tr><tr class="margin-tr"></tr>';
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
				        	demo(obj.curr);
				        }
				    }
				});
			}); 
		}
	});
}
</script>