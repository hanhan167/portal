
package com.hansy.portal.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户收货地址
 * TODO javadoc for com.hansy.portal.model.vo.TUserAddress
 * @Copyright: 2017成都环赛信息技术有限公司 
 * @author: cj
 * @since: 2017年2月27日
 */
public class TUserAddressVo implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -1445615013104143255L;

	private String tableKey;	//主键
	private String userNo;		//用户编号
	private String userName;	//用户名称
	private String addresseeName;	//收信人名称
	private String addressNo;	//地址编号
	private String address;		//收货地址
	private String province;	//省份
	private String city;		//市
	private String area;		//区/县
	private String street;		//街道
	private String telephone;	//联系方式
	private String postCode;	//邮政号码
	private String addressStatus;	//是否是默认地址
	private String status;		//使用状态
	private Date insertDate;	//新增时间
	private Date updateDate;	//更新时间
	
	public String getTableKey() {
		return tableKey;
	}
	
	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
	}
	
	public String getUserNo() {
		return userNo;
	}
	
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getAddresseeName() {
		return addresseeName;
	}
	
	public void setAddresseeName(String addresseeName) {
		this.addresseeName = addresseeName;
	}
	
	public String getAddressNo() {
		return addressNo;
	}
	
	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getProvince() {
		return province;
	}
	
	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getArea() {
		return area;
	}
	
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getAddressStatus() {
		return addressStatus;
	}
	
	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
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
		return "TUserAddressVo [tableKey=" + tableKey + ", userNo=" + userNo + ", userName=" + userName + ", addresseeName=" + addresseeName + ", addressNo=" + addressNo
				+ ", address=" + address + ", province=" + province + ", city=" + city + ", area=" + area + ", street=" + street + ", telephone=" + telephone + ", postCode="
				+ postCode + ", addressStatus=" + addressStatus + ", status=" + status + ", insertDate=" + insertDate + ", updateDate=" + updateDate + "]";
	}
}

