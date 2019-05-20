/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsiteminventoryvs.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 流水查询Entity
 * @author 郑利
 * @version 2018-05-16
 */
public class WmsItemInventoryVs extends DataEntity<WmsItemInventoryVs> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String itemCode;		// 物料代码
	private String itemName;		// 物料名称
	private String marque;		// 牌号
	private String specs;		// 规格
	private String techCondition;		// 技术条件
	private String batchNo;		// batch_no
	private String qualityCode;		// quality_code
	private String lph;		// lph
	private String currentOnhandQty;		// current_onhand_qty
	private String engagedQty;		// engaged_qty
	private String wmsInventoryId;		// wms_inventory_id
	private String inventoryCode;		// 库房代码
	private String inventoryName;		// 库房名称
	private String locatorCode;		// 库位号
	private String sysOfficeId;		// 部门
	private String sysOfficeName;		// 名称
	
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public WmsItemInventoryVs() {
		super();
	}

	public WmsItemInventoryVs(String id){
		super(id);
	}

	@ExcelField(title="物料代码", align=2, sort=1)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@ExcelField(title="物料名称", align=2, sort=2)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@ExcelField(title="牌号", align=2, sort=3)
	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}
	
	@ExcelField(title="规格", align=2, sort=4)
	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	@ExcelField(title="技术条件", align=2, sort=5)
	public String getTechCondition() {
		return techCondition;
	}

	public void setTechCondition(String techCondition) {
		this.techCondition = techCondition;
	}
	
	@ExcelField(title="batch_no", align=2, sort=6)
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@ExcelField(title="quality_code", align=2, sort=7)
	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
	}
	
	@ExcelField(title="lph", align=2, sort=8)
	public String getLph() {
		return lph;
	}

	public void setLph(String lph) {
		this.lph = lph;
	}
	
	@ExcelField(title="current_onhand_qty", align=2, sort=9)
	public String getCurrentOnhandQty() {
		return currentOnhandQty;
	}

	public void setCurrentOnhandQty(String currentOnhandQty) {
		this.currentOnhandQty = currentOnhandQty;
	}
	
	@ExcelField(title="engaged_qty", align=2, sort=10)
	public String getEngagedQty() {
		return engagedQty;
	}

	public void setEngagedQty(String engagedQty) {
		this.engagedQty = engagedQty;
	}
	
	@ExcelField(title="wms_inventory_id", align=2, sort=11)
	public String getWmsInventoryId() {
		return wmsInventoryId;
	}

	public void setWmsInventoryId(String wmsInventoryId) {
		this.wmsInventoryId = wmsInventoryId;
	}
	
	@ExcelField(title="库房代码", align=2, sort=12)
	public String getInventoryCode() {
		return inventoryCode;
	}

	public void setInventoryCode(String inventoryCode) {
		this.inventoryCode = inventoryCode;
	}
	
	@ExcelField(title="库房名称", align=2, sort=13)
	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	
	@ExcelField(title="库位号", align=2, sort=14)
	public String getLocatorCode() {
		return locatorCode;
	}

	public void setLocatorCode(String locatorCode) {
		this.locatorCode = locatorCode;
	}
	
	@ExcelField(title="部门", align=2, sort=15)
	public String getSysOfficeId() {
		return sysOfficeId;
	}

	public void setSysOfficeId(String sysOfficeId) {
		this.sysOfficeId = sysOfficeId;
	}
	
	@ExcelField(title="名称", align=2, sort=16)
	public String getSysOfficeName() {
		return sysOfficeName;
	}

	public void setSysOfficeName(String sysOfficeName) {
		this.sysOfficeName = sysOfficeName;
	}
	
}