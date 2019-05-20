/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvinbill.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 入库申请单子表Entity
 * @author 龚志强
 * @version 2018-05-15
 */
public class WmsInvInBillList extends DataEntity<WmsInvInBillList> {
	
	private static final long serialVersionUID = 1L;
	private WmsInvInBill wmsInvInBill;		// 单子主表 父类
	private String wmsLocatorId;		// 库位id
	private String mdsItemId;		// 物料ID
	private String qualityNo;		// 质量编号
	private String supplyRegularNo;		// 供方合格证号
	private String applyQty;		// 申请数量
	private String inQty;		// 入库数量
	private String inSysUserId;		// 入库人
	private Date inDate;		// 入库日期
	private String refuseReason;		// 拒绝原因
	private String note;		// 备注
	private String status;		// 单据状态 ^0、已生成；5、提交申请；10、审核通过
	
	//表外字段
	private String wmsLocatorCode;//库位
	private String itemCode;//物料号
	private String itemName;//物料名称
	private String itemMarque;//物料牌号
	private String itemSpecs;//物料规格
	private String inSysUserName;//入库人
	private String statusLabel;//入库单状态
	
	public String getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}

	public String getItemMarque() {
		return itemMarque;
	}

	public String getInSysUserName() {
		return inSysUserName;
	}

	public void setInSysUserName(String inSysUserName) {
		this.inSysUserName = inSysUserName;
	}

	public void setItemMarque(String itemMarque) {
		this.itemMarque = itemMarque;
	}

	public String getItemSpecs() {
		return itemSpecs;
	}

	public void setItemSpecs(String itemSpecs) {
		this.itemSpecs = itemSpecs;
	}

	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public String getWmsLocatorCode() {
		return wmsLocatorCode;
	}

	public void setWmsLocatorCode(String wmsLocatorCode) {
		this.wmsLocatorCode = wmsLocatorCode;
	}
	

	public WmsInvInBillList() {
		super();
	}

	public WmsInvInBillList(String id){
		super(id);
	}

	public WmsInvInBillList(WmsInvInBill wmsInvInBill){
		this.wmsInvInBill = wmsInvInBill;
	}

	public WmsInvInBill getWmsInvInBill() {
		return wmsInvInBill;
	}

	public void setWmsInvInBill(WmsInvInBill wmsInvInBill) {
		this.wmsInvInBill = wmsInvInBill;
	}
	
	public String getWmsLocatorId() {
		return wmsLocatorId;
	}

	public void setWmsLocatorId(String wmsLocatorId) {
		this.wmsLocatorId = wmsLocatorId;
	}
	
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="质量编号", align=2, sort=4)
	public String getQualityNo() {
		return qualityNo;
	}

	public void setQualityNo(String qualityNo) {
		this.qualityNo = qualityNo;
	}
	
	@ExcelField(title="供方合格证号", align=2, sort=5)
	public String getSupplyRegularNo() {
		return supplyRegularNo;
	}

	public void setSupplyRegularNo(String supplyRegularNo) {
		this.supplyRegularNo = supplyRegularNo;
	}
	
	@ExcelField(title="申请数量", align=2, sort=6)
	public String getApplyQty() {
		return applyQty;
	}

	public void setApplyQty(String applyQty) {
		this.applyQty = applyQty;
	}
	
	@ExcelField(title="入库数量", align=2, sort=7)
	public String getInQty() {
		return inQty;
	}

	public void setInQty(String inQty) {
		this.inQty = inQty;
	}
	
	@ExcelField(title="入库人", align=2, sort=8)
	public String getInSysUserId() {
		return inSysUserId;
	}

	public void setInSysUserId(String inSysUserId) {
		this.inSysUserId = inSysUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="入库日期", align=2, sort=9)
	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	
	@ExcelField(title="拒绝原因", align=2, sort=10)
	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	
	@ExcelField(title="备注", align=2, sort=11)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@ExcelField(title="单据状态 ^0、已生成；5、提交申请；10、审核通过", align=2, sort=12)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}