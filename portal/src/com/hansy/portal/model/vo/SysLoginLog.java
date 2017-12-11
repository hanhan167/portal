
package com.hansy.portal.model.vo;

import java.io.Serializable;
import java.util.Date;


public class SysLoginLog implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -8581145871675943367L;
	private String tableKey;
	private String orgId;
	private String roleId;
	private String staffId;
	private Date loginDate;
	private String loginIp;
	
	public String getTableKey() {
		return tableKey;
	}
	
	public void setTableKey(String tableKey) {
		this.tableKey = tableKey;
	}
	
	public String getOrgId() {
		return orgId;
	}
	
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	public String getRoleId() {
		return roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getStaffId() {
		return staffId;
	}
	
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	public Date getLoginDate() {
		return loginDate;
	}
	
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	
	public String getLoginIp() {
		return loginIp;
	}
	
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	@Override
	public String toString() {
		return "SysLoginLog [tableKey=" + tableKey + ", orgId=" + orgId + ", roleId=" + roleId + ", staffId=" + staffId + ", loginDate=" + loginDate + ", loginIp=" + loginIp + "]";
	}
}

