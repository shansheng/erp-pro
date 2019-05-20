/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininventory.entity;

import java.util.List;
import com.google.common.collect.Lists;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 台账明细表Entity
 * @author cjx
 * @version 2018-10-17
 */
public class WmsMainInventory extends DataEntity<WmsMainInventory> {
	
	private static final long serialVersionUID = 1L;
	private String itemId;		// 物料id
	private int currentOnhandQty;		// 现在的库存
	private String column1;		// column1
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private List<WmsMainRecord> wmsMainRecordList = Lists.newArrayList();		// 子表列表
	
	private String itemCode;	//物料编码
	private String itemName;	//物料名字
	private String normalField;		// 标准号
	private String modelNum;		// 型号
	private String specs;		// 规格
	
	public WmsMainInventory() {
		super();
	}

	public WmsMainInventory(String id){
		super(id);
	}

	@ExcelField(title="物料id", align=2, sort=1)
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@ExcelField(title="现在的库存", align=2, sort=2)
	public int getCurrentOnhandQty() {
		return currentOnhandQty;
	}

	public void setCurrentOnhandQty(int currentOnhandQty) {
		this.currentOnhandQty = currentOnhandQty;
	}
	
	@ExcelField(title="column1", align=2, sort=3)
	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	
	@ExcelField(title="column2", align=2, sort=4)
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	
	@ExcelField(title="column3", align=2, sort=5)
	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	@ExcelField(title="column4", align=2, sort=6)
	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	@ExcelField(title="column5", align=2, sort=7)
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	
	public List<WmsMainRecord> getWmsMainRecordList() {
		return wmsMainRecordList;
	}

	public void setWmsMainRecordList(List<WmsMainRecord> wmsMainRecordList) {
		this.wmsMainRecordList = wmsMainRecordList;
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
	
	
}