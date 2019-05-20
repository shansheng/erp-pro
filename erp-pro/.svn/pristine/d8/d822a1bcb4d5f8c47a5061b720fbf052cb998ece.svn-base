/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsitemtransaction.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.modules.mds.mdsitem.entity.MdsItem;
import com.pt.modules.wms.wmsinventory.entity.WmsInventory;
import com.pt.modules.wms.wmsiteminventoryvs.entity.WmsItemInventoryVs;
import com.pt.modules.wms.wmslocator.entity.WmsLocator;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 库房事务Entity
 * @author 郑利
 * @version 2018-05-18
 */
public class WmsItemTransaction extends DataEntity<WmsItemTransaction> {
	
	private static final long serialVersionUID = 1L;
	private String transactionCode;		// 事务处理流水号 ^
	private String mdsItemId;		// 物料ID ^ 
	private String wmsInventoryId;		// 库房ID ^ 
	private String wmsLocatorId;		// 库位ID ^ 
	private String wmsItemInventoryId;		// 台帐ID ^ 
	private String wmsInventoryTransactionId;		// 库存事务类型代码 ^  WMS_INVENTORY_TRANSACTION ID
	private String invTransactStatus;		// 库存事务状态 D,J ,B-调错记录^  
	private String printFlag;		// 是否打印业务单据的标记 ^  
	private String backFlag;		// 事务处理对冲标记 ^  
	private String backTransactionId;		// 对冲事务流水ID ^ 对于冲账与被冲的流水分别是对方的流水ID
	private String backTransactionCode;		// 对冲事务流水号 ^ 对于冲账与被冲的流水分别是对方的流水号
	private String qualityCode;		// 质量编号 ^ 用于控制产品质量及性能的产品质量检验编号。0表示此物料不需要质量跟踪
	private String initQty;		// 结存数量 ^
	private String transactionQty;		// 库存事务数量 
	private Date transactionDate;		// 事务日期 ^
	private String mdsUnitId;		// 计量单位 ^
	private String supplyRegularNo;		// 供方合格证号 ^ 供方合格证号或炉批号
	private String originalOrderId;		// 原始单据ID（采购订单、销售订单、生产订单）
	private String originalOrderDetailId;		// 原始单据子表ID（采购订单、销售订单、生产订单）
	private String item01;		// item_01
	private String item02;		// item_02
	private String item03;		// item_03
	private String item04;		// item_04
	private String item05;		// item_05
	
	private String itemCode;
	private String itemName;
	private String wmsInventoryName;
	private String wmsLocatorCode;
	private String eId;
	private String fName;
	
	public WmsItemTransaction() {
		super();
	}

	public WmsItemTransaction(String id){
		super(id);
	}

	@ExcelField(title="事务处理流水号 ^", align=2, sort=1)
	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	
	@ExcelField(title="物料ID ^ ", dictType="mds_item_id", align=2, sort=2)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="库房ID ^ ", dictType="wms_inventory_id", align=2, sort=3)
	public String getWmsInventoryId() {
		return wmsInventoryId;
	}

	public void setWmsInventoryId(String wmsInventoryId) {
		this.wmsInventoryId = wmsInventoryId;
	}
	
	@ExcelField(title="库位ID ^ ", dictType="wms_locator_id", align=2, sort=4)
	public String getWmsLocatorId() {
		return wmsLocatorId;
	}

	public void setWmsLocatorId(String wmsLocatorId) {
		this.wmsLocatorId = wmsLocatorId;
	}
	
	@ExcelField(title="台帐ID ^ ", dictType="wms_item_inventory_id", align=2, sort=5)
	public String getWmsItemInventoryId() {
		return wmsItemInventoryId;
	}

	public void setWmsItemInventoryId(String wmsItemInventoryId) {
		this.wmsItemInventoryId = wmsItemInventoryId;
	}
	
	@ExcelField(title="库存事务类型代码 ^  WMS_INVENTORY_TRANSACTION ID", align=2, sort=6)
	public String getWmsInventoryTransactionId() {
		return wmsInventoryTransactionId;
	}

	public void setWmsInventoryTransactionId(String wmsInventoryTransactionId) {
		this.wmsInventoryTransactionId = wmsInventoryTransactionId;
	}
	
	@ExcelField(title="库存事务状态 D,J ,B-调错记录^  ", align=2, sort=7)
	public String getInvTransactStatus() {
		return invTransactStatus;
	}

	public void setInvTransactStatus(String invTransactStatus) {
		this.invTransactStatus = invTransactStatus;
	}
	
	@ExcelField(title="是否打印业务单据的标记 ^  ", align=2, sort=8)
	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}
	
	@ExcelField(title="事务处理对冲标记 ^  ", align=2, sort=9)
	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	
	@ExcelField(title="对冲事务流水ID ^ 对于冲账与被冲的流水分别是对方的流水ID", align=2, sort=10)
	public String getBackTransactionId() {
		return backTransactionId;
	}

	public void setBackTransactionId(String backTransactionId) {
		this.backTransactionId = backTransactionId;
	}
	
	@ExcelField(title="对冲事务流水号 ^ 对于冲账与被冲的流水分别是对方的流水号", align=2, sort=11)
	public String getBackTransactionCode() {
		return backTransactionCode;
	}

	public void setBackTransactionCode(String backTransactionCode) {
		this.backTransactionCode = backTransactionCode;
	}
	
	@ExcelField(title="质量编号 ^ 用于控制产品质量及性能的产品质量检验编号。0表示此物料不需要质量跟踪", align=2, sort=12)
	public String getQualityCode() {
		return qualityCode;
	}

	public void setQualityCode(String qualityCode) {
		this.qualityCode = qualityCode;
	}
	
	@ExcelField(title="结存数量 ^", align=2, sort=13)
	public String getInitQty() {
		return initQty;
	}

	public void setInitQty(String initQty) {
		this.initQty = initQty;
	}
	
	@ExcelField(title="库存事务数量 ", align=2, sort=14)
	public String getTransactionQty() {
		return transactionQty;
	}

	public void setTransactionQty(String transactionQty) {
		this.transactionQty = transactionQty;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="事务日期 ^", align=2, sort=15)
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	@ExcelField(title="计量单位 ^", align=2, sort=16)
	public String getMdsUnitId() {
		return mdsUnitId;
	}

	public void setMdsUnitId(String mdsUnitId) {
		this.mdsUnitId = mdsUnitId;
	}
	
	@ExcelField(title="供方合格证号 ^ 供方合格证号或炉批号", align=2, sort=17)
	public String getSupplyRegularNo() {
		return supplyRegularNo;
	}

	public void setSupplyRegularNo(String supplyRegularNo) {
		this.supplyRegularNo = supplyRegularNo;
	}
	
	@ExcelField(title="原始单据ID（采购订单、销售订单、生产订单）", align=2, sort=18)
	public String getOriginalOrderId() {
		return originalOrderId;
	}

	public void setOriginalOrderId(String originalOrderId) {
		this.originalOrderId = originalOrderId;
	}
	
	@ExcelField(title="原始单据子表ID（采购订单、销售订单、生产订单）", align=2, sort=19)
	public String getOriginalOrderDetailId() {
		return originalOrderDetailId;
	}

	public void setOriginalOrderDetailId(String originalOrderDetailId) {
		this.originalOrderDetailId = originalOrderDetailId;
	}
	
	@ExcelField(title="item_01", align=2, sort=20)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="item_02", align=2, sort=21)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="item_03", align=2, sort=22)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="item_04", align=2, sort=23)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="item_05", align=2, sort=24)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getWmsInventoryName() {
		return wmsInventoryName;
	}

	public void setWmsInventoryName(String wmsInventoryName) {
		this.wmsInventoryName = wmsInventoryName;
	}

	public String getWmsLocatorCode() {
		return wmsLocatorCode;
	}

	public void setWmsLocatorCode(String wmsLocatorCode) {
		this.wmsLocatorCode = wmsLocatorCode;
	}

	public String geteId() {
		return eId;
	}

	public void seteId(String eId) {
		this.eId = eId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	
	
}