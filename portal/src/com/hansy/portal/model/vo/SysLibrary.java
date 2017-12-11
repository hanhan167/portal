
package com.hansy.portal.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件保存
 * TODO javadoc for com.hansy.portal.model.vo.SysLibrary
 * @Copyright: 2017成都环赛信息技术有限公司 
 * @author: cj
 * @since: 2017年3月31日
 */
public class SysLibrary implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -2179601643425861654L;
	private String libId;
	private Date libCreateTime;
	private String libName;
	private String libTitle;
	private String libDirectory;
	private String libExtName;
	private Integer libType;
	private Integer state;
	private Long libSize;
	private String libDesc;
	private String libOwnerName;
	private String libOwnerId;
	private String libWidth;
	private String libHeight;
	private String libMini;
	private String libPage;
	private String libRoleId;
	private String menuId;
	
	public String getLibId() {
		return libId;
	}
	
	public void setLibId(String libId) {
		this.libId = libId;
	}
	
	public Date getLibCreateTime() {
		return libCreateTime;
	}
	
	public void setLibCreateTime(Date libCreateTime) {
		this.libCreateTime = libCreateTime;
	}
	
	public String getLibName() {
		return libName;
	}
	
	public void setLibName(String libName) {
		this.libName = libName;
	}
	
	public String getLibTitle() {
		return libTitle;
	}
	
	public void setLibTitle(String libTitle) {
		this.libTitle = libTitle;
	}
	
	public String getLibDirectory() {
		return libDirectory;
	}
	
	public void setLibDirectory(String libDirectory) {
		this.libDirectory = libDirectory;
	}
	
	public String getLibExtName() {
		return libExtName;
	}
	
	public void setLibExtName(String libExtName) {
		this.libExtName = libExtName;
	}
	
	public Integer getLibType() {
		return libType;
	}
	
	public void setLibType(Integer libType) {
		this.libType = libType;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public Long getLibSize() {
		return libSize;
	}
	
	public void setLibSize(Long libSize) {
		this.libSize = libSize;
	}
	
	public String getLibDesc() {
		return libDesc;
	}
	
	public void setLibDesc(String libDesc) {
		this.libDesc = libDesc;
	}
	
	public String getLibOwnerName() {
		return libOwnerName;
	}
	
	public void setLibOwnerName(String libOwnerName) {
		this.libOwnerName = libOwnerName;
	}
	
	public String getLibOwnerId() {
		return libOwnerId;
	}
	
	public void setLibOwnerId(String libOwnerId) {
		this.libOwnerId = libOwnerId;
	}
	
	public String getLibWidth() {
		return libWidth;
	}
	
	public void setLibWidth(String libWidth) {
		this.libWidth = libWidth;
	}
	
	public String getLibHeight() {
		return libHeight;
	}
	
	public void setLibHeight(String libHeight) {
		this.libHeight = libHeight;
	}
	
	public String getLibMini() {
		return libMini;
	}
	
	public void setLibMini(String libMini) {
		this.libMini = libMini;
	}
	
	public String getLibPage() {
		return libPage;
	}
	
	public void setLibPage(String libPage) {
		this.libPage = libPage;
	}
	
	public String getLibRoleId() {
		return libRoleId;
	}
	
	public void setLibRoleId(String libRoleId) {
		this.libRoleId = libRoleId;
	}
	
	public String getMenuId() {
		return menuId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}

