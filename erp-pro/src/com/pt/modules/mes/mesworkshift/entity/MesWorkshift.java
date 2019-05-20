/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.mesworkshift.entity;

import java.util.List;
import com.google.common.collect.Lists;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 班组管理Entity
 * @author 孙会楠
 * @version 2018-05-05
 */
public class MesWorkshift extends DataEntity<MesWorkshift> {
	
	private static final long serialVersionUID = 1L;
	private String workcenterCode;		// 班组代码
	private String workcenterName;		// 班组名称
	private String sysOfficeId;		// 所属部门 ^ 该工作中心所在的部门,引用Hr.Hr_Dept.ID
	private String item01;		// 备用字段01
	private String item02;		// 备用字段02
	private String item03;		// 备用字段03
	private String item04;		// 备用字段04
	private String item05;		// 备用字段05
	private List<MesWorkshiftUser> mesWorkshiftUserList = Lists.newArrayList();		// 子表列表
	//表外字段
	private String sysOfficeName;
	
	public String getSysOfficeName() {
		return sysOfficeName;
	}

	public void setSysOfficeName(String sysOfficeName) {
		this.sysOfficeName = sysOfficeName;
	}

	public MesWorkshift() {
		super();
	}

	public MesWorkshift(String id){
		super(id);
	}

	@ExcelField(title="班组代码", align=2, sort=1)
	public String getWorkcenterCode() {
		return workcenterCode;
	}

	public void setWorkcenterCode(String workcenterCode) {
		this.workcenterCode = workcenterCode;
	}
	
	@ExcelField(title="班组名称", align=2, sort=2)
	public String getWorkcenterName() {
		return workcenterName;
	}

	public void setWorkcenterName(String workcenterName) {
		this.workcenterName = workcenterName;
	}
	
	@ExcelField(title="所属部门 ^ 该工作中心所在的部门,引用Hr.Hr_Dept.ID", align=2, sort=3)
	public String getSysOfficeId() {
		return sysOfficeId;
	}

	public void setSysOfficeId(String sysOfficeId) {
		this.sysOfficeId = sysOfficeId;
	}
	
	@ExcelField(title="备用字段01", align=2, sort=4)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="备用字段02", align=2, sort=5)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="备用字段03", align=2, sort=6)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="备用字段04", align=2, sort=7)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="备用字段05", align=2, sort=8)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}
	
	public List<MesWorkshiftUser> getMesWorkshiftUserList() {
		return mesWorkshiftUserList;
	}

	public void setMesWorkshiftUserList(List<MesWorkshiftUser> mesWorkshiftUserList) {
		this.mesWorkshiftUserList = mesWorkshiftUserList;
	}
}