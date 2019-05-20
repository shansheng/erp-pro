/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvoutbill.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 出库申请单子表Entity
 * @author 龚志强
 * @version 2018-05-21
 */
public class WmsInvOutBillList extends DataEntity<WmsInvOutBillList> {
	
	private static final long serialVersionUID = 1L;
	private WmsInvOutBill wmsInvOutBill;		// 单子主表 父类
	private String wmsLocatorId;		// 库位id
	private String mdsItemId;		// 物料ID
	private String wmsItemInventoryId;		// 台账ID
	private String qualityNo;		// 质量编号
	private String supplyRegularNo;		// 供方合格证号
	private String applyQty;		// 申请数量
	private String outQty;		// 出库数量
	private String outSysUserId;		// 出库人
	private Date outDate;		// 出库日期
	private String leaderUserId;		// 领用人
	private String note;		// 备注
	private String status;		// 单据状态
	
	//表外字段
	private String wmsLocatorCode;//库位代码
	private String itemCode;//物料号
	private String itemName;//物料名称
	private String itemMarque;//物料牌号
	private String itemSpecs;//物料规格
	private String outSysUserName;//出库人
	private String leaderSysUserName;//领料人
	private String statusLabel;//入库单状态
	private String canUseQty;//可用数量
	

	public String getWmsLocatorCode() {
		return wmsLocatorCode;
	}

	public void setWmsLocatorCode(String wmsLocatorCode) {
		this.wmsLocatorCode = wmsLocatorCode;
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

	public String getItemMarque() {
		return itemMarque;
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

	public String getOutSysUserName() {
		return outSysUserName;
	}

	public void setOutSysUserName(String outSysUserName) {
		this.outSysUserName = outSysUserName;
	}

	public String getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(String statusLabel) {
		this.statusLabel = statusLabel;
	}

	public WmsInvOutBillList() {
		super();
	}

	public WmsInvOutBillList(String id){
		super(id);
	}

	public WmsInvOutBillList(WmsInvOutBill wmsInvOutBill){
		this.wmsInvOutBill = wmsInvOutBill;
	}

	public WmsInvOutBill getWmsInvOutBill() {
		return wmsInvOutBill;
	}

	public void setWmsInvOutBill(WmsInvOutBill wmsInvOutBill) {
		this.wmsInvOutBill = wmsInvOutBill;
	}
	
	@ExcelField(title="库位id", align=2, sort=2)
	public String getWmsLocatorId() {
		return wmsLocatorId;
	}

	public void setWmsLocatorId(String wmsLocatorId) {
		this.wmsLocatorId = wmsLocatorId;
	}
	
	@ExcelField(title="物料ID", align=2, sort=3)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="台账ID", align=2, sort=4)
	public String getWmsItemInventoryId() {
		return wmsItemInventoryId;
	}

	public void setWmsItemInventoryId(String wmsItemInventoryId) {
		this.wmsItemInventoryId = wmsItemInventoryId;
	}
	
	@ExcelField(title="质量编号", align=2, sort=5)
	public String getQualityNo() {
		return qualityNo;
	}

	public void setQualityNo(String qualityNo) {
		this.qualityNo = qualityNo;
	}
	
	@ExcelField(title="供方合格证号", align=2, sort=6)
	public String getSupplyRegularNo() {
		return supplyRegularNo;
	}

	public void setSupplyRegularNo(String supplyRegularNo) {
		this.supplyRegularNo = supplyRegularNo;
	}
	
	@ExcelField(title="申请数量", align=2, sort=7)
	public String getApplyQty() {
		return applyQty;
	}

	public void setApplyQty(String applyQty) {
		this.applyQty = applyQty;
	}
	
	@ExcelField(title="出库数量", align=2, sort=8)
	public String getOutQty() {
		return outQty;
	}

	public void setOutQty(String outQty) {
		this.outQty = outQty;
	}
	
	@ExcelField(title="出库人", align=2, sort=9)
	public String getOutSysUserId() {
		return outSysUserId;
	}

	public void setOutSysUserId(String outSysUserId) {
		this.outSysUserId = outSysUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="出库日期", align=2, sort=10)
	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	@ExcelField(title="领用人", align=2, sort=11)
	public String getLeaderUserId() {
		return leaderUserId;
	}

	public void setLeaderUserId(String leaderUserId) {
		this.leaderUserId = leaderUserId;
	}
	
	public String getLeaderSysUserName() {
		return leaderSysUserName;
	}

	public void setLeaderSysUserName(String leaderSysUserName) {
		this.leaderSysUserName = leaderSysUserName;
	}
	
	public String getCanUseQty() {
		return canUseQty;
	}

	public void setCanUseQty(String canUseQty) {
		this.canUseQty = canUseQty;
	}

	@ExcelField(title="备注", align=2, sort=12)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@ExcelField(title="单据状态", align=2, sort=13)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}