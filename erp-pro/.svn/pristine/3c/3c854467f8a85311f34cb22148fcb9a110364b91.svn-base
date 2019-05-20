/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprojectpcmplan.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.mds.mdsquota.entity.MdsQuota;

/**
 * 销售管理Entity
 * 
 * @author cjx
 * @version 2018-09-21
 */
public class MdsProjectpcmplan extends DataEntity<MdsProjectpcmplan> {

	private static final long serialVersionUID = 1L;
	private String mdsQuotaId;		// 材料定额ID
	private String sysMarketListId;		// 销售订单产品ID
	
	private String contractNo; 		// 合同编号
	private String mdsItemCode;		// 产品编号
	private String mdsItemName;		// 产品名字
	private int amount;	//数量
	private Date deliveryDate; 		// 交货日期
	private String principalName; 	// 负责人
	//private Date assignDate; 		// 下达令号时间
	private Date purchaseStartDate;	// 采购开始时间
	private Date purchaseEndDate;	// 采购结束时间
	private String contractClass; 	// 订单类型
	
	private String  mdsItemId;	//物料ID
	private Date purchaseStartDateEnd; //查询中采购开始时间结束日期
	private Date purchaseEndDateStart; //查询中采购结束时间开始日期
	
	private String  state;	//状态
	
	private List<MdsProjectQuotaList> mdsProjectQuotaList = Lists.newArrayList();	//物料列表
	public MdsProjectpcmplan() {
		super();
	}

	public MdsProjectpcmplan(String id) {
		super(id);
	}
	
	public MdsProjectpcmplan(MdsQuota mdsQuota) {
		sysMarketListId = mdsQuota.getSysMarketListId();
		state = "0";
	}

	public String getMdsQuotaId() {
		return mdsQuotaId;
	}

	public void setMdsQuotaId(String mdsQuotaId) {
		this.mdsQuotaId = mdsQuotaId;
	}

	public String getSysMarketListId() {
		return sysMarketListId;
	}

	public void setSysMarketListId(String sysMarketListId) {
		this.sysMarketListId = sysMarketListId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getPurchaseStartDate() {
		return purchaseStartDate;
	}

	public void setPurchaseStartDate(Date purchaseStartDate) {
		this.purchaseStartDate = purchaseStartDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getPurchaseEndDate() {
		return purchaseEndDate;
	}

	public void setPurchaseEndDate(Date purchaseEndDate) {
		this.purchaseEndDate = purchaseEndDate;
	}

	public String getContractClass() {
		return contractClass;
	}

	public void setContractClass(String contractClass) {
		this.contractClass = contractClass;
	}

	public List<MdsProjectQuotaList> getMdsProjectQuotaList() {
		return mdsProjectQuotaList;
	}

	public void setMdsProjectQuotaList(List<MdsProjectQuotaList> mdsProjectQuotaList) {
		this.mdsProjectQuotaList = mdsProjectQuotaList;
	}

	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}

	public Date getPurchaseStartDateEnd() {
		return purchaseStartDateEnd;
	}

	public void setPurchaseStartDateEnd(Date purchaseStartDateEnd) {
		this.purchaseStartDateEnd = purchaseStartDateEnd;
	}

	public Date getPurchaseEndDateStart() {
		return purchaseEndDateStart;
	}

	public void setPurchaseEndDateStart(Date purchaseEndDateStart) {
		this.purchaseEndDateStart = purchaseEndDateStart;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
		
}