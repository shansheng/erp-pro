/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprocurementplanfinish.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.pt.common.utils.excel.annotation.ExcelField;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.sys.entity.User;

/**
 * 采购完成状态Entity
 * @author cjx
 * @version 2018-09-24
 */
public class MdsProcurementPlanFinish extends DataEntity<MdsProcurementPlanFinish> {
	
	private static final long serialVersionUID = 1L;
	private String mdsItemId;		//物料id
	private String classify;		// 分类
	private String itemCode;		// 物料编码
	private String itemName;		// 物料名称
	private String normalField;		// 标准号
	private String modelnm;		// 型号
	private String specs;		// 规格
	private int purchaseAmount;		// 采购数量
	private int arrivalnum;			// 已到货数量
	private Date requiredDate;		// 需求时间
	private String supplierId;		//供应商id
	private String supplierName;	//供应商名称
	private String finishState;		// 状态(审批状态)
	private String procurementWay;	// 采购方式(项目采购,批量采购)
	private String fromId;		// 合同id或临时订单id
	private String sourceType;		// 来源类型('项目','临时订单','手动录入')
	private String particulars;	//产品名称或 临时订单号或 空
	
	private User buyer;		//采购员
	
	private Date beginRequiredDate;		// 开始 需求时间(查询)
	private Date endRequiredDate;		// 结束 需求时间(查询)
	
	private List<MdsPcmPlanFinishList> mdspcmplanfinishlist = Lists.newArrayList();		// 子表列表
	
	public MdsProcurementPlanFinish() {
		super();
	}

	public MdsProcurementPlanFinish(String id){
		super(id);
	}

	@ExcelField(title="物料id", align=2, sort=1)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="分类", align=2, sort=2)
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	@ExcelField(title="物料编码", align=2, sort=3)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@ExcelField(title="物料名称", align=2, sort=4)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@ExcelField(title="标准号", align=2, sort=5)
	public String getNormalField() {
		return normalField;
	}

	public void setNormalField(String normalField) {
		this.normalField = normalField;
	}
	
	@ExcelField(title="型号", align=2, sort=6)
	public String getModelnm() {
		return modelnm;
	}

	public void setModelnm(String modelnm) {
		this.modelnm = modelnm;
	}
	
	@ExcelField(title="规格", align=2, sort=7)
	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	@ExcelField(title="采购数量", align=2, sort=8)
	public int getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="需求时间", align=2, sort=9)
	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	
	public String getFinishState() {
		return finishState;
	}

	public void setFinishState(String finishState) {
		this.finishState = finishState;
	}

	@ExcelField(title="采购方式", align=2, sort=11)
	public String getProcurementWay() {
		return procurementWay;
	}

	public void setProcurementWay(String procurementWay) {
		this.procurementWay = procurementWay;
	}
	
	@ExcelField(title="产品id或临时订单id", align=2, sort=12)
	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
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

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public Integer getArrivalnum() {
		return arrivalnum;
	}

	public void setArrivalnum(Integer arrivalnum) {
		this.arrivalnum = arrivalnum;
	}

	public List<MdsPcmPlanFinishList> getMdspcmplanfinishlist() {
		return mdspcmplanfinishlist;
	}

	public void setMdspcmplanfinishlist(List<MdsPcmPlanFinishList> mdspcmplanfinishlist) {
		this.mdspcmplanfinishlist = mdspcmplanfinishlist;
	}

	public void setArrivalnum(int arrivalnum) {
		this.arrivalnum = arrivalnum;
	}

	

		
}