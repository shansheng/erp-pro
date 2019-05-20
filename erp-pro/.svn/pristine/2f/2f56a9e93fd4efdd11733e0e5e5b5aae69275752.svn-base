/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsequipmentuser.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.pt.core.persistence.DataEntity;
import com.pt.modules.mds.mdsequipment.entity.MdsEquipment;
import com.pt.modules.sys.entity.User;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 设备与人员关系表Entity
 * @author 郑利
 * @version 2018-05-05
 */
public class MdsEquipmentUser extends DataEntity<MdsEquipmentUser> {
	
	private static final long serialVersionUID = 1L;
	private String item01;		// 备用字段01
	private String item02;		// 备用字段02
	private String item03;		// 备用字段03
	private String item04;		// 备用字段04
	private String item05;		// 备用字段05
	
	//表外字段
	private String mdsEquipmentId;
	private String sysUserId;
	private String loginName;
	private String sysUserName;
	
	private User sysUser;
	private List<User> sysUserList = Lists.newArrayList();
	
	
	public MdsEquipmentUser() {
		super();
	}

	public MdsEquipmentUser(String id){
		super(id);
	}
	
	@ExcelField(title="备用字段01", align=2, sort=22)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="备用字段02", align=2, sort=23)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="备用字段03", align=2, sort=24)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="备用字段04", align=2, sort=25)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	public String getMdsEquipmentId() {
		return mdsEquipmentId;
	}

	public void setMdsEquipmentId(String mdsEquipmentId) {
		this.mdsEquipmentId = mdsEquipmentId;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}



	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSysUserName() {
		return sysUserName;
	}

	public void setSysUserName(String sysUserName) {
		this.sysUserName = sysUserName;
	}

	@ExcelField(title="备用字段05", align=2, sort=26)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}

	public User getSysUser() {
		return sysUser;
	}

	public void setSysUser(User sysUser) {
		this.sysUser = sysUser;
	}

	public List<User> getSysUserList() {
		return sysUserList;
	}

	public void setSysUserList(List<User> sysUserList) {
		this.sysUserList = sysUserList;
	}


	
}