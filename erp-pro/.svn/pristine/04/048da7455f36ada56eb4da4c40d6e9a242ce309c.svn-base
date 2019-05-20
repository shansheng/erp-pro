/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysquestion.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 问题转移Entity
 * @author 郑利
 * @version 2018-07-21
 */
public class SysQueTransfer extends DataEntity<SysQueTransfer> {
	
	private static final long serialVersionUID = 1L;
	private String sysUserId;		// 转移人
	private String sysUserName;
	private String sysOfficeId;		// 转移部门
	private String sysOfficeName;
	private String sysQueId;		// 对应问题主键
	private String proSysUserId;		// 被转移人
	private String proSysUserName;
	private String transferReason;		// 转移原因
	
	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	public String getSysOfficeName() {
		return sysOfficeName;
	}

	public void setSysOfficeName(String sysOfficeName) {
		this.sysOfficeName = sysOfficeName;
	}

	public String getProSysUserName() {
		return proSysUserName;
	}

	public void setProSysUserName(String proSysUserName) {
		this.proSysUserName = proSysUserName;
	}

	private String queTitle;
	public SysQueTransfer() {
		super();
	}

	public SysQueTransfer(String id){
		super(id);
	}

	
	
	
	
	public String getQueTitle() {
		return queTitle;
	}

	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}

	@ExcelField(title="转移人", fieldType=String.class, value="", align=2, sort=1)
	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	@ExcelField(title="转移部门", fieldType=String.class, value="", align=2, sort=2)
	public String getSysOfficeId() {
		return sysOfficeId;
	}

	public void setSysOfficeId(String sysOfficeId) {
		this.sysOfficeId = sysOfficeId;
	}
	
	@ExcelField(title="对应问题主键", align=2, sort=3)
	public String getSysQueId() {
		return sysQueId;
	}

	public void setSysQueId(String sysQueId) {
		this.sysQueId = sysQueId;
	}
	
	@ExcelField(title="被转移人", fieldType=String.class, value="", align=2, sort=6)
	public String getProSysUserId() {
		return proSysUserId;
	}

	public void setProSysUserId(String proSysUserId) {
		this.proSysUserId = proSysUserId;
	}
	
	@ExcelField(title="转移原因", align=2, sort=7)
	public String getTransferReason() {
		return transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}
	
}