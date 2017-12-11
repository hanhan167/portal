<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hansy.portal.model.vo.TUserAddressVo" %>
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
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/site.css" />
<title>PORTAL地址管理_修改地址</title>
</head>

<body>
	
	<div class="site_head">
		<h4>地址管理</h4>
	</div>
	
	<div class="moban">
		<form method="post" id="site_add" onsubmit="return false">
			<div id="">
				<label>省：</label>
				<select name="input_province" id="input_province" class="form-control">
				</select>
				<label>市：</label>
				<select name="input_city" id="input_city" class="form-control">
				</select>
				<label>区：</label>
				<select name="input_area" id="input_area" class="form-control">
				</select>
			</div>
			<div id="">
				<label>邮政编码：</label>
				<input type="text" name="youbian" value="${addressVo.postCode}" maxlength="6"/>
				<span></span>
			</div>
			<div id="">
				<label>详细地址：</label>
				<textarea name="dizhi">${addressVo.address}</textarea>
				<span></span>
			</div>
			<div id="">
				<label>收货人：</label>
				<input type="text" name="consignee" value="${addressVo.addresseeName}"/>
				<span></span>
			</div>
			<div id="">
				<label>手机号：</label>
				<input type="tel" name="phone" value="${addressVo.telephone}"/>
				<span></span>
			</div>
			<input type="hidden" value="${addressVo.tableKey}" name="tableKey"/>
			<input type="submit" value="确认地址" class="affirm"></input>
			<input type="button" value="取消" class="countermand"></input>
		</form>
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/pdata.js"></script>
<script type="text/javascript" src="frame/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="frame/static/js/messages_zh.js"></script>
<script type="text/javascript" src="frame/static/js/additional-methods.js"></script>
<script type="text/javascript" src="frame/static/js/jquery.validate.method.extend.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script type="text/javascript">
$(function () {
	var html = "<option value=''>== 请选择 ==</option>"; $("#input_city").append(html); $("#input_area").append(html);
	$.each(pdata,function(idx,item){
		if (parseInt(item.level) == 0) {
			html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
		}
	});
	$("#input_province").append(html);

	$("#input_province").change(function(){
		if ($(this).val() == "") return;
		$("#input_city option").remove(); $("#input_area option").remove();
		var code = $(this).find("option:selected").attr("exid"); code = code.substring(0,2);
		var html = "<option value=''>== 请选择 ==</option>"; $("#input_area").append(html);
		$.each(pdata,function(idx,item){
			if (parseInt(item.level) == 1 && code == item.code.substring(0,2)) {
   				html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
   			}
		});
		$("#input_city").append(html);		
	});

	$("#input_city").change(function(){
		if ($(this).val() == "") return;
		$("#input_area option").remove();
		var code = $(this).find("option:selected").attr("exid"); code = code.substring(0,4);
		var html = "<option value=''>== 请选择 ==</option>";
		$.each(pdata,function(idx,item){
			if (parseInt(item.level) == 2 && code == item.code.substring(0,4)) {
   				html += "<option value='" + item.names + "' exid='" + item.code + "'>" + item.names + "</option>";
   			}
		});
		$("#input_area").append(html);		
	});
	//绑定
	$("#input_province").val("${addressVo.province}");$("#input_province").change();
	$("#input_city").val("${addressVo.city}");$("#input_city").change();
	$("#input_area").val("${addressVo.area}");
	
	//验证
	$("#site_add").validate({
		rules:{
			youbian:{
				isDigits:true,
				minlength:6,
			},dizhi:{
				required:true,
			},consignee:{
				required:true,
			},phone:{
				required:true,
				isMobile:true,
			}
		},//rules-end
		messages:{
			youbian:{
				isDigits:"邮政编码只能为6位数字",
				minlength:"邮政编码只能为6位数字",
			},dizhi:{
				required:"请输入详细地址",
			},consignee:{
				required:"请输入收货人姓名",
			},phone:{
				required:"请输入收货人手机号",
				isMobile:"请输入正确的电话号码"
			}
		},
		//错误信息位置设置方法  
	 	 errorPlacement: function (error, element) {
	 		error.appendTo(element.siblings("span"));
         },
         submitHandler: function(form){
  			var province=$("[name='input_province']").val();
  			var city=$("[name='input_city']").val();
  			var area=$("[name='input_area']").val();
  			var postCode=$("[name='youbian']").val();
  			var address=$("[name='dizhi']").val();
  			var addresseeName=$("[name='consignee']").val();
  			var telephone=$("[name='phone']").val();
  			var tableKey=$("[name='tableKey']").val();
  			$.ajax({
  				url:"user/updateAddress.do",
  				data:{"tableKey":tableKey,"province":province,"city":city,"area":area,"postCode":postCode,"address":address,"addresseeName":addresseeName,"telephone":telephone},
  				type:"post",
              	success : function(data){         		
   					if(!data.success){
   						layer.open({
  							 title: '错误信息'
  							 ,content:data.msg
  						}); 
   					}else{
   						$("#site_add").parent().parent().load("user/toSite.do");
   					}
              	}
  			});
  		}
	});
	
	//取消按钮
	$(".countermand").click(function(){
		$("#site_add").parent().parent().load("user/toSite.do");
	})
});
</script>