/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matrequisition.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.google.common.collect.Lists;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 领料单申请Entity
 * @author 郑利
 * @version 2018-09-18
 */
public class MatRequisition extends DataEntity<MatRequisition> {
	
	private static final long serialVersionUID = 1L;
	private String departmentassessing;		// departmentassessing
	private Date departmentAssessingDate;		// department_assessing_date
	private String deputyAssessing;		// deputy_assessing
	private Date deputyAssessingDate;		// deputy_assessing_date
	private String state;		// state
	private String column1;		// column1
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private String code;		// 申请单号
	private Date beginCreateDate;		// 开始 create_date
	private Date endCreateDate;		// 结束 create_date
	private String userName;
	private List<MatRequisitionList> matRequisitionListList = Lists.newArrayList();		// 子表列表
	private String statying;	//用户选择状态
	
	private String proMdsItemCode;
	private String itemName;
	private String itemId;
	
	private String createByName; //申请人名字
	private String departmentAssessingName;//部门审批人名字
	public MatRequisition() {
		super();
	}

	public MatRequisition(String id){
		super(id);
	}

	@ExcelField(title="departmentassessing", align=2, sort=1)
	public String getDepartmentassessing() {
		return departmentassessing;
	}

	public void setDepartmentassessing(String departmentassessing) {
		this.departmentassessing = departmentassessing;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="department_assessing_date", align=2, sort=2)
	public Date getDepartmentAssessingDate() {
		return departmentAssessingDate;
	}

	public void setDepartmentAssessingDate(Date departmentAssessingDate) {
		this.departmentAssessingDate = departmentAssessingDate;
	}
	
	@ExcelField(title="deputy_assessing", align=2, sort=3)
	public String getDeputyAssessing() {
		return deputyAssessing;
	}

	public void setDeputyAssessing(String deputyAssessing) {
		this.deputyAssessing = deputyAssessing;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="deputy_assessing_date", align=2, sort=4)
	public Date getDeputyAssessingDate() {
		return deputyAssessingDate;
	}

	public void setDeputyAssessingDate(Date deputyAssessingDate) {
		this.deputyAssessingDate = deputyAssessingDate;
	}
	
	@ExcelField(title="state", align=2, sort=5)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@ExcelField(title="column1", align=2, sort=6)
	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	
	@ExcelField(title="column2", align=2, sort=7)
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	
	@ExcelField(title="column3", align=2, sort=8)
	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	@ExcelField(title="column4", align=2, sort=9)
	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	@ExcelField(title="column5", align=2, sort=10)
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	
	@ExcelField(title="申请单号", align=2, sort=17)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
	public List<MatRequisitionList> getMatRequisitionListList() {
		return matRequisitionListList;
	}

	public void setMatRequisitionListList(List<MatRequisitionList> matRequisitionListList) {
		this.matRequisitionListList = matRequisitionListList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatying() {
		return statying;
	}

	public void setStatying(String statying) {
		this.statying = statying;
	}

	public String getDepartmentAssessingName() {
		return departmentAssessingName;
	}

	public void setDepartmentAssessingName(String departmentAssessingName) {
		this.departmentAssessingName = departmentAssessingName;
	}

	public String getProMdsItemCode() {
		return proMdsItemCode;
	}

	public void setProMdsItemCode(String proMdsItemCode) {
		this.proMdsItemCode = proMdsItemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}




	
	
}