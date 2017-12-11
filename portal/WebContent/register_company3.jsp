<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
	String toUrl =  (String)request.getSession().getAttribute("tourl"); 
	if(toUrl == null || toUrl == ""){
		toUrl = "index.jsp";
	}
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="stylesheet" type="text/css" href="frame/static/css/bootstrap.css"/>	
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/register.css" />
<link rel="stylesheet" type="text/css" href="frame/static/uploadify/uploadify.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/bootstrap-datetimepicker.css"/>	
<style type="text/css">
form input{padding-left: 5px;}
</style>
<title>PORTAL企业注册</title>
<style text="text/css">
	#uploadImg1,#uploadImg2{margin: 5px 0 10px}
	.uploadify-button-text{
		padding-left:90px;
	}
	#previewImg1-button{
		background-color:#03A1A4;
	}
	#previewImg2-button{
		background-color:#03A1A4;
	}
</style>
</head>
<body>
	
	<!--顶部-->
	<div class="head">
		<div class="content">
			<a href="index.jsp"><img alt="" src="frame/static/img/logo.png"></a>
			<img alt="" src="frame/static/img/help.png">
		</div>
	</div>
	<!--内容-->
	<div class="centre">
		<div class="content">
			<div class="tab">
				<h3>企业注册</h3>
				<ul>
					<li class="selected">
						<em>1</em>
						<span>设置用户名</span>
					</li>
					<li class="selected">
						<em>2</em>
						<span>填写帐号信息</span>
					</li>
					<li class="selected">
						<em>3</em>
						<span>填写企业信息</span>
					</li>
				</ul>
			</div>
			<form method="post" id="company3" onsubmit="return false;" enctype="multipart/form-data">
				
				<div class="line">
					<label class="label">社会统一编码:</label>
					<input type="text" name="regNo" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">注册登记类型:</label>
					<select name="regType">
						<option value="00050001">国有</option>
						<option value="00050002">集体</option>
						<option value="00050003">股份制</option>
						<option value="00050004">私营</option>
						<option value="00050005">中外合资</option>
						<option value="00050006">外资</option>
					</select>
					<!-- <input type="text" name="regType"/> -->
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">证件开始日期:</label>
					<input type="text" name="transTimeBg" id="transTimeBg"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">证件到期日期:</label>
					<input type="text" name="transTimeEnd" id="transTimeEnd"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">公司规模：</label>
					<select name="scale">
						<option value="00060004">微型</option>
						<option value="00060004">小型</option>
						<option value="00060002">中型</option>
						<option value="00060002">大型</option>
					</select>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">注册地址：</label>
					<input type="text" name="regAddress"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">企业经营地址：</label>
					<input type="text" name="bizAddress"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
			   <div class="line preview">
					<label class="label">营业执照：</label>
					<input type="file"  name="fileData" id="previewImg1"/>
					<span class=""></span><br>
					<img id="uploadImg1" src="frame/static/img/upload.png" style="width:234px;height:200px">
				</div>
				<div class="line">
					<label class="label">法人代表：</label>
					<input type="text" name="legalPerson"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">法人代表证件类型：</label>
					<select name="legalCertType">
						<option value="00090001">身份证</option>
						<option value="00090002">护照</option>
						<option value="00090003">军人证</option>
						<option value="00090004">港澳通行证</option>
						<option value="00090005">经营许可证</option>
					</select>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">法人代表证件号码：</label>
					<input type="text" name="legalCertNo"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">基本开户行：</label>
					<input type="text" name="baseAcctOrg"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">基本账户号：</label>
					<input type="text" name="baseAcctNo"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">基本账户名：</label>
					<input type="text" name="baseAcctName"/>
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line preview">
					<label class="label">身份证照：</label>
					<input type="file"  name="fileData" id="previewImg2"/>
					<span class=""></span><br>
					<img id="uploadImg2" src="frame/static/img/upload.png" style="width:234px;height:200px">
				</div>
				<div class="line" style="margin:auto;">
					<label class="label"></label>
					<input type="submit" class="fillet" value="立即注册"/>
				</div>
			</form>
		</div>
	</div>
	<!--底部-->
	<div class="base">
		 <jsp:include page="base.jsp" flush="true"/>
	</div>
	
</body>
</html>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script type="text/javascript" src="frame/static/js/messages_zh.js"></script>
<script type="text/javascript" src="frame/static/js/additional-methods.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript" src="frame/static/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="frame/static/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="frame/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
 <script type="text/javascript">
 var maxFileSize = 10 * 1024 * 1024;  
 /** 
  * @title 上传图片 
  * @author zhoudong 
  */  
 $(function(){    
     $("#previewImg1").uploadify({    
         'debug'     : false, //开启调试    
         'auto'      :true,  //是否自动上传    
         'buttonText':'选择图片',  //按钮上的文字  
         'swf'       : "frame/static/uploadify/uploadify.swf",//flash      
         'queueID'   :'uploadfileQueue',  //文件选择后的容器ID    
         'uploader'  :'sys/uploadImgOrg.do',  //后台action  
         'width'     :'234',  //按钮宽度  
         'height'    :'24',  //按钮高度  
         'multi'     :false,  //是否支持多文件上传，默认就是true  
         'fileObjName':'fileData',//后台接收的参数，如：使用struts2上传时，后台有get,set方法的接收参数  
         'fileTypeDesc':'支持的格式：',//  可选择文件类型说明  
         'fileTypeExts':'*.jpg;*.jpge;*.png',  //允许上传文件的类型  
         'fileSizeLimit': maxFileSize,  //文件上传的最大大小  
/*          'removeTimeout':1,     */
         'cancelImg'    : 'frame/static/uploadify/uploadify-cancel.png',
         //返回一个错误，选择文件的时候触发    
         'onSelectError':function(file, errorCode, errorMsg){    
             switch(errorCode) {       
                 case -110:    
                     alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");    
                     break;    
                 case -120:    
                     alert("文件 ["+file.name+"] 大小异常！");    
                     break;    
                 case -130:    
                     alert("文件 ["+file.name+"] 类型不正确！");    
                     break;    
             }    
         },    
         //检测FLASH失败调用    
         'onFallback':function(){    
             alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");    
         },    
         'onSelect': function(file){  
             $("#alertDiv").text("正在上传...");  
             $("#alertDiv").show();  
         },  
         //上传到服务器，服务器返回相应信息到data里    
         'onUploadSuccess':function(file, data, response){ 
        	 var json = eval("(" + data + ")");
             $("#uploadImg1").attr("src",'data:image/png;base64,' +json.obj);
             //如需上传后生成预览，可在此操作  
         },  
/*          'onQueueComplete': function(queueData){ //队列里所有的文件处理完成后调用  
             alert(queueData.uploadsSuccessful);  
         }  */ 
     });    
     
     $("#previewImg2").uploadify({    
         'debug'     : false, //开启调试    
         'auto'      :true,  //是否自动上传    
         'buttonText':'选择图片',  //按钮上的文字  
         'swf'       : "frame/static/uploadify/uploadify.swf",//flash      
         'queueID'   :'uploadfileQueue',  //文件选择后的容器ID    
         'uploader'  :'sys/uploadImgCert.do',  //后台action  
         'width'     :'234',  //按钮宽度  
         'height'    :'24',  //按钮高度  
         'multi'     :false,  //是否支持多文件上传，默认就是true  
         'fileObjName':'fileData',//后台接收的参数，如：使用struts2上传时，后台有get,set方法的接收参数  
         'fileTypeDesc':'支持的格式：',//  可选择文件类型说明  
         'fileTypeExts':'*.jpg;*.jpge;*.gif;*.png',  //允许上传文件的类型  
         'fileSizeLimit': maxFileSize,  //文件上传的最大大小  
/*          'removeTimeout':1,     */
         'cancelImg'    : 'frame/static/uploadify/uploadify-cancel.png',
         //返回一个错误，选择文件的时候触发    
         'onSelectError':function(file, errorCode, errorMsg){    
             switch(errorCode) {       
                 case -110:    
                     alert("文件 ["+file.name+"] 大小超出系统限制的"+$('#file_upload').uploadify('settings','fileSizeLimit')+"大小！");    
                     break;    
                 case -120:    
                     alert("文件 ["+file.name+"] 大小异常！");    
                     break;    
                 case -130:    
                     alert("文件 ["+file.name+"] 类型不正确！");    
                     break;    
             }    
         },    
         //检测FLASH失败调用    
         'onFallback':function(){    
             alert("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试。");    
         },    
         'onSelect': function(file){  
             $("#alertDiv").text("正在上传...");  
             $("#alertDiv").show();  
         },  
         //上传到服务器，服务器返回相应信息到data里    
         'onUploadSuccess':function(file, data, response){ 
        	 var json = eval("(" + data + ")");
             $("#uploadImg2").attr("src",'data:image/png;base64,' +json.obj);
             //如需上传后生成预览，可在此操作  
         },  
/*          'onQueueComplete': function(queueData){ //队列里所有的文件处理完成后调用  
             alert(queueData.uploadsSuccessful);  
         }  */ 
     });  
 }); 
</script>
<script type="text/javascript">
$(function(){	
	//开始结束时间控件
	/* $('#transTimeBg').datetimepicker({
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
		format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 	
		language: 'zh-CN', //汉化 
		pickerPosition: "bottom-left",
		autoclose:true //选择日期后自动关闭
	}).on("changeDate",function(ev){
		var startTime=$("#transTimeBg").val();
		$('#transTimeEnd').datetimepicker('setStartDate',startTime,'setMinView',"month");
	}); */
	$("#transTimeBg").datetimepicker({
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
		format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 	
		language: 'zh-CN', //汉化 
		pickerPosition: "bottom-left",
		autoclose:true //选择日期后自动关闭
	}).on("click",function(){
	    $("#transTimeBg").datetimepicker("setEndDate",$("#transTimeEnd").val());
	});
	$("#transTimeEnd").datetimepicker({
		minView: "month", //选择日期后，不会再跳转去选择时分秒 
		format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式 	
		language: 'zh-CN', //汉化 
		pickerPosition: "bottom-left",
		autoclose:true //选择日期后自动关闭
	}).on("click",function(){
	    $("#transTimeEnd").datetimepicker("setStartDate",$("#transTimeBg").val());
	});
	
	$("#company3").validate({ 
		rules:{
			regNo:{
				required:true,
			},
			regType:{
				required:true,
			},
			transTimeBg:{
				required:true,
			},
			transTimeEnd:{
				required:true,
			},
			scale:{
				required:true,
			},
			regAddress:{
				required:true,
			},
			bizAddress:{
				required:true,
			},
			legalPerson:{
				required:true,
			},
			legalCertType:{
				required:true,
			},
			legalCertType:{
				required:true,
			},
			legalCertNo:{
				required:true,
			},
			baseAcctOrg:{
				required:true,
			},
			baseAcctNo:{
				required:true,
			},
			baseAcctName:{
				required:true,
			},
		},//rules-end
		messages:{
			regNo:{
				required: "请输入注册登记编号 ",
			},
			regType:{
				required: "请输入注册登记类型 ",
			},
			transTimeBg:{
				required: "请选择证件开始日期 ",
			},
			transTimeEnd:{
				required: "请选择证件到期日期",
			},
			scale:{
				required: "请输入公司规模 ",
			},
			regAddress:{
				required: "请输入注册地址 ",
			},
			bizAddress:{
				required: "请输入企业经营地址 ",
			},
			legalPerson:{
				required: "请输入法人代表 ",
			},
			legalCertType:{
				required: "请输入法人代表证件类型 ",
			},
			legalCertType:{
				required: "请输入 法人代表证件号码",
			},
			legalCertNo:{
				required: "请输入基本开户行 ",
			},
			baseAcctOrg:{
				required: "请输入基本账户号 ",
			},
			baseAcctNo:{
				required: "请输入基本账户名 ",
			},
			baseAcctName:{
				required:"请输入 基本账户名",
			},
		},
		//错误信息位置设置方法  
	 	 errorPlacement: function (error, element) {
	 		error.appendTo(element.siblings("span"));
         },
         submitHandler: function(form){
   			var regNo=$("[name='regNo']").val();
   			var regType=$("[name='regType']").val();
   			var certBeginDt=$("[name='transTimeBg']").val();
   			var certEndDt=$("[name='transTimeEnd']").val();
   			var scale=$("[name='scale']").val();
   			var regAddress=$("[name='regAddress']").val();
   			var bizAddress=$("[name='bizAddress']").val();
   			var legalPerson=$("[name='legalPerson']").val();
   			var legalCertType=$("[name='legalCertType']").val();
   			var legalCertNo=$("[name='legalCertNo']").val();
   			var baseAcctOrg=$("[name='baseAcctOrg']").val();
   			var baseAcctNo=$("[name='baseAcctNo']").val();
   			var baseAcctName=$("[name='baseAcctName']").val();
   			$.ajax({
   				url:"sys/comRegisterThird.do",
   				data:{"regNo":regNo,"regType":regType,"certBeginDtP":certBeginDt,"certEndDtP":certEndDt,"scale":scale,"regAddress":regAddress,"bizAddress":bizAddress,"legalPerson":legalPerson,"legalCertType":legalCertType,"legalCertNo":legalCertNo,"baseAcctOrg":baseAcctOrg,"baseAcctNo":baseAcctNo,"baseAcctName":baseAcctName},
   				type:"post",
   				beforeSend: function(){
					$(".fillet").attr("disabled","disabled");
				},
               	success : function(data){
   					if(!data.success){
    					layer.open({
   							title: '错误信息'
   							,content:data.msg
   						}); 
   					}else{
    					layer.open({
   							title: '消息提示'
   							,content:data.msg
   							,btn: '确定'
   		  					,yes: function(index, layero){
   		  						location.href='<%=toUrl %>';
   		  					}
   						}); 
   					}
               	}
   			});
   		}
	});
});
//日期计算
function  addmulMonth(dtstr,n){   // n个月后 
	   var s=dtstr.split("-");
	   var yy=parseInt(s[0]); var mm=parseInt(s[1])-1;var dd=parseInt(s[2]);
	   var dt=new Date(yy,mm,dd);dt.setMonth(dt.getMonth()+n);
	   if( (dt.getYear()*12+dt.getMonth()) > (yy*12+mm + n) ){dt=new Date(dt.getYear(),dt.getMonth(),0);}
	   return dt;
} 
//日期格式化
Date.prototype.Format = function (fmt) {
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