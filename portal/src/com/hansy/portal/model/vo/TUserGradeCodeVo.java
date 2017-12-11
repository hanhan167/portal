
package com.hansy.portal.model.vo;

import java.io.Serializable;
import java.util.Date;


public class TUserGradeCodeVo implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 6782268396827276805L;
	private String gradeNo;			//用户编号
	private String gradeName;		//用户名称
	private Date insertDate;		//新增时间
	private Date updateDate;		//更新时间
	
	public String getGradeNo() {
		return gradeNo;
	}
	
	public void setGradeNo(String gradeNo) {
		this.gradeNo = gradeNo;
	}
	
	public String getGradeName() {
		return gradeName;
	}
	
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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
		return "TUserGradeCodeVo [gradeNo=" + gradeNo + ", gradeName=" + gradeName + ", insertDate=" + insertDate + ", updateDate=" + updateDate + "]";
	}
}

