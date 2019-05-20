/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsitem.entity;

import com.pt.core.persistence.DataEntity;
import com.pt.modules.sys.entity.Office;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 物料管理Entity
 * 
 * @author 郑利
 * @version 2018-05-12
 */
public class MdsItem extends DataEntity<MdsItem> {

	private static final long serialVersionUID = 1L;
	private String itemCode; // 物料代码
	private String itemName; // 物料名称
	private String itemVersion; // 物料版本
	private String itemType; // 物料类型G-产品，A-组合件，M-零件，R-原材料，P-虚件(Phantoms),S-伪件(pSedudomneom),C--类,T--工具（Tools）,E--流程（procEss）,N--工序（operatioN）B-备件，Z-站位
	private String sourceType; // 来源类型,0、自制，1外购
	private String outSourceType;//外购类型
	private String mdsUnitId; // 计量单位
	private String marque; // 牌号
	private String specs; // 规格
	private String techCondition; // 技术条件
	private String oemSupplyFlag; // 外协是否带料N,Y
	private String mpsMode; // 计划参数,用于标识不同的计划环境和计划类型,1-MTS 为库存生产;2-MTO
							// 为定单生产;3-ATO 为定单装配;4-ETO 为定单设计;5-BTO 为定单构造
	private String batchMode; // 批次管理方式,按单件还是批量生产，1-单件，2-批量
	private String keyFlag; // 关键件标记 ,N --非关键件； Y --关键件
	private String makeSpeed; // JIT生产节拍
	private Double alocLt; // 备料提前期
	private Double safLt; // 安全提前期
	private Double planCLt; // 计划不变提前期(天) , 使用的提前期中不随批量大小而变化的部分。定义时为对应平均批量的值
	private Double planVLt; // 计划可变提前期(天) , 使用的提前期中随着批量大小而变化的部分。定义时为对应平均批量值
	private Double theoryCLt; // 理论不变提前期(天) ,
								// 根据工艺路线给定的工时数据计算得出的提前期中不随批量大小而变化的部分。计算时为对应平均批量的值。
	private Double theoryVLt; // 理论可变提前期(天) ,
								// 根据工艺路线给定的工时数据计算得出的提前期中随着批量大小而变化的部分。计算时为对应平均批量的值。
	private Double stdCumLt; // 标准累计提前期 , 标准累计提前期,根据工艺路线使用零件车间子表模块计算得出
	private Double safetyStock; // 安全库存
	private String toolType; // 工装类型,1-通用,2-专用
	private String maxLevel; // 底层码
	private String item01; // 型号
	private String item02; // 标准字段
	private String item03; // 备用字段03
	private String item04; // 备用字段04
	private String item05; // 备用字段05
	private String sysOfficeId;
	private Office office;
	private String modelNum;	//材质
	private String normalField;
	
	private String remarks;	//备注
	
	
	public MdsItem() {
		super();
	}

	public MdsItem(String id) {
		super(id);
	}
    
	@ExcelField(title = "物料代码", align = 2, sort = 1)
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	@ExcelField(title = "物料名称", align = 2, sort = 2)
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/*@ExcelField(title = "物料版本", align = 2, sort = 3)*/
	public String getItemVersion() {
		return itemVersion;
	}

	public void setItemVersion(String itemVersion) {
		this.itemVersion = itemVersion;
	}

	//G-产品，A-组合件，M-零件，R-原材料，P-虚件(Phantoms),S-伪件(pSedudomneom),C--类,T--工具（Tools）,E--流程（procEss）,N--工序（operatioN）B-备件，Z-站位
	@ExcelField(title = "物料类型",dictType="item_type", align = 2, sort = 3)
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	// 0、自制，1、外购  ，2、协作，3、采购，4、自用，5、非制造件,说明项目来于购买或自制以及自制的程度
	@ExcelField(title = "来源类型",dictType="source_type", align = 2, sort = 4)
	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	@ExcelField(title = "外购类型",dictType="out_source_type", align = 2, sort = 5)
	public String getOutSourceType() {
		return outSourceType;
	}

	public void setOutSourceType(String outSourceType) {
		this.outSourceType = outSourceType;
	}
	
	@ExcelField(title = "备注", align = 2, sort = 6)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@ExcelField(title = "规格/型号", align = 2, sort = 7)
	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}
	
	@ExcelField(title = "材质", align = 2, sort = 8)
	public String getModelNum() {
		return modelNum;
	}

	public void setModelNum(String modelNum) {
		this.modelNum = modelNum;
	}
	
	@ExcelField(title = "技术标准", align = 2, sort = 9)
	public String getTechCondition() {
		return techCondition;
	}

	public void setTechCondition(String techCondition) {
		this.techCondition = techCondition;
	}
	
	@ExcelField(title = "计量单位", align = 2, sort = 10)
	public String getMdsUnitId() {
		return mdsUnitId;
	}

	public void setMdsUnitId(String mdsUnitId) {
		this.mdsUnitId = mdsUnitId;
	}
	
	@ExcelField(title = "标准", align = 2, sort = 11)
	public String getNormalField() {
		return normalField;
	}

	public void setNormalField(String normalField) {
		this.normalField = normalField;
	}
	
	@ExcelField(title = "牌号", align = 2, sort = 12)
	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}


	/*@ExcelField(title = "设计部门名称", align = 2, sort = 7)*/
	public String getSysOfficeId() {
		return sysOfficeId;
	}

	public void setSysOfficeId(String sysOfficeId) {
		this.sysOfficeId = sysOfficeId;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

//	@ExcelField(title = "关键件标记 ",dictType="key_flag", align = 2, sort = 13)
	public String getKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(String keyFlag) {
		this.keyFlag = keyFlag;
	}
	
	/*@ExcelField(title = "批次管理方式",dictType="batch_Mode_state", align = 2, sort = 14)*/
	public String getBatchMode() {
		return batchMode;
	}

	public void setBatchMode(String batchMode) {
		this.batchMode = batchMode;
	}

	// , 使用的提前期中不随批量大小而变化的部分。定义时为对应平均批量的值
	/*@ExcelField(title="计划不变提前期(天)", align=2, sort=15)*/
	public Double getPlanCLt() {
		return planCLt;
	}

	public void setPlanCLt(Double planCLt) {
		this.planCLt = planCLt;
	}
	
	// , 使用的提前期中随着批量大小而变化的部分。定义时为对应平均批量值
	/*@ExcelField(title="计划可变提前期(天)", align=2, sort=16)*/
	public Double getPlanVLt() {
		return planVLt;
	}

	public void setPlanVLt(Double planVLt) {
		this.planVLt = planVLt;
	}
	
	/*@ExcelField(title = "安全库存", align = 2, sort = 17)*/
	public Double getSafetyStock() {
		return safetyStock;
	}

	public void setSafetyStock(Double safetyStock) {
		this.safetyStock = safetyStock;
	}
	
	/*@ExcelField(title = "外协是否带料N,Y", align = 2, sort = 10)*/
	public String getOemSupplyFlag() {
		return oemSupplyFlag;
	}

	public void setOemSupplyFlag(String oemSupplyFlag) {
		this.oemSupplyFlag = oemSupplyFlag;
	}

	//,用于标识不同的计划环境和计划类型,1-MTS 为库存生产;2-MTO 为定单生产;3-ATO 为定单装配;4-ETO 为定单设计;5-BTO 为定单构造
	/*@ExcelField(title = "计划参数", align = 2, sort = 11)*/
	public String getMpsMode() {
		return mpsMode;
	}

	public void setMpsMode(String mpsMode) {
		this.mpsMode = mpsMode;
	}

	/*@ExcelField(title = "JIT生产节拍", align = 2, sort = 14)*/
	public String getMakeSpeed() {
		return makeSpeed;
	}

	public void setMakeSpeed(String makeSpeed) {
		this.makeSpeed = makeSpeed;
	}

	/*@ExcelField(title="备料提前期", align=2, sort=16)*/
	public Double getAlocLt() {
		return alocLt;
	}

	public void setAlocLt(Double alocLt) {
		this.alocLt = alocLt;
	}
	
	/*@ExcelField(title="安全提前期", align=2, sort=17)*/
	public Double getSafLt() {
		return safLt;
	}

	public void setSafLt(Double safLt) {
		this.safLt = safLt;
	}
	
	/*@ExcelField(title="理论不变提前期(天) , 根据工艺路线给定的工时数据计算得出的提前期中不随批量大小而变化的部分。计算时为对应平均批量的值。", align=2, sort=20)*/
	public Double getTheoryCLt() {
		return theoryCLt;
	}

	public void setTheoryCLt(Double theoryCLt) {
		this.theoryCLt = theoryCLt;
	}
	
	/*@ExcelField(title="理论可变提前期(天) , 根据工艺路线给定的工时数据计算得出的提前期中随着批量大小而变化的部分。计算时为对应平均批量的值。", align=2, sort=21)*/
	public Double getTheoryVLt() {
		return theoryVLt;
	}

	public void setTheoryVLt(Double theoryVLt) {
		this.theoryVLt = theoryVLt;
	}
	
	/*@ExcelField(title="标准累计提前期 , 标准累计提前期,根据工艺路线使用零件车间子表模块计算得出", align=2, sort=22)*/
	public Double getStdCumLt() {
		return stdCumLt;
	}

	public void setStdCumLt(Double stdCumLt) {
		this.stdCumLt = stdCumLt;
	}
	
	/*@ExcelField(title = "工装类型,1-通用,2-专用", align = 2, sort = 23)*/
	public String getToolType() {
		return toolType;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	/*@ExcelField(title = "底层码", align = 2, sort = 24)*/
	public String getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(String maxLevel) {
		this.maxLevel = maxLevel;
	}

	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}

	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}

	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}

	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}

	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}

}