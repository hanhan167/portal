
package com.hansy.portal.common.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.hansy.portal.common.BusinessMap;
import com.hansy.portal.common.constant.ApiCosntants;

/**
 * 调用短信验证码接口
 * TODO javadoc for com.hansy.portal.common.utils.SmsApiHelper
 * @Copyright: 2017成都环赛信息技术有限公司 
 * @author: cj
 * @since: 2017年2月28日
 */
public class SmsApiHelper {
	public static BusinessMap<Object> sendSms(String mobile){
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		//tpl_value是key_value形式，验证码为随机6位数字
		String yzm=UUIDUtil.getFixLength(6);
		String tpl_value=ApiCosntants.SMS_TPL_VALUE_KEY+yzm;
		HttpClient client;
		HttpGet get;
		String reqStr =ApiCosntants.SMS_URL+"?mobile="+mobile+"&tpl_id="+ApiCosntants.SMS_TPL_ID+"&tpl_value="+tpl_value+"&key="+ApiCosntants.SMS_KEY;
		client = HttpClientBuilder.create().build();
		get = new HttpGet(reqStr);
		get.setHeader("content-type","application/json;charset=UTF-8");
		get.setHeader("Accept","application/json");
		HttpResponse response;
		String tem=null;
		try {
			response = client.execute(get);
			//接口回调的信息
			tem = EntityUtils.toString(response.getEntity(),"UTF-8");
			JSONObject resData = JSONObject.parseObject(tem);
			//判断接口是否调用正确
			if (resData.getString("error_code").equals("0")) {
				bMap.setIsSucc(true);
				bMap.setInfoBody(yzm);
			}else {
				bMap.setIsSucc(false);
				bMap.setMsg("调用短信接口失败");
			}
		} catch (IOException e) {
			bMap.setIsSucc(false);
			bMap.setMsg("调用短信接口失败");
		}
		return bMap;
	}
	
	/**
	 * 用户找回密码的
	 * @param mobile
	 * @param pwd
	 * @return
	 */
	public static BusinessMap<Object> sendPwd(String mobile, String pwd){
		BusinessMap<Object> bMap=new BusinessMap<Object>();
		String tpl_value=ApiCosntants.SMS_TPL_VALUE_KEY+pwd;
		HttpClient client;
		HttpGet get;
		String reqStr =ApiCosntants.SMS_URL+"?mobile="+mobile+"&tpl_id="+ApiCosntants.SMS_TPL_ID+"&tpl_value="+tpl_value+"&key="+ApiCosntants.SMS_KEY;
		client = HttpClientBuilder.create().build();
		get = new HttpGet(reqStr);
		get.setHeader("content-type","application/json;charset=UTF-8");
		get.setHeader("Accept","application/json");
		HttpResponse response;
		String tem=null;
		try {
			response = client.execute(get);
			//接口回调的信息
			tem = EntityUtils.toString(response.getEntity(),"UTF-8");
			JSONObject resData = JSONObject.parseObject(tem);
			//判断接口是否调用正确
			if (resData.getString("error_code").equals("0")) {
				bMap.setIsSucc(true);
				bMap.setInfoBody(pwd);
			}else {
				bMap.setIsSucc(false);
				bMap.setMsg("调用短信接口失败");
			}
		} catch (IOException e) {
			bMap.setIsSucc(false);
			bMap.setMsg("调用短信接口失败");
		}
		return bMap;
	}
}

