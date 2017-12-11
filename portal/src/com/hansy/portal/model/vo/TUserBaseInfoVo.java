package com.hansy.portal.model.vo;

import java.util.Date;

/**
 * 登录用户信息表 VO对象
 * @author wei
 *
 */
public class TUserBaseInfoVo {

	private String userNo;//			varchar2(32)	n			�û����
	private String userLoginName;//	varchar2(32)	y			
	private String userName;//		varchar2(100)	y			�û����
	private String userType;//		varchar2(8)	y			�û�����
	private String userRole;//		varchar2(8)	y			
	private String userPwd;//		varchar2(32)	y			�û�����
	private String custNo;//			varchar2(32)	y			
	private String custName;//		varchar2(100)	y			
	private String userAlias;//		varchar2(100)	y			�ǳ�
	private Long userPhone;//		number	y			�ֻ����
	private String userEmail;//		varchar2(100)	y			����
	private String superUserNo;//		varchar2(32)	y		推荐人（这个用户作为推荐人的粉丝）
	private Date insertDate;//		date	y			ע��ʱ��
	private Date updateDate;//		date	y			����ʱ��
	private String registerStatus;//	varchar2(8) 用户注册状态
	private String userStatus;//启用状态
	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserLoginName() {
		return userLoginName;
	}
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getUserAlias() {
		return userAlias;
	}
	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
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
	public Long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(Long userPhone) {
		this.userPhone = userPhone;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getRegisterStatus() {
		return registerStatus;
	}
	
	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus;
	}
	@Override
	public String toString() {
		return "TUserBaseInfoVo [userNo=" + userNo + ", userLoginName=" + userLoginName + ", userName=" + userName + ", userType=" + userType + ", userRole=" + userRole
				+ ", userPwd=" + userPwd + ", custNo=" + custNo + ", custName=" + custName + ", userAlias=" + userAlias + ", userPhone=" + userPhone + ", userEmail=" + userEmail
				+ ", superUserNo=" + superUserNo + ", insertDate=" + insertDate + ", updateDate=" + updateDate + ", registerStatus=" + registerStatus + "]";
	}
}
