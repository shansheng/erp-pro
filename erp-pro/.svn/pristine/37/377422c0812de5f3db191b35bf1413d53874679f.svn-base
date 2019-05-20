/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mat.matbom.entity;

import javax.validation.constraints.NotNull;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * mat_bom_tableEntity
 * @author 郑利
 * @version 2018-09-30
 */
public class MatBomTable extends DataEntity<MatBomTable> {
	
	private static final long serialVersionUID = 1L;
	private String matBomDetailId;		// mat_bom_detail_id 父类
	private Integer serial;		// serial
	private String mdsItemId;		// mds_item_id
	private String mdsItemCode;		//产品编号
	private String mdsItemName;	//产品名字
	private String mdsItemspecs;	//规格
	private String mdsItemmodelNum;	//型号
	private String itemCode;		//产品编号
	private String itemName;	//产品名字
	private String itemspecs;	//规格
	private String itemmodelNum;	//型号
	private Integer nedNum;		//数量
	public MatBomTable() {
		super();
	}

	public MatBomTable(String id){
		super(id);
	}



	public String getMatBomDetailId() {
		return matBomDetailId;
	}

	public void setMatBomDetailId(String matBomDetailId) {
		this.matBomDetailId = matBomDetailId;
	}
	
	@NotNull(message="serial不能为空")
	@ExcelField(title="serial", align=2, sort=2)
	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}
	
	@ExcelField(title="mds_item_id", align=2, sort=3)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}

	public String getMdsItemCode() {
		return mdsItemCode;
	}

	public void setMdsItemCode(String mdsItemCode) {
		this.mdsItemCode = mdsItemCode;
	}

	public String getMdsItemName() {
		return mdsItemName;
	}

	public void setMdsItemName(String mdsItemName) {
		this.mdsItemName = mdsItemName;
	}

	public String getMdsItemspecs() {
		return mdsItemspecs;
	}

	public void setMdsItemspecs(String mdsItemspecs) {
		this.mdsItemspecs = mdsItemspecs;
	}

	public String getMdsItemmodelNum() {
		return mdsItemmodelNum;
	}

	public void setMdsItemmodelNum(String mdsItemmodelNum) {
		this.mdsItemmodelNum = mdsItemmodelNum;
	}

	public Integer getNedNum() {
		return nedNum;
	}

	public void setNedNum(Integer nedNum) {
		this.nedNum = nedNum;
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

	public String getItemspecs() {
		return itemspecs;
	}

	public void setItemspecs(String itemspecs) {
		this.itemspecs = itemspecs;
	}

	public String getItemmodelNum() {
		return itemmodelNum;
	}

	public void setItemmodelNum(String itemmodelNum) {
		this.itemmodelNum = itemmodelNum;
	}


	
}