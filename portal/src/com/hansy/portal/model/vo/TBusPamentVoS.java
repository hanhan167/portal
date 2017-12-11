
package com.hansy.portal.model.vo;

import java.util.List;

public class TBusPamentVoS {
	private List<TBusPamentVo>orderList;
	private Double billMoney;
	private String billTitle;
	private String companyName;
	
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

	public List<TBusPamentVo> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<TBusPamentVo> orderList) {
		this.orderList = orderList;
	}

}

