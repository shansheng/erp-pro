/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsarrivalcheckout.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.modules.mds.mdsarrivalaffirm.entity.MdsArrivalAffirm;
import com.pt.common.utils.IdGen;
import com.pt.modules.sys.entity.User;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 到货检验Entity
 * @author cjx
 * @version 2018-10-11
 */
public class MdsArrivalCheckout extends DataEntity<MdsArrivalCheckout> {
	
	private static final long serialVersionUID = 1L;
	private String mdsProcurmentPlanId;		// 采购计划表id
	private int arrivalNum;		// 到货数量
	private int qualified;		// 合格数
	private int disqualification;		// 不合格数
	private int inStorage;		// 入库数
	private User inStorageBy;		// 入库人
	private Date inStorageDate;		// 入库时间
	private String state;		// 状态(0:未检验,1:已检验)
	private String inStoargeState;	//入库状态
	
	/*private User createBy;	//到货确认者
	private Date createDate;	//到货确认日期
	private User updateBy;		//检验人
	private Date updateDate;   //检验时间 */
	
	
	private String itemCode;	//物料编号	
	private String itemName;	//物料编码
	private String normalField;	//标准号
	private String modelnm;		//型号
	private String specs;		//规格
	private String supplierName; //供应商名称
	private String supplierId;	//供应商id
	
	public MdsArrivalCheckout() {
		super();
	}

	public MdsArrivalCheckout(String id){
		super(id);
	}
	public MdsArrivalCheckout(MdsArrivalAffirm mdsProcurmentPlan){
		this.mdsProcurmentPlanId = mdsProcurmentPlan.getId();
	}
	public MdsArrivalCheckout(String date,String n){
		String entityArray[] =date.split(":");
		this.id = entityArray[0];
		this.qualified = Integer.parseInt(entityArray[1]);
		this.disqualification = Integer.parseInt(entityArray[2]);
	}
	public MdsArrivalCheckout(MdsArrivalAffirm mds,int ArrivalnumNew,User user){
		this.id = IdGen.uuid();
		this.mdsProcurmentPlanId = mds.getId();
		this.arrivalNum = ArrivalnumNew;
		this.createBy = user;
		this.createDate = mds.getArrivalDate();
	}

	@ExcelField(title="采购计划表id", align=2, sort=1)
	public String getMdsProcurmentPlanId() {
		return mdsProcurmentPlanId;
	}

	public void setMdsProcurmentPlanId(String mdsProcurmentPlanId) {
		this.mdsProcurmentPlanId = mdsProcurmentPlanId;
	}
	
	@ExcelField(title="到货数量", align=2, sort=2)
	public int getArrivalNum() {
		return arrivalNum;
	}

	public void setArrivalNum(int arrivalNum) {
		this.arrivalNum = arrivalNum;
	}
	
	@ExcelField(title="合格数", align=2, sort=5)
	public int getQualified() {
		return qualified;
	}

	public void setQualified(int qualified) {
		this.qualified = qualified;
	}
	
	@ExcelField(title="不合格数", align=2, sort=6)
	public int getDisqualification() {
		return disqualification;
	}

	public void setDisqualification(int disqualification) {
		this.disqualification = disqualification;
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
	
	@ExcelField(title="状态(0:未检验,1:已检验)", align=2, sort=12)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getInStoargeState() {
		return inStoargeState;
	}

	public void setInStoargeState(String inStoargeState) {
		this.inStoargeState = inStoargeState;
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

	public String getModelnm() {
		return modelnm;
	}

	public void setModelnm(String modelnm) {
		this.modelnm = modelnm;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	

}