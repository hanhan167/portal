package com.hansy.portal.model.vo;

import java.util.Date;

/**
 * 用户申请表VO 对象
 * @author wei
 *
 */
public class TUserApplyVo {
	private String applyNo;//varchar2(32)	N			申请编号
	private String custNo;//varchar2(32)	N			用户编号
	private String custName;//varchaR2(100)	Y			用户名称
	private String userType;//varchaR2(8)	Y			用户类型
	private String certNo;//varchar2(100)	Y			客户证件号
	private String applyCustNo;//vaRCHAR2(32)	Y			申请人
	private String applyCustName;//VARCHAR2(200)	Y			申请人名称
	private String aprovResult;//varCHAR2(8)	Y			审核状态
	private String aprovRemark;//varCHAR2(400)	Y			审核备注
	private Date aprovDate;//date	y			审批时间
	private String aprovStaffNo;//vARCHAR2(32)	Y			审核员
	private String aprovStaffName;//VARCHAR2(200)	Y			审核员名称
	private String aprovDecision;//	VARCHAR2(400)	Y			审核描述
	private String aprovStatus;//varCHAR2(8)	Y			审核通过状态
	private Date applyDate;//date	y			申请日期
	private Date regDate;//date	y			注册时间
	
	public String getApplyNo() {
		return applyNo;
	}
	
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
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
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public String getCertNo() {
		return certNo;
	}
	
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	
	public String getApplyCustNo() {
		return applyCustNo;
	}
	
	public void setApplyCustNo(String applyCustNo) {
		this.applyCustNo = applyCustNo;
	}
	
	public String getApplyCustName() {
		return applyCustName;
	}
	
	public void setApplyCustName(String applyCustName) {
		this.applyCustName = applyCustName;
	}
	
	public String getAprovResult() {
		return aprovResult;
	}
	
	public void setAprovResult(String aprovResult) {
		this.aprovResult = aprovResult;
	}
	
	public String getAprovRemark() {
		return aprovRemark;
	}
	
	public void setAprovRemark(String aprovRemark) {
		this.aprovRemark = aprovRemark;
	}
	
	public Date getAprovDate() {
		return aprovDate;
	}
	
	public void setAprovDate(Date aprovDate) {
		this.aprovDate = aprovDate;
	}
	
	public String getAprovStaffNo() {
		return aprovStaffNo;
	}
	
	public void setAprovStaffNo(String aprovStaffNo) {
		this.aprovStaffNo = aprovStaffNo;
	}
	
	public String getAprovStaffName() {
		return aprovStaffName;
	}
	
	public void setAprovStaffName(String aprovStaffName) {
		this.aprovStaffName = aprovStaffName;
	}
	
	public String getAprovDecision() {
		return aprovDecision;
	}
	
	public void setAprovDecision(String aprovDecision) {
		this.aprovDecision = aprovDecision;
	}
	
	public String getAprovStatus() {
		return aprovStatus;
	}
	
	public void setAprovStatus(String aprovStatus) {
		this.aprovStatus = aprovStatus;
	}
	
	public Date getApplyDate() {
		return applyDate;
	}
	
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	public Date getRegDate() {
		return regDate;
	}
	
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
