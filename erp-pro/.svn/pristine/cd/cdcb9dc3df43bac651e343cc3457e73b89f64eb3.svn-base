/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.sys.sysmarketship.entity;

import javax.validation.constraints.NotNull;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 销售管理字表Entity
 * @author 郑利
 * @version 2018-09-04
 */
public class SysMarketShipList extends DataEntity<SysMarketShipList> {
	
	private static final long serialVersionUID = 1L;
	private String sysMarketId;
	private String mdsItemId;	// mds_item_id
	private Integer num;		// num
	
	
	
	private String itemSpecs;		// specs
	private String modelNum;
	private String itemCode;//物料号
	private String itemName;//物料名称
	private String deliverNum;//发货数
	private String deliverNums;//累计发货数
		private SysMarketShip sysMarket;
	
	public SysMarketShipList() {
		super();
	}

	public SysMarketShipList(String id){
		super(id);
	}

	
	public String getSysMarketId() {
		return sysMarketId;
	}

	public void setSysMarketId(String sysMarketId) {
		this.sysMarketId = sysMarketId;
	}

	@ExcelField(title="mds_item_id", align=2, sort=1)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@NotNull(message="num不能为空")
	@ExcelField(title="num", align=2, sort=2)
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	


	public String getItemSpecs() {
		return itemSpecs;
	}

	public void setItemSpecs(String itemSpecs) {
		this.itemSpecs = itemSpecs;
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

	public SysMarketShipList(SysMarketShip sysMarket){
		this.sysMarket = sysMarket;
	}

	public SysMarketShip getSysMarket() {
		return sysMarket;
	}

	public void setSysMarket(SysMarketShip sysMarket) {
		this.sysMarket = sysMarket;
	}

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public String getDeliverNum() {
		return deliverNum;
	}

	public void setDeliverNum(String deliverNum) {
		this.deliverNum = deliverNum;
	}

	public String getDeliverNums() {
		return deliverNums;
	}

	public void setDeliverNums(String deliverNums) {
		this.deliverNums = deliverNums;
	}



}