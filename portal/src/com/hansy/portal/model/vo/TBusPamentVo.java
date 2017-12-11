
package com.hansy.portal.model.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TBusPamentVo {
	private String tableKey;
	private String orderNo;	//订单编号
	private String custNo;//客户编号
	private String custName;//客户名称
	private String supplyNo;//供方编号
	private Double goodsMoney;//付款金额
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date payDate;//付款时间
	private String billNo;//发票编号
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date billDate;//开票时间
	private String billNatrue;			//发票性质(纸质发票，电子发票)
	private String billStatus;			//发票状态(0,1)
	private String billTitle;			//发票开头(个人公司)
	private String companyName;			//公司名称
	private Double billMoney;			//发票总金额
	private String billType;			//发票类型
	private String billReceiveName;		//收信人
	private String billReceiveAddress;	//收取地址
	private String billReceiveMail;		//邮编
	private String billReceivePhone;	//联系电话
	private String logisticsName;		//物流公司
	private String expressNumber;		//快递编号
	private Date insertDate;			//新增时间
	
	public String getTableKey() {
		return tableKey;
	}
	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
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
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	public String getSupplyNo() {
		return supplyNo;
	}
	public void setSupplyNo(String supplyNo) {
		this.supplyNo = supplyNo;
	}
	public Double getGoodsMoney() {
		return goodsMoney;
	}
	public void setGoodsMoney(Double goodsMoney) {
		this.goodsMoney = goodsMoney;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
}

