/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.mesworkshift.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.modules.sys.entity.User;

import java.util.List;

import com.google.common.collect.Lists;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 班组用户关系表Entity
 * @author 孙会楠
 * @version 2018-05-05
 */
public class MesWorkshiftUser extends DataEntity<MesWorkshiftUser> {
	
	private static final long serialVersionUID = 1L;
	private String mesWorkshiftId;		// 班组id
	private String sysUserId;		// 用户id
	private String item01;		// 备用字段01
	private String item02;		// 备用字段02
	private String item03;		// 备用字段03
	private String item04;		// 备用字段04
	private String item05;		// 备用字段05
	
	//表外字段
	private String loginName;
	private String sysUserName;
	
	private User sysUser;
	private List<User> sysUserList = Lists.newArrayList();
	
	public MesWorkshiftUser() {
		super();
	}

	public MesWorkshiftUser(String id){
		super(id);
	}

	public MesWorkshiftUser(MesWorkshift mesWorkshift) {
		
	}
	
	@ExcelField(title="班组id", align=2, sort=1)
	public String getMesWorkshiftId() {
		return mesWorkshiftId;
	}

	public void setMesWorkshiftId(String mesWorkshiftId) {
		this.mesWorkshiftId = mesWorkshiftId;
	}
	
	@ExcelField(title="用户id", align=2, sort=2)
	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	@ExcelField(title="备用字段01", align=2, sort=3)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="备用字段02", align=2, sort=4)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="备用字段03", align=2, sort=5)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="备用字段04", align=2, sort=6)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="备用字段05", align=2, sort=7)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
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