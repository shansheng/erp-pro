/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsequipment.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import com.pt.core.persistence.DataEntity;
import com.pt.modules.mds.mdsequipmentuser.entity.MdsEquipmentUser;
import com.pt.modules.sys.entity.Office;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 设备管理Entity
 * @author 郑利
 * @version 2018-05-26
 */
public class MdsEquipment extends DataEntity<MdsEquipment> {
	
	private static final long serialVersionUID = 1L;
	private String equipmentCode;		// 设备代码
	private String equipmentName;		// 设备名称
	private String equipmentModel;		// 设备型号/IT型号
	private String equipmentSpec;		// 设备规格
	private String tpmEquipmentClassId;		// 设备大类id
	private String equipmentWeigth;		// 设备重量
	private String mdsUnitId;		// 单位
	private String controlSystem;		// 控制系统
	private String assetType;		// 资产类型 ^ 0 固定资产,1 流动资产
	private String assetNo;		// IT设备编号（保密编号）
	private String fixedAssetNo;		// 固定资产编号
	private Office useDeptId;		// 使用部门 ^ 引用HR.HR_Dept.ID(车间级)
	private String mdsWorkcenterId;		// 所属制造单元 ^ 引用MDS_WorkCenter.ID
	private Office maintainDeptId;		// 使用单位 ^ 引用HR.HR_Dept.ID(厂级)
	private String supplierSrmVendorId;		// 供应商
	private String factoryNo;		// 出厂编号
	private Date factoryDate;		// 出厂日期
	private Date dateOfReceiving;		// 到货日期
	private Date dateOfInstall;		// 安装日期
	private String abcCode;		// ABC代码
	private String equipmentStatus;		// 设备状态 ^ 1--使用;2--封存;3--闲置;4--调试,5--报废;6--维修;7--待修;8--禁用;9--入库;
	private String item01;		// 备用字段01
	private String item02;		// 备用字段02
	private String item03;		// 备用字段03
	private String item04;		// 备用字段04
	private String item05;		// 备用字段05
	
	private List<MdsEquipmentUser> mdsEquipmentUserList = Lists.newArrayList();		// 子表列表
	


	public MdsEquipment() {
		super();
	}

	public MdsEquipment(String id){
		super(id);
	}

	@ExcelField(title="设备代码", align=2, sort=1)
	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	
	@ExcelField(title="设备名称", align=2, sort=2)
	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	@ExcelField(title="设备型号/IT型号", align=2, sort=3)
	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}
	
	@ExcelField(title="设备规格", align=2, sort=4)
	public String getEquipmentSpec() {
		return equipmentSpec;
	}

	public void setEquipmentSpec(String equipmentSpec) {
		this.equipmentSpec = equipmentSpec;
	}
	
	@ExcelField(title="设备大类id", align=2, sort=5)
	public String getTpmEquipmentClassId() {
		return tpmEquipmentClassId;
	}

	public void setTpmEquipmentClassId(String tpmEquipmentClassId) {
		this.tpmEquipmentClassId = tpmEquipmentClassId;
	}
	
	@ExcelField(title="设备重量", align=2, sort=6)
	public String getEquipmentWeigth() {
		return equipmentWeigth;
	}

	public void setEquipmentWeigth(String equipmentWeigth) {
		this.equipmentWeigth = equipmentWeigth;
	}
	
	@ExcelField(title="单位", align=2, sort=7)
	public String getMdsUnitId() {
		return mdsUnitId;
	}

	public void setMdsUnitId(String mdsUnitId) {
		this.mdsUnitId = mdsUnitId;
	}
	
	@ExcelField(title="控制系统", align=2, sort=8)
	public String getControlSystem() {
		return controlSystem;
	}

	public void setControlSystem(String controlSystem) {
		this.controlSystem = controlSystem;
	}
	
	@ExcelField(title="资产类型 ^ 0 固定资产,1 流动资产", dictType="asset_type", align=2, sort=9)
	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	
	@ExcelField(title="IT设备编号（保密编号）", align=2, sort=10)
	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}
	
	@ExcelField(title="固定资产编号", align=2, sort=11)
	public String getFixedAssetNo() {
		return fixedAssetNo;
	}

	public void setFixedAssetNo(String fixedAssetNo) {
		this.fixedAssetNo = fixedAssetNo;
	}
	
	@ExcelField(title="使用部门 ^ 引用HR.HR_Dept.ID(车间级)", fieldType=String.class, value="", align=2, sort=12)
	public Office getUseDeptId() {
		return useDeptId;
	}

	public void setUseDeptId(Office useDeptId) {
		this.useDeptId = useDeptId;
	}

	
	@ExcelField(title="所属制造单元 ^ 引用MDS_WorkCenter.ID", align=2, sort=13)
	public String getMdsWorkcenterId() {
		return mdsWorkcenterId;
	}


	public void setMdsWorkcenterId(String mdsWorkcenterId) {
		this.mdsWorkcenterId = mdsWorkcenterId;
	}
	
	@ExcelField(title="使用单位 ^ 引用HR.HR_Dept.ID(厂级)", align=2, sort=14)
	public Office getMaintainDeptId() {
		return maintainDeptId;
	}

	public void setMaintainDeptId(Office maintainDeptId) {
		this.maintainDeptId = maintainDeptId;
	}
	
	@ExcelField(title="供应商", align=2, sort=15)
	public String getSupplierSrmVendorId() {
		return supplierSrmVendorId;
	}



	public void setSupplierSrmVendorId(String supplierSrmVendorId) {
		this.supplierSrmVendorId = supplierSrmVendorId;
	}
	
	@ExcelField(title="出厂编号", align=2, sort=16)
	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="出厂日期", align=2, sort=17)
	public Date getFactoryDate() {
		return factoryDate;
	}

	public void setFactoryDate(Date factoryDate) {
		this.factoryDate = factoryDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="到货日期", align=2, sort=18)
	public Date getDateOfReceiving() {
		return dateOfReceiving;
	}

	public void setDateOfReceiving(Date dateOfReceiving) {
		this.dateOfReceiving = dateOfReceiving;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelField(title="安装日期", align=2, sort=19)
	public Date getDateOfInstall() {
		return dateOfInstall;
	}

	public void setDateOfInstall(Date dateOfInstall) {
		this.dateOfInstall = dateOfInstall;
	}
	
	@ExcelField(title="ABC代码", align=2, sort=20)
	public String getAbcCode() {
		return abcCode;
	}

	public void setAbcCode(String abcCode) {
		this.abcCode = abcCode;
	}
	
	@ExcelField(title="设备状态 ^ 1--使用;2--封存;3--闲置;4--调试,5--报废;6--维修;7--待修;8--禁用;9--入库;", dictType="equipment_status", align=2, sort=21)
	public String getEquipmentStatus() {
		return equipmentStatus;
	}

	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	
	@ExcelField(title="备用字段01", align=2, sort=22)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="备用字段02", align=2, sort=23)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="备用字段03", align=2, sort=24)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="备用字段04", align=2, sort=25)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="备用字段05", align=2, sort=26)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}

	public List<MdsEquipmentUser> getMdsEquipmentUserList() {
		return mdsEquipmentUserList;
	}

	public void setMdsEquipmentUserList(List<MdsEquipmentUser> mdsEquipmentUserList) {
		this.mdsEquipmentUserList = mdsEquipmentUserList;
	}
	
}