
package com.hansy.portal.model.vo;

import java.io.Serializable;
import java.util.Date;


public class TFlowTaskLogVo implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -1830336265107114852L;
	private String tableKey;
	private String taskNo;
	private String rectName;
	private String rectState;
	private Integer rectOrder;
	private String dealUser;
	private String dealUserName;
	private String dealRole;
	private Date takeTime;
	private Date finishTime;
	private String rectType;
	private String dealComment;
	private String dealResult;
	private String dealPage;
	private String dealDecision;
	private String rejectComment;
	private Date insertTime;
	private String updateUser;
	private String updateUserName;
	
	public String getTableKey() {
		return tableKey;
	}
	
	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
	}
	
	public String getTaskNo() {
		return taskNo;
	}
	
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	public String getRectName() {
		return rectName;
	}
	
	public void setRectName(String rectName) {
		this.rectName = rectName;
	}
	
	public String getRectState() {
		return rectState;
	}
	
	public void setRectState(String rectState) {
		this.rectState = rectState;
	}
	
	public Integer getRectOrder() {
		return rectOrder;
	}
	
	public void setRectOrder(Integer rectOrder) {
		this.rectOrder = rectOrder;
	}
	
	public String getDealUser() {
		return dealUser;
	}
	
	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}
	
	public String getDealUserName() {
		return dealUserName;
	}
	
	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
	}
	
	public String getDealRole() {
		return dealRole;
	}
	
	public void setDealRole(String dealRole) {
		this.dealRole = dealRole;
	}
	
	public Date getTakeTime() {
		return takeTime;
	}
	
	public void setTakeTime(Date takeTime) {
		this.takeTime = takeTime;
	}
	
	public Date getFinishTime() {
		return finishTime;
	}
	
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	public String getRectType() {
		return rectType;
	}
	
	public void setRectType(String rectType) {
		this.rectType = rectType;
	}
	
	public String getDealComment() {
		return dealComment;
	}
	
	public void setDealComment(String dealComment) {
		this.dealComment = dealComment;
	}
	
	public String getDealResult() {
		return dealResult;
	}
	
	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}
	
	public String getDealPage() {
		return dealPage;
	}
	
	public void setDealPage(String dealPage) {
		this.dealPage = dealPage;
	}
	
	public String getDealDecision() {
		return dealDecision;
	}
	
	public void setDealDecision(String dealDecision) {
		this.dealDecision = dealDecision;
	}
	
	public String getRejectComment() {
		return rejectComment;
	}
	
	public void setRejectComment(String rejectComment) {
		this.rejectComment = rejectComment;
	}
	
	public Date getInsertTime() {
		return insertTime;
	}
	
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	public String getUpdateUser() {
		return updateUser;
	}
	
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	public String getUpdateUserName() {
		return updateUserName;
	}
	
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
}

