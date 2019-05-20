/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.templandepartment.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 临时计划部门审核Entity
 * @author cjx
 * @version 2018-09-05
 */
public class TemplanDepartment extends DataEntity<TemplanDepartment> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 申请单号
	private String departmentAssessing;		// 部门审批人
	private Date departmentAssessingDate;		// 部门审批时间
	private String deputyAssessing;		// 副总审批人
	private Date deputyAssessingDate;		// 副总审批时间
	private String state;		// 状态(10:未提交,11:部门领导驳回,20:部门领导未审核,21:副总驳回,30:付总未审核)
	private String column1;		// column1
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private List<TemplanDepartmentList> templanDepartmentListList = Lists.newArrayList();		// 子表列表
	
	private Date createDateEnd;	//查询中申请人的结束日期
	private String statying;	//用户选择状态
	
	public TemplanDepartment() {
		super();
	}

	public TemplanDepartment(String id){
		super(id);
	}

	@ExcelField(title="申请单号", align=2, sort=1)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@ExcelField(title="部门审批人", align=2, sort=2)
	public String getDepartmentAssessing() {
		return departmentAssessing;
	}

	public void setDepartmentAssessing(String departmentAssessing) {
		this.departmentAssessing = departmentAssessing;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="部门审批时间", align=2, sort=3)
	public Date getDepartmentAssessingDate() {
		return departmentAssessingDate;
	}

	public void setDepartmentAssessingDate(Date departmentAssessingDate) {
		this.departmentAssessingDate = departmentAssessingDate;
	}
	
	@ExcelField(title="副总审批人", align=2, sort=4)
	public String getDeputyAssessing() {
		return deputyAssessing;
	}

	public void setDeputyAssessing(String deputyAssessing) {
		this.deputyAssessing = deputyAssessing;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="副总审批时间", align=2, sort=5)
	public Date getDeputyAssessingDate() {
		return deputyAssessingDate;
	}

	public void setDeputyAssessingDate(Date deputyAssessingDate) {
		this.deputyAssessingDate = deputyAssessingDate;
	}
	
	@ExcelField(title="状态(10:未提交,11:部门领导驳回,20:部门领导未审核,21:副总驳回,30:付总未审核)", align=2, sort=6)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	
	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	
	public List<TemplanDepartmentList> getTemplanDepartmentListList() {
		return templanDepartmentListList;
	}

	public void setTemplanDepartmentListList(List<TemplanDepartmentList> templanDepartmentListList) {
		this.templanDepartmentListList = templanDepartmentListList;
	}
	
	@ExcelField(title="申请日期", align=2, sort=7)
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getStatying() {
		return statying;
	}

	public void setStatying(String statying) {
		this.statying = statying;
	}
	
	

}