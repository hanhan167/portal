
package com.hansy.portal.model.vo;

import java.io.Serializable;
import java.util.Date;


public class TFlowTaskInfoVo implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -614695062265773516L;
	private String taskNo;
	private String flowNo;
	private String busiKey;
	private String taskDesc;
	private String insertUser;
	private String insertUserName;
	private Date insertTime;
	private String taskState;
	private String busiKey1;
	private String busiKey2;
	
	public String getTaskNo() {
		return taskNo;
	}
	
	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	public String getFlowNo() {
		return flowNo;
	}
	
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	
	public String getBusiKey() {
		return busiKey;
	}
	
	public void setBusiKey(String busiKey) {
		this.busiKey = busiKey;
	}
	
	public String getTaskDesc() {
		return taskDesc;
	}
	
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	
	public String getInsertUser() {
		return insertUser;
	}
	
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
	
	public String getInsertUserName() {
		return insertUserName;
	}
	
	public void setInsertUserName(String insertUserName) {
		this.insertUserName = insertUserName;
	}
	
	public Date getInsertTime() {
		return insertTime;
	}
	
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	public String getTaskState() {
		return taskState;
	}
	
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	
	public String getBusiKey1() {
		return busiKey1;
	}
	
	public void setBusiKey1(String busiKey1) {
		this.busiKey1 = busiKey1;
	}
	
	public String getBusiKey2() {
		return busiKey2;
	}
	
	public void setBusiKey2(String busiKey2) {
		this.busiKey2 = busiKey2;
	}
	
	
}

