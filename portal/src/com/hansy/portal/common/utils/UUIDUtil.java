package com.hansy.portal.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 
 * @author cj
 * @time 2016-11-30
 */
public class UUIDUtil {
	/**
	 * 获取String类型UUID
	 * @return
	 */
	public static String getStrUUID(){
		return UUID.randomUUID().toString();
	}
	/**
	 * 获取UUID类型UUID
	 * @return
	 */
	public static UUID getUUID(){
		return UUID.randomUUID();
	}
	/**
	 * 获取去掉"-"的UUID
	 * @return
	 */
	public static String getParseUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 随机生成strlength长度的数字字符串
	 * @description: TODO
	 * @creator: cj
	 * @createDate: 2017年2月23日 
	 * @modifier:
	 * @modifiedDate:
	 * @param strLength
	 * @return
	 */
	 public static String getFixLength(int strLength){
	        StringBuffer sb = new StringBuffer();
	        String str = "0123456789";
	        Random r = new Random();
	        for(int i=0;i<strLength;i++){
	            int num = r.nextInt(str.length());
	            sb.append(str.charAt(num));
	        }
	        return sb.toString();
	 } 
	
	 /**
	  * 根据时间获取时间字符串用作userNo
	  * @description: TODO
	  * @creator: cj
	  * @createDate: 2017年2月27日 
	  * @modifier:
	  * @modifiedDate:
	  * @param date
	  * @return
	  */
	 public static String getDateString(Date date){
		SimpleDateFormat sFormat=new SimpleDateFormat("YYYYMMdHms");
		return sFormat.format(date).toString();
	 }
	 
	public static void main(String[] args) {
		System.out.println(getFixLength(11));
	}
}
