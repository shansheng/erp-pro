/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.work.workorderretur.entity;

import java.util.Date;


import com.pt.core.persistence.DataEntity;
import com.pt.modules.work.workorderson.entity.WorkOrderSon;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 工作计划下达Entity
 * @author 郑利
 * @version 2018-10-13
 */
public class WorkOrderReturDetail extends DataEntity<WorkOrderReturDetail> {
	
	private static final long serialVersionUID = 1L;
	private String mdsItemId;		// mds_item_id
	private String modelNum;		// model_num
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private String classify;		// classify
	private WorkOrderReturDetail parent;		// parent_id
	private String parentIds;		// parent_ids
	private String name;		// name
	private String sort;		// sort
	private String matBomId;		// mat_bom_id
	private String workOrderId;		// work_order_id
	private String workOrderSonId;
	private Date planStartDate;
	private Date planEndDate;
	private String mdsItemName;
	private String finishState;		// 完成状态
	private String finishPlan;		// 完成进度
	private Date relBeginDate;		// rel_begin_date
	private Date relEndDate;		// rel_end_date
	

	public WorkOrderReturDetail getParent() {
		return parent;
	}
	public void setParent(WorkOrderReturDetail parent) {
		this.parent = parent;
	}

	private WorkOrderSon workOrderSon;
	public WorkOrderReturDetail() {
		super();
	}
	public WorkOrderReturDetail(WorkOrderSon workOrderSon){
		this.workOrderSon = workOrderSon;
	}
	public WorkOrderSon getWorkOrderSon() {
		return workOrderSon;
	}

	public void setWorkOrderSon(WorkOrderSon workOrderSon) {
		this.workOrderSon = workOrderSon;
	}

	public WorkOrderReturDetail(String id){
		super(id);
	}

	@ExcelField(title="mds_item_id", align=2, sort=1)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="model_num", align=2, sort=2)
	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}
	
	@ExcelField(title="column2", align=2, sort=3)
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	
	@ExcelField(title="column3", align=2, sort=4)
	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	@ExcelField(title="column4", align=2, sort=5)
	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	@ExcelField(title="column5", align=2, sort=6)
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	
	@ExcelField(title="classify", align=2, sort=10)
	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}
	

	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";

	}
	
	@ExcelField(title="parent_ids", align=2, sort=12)
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@ExcelField(title="name", align=2, sort=13)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ExcelField(title="sort", align=2, sort=14)
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@ExcelField(title="mat_bom_id", align=2, sort=15)
	public String getMatBomId() {
		return matBomId;
	}

	public void setMatBomId(String matBomId) {
		this.matBomId = matBomId;
	}
	
	@ExcelField(title="work_order_id", align=2, sort=16)
	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getWorkOrderSonId() {
		return workOrderSonId;
	}

	public void setWorkOrderSonId(String workOrderSonId) {
		this.workOrderSonId = workOrderSonId;
	}

	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	public String getMdsItemName() {
		return mdsItemName;
	}

	public void setMdsItemName(String mdsItemName) {
		this.mdsItemName = mdsItemName;
	}
	public String getFinishState() {
		return finishState;
	}
	public void setFinishState(String finishState) {
		this.finishState = finishState;
	}
	public String getFinishPlan() {
		return finishPlan;
	}
	public void setFinishPlan(String finishPlan) {
		this.finishPlan = finishPlan;
	}
	public Date getRelBeginDate() {
		return relBeginDate;
	}
	public void setRelBeginDate(Date relBeginDate) {
		this.relBeginDate = relBeginDate;
	}
	public Date getRelEndDate() {
		return relEndDate;
	}
	public void setRelEndDate(Date relEndDate) {
		this.relEndDate = relEndDate;
	}



	
}