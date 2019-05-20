/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.purshchaselist.entiry;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 批量采购计划Entity
 * @author zjj
 * @version 2018-09-19
 */
public class MdsPurchaseList extends DataEntity<MdsPurchaseList> {
	
	private static final long serialVersionUID = 1L;
	private String createByid;		// 创建者
	private String updateByid;		// 更新者
	private String mdsItemId;		// 物料ID
	private int purchaseNumber;		// 采购数量
	private int demandNumber;		// 需求数量
	private Date demandDate;		// 需求时间
	private Long inventoryNumber;		// 库存数量
	private String state;		// 状态
	private String projectNumber;		// 项目编号
	private String temNumber;		// 临时计划申请单号
	private String purchaseState; //采购方式
	private String sourceType;  //来源类型
	private String marketId; 
	private String temPlanId; 
	private String remarks;
	
	private Date beginDemandDate;		// 开始 需求时间
	private Date endDemandDate;		// 结束 需求时间
	
	private String itemCode; //物料编码
	private String itemName;//物料名称
	private String normalField; //标准号
	private String modelNum; //型号
	private String specs; //规格
	private Date deputyAssessingDate;//副总审批时间
	private String mesId; //产品ID
	private String mesName; //产品名字
    private String classify; //分类
    private String summaryCode; //合同编码
    private String verifyBy;
	
	private Date assigndate; //下达令号时间
	private int currentOnhandQty;// 库存数
	
	public MdsPurchaseList() {
		super();
	}

	public MdsPurchaseList(String id){
		super(id);
	}

	@ExcelField(title="创建者", align=2, sort=1)
	public String getCreateByid() {
		return createByid;
	}

	public void setCreateByid(String createByid) {
		this.createByid = createByid;
	}
	
	@ExcelField(title="更新者", align=2, sort=3)
	public String getUpdateByid() {
		return updateByid;
	}

	public void setUpdateByid(String updateByid) {
		this.updateByid = updateByid;
	}
	
	@ExcelField(title="物料ID", align=2, sort=7)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="采购数量", align=2, sort=8)
	public int getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(int purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	
	@ExcelField(title="需求数量", align=2, sort=9)
	public int getDemandNumber() {
		return demandNumber;
	}

	public void setDemandNumber(int demandNumber) {
		this.demandNumber = demandNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="需求时间", align=2, sort=10)
	public Date getDemandDate() {
		return demandDate;
	}

	public void setDemandDate(Date demandDate) {
		this.demandDate = demandDate;
	}
	
	@ExcelField(title="库存数量", align=2, sort=11)
	public Long getInventoryNumber() {
		return inventoryNumber;
	}

	public void setInventoryNumber(Long inventoryNumber) {
		this.inventoryNumber = inventoryNumber;
	}
	
	@ExcelField(title="状态", align=2, sort=12)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@ExcelField(title="项目编号", align=2, sort=13)
	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	@ExcelField(title="临时计划申请单号", align=2, sort=14)
	public String getTemNumber() {
		return temNumber;
	}

	public void setTemNumber(String temNumber) {
		this.temNumber = temNumber;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBeginDemandDate() {
		return beginDemandDate;
	}

	public void setBeginDemandDate(Date beginDemandDate) {
		this.beginDemandDate = beginDemandDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEndDemandDate() {
		return endDemandDate;
	}

	public void setEndDemandDate(Date endDemandDate) {
		this.endDemandDate = endDemandDate;
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

	public Date getDeputyAssessingDate() {
		return deputyAssessingDate;
	}

	public void setDeputyAssessingDate(Date deputyAssessingDate) {
		this.deputyAssessingDate = deputyAssessingDate;
	}

	public Date getAssigndate() {
		return assigndate;
	}

	public void setAssigndate(Date assigndate) {
		this.assigndate = assigndate;
	}

	public String getPurchaseState() {
		return purchaseState;
	}

	public void setPurchaseState(String purchaseState) {
		this.purchaseState = purchaseState;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getTemPlanId() {
		return temPlanId;
	}

	public void setTemPlanId(String temPlanId) {
		this.temPlanId = temPlanId;
	}

	public String getMesId() {
		return mesId;
	}

	public void setMesId(String mesId) {
		this.mesId = mesId;
	}

	public String getMesName() {
		return mesName;
	}

	public void setMesName(String mesName) {
		this.mesName = mesName;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getSummaryCode() {
		return summaryCode;
	}

	public void setSummaryCode(String summaryCode) {
		this.summaryCode = summaryCode;
	}
    
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(String verifyBy) {
		this.verifyBy = verifyBy;
	}



	public int getCurrentOnhandQty() {
		return currentOnhandQty;
	}

	public void setCurrentOnhandQty(int currentOnhandQty) {
		this.currentOnhandQty = currentOnhandQty;
	}


	
	
}