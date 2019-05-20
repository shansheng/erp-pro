/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workordersynthesize.entity;


import com.pt.core.persistence.DataEntity;
import com.pt.modules.work.workorderdetail.entity.WorkOrderDetail;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 工作计划Entity
 * @author 郑利
 * @version 2018-10-13
 */
public class WorkOrderSynthesize extends DataEntity<WorkOrderSynthesize> {
	
	private static final long serialVersionUID = 1L;
	private String sysMarketId;		// sys_market_id
	private String orderState;		// order_state
	private String item01;		// item01
	private String item02;		// item02
	private String item03;		// item03
	private String item04;		// item04
	private String remake;		// remake
	private String sysMarketListId;		// sys_market_list_id
	private String mdsItemId;		// mds_item_id
	private String workOrderId;
	private String mdsItemName;
	private String clientName;
	private String mdsItemCode;
	private String mdsItemspecs;	//规格
	private String mdsItemmodelNum;	//型号
	private String finishSche;    //产品完成进度
	private int purchaseAmount; //采购数据
	private int arrivalnum;   //已到货数量
	private String arrivalState; //到货情况
	//合同相关
	private String clientId;
	private String contractNo; // 合同编号
	private Date contractDate; // 合同签订时间
	private Date deliveryDate; // 交货日期
	private String contractStatus; // 合同状态
	
	
	//材料定额相关
	private String mdsQuotaState; //材料定额状态
	private Date quotaDate;  //材料定额定版时间

	//bom相关
	private String bomState; //bom状态
	private Date bomDate;	//bom定版时间
	
	//采购计划相关
	private String mdsProcurementPlanState;//采购计划状态
	private Date mdsProcurementPlanDate; //采购下达时间
	
	private String shipments;//是否发货
	private Date shipmentsDate;//发货时间
	
	
	
	private List<WorkOrderDetail> workOrderDetailList = Lists.newArrayList();		// 子表列表
	public WorkOrderSynthesize() {
		super();
	}

	public WorkOrderSynthesize(String id){
		super(id);
	}

	@ExcelField(title="sys_market_id", align=2, sort=1)
	public String getSysMarketId() {
		return sysMarketId;
	}

	public void setSysMarketId(String sysMarketId) {
		this.sysMarketId = sysMarketId;
	}
	
	@ExcelField(title="order_state", align=2, sort=2)
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
	
	@ExcelField(title="sys_market_list_id", align=2, sort=12)
	public String getSysMarketListId() {
		return sysMarketListId;
	}

	public void setSysMarketListId(String sysMarketListId) {
		this.sysMarketListId = sysMarketListId;
	}
	
	@ExcelField(title="mds_item_id", align=2, sort=13)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}

	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
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
	public List<WorkOrderDetail> getWorkOrderDetailList() {
		return workOrderDetailList;
	}

	public void setWorkOrderDetailList(List<WorkOrderDetail> workOrderDetailList) {
		List<WorkOrderDetail> WorkOrderDetailList = workOrderDetailList;
	}

	public String getFinishSche() {
		return finishSche;
	}

	public void setFinishSche(String finishSche) {
		this.finishSche = finishSche;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getMdsQuotaState() {
		return mdsQuotaState;
	}

	public void setMdsQuotaState(String mdsQuotaState) {
		this.mdsQuotaState = mdsQuotaState;
	}



	public String getBomState() {
		return bomState;
	}

	public void setBomState(String bomState) {
		this.bomState = bomState;
	}

	public Date getQuotaDate() {
		return quotaDate;
	}

	public void setQuotaDate(Date quotaDate) {
		this.quotaDate = quotaDate;
	}

	public Date getBomDate() {
		return bomDate;
	}

	public void setBomDate(Date bomDate) {
		this.bomDate = bomDate;
	}

	public String getMdsProcurementPlanState() {
		return mdsProcurementPlanState;
	}

	public void setMdsProcurementPlanState(String mdsProcurementPlanState) {
		this.mdsProcurementPlanState = mdsProcurementPlanState;
	}

	public Date getMdsProcurementPlanDate() {
		return mdsProcurementPlanDate;
	}

	public void setMdsProcurementPlanDate(Date mdsProcurementPlanDate) {
		this.mdsProcurementPlanDate = mdsProcurementPlanDate;
	}

	public String getShipments() {
		return shipments;
	}

	public void setShipments(String shipments) {
		this.shipments = shipments;
	}

	public Date getShipmentsDate() {
		return shipmentsDate;
	}

	public void setShipmentsDate(Date shipmentsDate) {
		this.shipmentsDate = shipmentsDate;
	}


	public String getClientId() {
		return clientId;
	}

	public int getPurchaseAmount() {
		return purchaseAmount;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getArrivalState() {
		return arrivalState;
	}

	public void setArrivalState(String arrivalState) {
		this.arrivalState = arrivalState;
	}



	public void setPurchaseAmount(int purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}

	public int getArrivalnum() {
		return arrivalnum;
	}

	public void setArrivalnum(int arrivalnum) {
		this.arrivalnum = arrivalnum;
	}





	
	
}