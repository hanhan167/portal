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
<title>PORTAL地址管理</title>
</head>
<body>
	
	<div class="site_head">
		<h4>地址管理</h4>
		<button type="button" class="add_site"><img src="frame/static/img/add.png"/>添加新地址</button>
	</div>
	<div class="site_headline">
		<input class='name' value='收货人' disabled='disabled'>
      	<input class='site' value='收货人详细地址' disabled='disabled'>
      	<input class='postcode' value='邮编' disabled='disabled'>
      	<input class='tel' value='电话号码' disabled='disabled'>
	</div>
	<div class="site_box">
	</div>
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
	$(".site_head button").click(function(){
		console.log($(this).parent().parent())
		$(this).parent().parent().load("user/toAddSite.do");
	});
	var curr=${pageNo};
	//运行
	demo(curr);
	//以下将以jquery.ajax为例，演示一个异步分页
	function demo(curr){
		$.ajax({
			url:'user/getSitePage.do',
			data:{pageNo:curr||1},
			type:"get",
			success:function(data){
				  $(".site_box").empty();
		          $(data.obj.rows).each(function (n, Row) {
				  	  var content = "";
		        	  content += " <div class='site_module'>";
		        	  content += " <input class='name' value='"+Row.addresseeName+"' disabled='disabled'>";
		        	  content += " <input class='site' value='"+Row.address+"' disabled='disabled'>";
		        	  content += " <input class='postcode' value='"+Row.postCode+"' disabled='disabled'>";
		        	  content += " <input class='tel' value='"+Row.telephone+"' disabled='disabled'>";
		        	  content += " <a onclick='updateSite(this)'>编辑</a>";
		        	  content += " <input type='hidden' value='"+Row.tableKey+"'>";
		        	  content += " <a onclick='deleteSite(this)'>删除</a>";
		        	  if(Row.addressStatus=="1"){
		        		  content += " <a class='default_address'>默认地址</a>";
		        	  }else{
		        		  content += " <a class='default_address choose_address' onclick='defaultAddress(this)' pageNo='"+data.obj.pageNo+"'>设为默认</a>";
		        	  }
		        	  content += "  </div>";
			   		  $(".site_box").append(content);
		          }); 
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
					  	  prev:"< 上一页",
					  	  next:"> 下一页",
					      jump: function(obj, first){ //触发分页后的回调
					        if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
					          demo(obj.curr);
					        }
					      }
					});
				}); 
			}
		})	  
	};
})
//删除
function deleteSite(element){
	var tableKey=$(element).prev().val();
	layer.open({
		 title: '提示信息'
		 ,content:"是否删除该地址?"
		 ,btn:['确认','取消']
		 ,yes: function(index, layero){
			 layer.close(index);
			 $.ajax({
					url:"user/deleteSite.do",
					data:{"tableKey":tableKey},
					success:function(data){
						if(!data.success){
							layer.open({
								 title: '错误信息'
								 ,content:data.msg
							}); 
						}else{
							$(".site_head").parent().load("user/toSite.do");
						}
					}
				})
		  }
		  ,btn2: function(index, layero){
			 layer.close(index);
		  }
		  ,closeBtn: 0
	}); 
}

//到编辑页面
function updateSite(element){
	var tableKey=$(element).next().val();
	$(".site_headline").parent().load("user/toEditSite.do?tableKey="+tableKey);
}

//设置默认地址
function defaultAddress(element){
	var tableKey=$(element).prev().prev().val();
	var pageNo=$(element).attr('pageno');
	$.ajax({
		url:"user/defaultAddress.do",
		data:{"tableKey":tableKey,"pageNo":pageNo},
		success:function(data){
			if(!data.success){
				layer.open({
					 title: '错误信息'
					 ,content:data.msg
				}); 
			}else{
				$(".site_head").parent().load("user/toSite.do?pageNo="+pageNo);
			}
		}
	});
}
</script>