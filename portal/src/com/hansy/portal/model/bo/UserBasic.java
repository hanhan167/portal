
package com.hansy.portal.model.bo;

import java.io.Serializable;


public class UserBasic implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -3338334120942117587L;
	private String userName;		//真实姓名
	private String userAlias;		//昵称
	private String userPhone;		//电话号码
	private String certNo;			//省份证号码
	private String userEmail;		//邮箱
	private String superUserNo;		//推荐者
	private String regNo;			//企业注册登记号
	private String userType;		//用户类型
	private String userNo;			//用户编号
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserAlias() {
		return userAlias;
	}
	
	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}
	
	public String getUserPhone() {
		return userPhone;
	}
	
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public String getCertNo() {
		return certNo;
	}
	
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getSuperUserNo() {
		return superUserNo;
	}
	
	public void setSuperUserNo(String superUserNo) {
		this.superUserNo = superUserNo;
	}
	
	public String getRegNo() {
		return regNo;
	}
	
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserNo() {
		return userNo;
	}
	
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	@Override
	public String toString() {
		return "UserBasic [userName=" + userName + ", userAlias=" + userAlias + ", userPhone=" + userPhone + ", certNo=" + certNo + ", userEmail=" + userEmail + ", superUserNo="
				+ superUserNo + ", regNo=" + regNo + ", userType=" + userType + ", userNo=" + userNo + "]";
	}
	
	
}

