/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mes.selectmesewo.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 生产计划查询Entity
 * @author 郑利
 * @version 2018-06-28
 */
public class MesEwoV extends DataEntity<MesEwoV> {
	
	private static final long serialVersionUID = 1L;
	private String ewoNo;		// 执行工作订单号
	private String taskNo;		// 任务编号
	private String batchNo;		// 批次号，该需求所属批次号
	private String proMdsItemId;		// 生产物料对应的产品型号
	private String proMdsItemCode;		// 物料代码
	private String mdsItemId;		// 物料ID,用以标识一个制造对象项目的代码。物料包括产品、零件、各级组合件和物。
	private String mdsItemCode;		// 物料代码
	private String mdsItemName;		// 物料名称
	private String itemTypeLabel;		// item_type_label
	private String routeShop;		// route_shop
	private String reqQty;		// 需求数量，默认0
	private Date planStartDate;		// 计划开工日期
	private Date planEndDate;		// 计划完工日期
	private Date actualStartDate;		// 实际开工日期
	private Date actualEndDate;		// 实际完工日期
	private String mainOfficeId;		// 主制部门
	private String mainOfficeName;		// 名称
	private String ewoStatus;		// 订单状态 0未下达，1已下达
	private String ewoStatusLabel;		// ewo_status_label
	private String workFlowChartNo;		// 流卡号
	private String mesRouteId;		// 工艺路线ID
	private String seqInfo;		// seq_info
	
	public MesEwoV() {
		super();
	}

	public MesEwoV(String id){
		super(id);
	}

	@ExcelField(title="执行工作订单号", align=2, sort=1)
	public String getEwoNo() {
		return ewoNo;
	}

	public void setEwoNo(String ewoNo) {
		this.ewoNo = ewoNo;
	}
	
	@ExcelField(title="任务编号", align=2, sort=2)
	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	@ExcelField(title="批次号，该需求所属批次号", align=2, sort=3)
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@ExcelField(title="生产物料对应的产品型号", align=2, sort=4)
	public String getProMdsItemId() {
		return proMdsItemId;
	}

	public void setProMdsItemId(String proMdsItemId) {
		this.proMdsItemId = proMdsItemId;
	}
	
	@ExcelField(title="物料代码", align=2, sort=5)
	public String getProMdsItemCode() {
		return proMdsItemCode;
	}

	public void setProMdsItemCode(String proMdsItemCode) {
		this.proMdsItemCode = proMdsItemCode;
	}
	
	@ExcelField(title="物料ID,用以标识一个制造对象项目的代码。物料包括产品、零件、各级组合件和物。", align=2, sort=6)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="物料代码", align=2, sort=7)
	public String getMdsItemCode() {
		return mdsItemCode;
	}

	public void setMdsItemCode(String mdsItemCode) {
		this.mdsItemCode = mdsItemCode;
	}
	
	@ExcelField(title="物料名称", align=2, sort=8)
	public String getMdsItemName() {
		return mdsItemName;
	}

	public void setMdsItemName(String mdsItemName) {
		this.mdsItemName = mdsItemName;
	}
	
	@ExcelField(title="item_type_label", align=2, sort=9)
	public String getItemTypeLabel() {
		return itemTypeLabel;
	}

	public void setItemTypeLabel(String itemTypeLabel) {
		this.itemTypeLabel = itemTypeLabel;
	}
	
	@ExcelField(title="route_shop", align=2, sort=10)
	public String getRouteShop() {
		return routeShop;
	}

	public void setRouteShop(String routeShop) {
		this.routeShop = routeShop;
	}
	
	@ExcelField(title="需求数量，默认0", align=2, sort=11)
	public String getReqQty() {
		return reqQty;
	}

	public void setReqQty(String reqQty) {
		this.reqQty = reqQty;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="计划开工日期", align=2, sort=12)
	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="计划完工日期", align=2, sort=13)
	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="实际开工日期", align=2, sort=14)
	public Date getActualStartDate() {
		return actualStartDate;
	}

	public void setActualStartDate(Date actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="实际完工日期", align=2, sort=15)
	public Date getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(Date actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	
	@ExcelField(title="主制部门", align=2, sort=16)
	public String getMainOfficeId() {
		return mainOfficeId;
	}

	public void setMainOfficeId(String mainOfficeId) {
		this.mainOfficeId = mainOfficeId;
	}
	
	@ExcelField(title="名称", align=2, sort=17)
	public String getMainOfficeName() {
		return mainOfficeName;
	}

	public void setMainOfficeName(String mainOfficeName) {
		this.mainOfficeName = mainOfficeName;
	}
	
	@ExcelField(title="订单状态 0未下达，1已下达", dictType="ewo_status", align=2, sort=18)
	public String getEwoStatus() {
		return ewoStatus;
	}

	public void setEwoStatus(String ewoStatus) {
		this.ewoStatus = ewoStatus;
	}
	
	@ExcelField(title="ewo_status_label", align=2, sort=19)
	public String getEwoStatusLabel() {
		return ewoStatusLabel;
	}

	public void setEwoStatusLabel(String ewoStatusLabel) {
		this.ewoStatusLabel = ewoStatusLabel;
	}
	
	@ExcelField(title="流卡号", align=2, sort=20)
	public String getWorkFlowChartNo() {
		return workFlowChartNo;
	}

	public void setWorkFlowChartNo(String workFlowChartNo) {
		this.workFlowChartNo = workFlowChartNo;
	}
	
	@ExcelField(title="工艺路线ID", align=2, sort=21)
	public String getMesRouteId() {
		return mesRouteId;
	}

	public void setMesRouteId(String mesRouteId) {
		this.mesRouteId = mesRouteId;
	}
	
	@ExcelField(title="seq_info", align=2, sort=22)
	public String getSeqInfo() {
		return seqInfo;
	}

	public void setSeqInfo(String seqInfo) {
		this.seqInfo = seqInfo;
	}
	
}