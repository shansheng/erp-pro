/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matrequisition.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * mat_requisition_listEntity
 * @author 郑利
 * @version 2018-09-18
 */
public class MatRequisitionList extends DataEntity<MatRequisitionList> {
	
	private static final long serialVersionUID = 1L;
	private String matRequisitionId;		// 领料单ID 父类
	private String mdsItemId;		// 物料ID
	private int applyNumber;		// 申请数量
	private int applyovalNumber;		// 申请数量
	private Date demandDate;		// 需求日期
	private String item01;		// item_01
	private String item02;		// item_02
	private String item03;		// item_03
	private String item04;		// item_04
	private String item05;		// item_05
	private String itemCode;	//物料编号
	private String itemName;	//物料名字
	private String outputStatus; //出库状态
	public MatRequisitionList() {
		super();
	}

	public MatRequisitionList(String id){
		super(id);
	}

	public MatRequisitionList(MatRequisition matRequisitionId){
		this.matRequisitionId = matRequisitionId.getId();
	}

	public String  getMatRequisitionId() {
		return matRequisitionId;
	}

	public void setMatRequisitionId(String matRequisitionId) {
		this.matRequisitionId = matRequisitionId;
	}
	
	@ExcelField(title="物料ID", align=2, sort=2)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@NotNull(message="申请数量不能为空")
	@ExcelField(title="申请数量", align=2, sort=3)
	public int getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(int applyNumber) {
		this.applyNumber = applyNumber;
	}
	
	@NotNull(message="申请数量不能为空")
	@ExcelField(title="申请数量", align=2, sort=4)
	public int getApplyovalNumber() {
		return applyovalNumber;
	}

	public void setApplyovalNumber(int applyovalNumber) {
		this.applyovalNumber = applyovalNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="需求日期不能为空")
	@ExcelField(title="需求日期", align=2, sort=5)
	public Date getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}
	
	@ExcelField(title="item_01", align=2, sort=6)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="item_02", align=2, sort=7)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="item_03", align=2, sort=8)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="item_04", align=2, sort=9)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="item_05", align=2, sort=10)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getOutputStatus() {
		return outputStatus;
	}

	public void setOutputStatus(String outputStatus) {
		this.outputStatus = outputStatus;
	}
	
}