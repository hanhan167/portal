
package com.hansy.portal.model.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户粉丝实体
 * TODO javadoc for com.hansy.portal.model.bo.Fans
 * @Copyright: 2017成都环赛信息技术有限公司 
 * @author: cj
 * @since: 2017年2月27日
 */
public class Fans implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2944854244803846323L;
	private String name;		//粉丝名
	private String grade;		//粉丝等级
	private String email;		//粉丝邮箱
	private String phoneNo;		//粉丝电话
	private String totalConsume;//总消费
	private String custNo;
	private String tota;//交易完成量
	private Date logintime;
	private String reward;
	
	
	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getTota() {
		return tota;
	}

	public void setTota(String tota) {
		this.tota = tota;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getTotalConsume() {
		return totalConsume;
	}
	
	public void setTotalConsume(String totalConsume) {
		this.totalConsume = totalConsume;
	}

	@Override
	public String toString() {
		return "Fans [name=" + name + ", grade=" + grade + ", email=" + email + ", phoneNo=" + phoneNo + ", totalConsume=" + totalConsume + "]";
	}
}

