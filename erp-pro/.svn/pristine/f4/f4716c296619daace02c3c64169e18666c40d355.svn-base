/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsprocurementplan.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pt.common.utils.IdGen;
import com.pt.common.utils.excel.annotation.ExcelField;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectQuotaList;
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectpcmplan;

/**
 * 统计查询类Entity
 * @author cjx
 * @version 2018-09-24
 */
public class CollectEntity extends DataEntity<CollectEntity> {
	
	private static final long serialVersionUID = 1L;
	private String pcmItemCode;		// 主页物料编码
	private String pcmItemName;		// 主页物料名称
	private Date pcmBeginRequiredDate;		// 主页开始 需求时间
	private Date pcmEndRequiredDate;		// 主页结束 需求时间
	private String pcmState;		// 主页状态(审批状态)
	private String pcmProcurementWay;	//主页采购方式
	
	private String itemCode;	//物料编码
	private String itemName;	//物料名称
	private Date beginRequiredDate;	//采购开始时间
	private Date endRequiredDate;	//采购结束时间
	private String state;		//状态
	
	public CollectEntity(){
		
	}
	public CollectEntity(MdsProcurementPlan mdsProcurementPlan){
		pcmItemCode = mdsProcurementPlan.getItemCode();
		pcmItemName = mdsProcurementPlan.getItemName();
		pcmBeginRequiredDate = mdsProcurementPlan.getBeginRequiredDate();
		pcmEndRequiredDate = mdsProcurementPlan.getEndRequiredDate();
		pcmState = mdsProcurementPlan.getState();
		pcmProcurementWay = mdsProcurementPlan.getProcurementWay();
	}

	public String getPcmItemCode() {
		return pcmItemCode;
	}
	public void setPcmItemCode(String pcmItemCode) {
		this.pcmItemCode = pcmItemCode;
	}
	public String getPcmItemName() {
		return pcmItemName;
	}
	public void setPcmItemName(String pcmItemName) {
		this.pcmItemName = pcmItemName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getPcmBeginRequiredDate() {
		return pcmBeginRequiredDate;
	}
	public void setPcmBeginRequiredDate(Date pcmBeginRequiredDate) {
		this.pcmBeginRequiredDate = pcmBeginRequiredDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getPcmEndRequiredDate() {
		return pcmEndRequiredDate;
	}
	public void setPcmEndRequiredDate(Date pcmEndRequiredDate) {
		this.pcmEndRequiredDate = pcmEndRequiredDate;
	}
	public String getPcmState() {
		return pcmState;
	}
	public void setPcmState(String pcmState) {
		this.pcmState = pcmState;
	}
	public String getPcmProcurementWay() {
		return pcmProcurementWay;
	}
	public void setPcmProcurementWay(String pcmProcurementWay) {
		this.pcmProcurementWay = pcmProcurementWay;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}