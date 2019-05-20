/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprocurementplan.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pt.common.utils.IdGen;
import com.pt.common.utils.excel.annotation.ExcelField;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectQuotaList;
import com.pt.modules.sys.entity.User;

/**
 * 采购计划申请Entity
 * @author cjx
 * @version 2018-09-24
 */
public class MdsProcurementPlan extends DataEntity<MdsProcurementPlan> {
	
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
	private String procurementWay;		// 采购方式(项目采购,批量采购
	private String fromId;		// 产品id或临时订单id
	private String sourceType;		// 来源类型('项目','临时订单','手动录入')
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	
	private String supplierId;	//供应商id
	private String supplierName;	//供应商名称
	
	private User buyer;		//采购员
	
	private Date beginRequiredDate;		// 开始 需求时间(查询)
	private Date endRequiredDate;		// 结束 需求时间(查询)
	
	private String particulars;	//产品名称或 临时订单号或 空
	
	private String supplierNo;		// 供应商编码
	private String shortName;		// 简称
	private String linkman;		// 联系人
	private String phone;		// 电话
	private String industry;		// 行业
	
	private String finishState;	//采购完成状态
	
	public MdsProcurementPlan() {
		super();
	}

	public MdsProcurementPlan(String id){
		super(id);
	}
	//前端保存采购数量和时间的字符串,分别复制到属性中
	public MdsProcurementPlan(String date,String n){
		String entityArray[] =date.split(":");
		this.id = entityArray[0];
		this.purchaseAmount = Integer.parseInt(entityArray[1]);
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd"); 
		try { 
	          this.requiredDate = ft.parse(entityArray[2]);
	      } catch (Exception e) { 
	          System.out.println("字符串未转换成日期 " + ft); 
	      }
	}
	public MdsProcurementPlan(MdsProjectQuotaList mdsProjectQuotaList){
		id = IdGen.uuid();
		mdsItemId = mdsProjectQuotaList.getItemId();
		classify = mdsProjectQuotaList.getClassify();
		itemCode = mdsProjectQuotaList.getItemCode();
		itemName = mdsProjectQuotaList.getItemName();
		normalField = mdsProjectQuotaList.getNormalField();
		modelnm = mdsProjectQuotaList.getModelNm();
		specs = mdsProjectQuotaList.getSpecs();
		purchaseAmount = mdsProjectQuotaList.getPurchaseAmount();
		requiredDate = mdsProjectQuotaList.getRequiredDate();
		state = "0";
		procurementWay = "项目采购";
		fromId = mdsProjectQuotaList.getSysMarketListId();
		sourceType = "项目录入";
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MdsProcurementPlan other = (MdsProcurementPlan) obj;
		if (itemCode == null) {
			if (other.itemCode != null)
				return false;
		} else if (!itemCode.equals(other.itemCode))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (mdsItemId == null) {
			if (other.mdsItemId != null)
				return false;
		} else if (!mdsItemId.equals(other.mdsItemId))
			return false;
		if (modelnm == null) {
			if (other.modelnm != null)
				return false;
		} else if (!modelnm.equals(other.modelnm))
			return false;
		if (normalField == null) {
			if (other.normalField != null)
				return false;
		} else if (!normalField.equals(other.normalField))
			return false;
		if (requiredDate == null) {
			if (other.requiredDate != null)
				return false;
		} else if (!requiredDate.equals(other.requiredDate))
			return false;
		if (specs == null) {
			if (other.specs != null)
				return false;
		} else if (!specs.equals(other.specs))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	public String getSupplierNo() {
		return supplierNo;
	}

	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}


	public String getFinishState() {
		return finishState;
	}

	public void setFinishState(String finishState) {
		this.finishState = finishState;
	}

		
}