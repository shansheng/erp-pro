/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.wms.wmsmaininstorage.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pt.common.utils.excel.annotation.ExcelField;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.sys.entity.User;

/**
 * 入库管理Entity
 * @author cjx
 * @version 2018-10-17
 */
public class WmsMainInStorage extends DataEntity<WmsMainInStorage> {
	
	private static final long serialVersionUID = 1L;
	private String mdsProcurmentPlanId;		// 采购计划表id
	private int qualified;		// 合格数
	private int inStorage;		// 入库数
	private User inStorageBy;		// 入库人
	private Date inStorageDate;		// 入库时间
	private String inStoargeState;		// 入库状态
	private Date beginUpdateDate;		// 开始 检验时间
	private Date endUpdateDate;		// 结束 检验时间
	
	/*private User createBy;	//到货确认者
	private Date createDate;	//到货确认日期
	private User updateBy;		//检验人
	private Date updateDate;   //检验时间 */
	
	private String itemId;	//物料id
	private String itemCode;		// 物料编码
	private String itemName;		// 物料名称
	private String normalField;		// 标准号
	private String modelNum;		// 型号
	private String specs;		// 规格
	private String state;
	private String supplierName; //供应商名字
	private String source;	//入库来源(0:检查入库,1:手动添加)
	
	public WmsMainInStorage() {
		super();
	}

	public WmsMainInStorage(String id){
		super(id);
	}

	@ExcelField(title="采购计划表id", align=2, sort=1)
	public String getMdsProcurmentPlanId() {
		return mdsProcurmentPlanId;
	}

	public void setMdsProcurmentPlanId(String mdsProcurmentPlanId) {
		this.mdsProcurmentPlanId = mdsProcurmentPlanId;
	}
	
	@ExcelField(title="合格数", align=2, sort=5)
	public int getQualified() {
		return qualified;
	}

	public void setQualified(int qualified) {
		this.qualified = qualified;
	}
	
	@ExcelField(title="入库数", align=2, sort=9)
	public int getInStorage() {
		return inStorage;
	}

	public void setInStorage(int inStorage) {
		this.inStorage = inStorage;
	}
	
	@ExcelField(title="入库人", align=2, sort=10)
	public User getInStorageBy() {
		return inStorageBy;
	}

	public void setInStorageBy(User inStorageBy) {
		this.inStorageBy = inStorageBy;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="入库时间", align=2, sort=11)
	public Date getInStorageDate() {
		return inStorageDate;
	}

	public void setInStorageDate(Date inStorageDate) {
		this.inStorageDate = inStorageDate;
	}
	
	@ExcelField(title="入库状态", align=2, sort=13)
	public String getInStoargeState() {
		return inStoargeState;
	}

	public void setInStoargeState(String inStoargeState) {
		this.inStoargeState = inStoargeState;
	}
	
	public Date getBeginUpdateDate() {
		return beginUpdateDate;
	}

	public void setBeginUpdateDate(Date beginUpdateDate) {
		this.beginUpdateDate = beginUpdateDate;
	}
	
	public Date getEndUpdateDate() {
		return endUpdateDate;
	}

	public void setEndUpdateDate(Date endUpdateDate) {
		this.endUpdateDate = endUpdateDate;
	}
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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

	public String getNormalField() {
		return normalField;
	}

	public void setNormalField(String normalField) {
		this.normalField = normalField;
	}

	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}