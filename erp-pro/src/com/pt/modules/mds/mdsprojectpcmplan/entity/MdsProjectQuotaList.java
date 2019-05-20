/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprojectpcmplan.entity;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.pt.core.persistence.DataEntity;

/**
 * 项目采购计划明细Entity
 * @author cjx
 * @version 2018-09-21
 */
public class MdsProjectQuotaList extends DataEntity<MdsProjectQuotaList> {
	
	private static final long serialVersionUID = 1L;
	private String sysMarketListId;		// 销售订单中产品ID
	private String itemId;		// 物料id
	private int amount;		// 需求数量
	private int purchaseAmount;		// 采购数量
	private Date requiredDate;		// 需求时间
	private String state;		// 状态(已添加,未添加)
	private String classify;		// 分类
	
	private String itemCode;	//物料编码
	private String itemName;	//物料名字
	private String normalField;	//标准号
	private String modelNm;	//型号
	private String specs;	//规格
	
	private int pcs;	//库存数量
	private int usable;	//可使用库存
	
	private String mdsQuotaDetailId;	//材料定额子表id
	public MdsProjectQuotaList() {
		super();
	}

	public MdsProjectQuotaList(String id){
		super(id);
	}

	public MdsProjectQuotaList(MdsProjectpcmplan mdsProjectpcmplan){
		this.sysMarketListId = mdsProjectpcmplan.getSysMarketListId();
	}

	public String getSysMarketListId() {
		return sysMarketListId;
	}

	public void setSysMarketListId(String sysMarketListId) {
		this.sysMarketListId = sysMarketListId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getModelNm() {
		return modelNm;
	}

	public void setModelNm(String modelNm) {
		this.modelNm = modelNm;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public int getPcs() {
		return pcs;
	}

	public void setPcs(int pcs) {
		this.pcs = pcs;
	}

	public int getUsable() {
		return usable;
	}

	public void setUsable(int usable) {
		this.usable = usable;
	}

	public String getMdsQuotaDetailId() {
		return mdsQuotaDetailId;
	}

	public void setMdsQuotaDetailId(String mdsQuotaDetailId) {
		this.mdsQuotaDetailId = mdsQuotaDetailId;
	}

	
	
}