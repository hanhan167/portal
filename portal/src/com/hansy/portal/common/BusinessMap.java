
package com.hansy.portal.common;

import java.io.Serializable;

/**
 * 业务层返回值
 * javadoc for com.hansy.base.exception.BusinessMap
 * @Copyright: 2016成都环赛信息技术有限公司 
 * @author: roilat-D
 * @since: 2016年10月12日
 */
public class BusinessMap<T> implements Serializable {

	private static final long serialVersionUID = 1500570918148141440L;

	//处理状态
	private boolean isSucc;

	//处理消息
	private String msg;

	//加密过的报文体
	private T infoBody;

	public BusinessMap() {}

	public BusinessMap(boolean isSucc, String msg, T infoBody) {
		super();
		this.isSucc = isSucc;
		this.msg = msg;
		this.infoBody = infoBody;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getInfoBody() {
		return infoBody;
	}

	public void setInfoBody(T infoBody) {
		this.infoBody = infoBody;
	}
	
	public boolean getIsSucc() {
		return this.isSucc;
	}

	
	public void setIsSucc(boolean isSucc) {
		this.isSucc = isSucc;
	}
}
