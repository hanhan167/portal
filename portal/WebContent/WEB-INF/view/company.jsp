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
<title>PORTAL企业用户注册</title>
<link rel="stylesheet" type="text/css" href="frame/static/css/commonality.css" />
<link rel="stylesheet" type="text/css" href="frame/static/css/register.css" />
<link rel="stylesheet" type="text/css" href="frame/static/layer/skin/default/layer.css" />
</head>
<body>
	<!--底部-->
	<div class="base">
		 <jsp:include page="../../head.jsp" flush="true"/>
	</div>
	<!--中间-->
	<div class="centre">
		<div class="content">
			<div class="tab">
				<ul>
					<li class="personage">
						<a href="user/toRegisterPerson.do">个人用户</a>
					</li>
					<li class="company selected">
						<a href="user/toRegisterCompany.do">企业用户</a>
					</li>
				</ul>
			</div>
			<div class="superior">
				<h3>成为企业会员……</h3>
				<p>成为企业用户的特权……</p>
			</div>
			<form method="post">
				<h3>账户信息</h3>
				<div class="line">
					<label class="label">昵称:</label>
					<input type="text" class="text" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">密码:</label>
					<input type="password" class="text" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">确认密码:</label>
					<input type="password" class="text" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				
				<h3>公司信息</h3>
				<div class="line">
					<label class="label">注册登记编号:</label>
					<input type="text" class="text" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">组织机构代码:</label>
					<input type="text" class="text" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">税务登记证号:</label>
					<input type="text" class="text" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">注册登记类型:</label>
					<input type="text" class="text" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<<div class="line">
					<label class="label">证件开始日期:</label>
					<input type="date" class="text" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">证件到期日期:</label>
					<input type="date" class="text" />
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">公司规模：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line preview">
					<label class="label">营业执照：</label>
					<div id="preview" onClick="$('#previewImg').click();">
						<img id="LAY_demo_upload" alt="" src="frame/static/img/upload.png">
						<span>请上传营业执照</span>
					</div>
					<input type="file" onchange="previewImage(this)" style="display:none;" name="img" id="previewImg">
					<b class="">*</b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">法人代表：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">法人代表证件类型：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">法人代表证件号码：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">基本开户行：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">基本账户号：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">基本账户名：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">年审到期日：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">注册资本：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">注册地址：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">注册币种：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				
				<h3>联系人信息</h3>
				<div class="line">
					<label class="label">联系人：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">联系人手机号码：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">联系人邮箱：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label">推荐者：</label>
					<input type="text" class="text" />
					<b class=""></b>
					<span class=""></span>
				</div>
				<div class="line">
					<label class="label"></label>
					<input type="checkbox" id="check" name="agree" />
					<label>我已阅读并同意<a href="#">《xxx用户协议》</a></label>
				</div>
				<div class="line">
					<label class="label"></label>
					<button type="submit" class="fillet" disabled="disabled">立即注册</button>
					<button type="button" id="zz">fsdf</button>
				</div>
			</form>
		</div>
	</div>
	<!--底部-->
	<div class="base">
		 <jsp:include page="../../base.jsp" flush="true"/>
	</div>
	
</body>
<script type="text/javascript" src="frame/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="frame/static/js/bdc1c24f72be4df0ab99fd49ca0b0f23.js"></script>
<script type="text/javascript" src="frame/static/js/company.js"></script>
<script type="text/javascript" src="frame/static/layer/layer.js"></script>
<script type="text/javascript" src="frame/static/js/layui.js"></script>
<script type="text/javascript">
$(function(){
	layui.use('upload', function(){
		  layui.upload({
		    url: 'user/uploadImg.do'
		    ,elem:'#previewImg'
		    ,method: 'post' //上传接口的http类型
		    ,ext: 'jpg|png|gif'
		    ,unwrap: true
		    ,success: function(res){
		    	/* LAY_demo_upload.src = res.msg; */
		    }
		  });
	});
	
	//获取上传图片的名字
	$("#zz").click(function(){
		alert($("#LAY_demo_upload").attr("src"));
		var img=$("#LAY_demo_upload").attr("src");
		var lastIndex=img.lastIndexOf('/');
		alert(lastIndex);
		var result=img.substring(lastIndex+1,img.length+1);
		alert(result);	
	})
})
</script>
</html>