/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsinvbalancebilllist.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 结转查询Entity
 * @author hexl
 * @version 2018-05-18
 */
public class WmsInvBalanceBillListV extends DataEntity<WmsInvBalanceBillListV> {
	
	private static final long serialVersionUID = 1L;
	private String wmsInvBalanceBillId;		// 结转主表id
	private String year;		// 会计年度
	private String period;		// 期段号
	private Date startDate;		// 开始日期
	private Date endDate;		// 结束日期
	private String wmsItemInventoryId;		// 台账表id
	private String itemCode;		// 物料代码
	private String itemName;		// 物料名称
	private String currentInitInvQty;		// current_init_inv_qty
	private String currentSumupInQty;		// current_sumup_in_qty
	private String currentSumupOutQty;		// current_sumup_out_qty
	private String currentSumupAdjustQty;		// current_sumup_adjust_qty
	private String currentOnhandQty;		// current_onhand_qty
	private String engagedQty;		// engaged_qty
	private String takeAwayEngagedQty;		// take_away_engaged_qty
	private String inPrice;		// in_price
	private String inventoryStatus;		// 状态

	public WmsInvBalanceBillListV() {
		super();
	}

	public WmsInvBalanceBillListV(String id){
		super(id);
	}

	@ExcelField(title="结转主表id", align=2, sort=1)
	public String getWmsInvBalanceBillId() {
		return wmsInvBalanceBillId;
	}

	public void setWmsInvBalanceBillId(String wmsInvBalanceBillId) {
		this.wmsInvBalanceBillId = wmsInvBalanceBillId;
	}
	
	@ExcelField(title="会计年度", align=2, sort=2)
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@ExcelField(title="期段号", align=2, sort=3)
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="开始日期", align=2, sort=4)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="结束日期", align=2, sort=5)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@ExcelField(title="台账表id", align=2, sort=6)
	public String getWmsItemInventoryId() {
		return wmsItemInventoryId;
	}

	public void setWmsItemInventoryId(String wmsItemInventoryId) {
		this.wmsItemInventoryId = wmsItemInventoryId;
	}
	
	@ExcelField(title="物料代码", align=2, sort=7)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@ExcelField(title="物料名称", align=2, sort=8)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@ExcelField(title="current_init_inv_qty", align=2, sort=9)
	public String getCurrentInitInvQty() {
		return currentInitInvQty;
	}

	public void setCurrentInitInvQty(String currentInitInvQty) {
		this.currentInitInvQty = currentInitInvQty;
	}
	
	@ExcelField(title="current_sumup_in_qty", align=2, sort=10)
	public String getCurrentSumupInQty() {
		return currentSumupInQty;
	}

	public void setCurrentSumupInQty(String currentSumupInQty) {
		this.currentSumupInQty = currentSumupInQty;
	}
	
	@ExcelField(title="current_sumup_out_qty", align=2, sort=11)
	public String getCurrentSumupOutQty() {
		return currentSumupOutQty;
	}

	public void setCurrentSumupOutQty(String currentSumupOutQty) {
		this.currentSumupOutQty = currentSumupOutQty;
	}
	
	@ExcelField(title="current_sumup_adjust_qty", align=2, sort=12)
	public String getCurrentSumupAdjustQty() {
		return currentSumupAdjustQty;
	}

	public void setCurrentSumupAdjustQty(String currentSumupAdjustQty) {
		this.currentSumupAdjustQty = currentSumupAdjustQty;
	}
	
	@ExcelField(title="current_onhand_qty", align=2, sort=13)
	public String getCurrentOnhandQty() {
		return currentOnhandQty;
	}

	public void setCurrentOnhandQty(String currentOnhandQty) {
		this.currentOnhandQty = currentOnhandQty;
	}
	
	@ExcelField(title="engaged_qty", align=2, sort=14)
	public String getEngagedQty() {
		return engagedQty;
	}

	public void setEngagedQty(String engagedQty) {
		this.engagedQty = engagedQty;
	}
	
	@ExcelField(title="take_away_engaged_qty", align=2, sort=15)
	public String getTakeAwayEngagedQty() {
		return takeAwayEngagedQty;
	}

	public void setTakeAwayEngagedQty(String takeAwayEngagedQty) {
		this.takeAwayEngagedQty = takeAwayEngagedQty;
	}
	
	@ExcelField(title="in_price", align=2, sort=16)
	public String getInPrice() {
		return inPrice;
	}

	public void setInPrice(String inPrice) {
		this.inPrice = inPrice;
	}
	
	@ExcelField(title="状态", align=2, sort=17)
	public String getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}
	
}