
package com.hansy.portal.model.vo;

import java.util.Date;

public class TBusCompleteBillVo {
	private String billNo;				//发票编号
	private String billNatrue;			//发票性质(纸质发票，电子发票)
	private String billStatus;			//发票状态(1:未寄送,2：已寄送)
	private String billTitle;			//发票开头(个人公司)
	private String companyName;			//公司名称
	private Double billMoney;			//发票总金额
	private Date billDate;				//开票时间
	private String billType;			//发票类型
	private String billReceiveName;		//收信人
	private String billReceiveAddress;	//收取地址
	private String billReceiveMail;		//邮编
	private String billReceivePhone;	//联系电话
	private String logisticsName;		//物流公司
	private String expressNumber;		//快递编号
	private String custNo;				//客户编号
	private String supplyNo;			//供方编号
	private Date insertDate;			//新增时间
	private String applyNo;			//申请编号
	private String billProvince;//省
	private String billCity;//市
	private String billArea;//区
	
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getBillNatrue() {
		return billNatrue;
	}
	public void setBillNatrue(String billNatrue) {
		this.billNatrue = billNatrue;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public String getBillTitle() {
		return billTitle;
	}
	public void setBillTitle(String billTitle) {
		this.billTitle = billTitle;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Double getBillMoney() {
		return billMoney;
	}
	public void setBillMoney(Double billMoney) {
		this.billMoney = billMoney;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getBillReceiveName() {
		return billReceiveName;
	}
	public void setBillReceiveName(String billReceiveName) {
		this.billReceiveName = billReceiveName;
	}
	public String getBillReceiveAddress() {
		return billReceiveAddress;
	}
	public void setBillReceiveAddress(String billReceiveAddress) {
		this.billReceiveAddress = billReceiveAddress;
	}
	public String getBillReceiveMail() {
		return billReceiveMail;
	}
	public void setBillReceiveMail(String billReceiveMail) {
		this.billReceiveMail = billReceiveMail;
	}
	public String getBillReceivePhone() {
		return billReceivePhone;
	}
	public void setBillReceivePhone(String billReceivePhone) {
		this.billReceivePhone = billReceivePhone;
	}
	public String getLogisticsName() {
		return logisticsName;
	}
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
	public String getExpressNumber() {
		return expressNumber;
	}
	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}
	public String getCustNo() {
		return custNo;
	}
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	public String getSupplyNo() {
		return supplyNo;
	}
	public void setSupplyNo(String supplyNo) {
		this.supplyNo = supplyNo;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	public String getBillProvince() {
		return billProvince;
	}
	public void setBillProvince(String billProvince) {
		this.billProvince = billProvince;
	}
	public String getBillCity() {
		return billCity;
	}
	public void setBillCity(String billCity) {
		this.billCity = billCity;
	}
	public String getBillArea() {
		return billArea;
	}
	public void setBillArea(String billArea) {
		this.billArea = billArea;
	}
	@Override
	public String toString() {
		return "TBusCompleteBillVo [billNo=" + billNo + ", billNatrue="
				+ billNatrue + ", billStatus=" + billStatus + ", billTitle="
				+ billTitle + ", companyName=" + companyName + ", billMoney="
				+ billMoney + ", billDate=" + billDate + ", billType="
				+ billType + ", billReceiveName=" + billReceiveName
				+ ", billReceiveAddress=" + billReceiveAddress
				+ ", billReceiveMail=" + billReceiveMail
				+ ", billReceivePhone=" + billReceivePhone + ", logisticsName="
				+ logisticsName + ", expressNumber=" + expressNumber
				+ ", custNo=" + custNo + ", supplyNo=" + supplyNo
				+ ", insertDate=" + insertDate + ", applyNo=" + applyNo
				+ ", billProvince=" + billProvince + ", billCity=" + billCity
				+ ", billArea=" + billArea + "]";
	}
	
	
}

