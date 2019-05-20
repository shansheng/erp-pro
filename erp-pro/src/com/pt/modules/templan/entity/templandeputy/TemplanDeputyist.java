/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.templan.entity.templandeputy;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 临时订单物料详情表Entity
 * @author cjx
 * @version 2018-09-08
 */
public class TemplanDeputyist extends DataEntity<TemplanDeputyist> {
	
	private static final long serialVersionUID = 1L;
	private String temporaryPlanId;		// 临时计划订单ID 父类
	private String mdsItemId;		// 物料ID
	private Long applyNumber;		// 申请数量
	private Long applyovalNumber;		// 审批数量
	private Date demandDate;		// 需求日期
	private String item01;		// item_01
	private String item02;		// item_02
	private String item03;		// item_03
	private String item04;		// item_04
	private String item05;		// item_05
	
	private String itemCode;
	private String itemName;
	
	
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

	public TemplanDeputyist() {
		super();
	}

	public TemplanDeputyist(String id){
		super(id);
	}

	public TemplanDeputyist(TemplanDeputy temporaryPlanId){
		this.temporaryPlanId = temporaryPlanId.getId();
	}

	public String getTemporaryPlanId() {
		return temporaryPlanId;
	}

	public void setTemporaryPlanId(String temporaryPlanId) {
		this.temporaryPlanId = temporaryPlanId;
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
	public Long getApplyNumber() {
		return applyNumber;
	}

	public void setApplyNumber(Long applyNumber) {
		this.applyNumber = applyNumber;
	}
	
	@NotNull(message="审批数量不能为空")
	@ExcelField(title="审批数量", align=2, sort=4)
	public Long getApplyovalNumber() {
		return applyovalNumber;
	}

	public void setApplyovalNumber(Long applyovalNumber) {
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
	
}