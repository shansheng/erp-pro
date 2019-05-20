/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsroute.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.pt.core.persistence.DataEntity;
import com.pt.modules.sys.entity.Office;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 工艺管理Entity
 * @author 郑利
 * @version 2018-05-12
 */
public class MdsRoute extends DataEntity<MdsRoute> {
	
	private static final long serialVersionUID = 1L;
	private String mdsItemId;		// 零部件id
	private String proMdsItemId;		// 产品id
	private String routeNo;		// 工艺编号
	private String routeEd;		// 工艺版本
	private String routeDesc;		// 工艺名称
	private String routeType;		// L:工艺路线 M：工艺
	private String routeClass;		// 典型工艺类型^ H-补焊 R-热处理 Z-铸造 B-表面处理
	private String routeStatus;		// 工艺状态 ^ 1-初始化、2-编辑中、3-审批中、4-已定版、5-已作废
	private Office office;		// sys_office_id 组织机构id
	private String effectiveType;		// 生效类型：B批次
	private Date effectiveDate;		// 生效时间
	private Date ineffectiveDate;		// 失效时间
	private String effectiveValue;		// 生效值
	private String ineffectiveValue;		// 失效值
	private String item01;		// item_01
	private String item02;		// item_02
	private String item03;		// item_03
	private String item04;		// item_04
	private String item05;		// item_05
	
	
	//表外字段
	private String routeStatusName;
	private String mdsItemCode; //物料号
	private String mdsItemName; //物料名
	private String itemVersion;
	private String itemType; //物料类型
	private String mdsItemTypeName;
	private String sysOfficeName;
	private String proMdsItemCode;
	private String proMdsItemName;
	
	private String mdsItemIdSel;
	private String mdsItemCodeSel;
	private String mdsItemNameSel;
	public MdsRoute() {
		super();
	}

	public MdsRoute(String id){
		super(id);
	}

	@ExcelField(title="零部件id", dictType="mds_item_id", align=2, sort=1)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="产品id", dictType="pro_mds_item_id", align=2, sort=2)
	public String getProMdsItemId() {
		return proMdsItemId;
	}

	public void setProMdsItemId(String proMdsItemId) {
		this.proMdsItemId = proMdsItemId;
	}
	
	@ExcelField(title="工艺编号", align=2, sort=3)
	public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}
	
	@ExcelField(title="工艺版本", align=2, sort=4)
	public String getRouteEd() {
		return routeEd;
	}

	public void setRouteEd(String routeEd) {
		this.routeEd = routeEd;
	}
	
	@ExcelField(title="工艺名称", align=2, sort=5)
	public String getRouteDesc() {
		return routeDesc;
	}

	public void setRouteDesc(String routeDesc) {
		this.routeDesc = routeDesc;
	}
	
	@ExcelField(title="L:工艺路线 M：工艺", align=2, sort=6)
	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	
	@ExcelField(title="典型工艺类型^ H-补焊 R-热处理 Z-铸造 B-表面处理", align=2, sort=7)
	public String getRouteClass() {
		return routeClass;
	}

	public void setRouteClass(String routeClass) {
		this.routeClass = routeClass;
	}
	
	@ExcelField(title="工艺状态 ^ 1-初始化、2-编辑中、3-审批中、4-已定版、5-已作废", align=2, sort=8)
	public String getRouteStatus() {
		return routeStatus;
	}

	public void setRouteStatus(String routeStatus) {
		this.routeStatus = routeStatus;
	}
	
	@ExcelField(title="sys_office_id 组织机构id", fieldType=String.class, value="office.name", align=2, sort=9)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@ExcelField(title="生效类型：B批次", align=2, sort=10)
	public String getEffectiveType() {
		return effectiveType;
	}

	

	public void setEffectiveType(String effectiveType) {
		this.effectiveType = effectiveType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="生效时间", align=2, sort=11)
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="失效时间", align=2, sort=12)
	public Date getIneffectiveDate() {
		return ineffectiveDate;
	}

	public void setIneffectiveDate(Date ineffectiveDate) {
		this.ineffectiveDate = ineffectiveDate;
	}
	
	@ExcelField(title="生效值", align=2, sort=13)
	public String getEffectiveValue() {
		return effectiveValue;
	}

	public void setEffectiveValue(String effectiveValue) {
		this.effectiveValue = effectiveValue;
	}
	
	@ExcelField(title="失效值", align=2, sort=14)
	public String getIneffectiveValue() {
		return ineffectiveValue;
	}

	public void setIneffectiveValue(String ineffectiveValue) {
		this.ineffectiveValue = ineffectiveValue;
	}
	
	@ExcelField(title="item_01", align=2, sort=15)
	public String getItem01() {
		return item01;
	}

	public void setItem01(String item01) {
		this.item01 = item01;
	}
	
	@ExcelField(title="item_02", align=2, sort=16)
	public String getItem02() {
		return item02;
	}

	public void setItem02(String item02) {
		this.item02 = item02;
	}
	
	@ExcelField(title="item_03", align=2, sort=17)
	public String getItem03() {
		return item03;
	}

	public void setItem03(String item03) {
		this.item03 = item03;
	}
	
	@ExcelField(title="item_04", align=2, sort=18)
	public String getItem04() {
		return item04;
	}

	public void setItem04(String item04) {
		this.item04 = item04;
	}
	
	@ExcelField(title="item_05", align=2, sort=19)
	public String getItem05() {
		return item05;
	}

	public void setItem05(String item05) {
		this.item05 = item05;
	}

	public String getRouteStatusName() {
		return routeStatusName;
	}

	public void setRouteStatusName(String routeStatusName) {
		this.routeStatusName = routeStatusName;
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

	public String getItemVersion() {
		return itemVersion;
	}

	public void setItemVersion(String itemVersion) {
		this.itemVersion = itemVersion;
	}



	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getMdsItemTypeName() {
		return mdsItemTypeName;
	}

	public void setMdsItemTypeName(String mdsItemTypeName) {
		this.mdsItemTypeName = mdsItemTypeName;
	}

	public String getSysOfficeName() {
		return sysOfficeName;
	}

	public void setSysOfficeName(String sysOfficeName) {
		this.sysOfficeName = sysOfficeName;
	}

	public String getProMdsItemCode() {
		return proMdsItemCode;
	}

	public void setProMdsItemCode(String proMdsItemCode) {
		this.proMdsItemCode = proMdsItemCode;
	}

	public String getProMdsItemName() {
		return proMdsItemName;
	}

	public void setProMdsItemName(String proMdsItemName) {
		this.proMdsItemName = proMdsItemName;
	}

	public String getMdsItemIdSel() {
		return mdsItemIdSel;
	}

	public void setMdsItemIdSel(String mdsItemIdSel) {
		this.mdsItemIdSel = mdsItemIdSel;
	}

	public String getMdsItemCodeSel() {
		return mdsItemCodeSel;
	}

	public void setMdsItemCodeSel(String mdsItemCodeSel) {
		this.mdsItemCodeSel = mdsItemCodeSel;
	}

	public String getMdsItemNameSel() {
		return mdsItemNameSel;
	}

	public void setMdsItemNameSel(String mdsItemNameSel) {
		this.mdsItemNameSel = mdsItemNameSel;
	}
	
	
	
	
}