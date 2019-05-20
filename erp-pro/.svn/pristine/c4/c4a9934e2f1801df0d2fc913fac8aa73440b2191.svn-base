/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsarrivalaffirm.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.pt.common.utils.excel.annotation.ExcelField;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.mds.mdsarrivalcheckout.entity.MdsArrivalCheckout;

/**
 * 到货确认Entity
 * @author cjx
 * @version 2018-10-10
 */
public class MdsArrivalAffirm extends DataEntity<MdsArrivalAffirm> {
	
	private static final long serialVersionUID = 1L;
	private String mdsItemId;		// 物料id
	private String classify;		// 分类
	private String itemCode;		// 物料编码
	private String itemName;		// 物料名称
	private String normalField;		// 标准号
	private String modelnm;		// 型号
	private String specs;		// 规格
	private int purchaseAmount;		// 采购数量
	private Date requiredDate;		// 需求时间
	private String state;		// 状态(审批状态)
	private String procurementWay;		// 采购方式
	private String fromId;		// 产品id或临时订单id
	private String sourceType;		// 来源类型('项目','临时订单','手动录入')
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private String supplierId;		// 供应商id
	private String supplierName;		// 供应商名字
	private int arrivalnum;		// 已到货数量
	private String arrivalBy;		// 到货操作人
	private Date arrivalDate;		// 到货确认时间
	private Date beginRequiredDate;		// 开始 需求时间
	private Date endRequiredDate;		// 结束 需求时间
	
	private int arrivalnumNew;		//本次到货数
	private String particulars;	//产品名称或 临时订单号或 空
	
	private String finishState;	//采购完成状态
	
	private List<MdsArrivalCheckout> mdsArrivalCheckoutList = Lists.newArrayList();		// 子表列表
	
	public MdsArrivalAffirm() {
		super();
	}

	public MdsArrivalAffirm(String id){
		super(id);
	}
	//前端保存的到货数
	public MdsArrivalAffirm(String date,String n){
		String entityArray[] =date.split(":");
		this.id = entityArray[0];
		this.arrivalnumNew = Integer.parseInt(entityArray[1]);
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
	
	@ExcelField(title="状态(审批状态)", align=2, sort=10)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@ExcelField(title="采购方式", dictType="", align=2, sort=11)
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
	
	@ExcelField(title="来源类型('项目','临时订单','手动录入')", align=2, sort=13)
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	@ExcelField(title="column2", align=2, sort=14)
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	
	@ExcelField(title="column3", align=2, sort=15)
	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	@ExcelField(title="column4", align=2, sort=16)
	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	@ExcelField(title="column5", align=2, sort=17)
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	
	@ExcelField(title="供应商id", align=2, sort=22)
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	@ExcelField(title="供应商名字", align=2, sort=23)
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	@ExcelField(title="已到货数量", align=2, sort=24)
	public int getArrivalnum() {
		return arrivalnum;
	}

	public void setArrivalnum(int arrivalnum) {
		this.arrivalnum = arrivalnum;
	}
	
	@ExcelField(title="到货操作人", align=2, sort=25)
	public String getArrivalBy() {
		return arrivalBy;
	}

	public void setArrivalBy(String arrivalBy) {
		this.arrivalBy = arrivalBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="到货确认时间", align=2, sort=26)
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginRequiredDate() {
		return beginRequiredDate;
	}

	public void setBeginRequiredDate(Date beginRequiredDate) {
		this.beginRequiredDate = beginRequiredDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndRequiredDate() {
		return endRequiredDate;
	}

	public void setEndRequiredDate(Date endRequiredDate) {
		this.endRequiredDate = endRequiredDate;
	}

	public int getArrivalnumNew() {
		return arrivalnumNew;
	}

	public void setArrivalnumNew(int arrivalnumNew) {
		this.arrivalnumNew = arrivalnumNew;
	}

	public String getParticulars() {
		return particulars;
	}

	public void setParticulars(String particulars) {
		this.particulars = particulars;
	}

	public List<MdsArrivalCheckout> getMdsArrivalCheckoutList() {
		return mdsArrivalCheckoutList;
	}

	public void setMdsArrivalCheckoutList(List<MdsArrivalCheckout> mdsArrivalCheckoutList) {
		this.mdsArrivalCheckoutList = mdsArrivalCheckoutList;
	}

	public String getFinishState() {
		return finishState;
	}

	public void setFinishState(String finishState) {
		this.finishState = finishState;
	}
		
	
}