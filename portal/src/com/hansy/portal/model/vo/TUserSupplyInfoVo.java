
package com.hansy.portal.model.vo;
import java.math.BigDecimal;
import java.util.Date;

public class TUserSupplyInfoVo{
	private String custNo;//varchar2(32)	n			cust_no
	private String custName;//varchar2(100)	y			客户名称
	private String regNo;//varchar2(32)	y			注册登记编号
	private String orgNo;//varchar2(32)	y			组织机构代码
	private String faxNo;//varchar2(32)	y			税务登记证号
	private String regType;//varchar2(32)	y			注册登记类型
	private Date certBeginDt;//date	y			证件开始日期
	private Date certEndDt;//date	y			证件到期日期
	private String scale;//varchar2(8)	y			规模
	private String legalPerson;//varchar2(60)	y			法人代表
	private String legalCertType;//varchar2(8)	y			法人代表证件类型
	private String legalCertNo;//varchar2(32)	y			法人代表证件
	private String baseAcctOrg;//varchar2(60)	y			基本开户行
	private String baseAcctNo;//varchar2(32)	y			基本户账号
	private String baseAcctName;//varchar2(60)	y			基本户户名
	private Date annualInspDt;//date	y			年审到期日
	private Date changeDt;//date	y			变更日期
	private Double regCapital;//number(20,2)	y			注册资本
	private String regAddress;//varchar2(200)	y			注册地址
	private String regCurrency;//varchar2(8)	y			注册币种
	private Double paidInCapital;//number(20,2)	y			实收资本
	private String paidCurrency;//varchar2(8)	y			实收资本币种
	private String bizAddress;//varchar2(200)	y			企业经营地址
	private Integer branchCnt;//number	y			分支机构数量
	private Date insertDate;//date	y			新增日期
	private Date updateDate;//date	y			更新日期
	private String fileId;//date	y			文件ID
	private String staffId;//date	y			文件ID
	private String staffName;//date	y			文件ID
	
	private String libName;
	private String libTitle;
	private String libDirectory;
	private String libExtName;
	private String supplyName;				//供应商名称
	private String supplyRemark;			//供应商描述
	private String supplyMobile;			//供应商销售电话
	private String supplyServerMobile;		//供应商服务电话
	private String libId;
	private String libName1;
	private String libTitle1;
	private String libDirectory1;
	private String libExtName1;
	private String libId1;
	public String getLibId() {
		return libId;
	}
	public void setLibId(String libId) {
		this.libId = libId;
	}
	public String getLibId1() {
		return libId1;
	}
	public void setLibId1(String libId1) {
		this.libId1 = libId1;
	}
	public String getLibName1() {
		return libName1;
	}
	public void setLibName1(String libName1) {
		this.libName1 = libName1;
	}
	public String getLibTitle1() {
		return libTitle1;
	}
	public void setLibTitle1(String libTitle1) {
		this.libTitle1 = libTitle1;
	}
	public String getLibDirectory1() {
		return libDirectory1;
	}
	public void setLibDirectory1(String libDirectory1) {
		this.libDirectory1 = libDirectory1;
	}
	public String getLibExtName1() {
		return libExtName1;
	}
	public void setLibExtName1(String libExtName1) {
		this.libExtName1 = libExtName1;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public String getSupplyRemark() {
		return supplyRemark;
	}
	public void setSupplyRemark(String supplyRemark) {
		this.supplyRemark = supplyRemark;
	}
	public String getSupplyMobile() {
		return supplyMobile;
	}
	public void setSupplyMobile(String supplyMobile) {
		this.supplyMobile = supplyMobile;
	}
	public String getSupplyServerMobile() {
		return supplyServerMobile;
	}
	public void setSupplyServerMobile(String supplyServerMobile) {
		this.supplyServerMobile = supplyServerMobile;
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
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getOrgNo() {
		return orgNo;
	}
	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getRegType() {
		return regType;
	}
	public void setRegType(String regType) {
		this.regType = regType;
	}
	public Date getCertBeginDt() {
		return certBeginDt;
	}
	public void setCertBeginDt(Date certBeginDt) {
		this.certBeginDt = certBeginDt;
	}
	public Date getCertEndDt() {
		return certEndDt;
	}
	public void setCertEndDt(Date certEndDt) {
		this.certEndDt = certEndDt;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getLegalCertType() {
		return legalCertType;
	}
	public void setLegalCertType(String legalCertType) {
		this.legalCertType = legalCertType;
	}
	public String getLegalCertNo() {
		return legalCertNo;
	}
	public void setLegalCertNo(String legalCertNo) {
		this.legalCertNo = legalCertNo;
	}
	public String getBaseAcctOrg() {
		return baseAcctOrg;
	}
	public void setBaseAcctOrg(String baseAcctOrg) {
		this.baseAcctOrg = baseAcctOrg;
	}
	public String getBaseAcctNo() {
		return baseAcctNo;
	}
	public void setBaseAcctNo(String baseAcctNo) {
		this.baseAcctNo = baseAcctNo;
	}
	public String getBaseAcctName() {
		return baseAcctName;
	}
	public void setBaseAcctName(String baseAcctName) {
		this.baseAcctName = baseAcctName;
	}
	public Date getAnnualInspDt() {
		return annualInspDt;
	}
	public void setAnnualInspDt(Date annualInspDt) {
		this.annualInspDt = annualInspDt;
	}
	public Date getChangeDt() {
		return changeDt;
	}
	public void setChangeDt(Date changeDt) {
		this.changeDt = changeDt;
	}
	public Double getRegCapital() {
		return regCapital;
	}
	public void setRegCapital(Double regCapital) {
		this.regCapital = regCapital;
	}
	public String getRegAddress() {
		return regAddress;
	}
	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}
	public String getRegCurrency() {
		return regCurrency;
	}
	public void setRegCurrency(String regCurrency) {
		this.regCurrency = regCurrency;
	}
	public Double getPaidInCapital() {
		return paidInCapital;
	}
	public void setPaidInCapital(Double paidInCapital) {
		this.paidInCapital = paidInCapital;
	}
	public String getPaidCurrency() {
		return paidCurrency;
	}
	public void setPaidCurrency(String paidCurrency) {
		this.paidCurrency = paidCurrency;
	}
	public String getBizAddress() {
		return bizAddress;
	}
	public void setBizAddress(String bizAddress) {
		this.bizAddress = bizAddress;
	}
	public Integer getBranchCnt() {
		return branchCnt;
	}
	public void setBranchCnt(Integer branchCnt) {
		this.branchCnt = branchCnt;
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
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
	public String getLibTitle() {
		return libTitle;
	}
	public void setLibTitle(String libTitle) {
		this.libTitle = libTitle;
	}
	public String getLibDirectory() {
		return libDirectory;
	}
	public void setLibDirectory(String libDirectory) {
		this.libDirectory = libDirectory;
	}
	public String getLibExtName() {
		return libExtName;
	}
	public void setLibExtName(String libExtName) {
		this.libExtName = libExtName;
	}
}

