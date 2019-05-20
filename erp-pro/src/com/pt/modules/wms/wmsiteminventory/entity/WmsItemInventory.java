/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsiteminventory.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 台账增删改查Entity
 * @author 孙会楠
 * @version 2018-05-16
 */
public class WmsItemInventory extends DataEntity<WmsItemInventory> {
	
	private static final long serialVersionUID = 1L;

	//表外字段
	    private String itemId;		// 物料代码
		private String itemCode;		// 物料代码
		private String itemName;		// 物料名称
		private String marque;		// 牌号
		private String specs;		// 规格
		private String techCondition;		// 技术条件
		private String batchNo;		// batch_no
		private String qualityCode;		// quality_code
		private String lph;		// lph
		private String currentOnhandQty;		// current_onhand_qty
		private String currentInitInvQty;		// current_init_inv_qty
		private String currentSumupInQty;		// current_sumup_in_qty
		private String currentSumupOutQty;		// current_sumup_out_qty
		private String currentSumupAdjustQty;		// current_sumup_adjust_qty
		private String engagedQty;		// engaged_qty
		private String wmsInventoryId;		// wms_inventory_id
		private String inventoryCode;		// 库房代码
		private String inventoryName;		// 库房名称
		private String locatorId;       //库位id
		private String locatorCode;		// 库位代码
		private String sysOfficeId;		// 部门
		private String sysOfficeName;		// 名称
		
		private String canUseQty;//可用数量
	
		
		public WmsItemInventory() {
			super();
		}

		public WmsItemInventory(String id){
			super(id);
		}
		
		public String getItemId() {
			return itemId;
		}

		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		
		@ExcelField(title="物料代码",align=2,sort=1)
		public String getItemCode() {
			return itemCode;
		}

		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}

		@ExcelField(title="物料名称",align=2,sort=2)
		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		@ExcelField(title="牌号",align=2,sort=3)
		public String getMarque() {
			return marque;
		}

		public void setMarque(String marque) {
			this.marque = marque;
		}

		@ExcelField(title="规格",align=2,sort=4)
		public String getSpecs() {
			return specs;
		}

		public void setSpecs(String specs) {
			this.specs = specs;
		}

		@ExcelField(title="技术条件",align=2,sort=5)
		public String getTechCondition() {
			return techCondition;
		}

		public void setTechCondition(String techCondition) {
			this.techCondition = techCondition;
		}
		
		@ExcelField(title="batch_no", align=2, sort=6)
		public String getBatchNo() {
			return batchNo;
		}

		public void setBatchNo(String batchNo) {
			this.batchNo = batchNo;
		}
		
		@ExcelField(title="quality_code", align=2, sort=7)
		public String getQualityCode() {
			return qualityCode;
		}

		public void setQualityCode(String qualityCode) {
			this.qualityCode = qualityCode;
		}
		
		@ExcelField(title="lph", align=2, sort=8)
		public String getLph() {
			return lph;
		}

		public void setLph(String lph) {
			this.lph = lph;
		}
		
		@ExcelField(title="current_onhand_qty", align=2, sort=9)
		public String getCurrentOnhandQty() {
			return currentOnhandQty;
		}

		public void setCurrentOnhandQty(String currentOnhandQty) {
			this.currentOnhandQty = currentOnhandQty;
		}
		
		@ExcelField(title="current_init_inv_qty", align=2, sort=10)
		public String getCurrentInitInvQty() {
			return currentInitInvQty;
		}

		public void setCurrentInitInvQty(String currentInitInvQty) {
			this.currentInitInvQty = currentInitInvQty;
		}
		
		@ExcelField(title="current_sumup_in_qty", align=2, sort=11)
		public String getCurrentSumupInQty() {
			return currentSumupInQty;
		}

		public void setCurrentSumupInQty(String currentSumupInQty) {
			this.currentSumupInQty = currentSumupInQty;
		}
		
		@ExcelField(title="current_sumup_out_qty", align=2, sort=12)
		public String getCurrentSumupOutQty() {
			return currentSumupOutQty;
		}

		public void setCurrentSumupOutQty(String currentSumupOutQty) {
			this.currentSumupOutQty = currentSumupOutQty;
		}
		
		@ExcelField(title="current_sumup_adjust_qty", align=2, sort=13)
		public String getCurrentSumupAdjustQty() {
			return currentSumupAdjustQty;
		}

		public void setCurrentSumupAdjustQty(String currentSumupAdjustQty) {
			this.currentSumupAdjustQty = currentSumupAdjustQty;
		}
		
		@ExcelField(title="engaged_qty", align=2, sort=14)
		public String getEngagedQty() {
			return engagedQty;
		}

		public void setEngagedQty(String engagedQty) {
			this.engagedQty = engagedQty;
		}
		
		@ExcelField(title="wms_inventory_id", align=2, sort=15)
		public String getWmsInventoryId() {
			return wmsInventoryId;
		}

		public void setWmsInventoryId(String wmsInventoryId) {
			this.wmsInventoryId = wmsInventoryId;
		}
		
		@ExcelField(title="locator_id", align=2, sort=16)
		public String getLocatorId() {
			return locatorId;
		}

		public void setLocatorId(String locatorId) {
			this.locatorId = locatorId;
		}
		
		@ExcelField(title="库房代码",align=2,sort=17)
		public String getInventoryCode() {
			return inventoryCode;
		}

		public void setInventoryCode(String inventoryCode) {
			this.inventoryCode = inventoryCode;
		}

		@ExcelField(title="库房名称",align=2,sort=18)
		public String getInventoryName() {
			return inventoryName;
		}

		public void setInventoryName(String inventoryName) {
			this.inventoryName = inventoryName;
		}

		@ExcelField(title="库位名称",align=2,sort=19)
		public String getLocatorCode() {
			return locatorCode;
		}

		public void setLocatorCode(String locatorCode) {
			this.locatorCode = locatorCode;
		}

		@ExcelField(title="部门",align=2,sort=20)
		public String getSysOfficeId() {
			return sysOfficeId;
		}

		public void setSysOfficeId(String sysOfficeId) {
			this.sysOfficeId = sysOfficeId;
		}

		@ExcelField(title="名称",align=2,sort=21)
		public String getSysOfficeName() {
			return sysOfficeName;
		}

		public void setSysOfficeName(String sysOfficeName) {
			this.sysOfficeName = sysOfficeName;
		}

		
	private String wmsLocatorId;		// wms_locator_id
	private String mdsItemId;		// mds_item_id
	private String proMdsItemId;		// pro_mds_item_id
	private String hgzNo;		// hgz_no
	private String takeAwayEngagedQty;		// take_away_engaged_qty
	private Date inventoryDate;		// inventory_date
	private String inPrice;		// in_price
	private Date outFactoryDate;		// out_factory_date
	private String lastCheckSysUserId;		// last_check_sys_user_id
	private Date lastCheckDate;		// last_check_date
	private String envelopCode;		// envelop_code
	private String envelopSysUserId;		// envelop_sys_user_id
	private Date envelopDate;		// envelop_date
	private String mdsUnitId;		// mds_unit_id
	private String toolInventoryStatus;		// tool_inventory_status
	private String inventoryStatus;		// 0
	private String checkArchivesNo;		// check_archives_no
	private String toolStatus;		// tool_status
	private String toolPiecesPerSuit;		// tool_pieces_per_suit
	private String toolCheckCycle;		// tool_check_cycle
	private String toolChkPeriodType;		// tool_chk_period_type
	private String toolCheckHour;		// tool_check_hour
	private String toolCheckFee;		// tool_check_fee
	private String toolChkSysUserId;		// tool_chk_sys_user_id
	private String toolChkPlanType;		// tool_chk_plan_type
	private String toolChkSysOfficeId;		// tool_chk_sys_office_id
	private String toolCheckType;		// tool_check_type
	private String checkRoom;		// check_room
	private String note;		// note
	private String checkStandardUniqueNo;		// check_standard_unique_no
	private String checkArea;		// check_area
	private String checkPrecision;		// check_precision
	private String pieceHypo;		// piece_hypo
	private String entrust;		// entrust
	private String checkitemClassId;		// checkitem_class_id
	private String resolvingPower;		// resolving_power
	private Date startUseDate;		// start_use_date
	private String standardLevel;		// standard_level
	private String laserCode;		// laser_code
	private String barCode;		// bar_code
	private String item01;		// item_01
	private String item02;		// item_02
	private String item03;		// item_03
	private String item04;		// item_04
	private String item05;		// item_05
	
	@ExcelField(title="wms_locator_id", align=2, sort=2)
	public String getWmsLocatorId() {
		return wmsLocatorId;
	}

	public void setWmsLocatorId(String wmsLocatorId) {
		this.wmsLocatorId = wmsLocatorId;
	}
	
	@ExcelField(title="mds_item_id", align=2, sort=3)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="pro_mds_item_id", align=2, sort=4)
	public String getProMdsItemId() {
		return proMdsItemId;
	}

	public void setProMdsItemId(String proMdsItemId) {
		this.proMdsItemId = proMdsItemId;
	}
	
	@ExcelField(title="hgz_no", align=2, sort=8)
	public String getHgzNo() {
		return hgzNo;
	}

	public void setHgzNo(String hgzNo) {
		this.hgzNo = hgzNo;
	}
	
	
	
	@ExcelField(title="take_away_engaged_qty", align=2, sort=15)
	public String getTakeAwayEngagedQty() {
		return takeAwayEngagedQty;
	}

	public void setTakeAwayEngagedQty(String takeAwayEngagedQty) {
		this.takeAwayEngagedQty = takeAwayEngagedQty;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="inventory_date不能为空")
	@ExcelField(title="inventory_date", align=2, sort=16)
	public Date getInventoryDate() {
		return inventoryDate;
	}

	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
	
	@ExcelField(title="in_price", align=2, sort=17)
	public String getInPrice() {
		return inPrice;
	}

	public void setInPrice(String inPrice) {
		this.inPrice = inPrice;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="out_factory_date不能为空")
	@ExcelField(title="out_factory_date", align=2, sort=18)
	public Date getOutFactoryDate() {
		return outFactoryDate;
	}

	public void setOutFactoryDate(Date outFactoryDate) {
		this.outFactoryDate = outFactoryDate;
	}
	
	@ExcelField(title="last_check_sys_user_id", align=2, sort=19)
	public String getLastCheckSysUserId() {
		return lastCheckSysUserId;
	}

	public void setLastCheckSysUserId(String lastCheckSysUserId) {
		this.lastCheckSysUserId = lastCheckSysUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="last_check_date不能为空")
	@ExcelField(title="last_check_date", align=2, sort=20)
	public Date getLastCheckDate() {
		return lastCheckDate;
	}

	public void setLastCheckDate(Date lastCheckDate) {
		this.lastCheckDate = lastCheckDate;
	}
	
	@ExcelField(title="envelop_code", align=2, sort=21)
	public String getEnvelopCode() {
		return envelopCode;
	}

	public void setEnvelopCode(String envelopCode) {
		this.envelopCode = envelopCode;
	}
	
	@ExcelField(title="envelop_sys_user_id", align=2, sort=22)
	public String getEnvelopSysUserId() {
		return envelopSysUserId;
	}

	public void setEnvelopSysUserId(String envelopSysUserId) {
		this.envelopSysUserId = envelopSysUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="envelop_date不能为空")
	@ExcelField(title="envelop_date", align=2, sort=23)
	public Date getEnvelopDate() {
		return envelopDate;
	}

	public void setEnvelopDate(Date envelopDate) {
		this.envelopDate = envelopDate;
	}
	
	@ExcelField(title="mds_unit_id", align=2, sort=24)
	public String getMdsUnitId() {
		return mdsUnitId;
	}

	public void setMdsUnitId(String mdsUnitId) {
		this.mdsUnitId = mdsUnitId;
	}
	
	@ExcelField(title="tool_inventory_status", align=2, sort=25)
	public String getToolInventoryStatus() {
		return toolInventoryStatus;
	}

	public void setToolInventoryStatus(String toolInventoryStatus) {
		this.toolInventoryStatus = toolInventoryStatus;
	}
	
	@ExcelField(title="0", align=2, sort=26)
	public String getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}
	
	@ExcelField(title="check_archives_no", align=2, sort=27)
	public String getCheckArchivesNo() {
		return checkArchivesNo;
	}

	public void setCheckArchivesNo(String checkArchivesNo) {
		this.checkArchivesNo = checkArchivesNo;
	}
	
	@ExcelField(title="tool_status", align=2, sort=28)
	public String getToolStatus() {
		return toolStatus;
	}

	public void setToolStatus(String toolStatus) {
		this.toolStatus = toolStatus;
	}
	
	@ExcelField(title="tool_pieces_per_suit", align=2, sort=29)
	public String getToolPiecesPerSuit() {
		return toolPiecesPerSuit;
	}

	public void setToolPiecesPerSuit(String toolPiecesPerSuit) {
		this.toolPiecesPerSuit = toolPiecesPerSuit;
	}
	
	@ExcelField(title="tool_check_cycle", align=2, sort=30)
	public String getToolCheckCycle() {
		return toolCheckCycle;
	}

	public void setToolCheckCycle(String toolCheckCycle) {
		this.toolCheckCycle = toolCheckCycle;
	}
	
	@ExcelField(title="tool_chk_period_type", align=2, sort=31)
	public String getToolChkPeriodType() {
		return toolChkPeriodType;
	}

	public void setToolChkPeriodType(String toolChkPeriodType) {
		this.toolChkPeriodType = toolChkPeriodType;
	}
	
	@ExcelField(title="tool_check_hour", align=2, sort=32)
	public String getToolCheckHour() {
		return toolCheckHour;
	}

	public void setToolCheckHour(String toolCheckHour) {
		this.toolCheckHour = toolCheckHour;
	}
	
	@ExcelField(title="tool_check_fee", align=2, sort=33)
	public String getToolCheckFee() {
		return toolCheckFee;
	}

	public void setToolCheckFee(String toolCheckFee) {
		this.toolCheckFee = toolCheckFee;
	}
	
	@ExcelField(title="tool_chk_sys_user_id", align=2, sort=34)
	public String getToolChkSysUserId() {
		return toolChkSysUserId;
	}

	public void setToolChkSysUserId(String toolChkSysUserId) {
		this.toolChkSysUserId = toolChkSysUserId;
	}
	
	@ExcelField(title="tool_chk_plan_type", align=2, sort=35)
	public String getToolChkPlanType() {
		return toolChkPlanType;
	}

	public void setToolChkPlanType(String toolChkPlanType) {
		this.toolChkPlanType = toolChkPlanType;
	}
	
	@ExcelField(title="tool_chk_sys_office_id", align=2, sort=36)
	public String getToolChkSysOfficeId() {
		return toolChkSysOfficeId;
	}

	public void setToolChkSysOfficeId(String toolChkSysOfficeId) {
		this.toolChkSysOfficeId = toolChkSysOfficeId;
	}
	
	@ExcelField(title="tool_check_type", align=2, sort=37)
	public String getToolCheckType() {
		return toolCheckType;
	}

	public void setToolCheckType(String toolCheckType) {
		this.toolCheckType = toolCheckType;
	}
	
	@ExcelField(title="check_room", align=2, sort=38)
	public String getCheckRoom() {
		return checkRoom;
	}

	public void setCheckRoom(String checkRoom) {
		this.checkRoom = checkRoom;
	}
	
	@ExcelField(title="note", align=2, sort=39)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	@ExcelField(title="check_standard_unique_no", align=2, sort=40)
	public String getCheckStandardUniqueNo() {
		return checkStandardUniqueNo;
	}

	public void setCheckStandardUniqueNo(String checkStandardUniqueNo) {
		this.checkStandardUniqueNo = checkStandardUniqueNo;
	}
	
	@ExcelField(title="check_area", align=2, sort=41)
	public String getCheckArea() {
		return checkArea;
	}

	public void setCheckArea(String checkArea) {
		this.checkArea = checkArea;
	}
	
	@ExcelField(title="check_precision", align=2, sort=42)
	public String getCheckPrecision() {
		return checkPrecision;
	}

	public void setCheckPrecision(String checkPrecision) {
		this.checkPrecision = checkPrecision;
	}
	
	@ExcelField(title="piece_hypo", align=2, sort=43)
	public String getPieceHypo() {
		return pieceHypo;
	}

	public void setPieceHypo(String pieceHypo) {
		this.pieceHypo = pieceHypo;
	}
	
	@ExcelField(title="entrust", align=2, sort=44)
	public String getEntrust() {
		return entrust;
	}

	public void setEntrust(String entrust) {
		this.entrust = entrust;
	}
	
	@ExcelField(title="checkitem_class_id", align=2, sort=45)
	public String getCheckitemClassId() {
		return checkitemClassId;
	}

	public void setCheckitemClassId(String checkitemClassId) {
		this.checkitemClassId = checkitemClassId;
	}
	
	@ExcelField(title="resolving_power", align=2, sort=46)
	public String getResolvingPower() {
		return resolvingPower;
	}

	public void setResolvingPower(String resolvingPower) {
		this.resolvingPower = resolvingPower;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="start_use_date不能为空")
	@ExcelField(title="start_use_date", align=2, sort=47)
	public Date getStartUseDate() {
		return startUseDate;
	}

	public void setStartUseDate(Date startUseDate) {
		this.startUseDate = startUseDate;
	}
	
	@ExcelField(title="standard_level", align=2, sort=48)
	public String getStandardLevel() {
		return standardLevel;
	}

	public void setStandardLevel(String standardLevel) {
		this.standardLevel = standardLevel;
	}
	
	@ExcelField(title="laser_code", align=2, sort=49)
	public String getLaserCode() {
		return laserCode;
	}

	public void setLaserCode(String laserCode) {
		this.laserCode = laserCode;
	}
	
	@ExcelField(title="bar_code", align=2, sort=50)
	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	
	@ExcelField(title="item_01", align=2, sort=51)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="item_02", align=2, sort=52)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="item_03", align=2, sort=53)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="item_04", align=2, sort=54)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="item_05", align=2, sort=55)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}
	
	public String getCanUseQty() {
		return canUseQty;
	}

	public void setCanUseQty(String canUseQty) {
		this.canUseQty = canUseQty;
	}
}