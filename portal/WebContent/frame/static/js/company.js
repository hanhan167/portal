//图片上传预览    IE是用了滤镜。
function previewImage(file){
  var MAXWIDTH  = 90; 
  var MAXHEIGHT = 90;
  var div = document.getElementById('preview');
  if (file.files && file.files[0]){
      div.innerHTML ='<img id=imghead onclick=$("#previewImg").click()>';
      var img = document.getElementById('imghead');
      var reader = new FileReader();
      reader.onload = function(evt){img.src = evt.target.result;}
      reader.readAsDataURL(file.files[0]);
  }else //兼容IE
  {
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
    file.select();
    var src = document.selection.createRange().text;
    div.innerHTML = '<img id=imghead>';
    var img = document.getElementById('imghead');
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
  }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight ){
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        
        if( rateWidth > rateHeight ){
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else{
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}

$(function() { 
	$(function() { 
		centre_height();
		$(window).resize(function(){
			centre_height();
		});
	});
	//内容的最小高度适应
	function centre_height(){
		var win_height = $(window).height();
		$(".centre").css("min-height",win_height-150);
		$(".centre .content").css("min-height",win_height-270);
	}
	//同意用户协议后运行提交
	$("#check").change(function(){
		/*chekbox选中返回true，否则为false*/
		var check = $("#check").is(":checked");
		if(check){
			$("button.fillet").removeAttr("disabled")
		}else{
			$("button.fillet").attr("disabled","disabled")
		}
	});
  //头部 导航栏选中状态
  var urlstr = location.href;
  var urlstatus=false;
  $("#head_content>ul a").each(function () {
    if ((urlstr + '/').indexOf($(this).attr('href')) > -1&&$(this).attr('href')!='') {
      $(this).addClass('header-cur'); urlstatus = true;
    } else {
      $(this).removeClass('header-cur');
    }
  });
  if (!urlstatus) {$("#head_content>ul a").eq(0).addClass('header-cur'); }
});
var DateFormat = function(time,format){
	//format ="yy/MM/dd-hh:mm:ss"
	if(time != null && time != "" && time != undefined){
		var weekArr = ["日","一","二","三","四","五","六"];
		var mouthZh = ["一","二","三","四","五","六","七","八","九","十","十一","十二"];
		var newTime = new Date(time*1);
		var year = timeZero(newTime.getFullYear());
		var mouth = timeZero(newTime.getMonth() + 1);
		var mouths = newTime.getMonth();
		var day = timeZero(newTime.getDate());
		var week = newTime.getDay();
		var hour = timeZero(newTime.getHours());
		var min = timeZero(newTime.getMinutes());
		var sec = timeZero(newTime.getSeconds());

		if (format == undefined || format == null) {
			return year+"-"+mouth+"-"+day
		}else{
			format = format.replace("yy",year);
			format = format.replace("MM",mouth);
			format = format.replace("mZ",mouthZh[mouths]);
			format = format.replace("dd",day);
			format = format.replace("ww",weekArr[week]);
			format = format.replace("hh",hour);
			format = format.replace("mm",min);
			format = format.replace("ss",sec);

			return format
		};

		function timeZero(count){
			if(count < 10){
				return "0"+count;
			}else{
				return count
			}
		}
	}
	return "无"
};
/*截取字符串*/
var splitStr = function(str,num){
	if(num == undefined){
		num = 10;
	}
	var returnStr = str;
	if(str.length > num+1){
		return returnStr = str.substring(0,num+1)+"..";
	}else{
		return str
	}

}