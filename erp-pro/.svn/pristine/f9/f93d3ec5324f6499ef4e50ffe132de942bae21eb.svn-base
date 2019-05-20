/**
 * Copyright &copy; 2015-2020 <a href="http://www.pt.org/">pt</a> All rights reserved.
 */
package com.pt.modules.mds.mdsquota.entity;

import java.util.List;
import com.google.common.collect.Lists;

import com.pt.core.persistence.DataEntity;
import com.pt.common.utils.excel.annotation.ExcelField;

/**
 * 材料定额管理Entity
 * @author cjx
 * @version 2018-09-11
 */
public class MdsQuota extends DataEntity<MdsQuota> {
	
	private static final long serialVersionUID = 1L;
	private String mdsItemId;		// 产品ID
	private String sysMarketId;		// 销售订单ID
	private String state;		// 订单状态(10:未编辑,20:编制中,30:已定版)
	private String sysMarketListId;		// 合同产品详情id
	private String column2;		// column2
	private String column3;		// column3
	private String column4;		// column4
	private String column5;		// column5
	private List<MdsQuotaDetail> mdsQuotaDetailList = Lists.newArrayList();		// 子表列表
	
	private String clientId;	//客户id
	private String clientName;	//客户名字
	private String mdsItemCode;		//产品编号
	private String mdsItemName;	//产品名字
	private String mdsItemspecs;	//规格
	private String mdsItemmodelNum;	//型号
	private String num;		//数量
	
	private String addProcurement;	//项目外购的产品是否添加到采购计划中(0:未添加,1:已添加)
	private String isAdd;	//批量外购的产品是否添加到采购计划中(0:未添加,1:已添加)
	
	public MdsQuota() {
		super();
	}

	public MdsQuota(String id){
		super(id);
	}

	@ExcelField(title="产品ID", align=2, sort=1)
	public String getMdsItemId() {
		return mdsItemId;
	}

	public void setMdsItemId(String mdsItemId) {
		this.mdsItemId = mdsItemId;
	}
	
	@ExcelField(title="销售订单ID", align=2, sort=2)
	public String getSysMarketId() {
		return sysMarketId;
	}

	public void setSysMarketId(String sysMarketId) {
		this.sysMarketId = sysMarketId;
	}
	
	@ExcelField(title="订单状态(10:未编辑,20:编制中,30:已定版)", align=2, sort=3)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@ExcelField(title="column1", align=2, sort=4)
	public String getSysMarketListId() {
		return sysMarketListId;
	}

	public void setSysMarketListId(String sysMarketListId) {
		this.sysMarketListId = sysMarketListId;
	}
	
	@ExcelField(title="column2", align=2, sort=5)
	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	
	@ExcelField(title="column3", align=2, sort=6)
	public String getColumn3() {
		return column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	
	@ExcelField(title="column4", align=2, sort=7)
	public String getColumn4() {
		return column4;
	}

	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	
	@ExcelField(title="column5", align=2, sort=8)
	public String getColumn5() {
		return column5;
	}

	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	
	public List<MdsQuotaDetail> getMdsQuotaDetailList() {
		return mdsQuotaDetailList;
	}

	public void setMdsQuotaDetailList(List<MdsQuotaDetail> mdsQuotaDetailList) {
		this.mdsQuotaDetailList = mdsQuotaDetailList;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public String getMdsItemspecs() {
		return mdsItemspecs;
	}

	public void setMdsItemspecs(String mdsItemspecs) {
		this.mdsItemspecs = mdsItemspecs;
	}

	public String getMdsItemmodelNum() {
		return mdsItemmodelNum;
	}

	public void setMdsItemmodelNum(String mdsItemmodelNum) {
		this.mdsItemmodelNum = mdsItemmodelNum;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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