/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorder.entity;


import com.pt.core.persistence.DataEntity;

import java.util.Date;

import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 工作订单下达Entity
 * @author 郑利
 * @version 2018-10-09
 */
public class WorkOrder extends DataEntity<WorkOrder> {
	
	private static final long serialVersionUID = 1L;
	private String sysMarketId;		// sys_market_id
	private String sysMarketListId;
	private String mdsItemId;
	private String orderState;		// 工作订单下达状态
	private String item01;		// item01
	private String item02;		// item02
	private String item03;		// item03
	private String item04;		// item04
	private String remake;		// remake
	private String mdsItemName;
	private String clientId;
	private String clientName;
	private String mdsItemCode;
	private String mdsItemspecs;	//规格
	private String mdsItemmodelNum;	//型号
	private Integer nedNum;		//数量
	private Date productionStartDate;		// 生产开始时间
	private Date productionEndDate;		// 生产结束时间
	private String lookIs; //是否显示
	public WorkOrder() {
		super();
	}

	public WorkOrder(String id){
		super(id);
	}

	@ExcelField(title="sys_market_id", align=2, sort=1)
	public String getSysMarketId() {
		return sysMarketId;
	}

	public void setSysMarketId(String sysMarketId) {
		this.sysMarketId = sysMarketId;
	}
	
	@ExcelField(title="工作订单下达状态", align=2, sort=2)
	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	@ExcelField(title="item01", align=2, sort=3)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="item02", align=2, sort=4)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="item03", align=2, sort=5)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="item04", align=2, sort=6)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="remake", align=2, sort=11)
	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getMdsItemName() {
		return mdsItemName;
	}

	public void setMdsItemName(String mdsItemName) {
		this.mdsItemName = mdsItemName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMdsItemCode() {
		return mdsItemCode;
	}

	public void setMdsItemCode(String mdsItemCode) {
		this.mdsItemCode = mdsItemCode;
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

	public Date getProductionStartDate() {
		return productionStartDate;
	}

	public void setProductionStartDate(Date productionStartDate) {
		this.productionStartDate = productionStartDate;
	}

	public Date getProductionEndDate() {
		return productionEndDate;
	}

	public void setProductionEndDate(Date productionEndDate) {
		this.productionEndDate = productionEndDate;
	}

	public String getSysMarketListId() {
		return sysMarketListId;
	}

	public void setSysMarketListId(String sysMarketListId) {
		this.sysMarketListId = sysMarketListId;
	}

	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getLookIs() {
		return lookIs;
	}

	public void setLookIs(String lookIs) {
		this.lookIs = lookIs;
	}
	
	
	
}