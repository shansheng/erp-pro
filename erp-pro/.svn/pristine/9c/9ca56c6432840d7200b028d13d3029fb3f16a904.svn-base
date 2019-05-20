/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsReplaceItem.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 代料申请Entity
 * @author zjj
 * @version 2018-10-11
 */
public class MdsReplaceItem extends DataEntity<MdsReplaceItem> {
	
	private static final long serialVersionUID = 1L;
	private String mdsProcurementPlanId;		// 采购订单Id
	private String beforeReplaceItemId;		// 替换前物料Id
	private String beforeReplaceItemName;		// 替换前物料名称
	private String itemId;		// 替换后物料id
	private String itemName;		// 替换后物料名称
	private String afterReplacePuraseNumber;		// 替换后采购数量
	private String approveBy;		// 审批人
	private Date approveDate;		// 审批时间
	private String replaceState;		// 申批状态
	private String purchaseMode;		// 采购方式
	
	private String classify;
	private String itemCode;
	private String normalField;
	private String modelnm;
	private String specs;
	private String purchaseAmount;
	private Date requiredDate;
	private String fromId;
	private String particulars;
	private String sourceType;
	
	public MdsReplaceItem() {
		super();
	}

	public MdsReplaceItem(String id){
		super(id);
	}

	@ExcelField(title="采购订单Id", align=2, sort=7)
	public String getMdsProcurementPlanId() {
		return mdsProcurementPlanId;
	}

	public void setMdsProcurementPlanId(String mdsProcurementPlanId) {
		this.mdsProcurementPlanId = mdsProcurementPlanId;
	}
	
	@ExcelField(title="替换前物料Id", align=2, sort=8)
	public String getBeforeReplaceItemId() {
		return beforeReplaceItemId;
	}

	public void setBeforeReplaceItemId(String beforeReplaceItemId) {
		this.beforeReplaceItemId = beforeReplaceItemId;
	}
	
	@ExcelField(title="替换前物料名称", align=2, sort=9)
	public String getBeforeReplaceItemName() {
		return beforeReplaceItemName;
	}

	public void setBeforeReplaceItemName(String beforeReplaceItemName) {
		this.beforeReplaceItemName = beforeReplaceItemName;
	}
	
	@ExcelField(title="替换后采购数量", align=2, sort=12)
	public String getAfterReplacePuraseNumber() {
		return afterReplacePuraseNumber;
	}
	@ExcelField(title="替换后物料id", align=2, sort=10)
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@ExcelField(title="替换后物料名称", align=2, sort=11)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setAfterReplacePuraseNumber(String afterReplacePuraseNumber) {
		this.afterReplacePuraseNumber = afterReplacePuraseNumber;
	}
	
	@ExcelField(title="审批人", align=2, sort=13)
	public String getApproveBy() {
		return approveBy;
	}

	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="审批时间", align=2, sort=14)
	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	
	@ExcelField(title="申批状态", align=2, sort=15)
	public String getReplaceState() {
		return replaceState;
	}

	public void setReplaceState(String replaceState) {
		this.replaceState = replaceState;
	}
	
	@ExcelField(title="采购方式", align=2, sort=16)
	public String getPurchaseMode() {
		return purchaseMode;
	}

	public void setPurchaseMode(String purchaseMode) {
		this.purchaseMode = purchaseMode;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getNormalField() {
		return normalField;
	}

	public void setNormalField(String normalField) {
		this.normalField = normalField;
	}

	public String getModelnm() {
		return modelnm;
	}

	public void setModelnm(String modelnm) {
		this.modelnm = modelnm;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
}