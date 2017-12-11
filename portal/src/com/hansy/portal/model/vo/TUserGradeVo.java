
package com.hansy.portal.model.vo;

import java.io.Serializable;
import java.util.Date;


public class TUserGradeVo implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 6176354814736260304L;

	private String custNo;			//用户编号
	private String custName;		//用户名称
	private String userType;		//用户类型
	private String gradeNo;			//用户等级
	private Date insertDate;		//新增时间
	private Date updateDate;		//更新时间
	
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
	
	public String getGradeNo() {
		return gradeNo;
	}
	
	public void setGradeNo(String gradeNo) {
		this.gradeNo = gradeNo;
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
}

