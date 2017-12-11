package com.hansy.portal.common.execption;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 9097049778164951786L;
	private String msg;
	private String code;
	
	public ServiceException(){
		
	}
	
	public ServiceException(String code, String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public ServiceException(String msg){
		super(msg);
		this.msg = msg;
		
	}
	
	public ServiceException(Throwable throwable){
		super(throwable);
	}
		
	public ServiceException(String mag, Throwable throwable){
		super(mag, throwable);
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
