/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mrp.mrpwofind.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 任务查询Entity
 * @author 孙会楠
 * @version 2018-06-07
 */
public class MrpWoFeeding extends DataEntity<MrpWoFeeding> {
	
	private static final long serialVersionUID = 1L;
	private String woNo;		// 工作订单号，编号规则为WO-YYMMAAA，即WO-,加上两位年的数字，两位月的数字和三位流水号
	private String woDateStr;		// 年月信息,采用YYYYMM方式，例如200710，200801
	private String yearStr;		// 
	private String monthStr;
	private String taskNo;		// 任务编号
	private String batchNo;     //该需求所属批次
	private String proMdsItemId;		// 产品型号，对应物料ID
	private String proMdsItemCode;      //物料代码
	private String mdsItemId;           //物料ID
	private String mdsItemCode;         //物料代码
	private String mdsItemName;         //物料名称
	private String itemTypeLable;       //
	private String routeShop;
	private Double reqQty;		// 需求数量，默认0
	private Double toEwoQty;		// 生成EWO数量
	private Double tlQty;
	private Date planStartDate;		// 计划开工日期
	private Date planEndDate;		// 计划完工日期
	private String mainOfficeId;		// 生产部门 
	private String mainOfficeName;		// 使用部门
	private String woStatus;		// 订单状态 ^ 0-未确认；1 未下达；2-已下达；3-已开工；4-已完工；
	private String woStatusLable;
	
	private String idStr;
	private String qtystr;
	
	public  MrpWoFeeding(){
		super();
	}
	
	public MrpWoFeeding(String id){
		super(id);
	}
	
	@ExcelField(title="工作订单号，编号规则为WO-YYMMAAA，即WO-,加上两位年的数字，两位月的数字和三位流水号", align=2, sort=1)
	public String getWoNo() {
		return woNo;
	}

	public void setWoNo(String woNo) {
		this.woNo = woNo;
	}
	
	@ExcelField(title="年月信息,采用YYYYMM方式，例如200710，200801", align=2, sort=2)
	public String getWoDateStr() {
		return woDateStr;
	}

	public void setWoDateStr(String woDateStr) {
		this.woDateStr = woDateStr;
	}
	
	@ExcelField(title="year_str", align=2, sort=3)
	public String getYearStr() {
		return yearStr;
	}

	public void setYearStr(String yearStr) {
		this.yearStr = yearStr;
	}

	@ExcelField(title="month_str", align=2, sort=4)
	public String getMonthStr() {
		return monthStr;
	}

	public void setMonthStr(String monthStr) {
		this.monthStr = monthStr;
	}
	
	@ExcelField(title="任务编号", align=2, sort=5)
	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}
	
	@ExcelField(title="该需求需求所属批次", align=2, sort=6)
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@ExcelField(title="产品型号，对应物料ID", align=2, sort=7)
	public String getProMdsItemId() {
		return proMdsItemId;
	}

	public void setProMdsItemId(String proMdsItemId) {
		this.proMdsItemId = proMdsItemId;
	}
	
	@ExcelField(title="物料代码", align=2, sort=8)
	public String getProMdsItemCode() {
		return proMdsItemCode;
	}

	public void setProMdsItemCode(String proMdsItemCode) {
		this.proMdsItemCode = proMdsItemCode;
	}
	
	@ExcelField(title="物料ID", align=2, sort=9)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="物料代码", align=2, sort=10)
	public String getMdsItemCode() {
		return mdsItemCode;
	}

	public void setMdsItemCode(String mdsItemCode) {
		this.mdsItemCode = mdsItemCode;
	}

	@ExcelField(title="物料名称", align=2, sort=11)
	public String getMdsItemName() {
		return mdsItemName;
	}

	public void setMdsItemName(String mdsItemName) {
		this.mdsItemName = mdsItemName;
	}

	@ExcelField(title="item_type_lable", align=2, sort=12)
	public String getItemTypeLable() {
		return itemTypeLable;
	}

	public void setItemTypeLable(String itemTypeLable) {
		this.itemTypeLable = itemTypeLable;
	}

	@ExcelField(title="route_shop", align=2, sort=13)
	public String getRouteShop() {
		return routeShop;
	}

	public void setRouteShop(String routeShop) {
		this.routeShop = routeShop;
	}

	public Double getReqQty() {
		return reqQty;
	}

	public void setReqQty(Double reqQty) {
		this.reqQty = reqQty;
	}

	public Double getToEwoQty() {
		return toEwoQty;
	}

	public void setToEwoQty(Double toEwoQty) {
		this.toEwoQty = toEwoQty;
	}

	public Double getTlQty() {
		return tlQty;
	}

	public void setTlQty(Double tlQty) {
		this.tlQty = tlQty;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="计划开工日期", align=2, sort=17)
	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="计划完工日期", align=2, sort=18)
	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	
	
	@ExcelField(title="生产部门 ", align=2, sort=19)
	public String getMainOfficeId() {
		return mainOfficeId;
	}

	public void setMainOfficeId(String mainOfficeId) {
		this.mainOfficeId = mainOfficeId;
	}
	
	@ExcelField(title="生产部门名称 ", align=2, sort=20)
	public String getMainOfficeName() {
		return mainOfficeName;
	}

	public void setMainOfficeName(String mainOfficeName) {
		this.mainOfficeName = mainOfficeName;
	}
	
	@ExcelField(title="订单状态 ^ 0-未确认；1 未下达；2-已下达；3-已开工；4-已完工；", align=2, sort=21)
	public String getWoStatus() {
		return woStatus;
	}

	public void setWoStatus(String woStatus) {
		this.woStatus = woStatus;
	}
	
	@ExcelField(title="wo_status_lable", align=2, sort=22)
	public String getWoStatusLable() {
		return woStatusLable;
	}

	public void setWoStatusLable(String woStatusLable) {
		this.woStatusLable = woStatusLable;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public String getQtystr() {
		return qtystr;
	}

	public void setQtystr(String qtystr) {
		this.qtystr = qtystr;
	}

}