/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsquota.entity;


import com.pt.common.utils.excel.annotation.ExcelField;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.mds.mdsprojectpcmplan.entity.MdsProjectQuotaList;

/**
 * 材料定额明细表Entity
 * @author cjx
 * @version 2018-09-11
 */
public class MdsQuotaDetail extends DataEntity<MdsQuotaDetail> {
	
	private static final long serialVersionUID = 1L;
	private String mdsQuotaId;		// 材料定额表 父类
	private String serial;		// 序号
	private String itemId;		// 物料id
	private String itemCode;		// 物料编码
	private String itemName;		// 物料名称
	private String normalField;		// 标准号
	private String modelNum;		// 型号
	private String specs;		// 规格
	private String quota;		// 单台定额
	private String mdsUnitId;		// 单位
	private String classify;		// 分类
	private String column2;		// 当前订单状态(只在主页到第二页时用)
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private String remarks; //备注
	
	private String clientId;	//主页查询条件,客户id
	private String mdsItemCode;	//主页查询条件,物料编码
	private String mdsItemName;	 //主页查询条件,物料名字
	private String state;	//主页查询条件,状态
	private Integer omiNum;//已发料数
	private Integer miNum;//发料数
	
	private String isVersion;	//是否定版
	private String verifyBy;	//校验人
	
	private String addProcurement;	//项目采购计划是否已经添加到采购计划中
	private String isAdd;			//批量采购计划是否添加
	
	public MdsQuotaDetail() {
		super();
	}

	public MdsQuotaDetail(String id){
		super(id);
	}
	
	public MdsQuotaDetail(MdsProjectQuotaList mdsProjectQuotaList){
		super(mdsProjectQuotaList.getMdsQuotaDetailId());
		this.addProcurement = "1";
	}
	
	public Integer getOmiNum() {
		return omiNum;
	}

	public void setOmiNum(Integer omiNum) {
		this.omiNum = omiNum;
	}

	public Integer getMiNum() {
		return miNum;
	}

	public void setMiNum(Integer miNum) {
		this.miNum = miNum;
	}
	
	public MdsQuotaDetail(MdsQuota mdsQuotaId){
		this.mdsQuotaId = mdsQuotaId.getId();
	}

	public String getMdsQuotaId() {
		return mdsQuotaId;
	}

	public void setMdsQuotaId(String mdsQuotaId) {
		this.mdsQuotaId = mdsQuotaId;
	}
	
	@ExcelField(title="*序号", align=2, sort=2)
	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@ExcelField(title="*物料编码", align=2, sort=3)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@ExcelField(title="*物料名称", align=2, sort=4)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@ExcelField(title="标准号", align=2, sort=5)
	public String getNormalField() {
		return normalField;
	}

	public void setNormalField(String normalField) {
		this.normalField = normalField;
	}
	
	@ExcelField(title="材质", align=2, sort=7)
	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}
	
	@ExcelField(title="规格/型号", align=2, sort=6)
	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	@ExcelField(title="*单台定额", align=2, sort=8)
	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}
	
	@ExcelField(title="单位", align=2, sort=9)
	public String getMdsUnitId() {
		return mdsUnitId;
	}

	public void setMdsUnitId(String mdsUnitId) {
		this.mdsUnitId = mdsUnitId;
	}
	
	@ExcelField(title="*分类", align=2, sort=1)
	public String getClassify() {
		return classify;
	}


	public void setClassify(String classify) {
		this.classify = classify;
	}
	
//	@ExcelField(title="校验人", align=2, sort=10)
	public String getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(String verifyBy) {
		this.verifyBy = verifyBy;
	}
	@ExcelField(title="备注", align=2, sort=10)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	
	
	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	
	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getMdsItemCode() {
		return mdsItemCode;
	}

	public void setMdsItemCode(String mdsItemCode) {
		this.mdsItemCode = mdsItemCode;
	}

	public String getMdsItemName() {
		return mdsItemName;
	}

	public void setMdsItemName(String mdsItemName) {
		this.mdsItemName = mdsItemName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsVersion() {
		return isVersion;
	}

	public void setIsVersion(String isVersion) {
		this.isVersion = isVersion;
	}

	public String getAddProcurement() {
		return addProcurement;
	}

	public void setAddProcurement(String addProcurement) {
		this.addProcurement = addProcurement;
	}

	public String getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}
	
	
}