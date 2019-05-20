/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininventory.entity;

import java.util.Date;

import com.pt.common.utils.excel.annotation.ExcelField;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.wms.wmsmaininstorage.entity.WmsMainInStorage;

/**
 * 库房记录表Entity
 * @author cjx
 * @version 2018-10-17
 */
public class WmsMainRecord extends DataEntity<WmsMainRecord> {
	
	private static final long serialVersionUID = 1L;
	private String itemId;		// 物料id 父类
	private int qty;		// 出入库数
	private String state;		// 状态(1:入库,2:出库)
	private String column1;		// column1
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private String remark;		// 备注
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private String associated;	//关联信息
	private String associatedId;	//关联id
	
	private String itemCode;		// 物料编码
	private String itemName;		// 物料名称
	private String normalField;		// 标准号
	private String modelNum;		// 型号
	private String specs;		// 规格
	
	private String supplierName;//供應商
	
	public WmsMainRecord() {
		super();
	}

	public WmsMainRecord(String id){
		super(id);
	}
	
	public WmsMainRecord(WmsMainInStorage wmsMainInStorage){
		this.itemId = wmsMainInStorage.getItemId();
		this.qty = wmsMainInStorage.getQualified();
		this.state = "1";
		this.createBy = wmsMainInStorage.getInStorageBy();
		this.createDate = wmsMainInStorage.getInStorageDate();
		this.updateBy = wmsMainInStorage.getInStorageBy();
		this.updateDate = wmsMainInStorage.getInStorageDate();
		if("0".equals(wmsMainInStorage.getSource())){
			this.associated = "检查入库";
		}else{
			this.associated = "手动入库";
		}
		this.associatedId = wmsMainInStorage.getId();
	}

	public WmsMainRecord(WmsMainInventory wmsMainInventory){
		this.itemId = wmsMainInventory.getItemId();
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@ExcelField(title="出入库数", align=2, sort=2)
	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	@ExcelField(title="状态(1:入库,2:出库)", align=2, sort=3)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@ExcelField(title="column1", align=2, sort=4)
	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	
	@ExcelField(title="column2", align=2, sort=5)
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	
	@ExcelField(title="column3", align=2, sort=6)
	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	@ExcelField(title="column4", align=2, sort=7)
	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	@ExcelField(title="column5", align=2, sort=8)
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	
	@ExcelField(title="备注", align=2, sort=13)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	
	public String getAssociated() {
		return associated;
	}

	public void setAssociated(String associated) {
		this.associated = associated;
	}

	public String getAssociatedId() {
		return associatedId;
	}

	public void setAssociatedId(String associatedId) {
		this.associatedId = associatedId;
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

	public String getNormalField() {
		return normalField;
	}

	public void setNormalField(String normalField) {
		this.normalField = normalField;
	}

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
   
}