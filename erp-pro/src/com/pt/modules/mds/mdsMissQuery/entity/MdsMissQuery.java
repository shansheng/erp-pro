package com.pt.modules.mds.mdsMissQuery.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pt.core.persistence.DataEntity;

public class MdsMissQuery extends DataEntity<MdsMissQuery>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contractNo; 		// 合同编号
	private String mesCode;		// 产品编号
	private String mesName;		// 产品名字
	private int amount;	//数量
	private Date deliveryDate; 		// 交货日期
	private Date purchaseStartDate;	// 采购开始时间
	private Date purchaseEndDate;	// 采购结束时间
	private String fromId; 
	private String itemCode; //物料编码
	private String itemName; //物料名称
	private String purchaseAmount; //采购数量
	private String arrivalnum; //到货数量
	private String missQueryNum; //缺件数量
	private String normalField; //标准号
	private String modelnm;//型号
	private String specs; //规格
	private Date requiredDate; //需求时间
	
	private Date beginRequiredDate;		// 开始 需求时间
	private Date endRequiredDate;		// 结束 需求时间
	
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getMesCode() {
		return mesCode;
	}
	public void setMesCode(String mesCode) {
		this.mesCode = mesCode;
	}
	public String getMesName() {
		return mesName;
	}
	public void setMesName(String mesName) {
		this.mesName = mesName;
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
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
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
	public String getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public String getArrivalnum() {
		return arrivalnum;
	}
	public void setArrivalnum(String arrivalnum) {
		this.arrivalnum = arrivalnum;
	}
	public String getMissQueryNum() {
		return missQueryNum;
	}
	public void setMissQueryNum(String missQueryNum) {
		this.missQueryNum = missQueryNum;
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
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBeginRequiredDate() {
		return beginRequiredDate;
	}
	public void setBeginRequiredDate(Date beginRequiredDate) {
		this.beginRequiredDate = beginRequiredDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEndRequiredDate() {
		return endRequiredDate;
	}
	public void setEndRequiredDate(Date endRequiredDate) {
		this.endRequiredDate = endRequiredDate;
	}
	
	
	
	
}
