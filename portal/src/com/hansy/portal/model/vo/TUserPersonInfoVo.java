
package com.hansy.portal.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 个人用户信息vo对象
 * TODO javadoc for com.hansy.portal.model.vo.TUserPersonInfoVo
 * @Copyright: 2017成都环赛信息技术有限公司 
 * @author: cj
 * @since: 2017年2月23日
 */
public class TUserPersonInfoVo implements Serializable {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -6454171148990268828L;
	
	private String custNo;			//VARCHAR2(32 BYTE)		用户编号
	private String custName;		//VARCHAR2(100 BYTE)		用户名称
	private String userType;		//VARCHAR2(8 BYTE)		用户类型
	private String userSex;			//VARCHAR2(8 BYTE)		性别
	private String userWork;		//VARCHAR2(40 BYTE)		职业
	private BigDecimal monthIncome;//NUMBER(15,2)			月收入
	private String education;		//VARCHAR2(8 BYTE)		学历
	private String maritalStatus;	//VARCHAR2(8 BYTE)		婚姻状态
	private String userPhone;		//VARCHAR2(11 BYTE)		手机号码
	private String userEmail;		//VARCHAR2(100 BYTE)		邮箱地址
	private String certType;		//VARCHAR2(8 BYTE)		证件类型
	private String certNo;			//VARCHAR2(40 BYTE)		证件号码
	private Date insertDate;		//DATE					新增时间
	private Date updateDate;		//DATE					更新时间
	
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
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getUserSex() {
		return userSex;
	}
	
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	
	public String getUserWork() {
		return userWork;
	}
	
	public void setUserWork(String userWork) {
		this.userWork = userWork;
	}
	
	public BigDecimal getMonthIncome() {
		return monthIncome;
	}
	
	public void setMonthIncome(BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}
	
	public String getEducation() {
		return education;
	}
	
	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public String getUserPhone() {
		return userPhone;
	}
	
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getCertType() {
		return certType;
	}
	
	public void setCertType(String certType) {
		this.certType = certType;
	}
	
	public String getCertNo() {
		return certNo;
	}
	
	public void setCertNo(String certNo) {
		this.certNo = certNo;
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

	@Override
	public String toString() {
		return "TUserPersonInfoVo [custNo=" + custNo + ", custName=" + custName + ", userType=" + userType + ", userSex=" + userSex + ", userWork=" + userWork + ", monthIncome="
				+ monthIncome + ", education=" + education + ", maritalStatus=" + maritalStatus + ", userPhone=" + userPhone + ", userEmail=" + userEmail + ", certType=" + certType
				+ ", certNo=" + certNo + ", insertDate=" + insertDate + ", updateDate=" + updateDate + "]";
	}
}

